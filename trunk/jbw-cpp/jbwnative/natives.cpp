#include <jni.h>
#include <stdio.h>

extern "C"
JNIEXPORT void JNICALL 
Java_neoe_jbw_Main_cprint1(JNIEnv *env, jobject obj)
{
	FILE *f = fopen("t:\\cprint1.txt","at");
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