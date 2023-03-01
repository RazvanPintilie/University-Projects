/**************************************************************************/
/* LabWindows/CVI User Interface Resource (UIR) Include File              */
/*                                                                        */
/* WARNING: Do not add to, delete from, or otherwise modify the contents  */
/*          of this include file.                                         */
/**************************************************************************/

#include <userint.h>

#ifdef __cplusplus
    extern "C" {
#endif

     /* Panels and Controls: */

#define  PANEL                            1       /* callback function: OnPanel */
#define  PANEL_BINARYSWITCH_2             2       /* control type: binary, callback function: OnSwitch */
#define  PANEL_LOAD_Button                3       /* control type: command, callback function: OnLoad */
#define  PANEL_BINARYSWITCH               4       /* control type: binary, callback function: OnPowerSwitch */
#define  PANEL_LED                        5       /* control type: LED, callback function: (none) */
#define  PANEL_GRAPH_RAW_DATA             6       /* control type: graph, callback function: (none) */
#define  PANEL_GRAPH_FILTRED_DATA         7       /* control type: graph, callback function: (none) */
#define  PANEL_NEXT_BUTTON                8       /* control type: command, callback function: OnNext */
#define  PANEL_PREV_BUTTON                9       /* control type: command, callback function: OnPrev */
#define  PANEL_Interval                   10      /* control type: textMsg, callback function: (none) */
#define  PANEL_Starts                     11      /* control type: textMsg, callback function: (none) */
#define  PANEL_Stops_Num                  12      /* control type: string, callback function: (none) */
#define  PANEL_Starts_Num                 13      /* control type: string, callback function: (none) */
#define  PANEL_Stops                      14      /* control type: textMsg, callback function: (none) */
#define  PANEL_Dispersia_Num              15      /* control type: numeric, callback function: (none) */
#define  PANEL_Mediana_Num                16      /* control type: numeric, callback function: (none) */
#define  PANEL_Zero_Crossing_Num          17      /* control type: numeric, callback function: (none) */
#define  PANEL_Medie_Num                  18      /* control type: numeric, callback function: (none) */
#define  PANEL_Indice_Minim_Num           19      /* control type: numeric, callback function: (none) */
#define  PANEL_Indice_Maxim_Num           20      /* control type: numeric, callback function: (none) */
#define  PANEL_Maxim_Num                  21      /* control type: numeric, callback function: (none) */
#define  PANEL_Minim_Num                  22      /* control type: numeric, callback function: (none) */
#define  PANEL_GRAPH_HISTOGRAM            23      /* control type: graph, callback function: (none) */
#define  PANEL_BUTTON_FILTRARE            24      /* control type: command, callback function: OnFilter */
#define  PANEL_RING                       25      /* control type: ring, callback function: OnChooseFilter */
#define  PANEL_NUMERIC_ALPHA              26      /* control type: numeric, callback function: (none) */
#define  PANEL_RING_2                     27      /* control type: ring, callback function: (none) */
#define  PANEL_COMMANDBUTTON_2            28      /* control type: command, callback function: OnAnvelopa */
#define  PANEL_COMMANDBUTTON              29      /* control type: command, callback function: OnDerivata */
#define  PANEL_DECORATION                 30      /* control type: deco, callback function: (none) */

#define  PANEL_2                          2       /* callback function: OnFrPanel */
#define  PANEL_2_BINARYSWITCH             2       /* control type: binary, callback function: OnSwitch */
#define  PANEL_2_GRAPH_5                  3       /* control type: graph, callback function: (none) */
#define  PANEL_2_GRAPH_6                  4       /* control type: graph, callback function: (none) */
#define  PANEL_2_GRAPH_4                  5       /* control type: graph, callback function: (none) */
#define  PANEL_2_GRAPH_3                  6       /* control type: graph, callback function: (none) */
#define  PANEL_2_GRAPH_2                  7       /* control type: graph, callback function: (none) */
#define  PANEL_2_GRAPH_1                  8       /* control type: graph, callback function: (none) */
#define  PANEL_2_NEXT_BUTTON              9       /* control type: command, callback function: OnNextFr */
#define  PANEL_2_PREV_BUTTON              10      /* control type: command, callback function: OnPrevFr */
#define  PANEL_2_Starts                   11      /* control type: textMsg, callback function: (none) */
#define  PANEL_2_Stops                    12      /* control type: textMsg, callback function: (none) */
#define  PANEL_2_BINARYSWITCH_2           13      /* control type: binary, callback function: OnFereastra */
#define  PANEL_2_PW_PEAK_NR_2             14      /* control type: numeric, callback function: (none) */
#define  PANEL_2_PW_PEAK_NR_3             15      /* control type: numeric, callback function: (none) */
#define  PANEL_2_PW_PEAK_NR_1             16      /* control type: numeric, callback function: (none) */
#define  PANEL_2_FR_PEAK_NR_3             17      /* control type: numeric, callback function: (none) */
#define  PANEL_2_FR_PEAK_NR_2             18      /* control type: numeric, callback function: (none) */
#define  PANEL_2_FR_PEAK_NR_1             19      /* control type: numeric, callback function: (none) */
#define  PANEL_2_RING                     20      /* control type: ring, callback function: OnN */
#define  PANEL_2_Stops_Num                21      /* control type: numeric, callback function: (none) */
#define  PANEL_2_Starts_Num               22      /* control type: numeric, callback function: (none) */


     /* Control Arrays: */

          /* (no control arrays in the resource file) */


     /* Menu Bars, Menus, and Menu Items: */

          /* (no menu bars in the resource file) */


     /* Callback Prototypes: */

int  CVICALLBACK OnAnvelopa(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnChooseFilter(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnDerivata(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnFereastra(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnFilter(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnFrPanel(int panel, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnLoad(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnN(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnNext(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnNextFr(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnPanel(int panel, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnPowerSwitch(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnPrev(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnPrevFr(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);
int  CVICALLBACK OnSwitch(int panel, int control, int event, void *callbackData, int eventData1, int eventData2);


#ifdef __cplusplus
    }
#endif