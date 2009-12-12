#include <stdio.h>
#include <windows.h>
#include <string>
#include <assert.h>
#include <shellapi.h>


#define BWLAPI 4
#define STARCRAFTBUILD 13
#define ENV_BUFFER_SIZE 512

struct ExchangeData
{
  int iPluginAPI;
  int iStarCraftBuild;
  BOOL bNotSCBWmodule;                //Inform user that closing BWL will shut down your plugin
  BOOL bConfigDialog;                 //Is Configurable
};

BOOL APIENTRY DllMain(HMODULE, DWORD, LPVOID)
{
  return TRUE;
}

// GET Functions for BWLauncher
//
extern "C" __declspec(dllexport) void GetPluginAPI(ExchangeData& Data)
{
  //BWL Gets version from Resource - VersionInfo
  Data.iPluginAPI = BWLAPI;
  Data.iStarCraftBuild = STARCRAFTBUILD;
  Data.bConfigDialog = FALSE;
  Data.bNotSCBWmodule = FALSE;
}

extern "C" __declspec(dllexport) void GetData(char* name, char* description, char* updateurl)
{
  char newDescription[512];
  sprintf_s(newDescription, 512, "Injects jbw.dll into the Broodwar process. \r\nCreated by the neoe");
  
  strcpy(name, "jbw (1.16.1)");
  strcpy(description, newDescription);
  strcpy(updateurl, "http://neoe.appspot.com");
}

// Functions called by BWLauncher
//
extern "C" __declspec(dllexport) bool OpenConfig()
{
  return false;
}

extern "C" __declspec(dllexport) bool ApplyPatchSuspended(HANDLE, DWORD)
{
  return true;
}

extern "C" __declspec(dllexport) bool ApplyPatch(HANDLE hProcess, DWORD)
{
  char envBuffer[ENV_BUFFER_SIZE];
  DWORD result = GetEnvironmentVariableA("ChaosDir", envBuffer, ENV_BUFFER_SIZE);
  if (result == 0)
    result = GetCurrentDirectoryA(ENV_BUFFER_SIZE, envBuffer);
  
  if (result == 0)
  {
    FILE* f = fopen("jbw-error.txt", "a+");
    fprintf(f, "Could not find ChaosDir or current directory.\n");
    MessageBoxA(NULL, "Could not find ChaosDir or current directory.\n", "Error", MB_OK);
    fclose(f);
  }

  std::string dllFileName(envBuffer);
  dllFileName.append("\\jbw.dll");

  LPTHREAD_START_ROUTINE loadLibAddress = (LPTHREAD_START_ROUTINE)GetProcAddress(GetModuleHandle("Kernel32"), "LoadLibraryA" );
  if (loadLibAddress == NULL)
  {
    FILE* f = fopen("jbw-error.txt", "a+");
    fprintf(f, "Could not get loadLibAddress.\n");
    MessageBoxA(NULL, "Could not get loadLibAddress.\n", "Error", MB_OK);
    fclose(f);
  }

  void* pathAddress = VirtualAllocEx(hProcess, NULL, dllFileName.size() + 1, MEM_COMMIT, PAGE_READWRITE);
  if (pathAddress == NULL)
  {
    FILE* f = fopen("jbw-error.txt", "a+");
    fprintf(f, "Could not get pathAddress.\n");
    MessageBoxA(NULL, "Could not get pathAddress.\n", "Error", MB_OK);
    fclose(f);
  }

  SIZE_T bytesWritten;
  BOOL success = WriteProcessMemory(hProcess, pathAddress, dllFileName.c_str(), dllFileName.size() + 1, &bytesWritten);
  if (success == FALSE || bytesWritten != dllFileName.size() + 1)
  {
    FILE* f = fopen("jbw-error.txt", "a+");
    fprintf(f, "Could not Write proc memory.\n");
    MessageBoxA(NULL, "Could not Write proc memory.\n", "Error", MB_OK);
    fclose(f);
  }

  HANDLE hThread = CreateRemoteThread(hProcess, NULL, 0, loadLibAddress, pathAddress, 0, NULL);
  if (hThread == NULL)
  {
    FILE* f = fopen("jbw-error.txt", "a+");
    fprintf(f, "Could not Create remote thread.\n");
    MessageBoxA(NULL, "Could not Create remote thread.\n", "Error", MB_OK);
    fclose(f);
  }

  WaitForSingleObject(hThread, INFINITE);

  DWORD hLibModule = NULL; // Base address of the loaded module
  GetExitCodeThread(hThread, &hLibModule);
  if (hLibModule == NULL)
  {
    FILE* f = fopen("jbw-error.txt", "a+");
    fprintf(f, "Could not get hLibModule.\n");
    MessageBoxA(NULL, "Could not get hLibModule.\n", "Error", MB_OK);
    fclose(f);
  }

  VirtualFreeEx(hProcess, pathAddress, dllFileName.size() + 1, MEM_RELEASE);
  CloseHandle(hThread);
  return true; //everything OK
}
