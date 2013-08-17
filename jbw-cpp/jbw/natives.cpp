#include <jni.h>
#include <stdio.h>

extern "C"
JNIEXPORT void JNICALL 
Java_neoe_jbw_Main_cprint1(JNIEnv *env, jobject obj)
{
	FILE *f = fopen("\\cprint1.txt","at");
	fprintf(f, "hello jbw \n");
	fclose(f);
	return;
}

extern "C"
JNIEXPORT jstring JNICALL 
Java_neoe_jbw_BW_getStr(JNIEnv *env, jobject obj, jint offset)
{
	return env->NewStringUTF((char*)offset);
}

//native byte[] getBytes(int offset, int size);
extern "C"
JNIEXPORT jbyteArray JNICALL 
Java_neoe_jbw_BW_getBytes(JNIEnv *env, jobject obj, jint offset, jint size)
{
	jbyteArray ba = env->NewByteArray(size);
	if (ba == NULL) {
		return NULL; /* out of memory error thrown */
	}
	env->SetByteArrayRegion(ba,0,size,(jbyte*)offset);
	return ba;
}
//native ByteBuffer getBB();
extern "C"
JNIEXPORT jobject JNICALL 
Java_neoe_jbw_BW_getBB(JNIEnv *env, jobject obj)
{
	return env->NewDirectByteBuffer((void*)0x400000, 0x6300000);
}
//native void print(int id, String s);
extern "C"
JNIEXPORT void JNICALL 
Java_neoe_jbw_BW_print(JNIEnv *env, jobject obj, jint id, jstring s)
{
	jint pID=id;
	const char *  txtout = env->GetStringUTFChars(s, false);
	unsigned int BWFXN_PrintText=0x0048D1C0;
	__asm
	{
		pushad
			push 0       // Unknown
			mov eax, pID   // Player ID (-1 for notification area)
			push txtout  // Text
			call dword ptr [BWFXN_PrintText]
		popad
	}
	env->ReleaseStringUTFChars(s, txtout);
}
static unsigned int BWFXN_NewIssueCommand = 0x00485BD9;
void __declspec(naked) IssueNewCommand()
{
	//execute the part of the function that we overwrote:
	__asm
	{
		push ebp
			mov ebp, esp
			push ecx
			mov eax, dword ptr ds: [0x654AA0]
		jmp [BWFXN_NewIssueCommand]
	}
}
void issueCommandFromMemory(void *pbBuffer, int iSize)
{
	__asm
	{
		mov ecx, pbBuffer
			mov edx, iSize
	}
	IssueNewCommand();
}
//native void command(byte[] combytes, int byteslen);
extern "C"
JNIEXPORT void JNICALL 
Java_neoe_jbw_BW_command(JNIEnv *env, jobject obj, jbyteArray ba, jint len)
{
	jbyte * buf = env->GetByteArrayElements(ba, false); 
	issueCommandFromMemory((void *)buf, len);
	env->ReleaseByteArrayElements(ba, buf, JNI_ABORT);
}