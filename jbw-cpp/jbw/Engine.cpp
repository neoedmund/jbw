#define WIN32_LEAN_AND_MEAN   // Exclude rarely-used stuff from Windows headers

#include "Engine.h"
#include <jni.h>
#include "jbwldr.h"
#include "FileLogger.h"

namespace BWAPI
{
	namespace Engine
	{
		bool jvminit=false;
		void onMatchFrame(){
			if (!jvminit){
				jvminit=true;
				Util::Logger::globalLog->log("init jvm");
				JBW::createJVM();
			}
			JBW::onMatchFrame();
		}
		void onMenuFrame(){}
		void onMatchEnd(){
			JBW::onMatchEnd();
		}
		bool onSendText(const char* text){return false;}
		void onUnitDeath(BW::Unit* unit){JBW::onUnitDeath(unit);}
		void onDllLoad(){}
		void onMessageIntercepted(const char* text){
			JBW::onText(text);
		}
		void onMatchDrawHigh(){}
	}
};
