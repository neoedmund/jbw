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
Java_neoe_jbw_Offset_getStr(JNIEnv *env, jobject obj, jint offset)
{
	char* p;
	p = (char*) offset;
	return env->NewStringUTF(p);
}