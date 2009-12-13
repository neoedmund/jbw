// jbwldr.h
#pragma once
#include <jni.h>
namespace BW { struct Unit; }
class JBW{
private:
	static jmethodID jonMatchEnd;
	static jmethodID jonMatchFrame;
	static jmethodID jonText;
	static jmethodID jonUnitDeath;
public:
	static JNIEnv *x_env;
	static JavaVM *x_jvm;
	//neoe.jbw.Main
	static jclass x_cls;
	static void createJVM();
	static void destoryJVM();
	static void onMatchEnd();
	static void onMatchFrame();
	static void onUnitDeath(BW::Unit* unit);
	static void onText(const char* text);
};

