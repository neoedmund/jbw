#pragma once
#pragma pack(1)

#include "Types.h"
namespace BW
{
  static const u8  PLAYER_COUNT          =  12;
  static const u8  PLAYABLE_PLAYER_COUNT =   8;
  static const u8  RACE_COUNT            =   3;
  static const u8  UNIT_TYPE_COUNT       = 228;
  static const u8  TECH_TYPE_COUNT       =  44;
  static const u8  UPGRADE_TYPE_COUNT    =  66;
  static const u8  WEAPON_TYPE_COUNT     = 130;
  static const u8  DAMAGE_TYPE_COUNT     =   5;
  static const u8  EXPLOSION_TYPE_COUNT  =  25;
  static const u8  FLINGY_TYPE_COUNT     = 209;

  static const int TILE_SIZE          =  32;


  struct Positions
  {
    u16   x;
    u16   y;
  };

  struct ForceName
  {
    char  name[30];
  };

  struct DatLoad
  {
    u32   address;
    u32   length;
    u32   entries;
  };

  struct PlayerAlliance
  {
    struct Alliances
    {
      u8 player[PLAYER_COUNT];
    };
    Alliances alliance[PLAYER_COUNT];
  };


  static DatLoad*       upgradesDat       = (DatLoad*)        0x005136E0;  // 1.15.3, 1.16, 1.16.1
  static DatLoad*       techdataDat       = (DatLoad*)        0x005137D8;
  static DatLoad*       weaponsDat        = (DatLoad*)        0x00513868;
  static DatLoad*       unitsDat          = (DatLoad*)        0x00513C30;
  static DatLoad*       flingyDat         = (DatLoad*)        0x00515A38;

  

  /* Speed Hacks */
  static u32     BWDATA_MenuLoadHack       =           0x004DE392;
  static u32     BWDATA_MenuInHack         =           0x004DD76E;
  static u32     BWDATA_MenuOutHack        =           0x004DD162;
  static u32     BWDATA_MultiplayerHack    =           0x004DD5A2;
  static u32     BWDATA_MultiplayerHack2   =           0x004DD5C9;
  static u32*    BWDATA_GameSpeedModifiers = (u32*)    0x005124F4;
  static u32     BWDATA_OpponentStartHack  =           0x004B995D;

  /* Native message boxes */
  static u32     BWFXN_gluPOK_MBox         = 0x004B7180;
  static u32     BWFXN_gluPOKCancel_MBox   = 0x004B73B0;
  static u32     BWFXN_gluPEdit_MBox       = 0x004B6E50;

  /** Higher 12 bits for tile group, lower 4 bits for variant of tile in the tile group. */
  typedef u16 TileID;
  const  u32            UNIT_ARRAY_MAX_LENGTH                   = 1700;

  static u8*            BWDATA_Latency                    = (u8*)          0x006556e4;
  static void (_stdcall* selectUnits)(int count, BW::Unit**  unitsToSelect)  = (void (_stdcall*) (int, BW::Unit * *))             0x004C0860;
  static void (_stdcall* selectUnitsHelperSTD)(int, BW::Unit** , bool, bool) = (void (_stdcall*) (int, BW::Unit * *, bool, bool)) 0x0049AFF0;
  static u32            BWFXN_OldIssueCommand                     =         0x00485BD0;
  static u32*           BWDATA_InGame                             = (u32*)  0x006556E0;
  static u32*           BWDATA_InReplay                           = (u32*)  0x006D0F14;
  static u8*            BWDATA_IsMultiplayer                      = (u8*)   0x0065fbf0;
  static u8*            BWDATA_IsNotPaused                        = (u8*)   0x0051CE6C;
  static u32*           BWDATA_NextMenu                           = (u32*)  0x006D11BC;
  static void (_stdcall* changeMenu)()              = (void (_stdcall*)())  0x004DCFA0;

  static u8*            BWDATA_GameState                          = (u8*)  0x006D11EC;
  static u16*           BWDATA_GamePosition                       = (u16*) 0x0051CE90;

  static u32            BWFXN_PrintText                           = 0x0048D1C0;

  static u16*           BWDATA_SendTextRequired                   = (u16*)  0x0057F1DA;
  static char*          BWDATA_CurrentPlayerName                  = (char*) 0x0057EE9C;            // 1.16.1

  static u32            BWFXN_SendTextCall                        = 0x004F335D;
  static u32            BWFXN_SendTextCallBack                    = BWFXN_SendTextCall + 5;
  static u32            BWFXN_SendTextCallTarget                  = 0x0041008E;

  static u32            BWFXN_SendPublicCall                      = 0x004F32B7;
  static u32            BWFXN_SendPublicCallBack                  = BWFXN_SendPublicCall + 5;
  static u32            BWFXN_SendPublicCallTarget                = 0x004C2420;

  static u32            BWFXN_SendLobbyCall                       = 0x004B98DE;
  static u32            BWFXN_SendLobbyCallBack                   = BWFXN_SendLobbyCall + 5;
  static u32            BWFXN_SendLobbyCallTarget                 = 0x004707D0;

  static Unit*          BWDATA_CurrentPlayerSelectionGroup        = (Unit*) 0x00597208;

  static u32            BWFXN_GameEnd                             = 0x004EE983;
  static u32            BWFXN_GameEndBack                         = BWFXN_GameEnd + 5;
  static u32            BWFXN_GameEndTarget                       = 0x00416D90;

  static u32            BWFXN_NextFrameHelperFunction             = 0x004D98BD;
  static u32            BWFXN_NextFrameHelperFunctionBack         = BWFXN_NextFrameHelperFunction + 5;
  static u32            BWFXN_NextFrameHelperFunctionTarget       = 0x004D14D0;

