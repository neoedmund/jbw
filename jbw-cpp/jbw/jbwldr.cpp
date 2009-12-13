// This is the main DLL file.
#include "jbwldr.h"
#include "FileLogger.h"
#include <jni.h>
#include <windows.h>
#include "hook.h"
#include <string>

JNIEnv * JBW::x_env;
JavaVM * JBW::x_jvm;
jclass JBW::x_cls;
jmethodID JBW::jonMatchEnd;
jmethodID JBW::jonMatchFrame;

static void loadJVMDll();

BOOL APIENTRY DllMain(HMODULE, DWORD ul_reason_for_call, LPVOID)
{
	switch (ul_reason_for_call)
	{
	case DLL_PROCESS_ATTACH:
		{
			delete Util::Logger::globalLog;			
			Util::Logger::globalLog = new Util::FileLogger("t:\\jbwldr", Util::LogLevel::MicroDetailed);
			Util::Logger::globalLog->log("jbw init started");
			loadJVMDll();
			BW::installHooks();
			Util::Logger::globalLog->log("hooks installed");
		}
		break;
	case DLL_PROCESS_DETACH:		
		JBW::destoryJVM();
		break;

	}
	return true;
}
std::string wstr_to_str(std::wstring str)
{
	size_t returned;
	char* ascii = new char[str.length() + 1];
	wcstombs_s(&returned, ascii, str.length() + 1, str.c_str(), _TRUNCATE);
	return ascii;
}

std::wstring str_to_wstr(std::string str)
{
	size_t returned;
	wchar_t* ret = new wchar_t[str.length() + 1];
	mbstowcs_s(&returned, ret, str.length() + 1, str.c_str(), _TRUNCATE);
	return ret;
}

bool file_exists(const char * filename)
{
	if (FILE * file = fopen(filename, "r"))
	{
		fclose(file);
		return true;
	}
	return false;
}
static HINSTANCE _libInst=NULL;
typedef jint (JNICALL CreateJavaVM_t)(JavaVM **pvm, void **penv, void *args);

void loadJVMDll(){
	if (_libInst!=NULL)return;
	std::string jvmdll = getenv("JAVA_HOME");
	Util::Logger::globalLog->log("java home=%s", jvmdll.c_str());
	if (file_exists((jvmdll + "\\bin\\client\\jvm.dll").c_str())) {
		jvmdll += "\\bin\\client\\jvm.dll";
	} else if (file_exists((jvmdll + "\\jre\\bin\\client\\jvm.dll").c_str())) {
		//prolly a JDK
		jvmdll += "\\jre\\bin\\client\\jvm.dll";
	} else {
		Util::Logger::globalLog->log("jvm.dll not found");
		return;
	}
	Util::Logger::globalLog->log("jvm.dll is %s", jvmdll.c_str());
	//load it
	if ( (_libInst = LoadLibrary(str_to_wstr(jvmdll).c_str())) == NULL) {
		Util::Logger::globalLog->log("Can't load JVM DLL");
		return;
	}

	Util::Logger::globalLog->log("dll loaded");
}


void JBW::createJVM(){
	/*Nevermind. It turns out that you can call JNI_CreateJavaVM from the DllMain
	of a DLL. No clue why, but if I move the call out of DllMain, it all works.*/

	CreateJavaVM_t* createFn = (CreateJavaVM_t *)GetProcAddress(_libInst, "JNI_CreateJavaVM");
	if (createFn == NULL) {
		Util::Logger::globalLog->log("Can't locate JNI_CreateJavaVM");
		return;
	}else{
		Util::Logger::globalLog->log("JNI_CreateJavaVM @ %x", createFn);		
	}
#define ENV_BUFFER_SIZE 1024
	char envBuffer[ENV_BUFFER_SIZE];
	DWORD result = GetEnvironmentVariableA("ChaosDir", envBuffer, ENV_BUFFER_SIZE);
	if (result == 0)
		result = GetCurrentDirectoryA(ENV_BUFFER_SIZE, envBuffer);

	if (result == 0)
	{
		MessageBoxA(NULL, "Could not find ChaosDir or current directory.\n", "Error", MB_OK);   
	}

	std::string ChaosDir(envBuffer);
	Util::Logger::globalLog->log("ChaosDir=%s", envBuffer);
	std::string  s1="-Djava.class.path="+ChaosDir+"\\jbw.jar";
	std::string  s2="-Djava.library.path="+ChaosDir;
	JavaVMOption options[3];
	// java
	options[0].optionString =(char *)s1.c_str();
	//where jni native impl dll is in (jbwnative.dll)
	options[1].optionString =(char *)s2.c_str();
	options[2].optionString ="-Xmx256M";
	JavaVMInitArgs vm_args;
	vm_args.version = JNI_VERSION_1_6;
	vm_args.options = options;
	vm_args.nOptions = 3;
	vm_args.ignoreUnrecognized = false;

	/* Create the Java VM */
	jint res = createFn(&x_jvm, (void**)&x_env, &vm_args);

	if (res < 0) {
		Util::Logger::globalLog->log("Can't create Java VM\n");
		return;
	}

	//entry class
	x_cls =  x_env ->FindClass("neoe/jbw/Main");
	if (x_cls == 0) {
		goto destroy;
	}

	jmethodID mid = x_env->GetStaticMethodID(x_cls, "initJVM","()V");
	if (mid == 0) {
		goto destroy;
	}

	x_env->CallStaticVoidMethod(x_cls, mid, NULL);
	Util::Logger::globalLog->log("JVM inited %x",x_env);
	JBW::jonMatchEnd=x_env->GetStaticMethodID(x_cls, "onMatchEnd","()V");
	JBW::jonMatchFrame=x_env->GetStaticMethodID(x_cls, "onMatchFrame","()V");
	Util::Logger::globalLog->log("java event method got");
	return;
destroy:
	Util::Logger::globalLog->log("jvm error");
	if (x_env->ExceptionOccurred()) {
		x_env->ExceptionDescribe();
	}
	x_jvm->DestroyJavaVM();
	return;
}

void JBW::destoryJVM(){
	if(x_jvm){
		Util::Logger::globalLog->log("destory jvm %x",x_jvm);
		x_jvm->DestroyJavaVM();
		Util::Logger::globalLog->log("destory jvm ok");
	}
}
void JBW::onMatchEnd(){
	JBW::x_env->CallStaticVoidMethod(JBW::x_cls, JBW::jonMatchEnd, NULL);
}
void JBW::onMatchFrame(){
	JBW::x_env->CallStaticVoidMethod(JBW::x_cls, JBW::jonMatchFrame, NULL);
}