  static u32            BWFXN_DrawBox                             = 0x004E1D20;
  static u32            BWFXN_PrintXY                             = 0x004200D0;
  static u32*           BWDATA_PrintXY_PositionX                  = (u32*) 0x006CE108;
  static u32*           BWDATA_PrintXY_PositionY                  = (u32*) 0x006CE0E0;
  static u32*           BWDATA_PrintXY_Current_Font               = (u32*) 0x006D5DDC;
  static u32*           BWDATA_PrintXY_PositionX2                 = (u32*) 0x006CE0CC;

  struct fontMemStruct
  {
    u32 tFontData;
    u32 tFontUnknown;
    u16 x1;
    u16 y1;
    u16 x2;
    u16 y2;
  };

  static fontMemStruct* BWDATA_PrintXY_Font                       = (fontMemStruct*) 0x006CE0C0;
  static u8*            BWDATA_PrintXY_Unknown1                   = (u8*)  0x006CE110;
  static u16*           BWDATA_PrintXY_Unknown2                   = (u16*) 0x006CE0C8;
  static u16*           BWDATA_PrintXY_Unknown3                   = (u16*) 0x006CE0CE;

  static u32            BWDATA_FontData                           = 0x006CE028;

  static u32            BWDATA_FontBase                           = 0x006CE0F4;
  static u32*           BWDATA_Font8_Handle                       = (u32*) BWDATA_FontBase;
  static u32*           BWDATA_Font10_Handle                      = (u32*) BWDATA_FontBase + 1;
  static u32*           BWDATA_Font16_Handle                      = (u32*) BWDATA_FontBase + 2;
  static u32*           BWDATA_Font16x_Handle                     = (u32*) BWDATA_FontBase + 3;

  static u8*            BWDATA_DrawColor                          = (u8*) 0x006CF4AC;

  static u32            BWFXN_Refresh                             = 0x0041E26B;
  static u32            BWFXN_RefreshTarget                       = 0x0041E0D0;
  static u32            BWFXN_RefreshBack                         = BWFXN_Refresh + 5;

  static u32            BWFXN_DrawHigh                            = 0x004BD614;
  static u32            BWFXN_DrawHighBack                        = BWFXN_DrawHigh + 5;
  static u32            BWFXN_DrawHighTarget                      = 0x0048CF60;

  static u32            BWFXN_NewIssueCommand                     = 0x00485BD9;

  static u32            BWFXN_KillUnit                            = 0x004EC504;
  static u32            BWFXN_KillUnitBack                        = BWFXN_KillUnit + 5;
  static u32            BWFXN_KillUnitTarget                      = 0x00479480;

  static u32            BWFXN_NextLogicFrame                      = 0x004D974E;
  static u32            BWFXN_NextLogicFrameBack                  = BWFXN_NextLogicFrame + 5;
  static u32            BWFXN_NextLogicFrameTarget                = 0x00488780;

  static u32            BWFXN_NextMenuFrame                       = 0x0041A0D3;
  static u32            BWFXN_NextMenuFrameBack                   = BWFXN_NextMenuFrame + 5;
  static u32            BWFXN_NextMenuFrameTarget                 = 0x004D1BF0;

  static u32            BWDATA_UpgradeLevelSC                     = 0x0058D2B0;
  static u32            BWDATA_UpgradeLevelBW                     = 0x0058F32C;
  static u32            BWDATA_TechResearchSC                     = 0x0058CF44;
  static u32            BWDATA_TechResearchBW                     = 0x0058F140;
  static u32            BWDATA_PlayerSelection                    = 0x006284E0;

  static u32            BWDATA_Mouse                              = 0x006CDDC4;
  static u32*           BWDATA_MouseX                             = (u32*)  BWDATA_Mouse;
  static u32*           BWDATA_MouseY                             = (u32*) (BWDATA_Mouse + 4);

  static u32            BWDATA_Screen                             = 0x00628448;
  static u32*           BWDATA_ScreenX                            = (u32*)  BWDATA_Screen;
  static u32*           BWDATA_ScreenY                            = (u32*) (BWDATA_Screen + 40);

  static char*          BWDATA_CurrentMapFileName                 = (char*)       0x0057FD3C;
  static char*          BWDATA_CurrentMapName                     = (char*)       0x0057FE40;
  static u16*           BWDATA_TechLabelIndex                     = (u16*)        techdataDat[7].address;
  static u16*           BWDATA_UpgradeLabelIndex                  = (u16*)        upgradesDat[8].address;
  static u8*            BWDATA_UpgradeMax                         = (u8*)         upgradesDat[10].address;
  static u16*           BWDATA_UpgradeMineralCostBase             = (u16*)        upgradesDat[0].address;
  static u16*           BWDATA_UpgradeMineralCostFactor           = (u16*)        upgradesDat[1].address;
  static u16*           BWDATA_UpgradeGasCostBase                 = (u16*)        upgradesDat[2].address;
  static u16*           BWDATA_UpgradeGasCostFactor               = (u16*)        upgradesDat[3].address;
  static u16*           BWDATA_UpgradeTimeCostBase                = (u16*)        upgradesDat[4].address;
  static u16*           BWDATA_UpgradeTimeCostFactor              = (u16*)        upgradesDat[5].address;
  static u8*            BWDATA_UpgradeRace                        = (u8*)         upgradesDat[9].address;
  static u32            BWDATA_StringTableOff                     =               0x006D1238;
  static u16**          BWDATA_StringTableIndex                   = (u16**)       BWDATA_StringTableOff;
  static char**         BWDATA_StringTable                        = (char**)      BWDATA_StringTableOff;
  static u32**          BWDATA_MapFogOfWar                        = (u32**)       0x006D1260;


};
#pragma pack()
