#include <utility.h>
#include <formatio.h>
#include <ansi_c.h>
#include <cvirte.h>		
#include <userint.h>
#include "Interfata.h"

#define SAMPLE_RATE		0
#define NPOINTS			1

static int panelHandle;
int powerOn = 0;

int waveInfo[2];
double sampleRate = 0.0;
int npoints = 0;
double *waveData = 0;
double** buffer = 0;
int starts = -1;
int stops = 0;

int main (int argc, char *argv[])
{
	if (InitCVIRTE (0, argv, 0) == 0)
		return -1;	/* out of memory */
	if ((panelHandle = LoadPanel (0, "Interfata.uir", PANEL)) < 0)
		return -1;
	DisplayPanel (panelHandle);
	RunUserInterface ();
	DiscardPanel (panelHandle);
	return 0;
}

int CVICALLBACK OnPanel (int panel, int event, void *callbackData,
						 int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_GOT_FOCUS:

			break;
		case EVENT_LOST_FOCUS:

			break;
		case EVENT_CLOSE:

			QuitUserInterface(0);
	}
	return 0;
}

int CVICALLBACK OnLoad (int panel, int control, int event,
						void *callbackData, int eventData1, int eventData2)
{
	
	
	switch (event)
	{
		case EVENT_COMMIT:
			//executa script python pentru conversia unui fisierului .wav in .txt
			LaunchExecutable("python main.py");
			
			//astept sa fie generate cele doua fisiere (modificati timpul daca este necesar
			//Delay(4);
			
			//incarc informatiile privind rata de esantionare si numarul de valori
			FileToArray("wafeInfo.txt", waveInfo, VAL_INTEGER, 2, 1, VAL_GROUPS_TOGETHER, VAL_GROUPS_AS_COLUMNS, VAL_ASCII);
			sampleRate = waveInfo[SAMPLE_RATE];
			npoints = waveInfo[NPOINTS];
			
			//alocare memorie pentru numarul de puncte
			waveData = (double *) calloc(npoints, sizeof(double));
			
			//incarcare din fisierul .txt in memorie (vector)
			FileToArray("waveData.txt", waveData, VAL_DOUBLE, npoints, 1, VAL_GROUPS_TOGETHER, VAL_GROUPS_AS_COLUMNS, VAL_ASCII);
			
			//afisare pe grapf
			PlotY(panel, PANEL_GRAPH_RAW_DATA, waveData, npoints, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
			
			//double buffer[6][(int)(npoints/6)];
			
		
			buffer = (double**)malloc(6 * sizeof(double*));
			for (int i=0; i < 6; ++i)
			    buffer[i] = (double*)malloc((int)(npoints/6) * sizeof(double));
			int i = 0;
			buffer[0][0] = waveData[0];
			for(int j = 1; j < npoints; ++j)
			{
				if(i % 22050 == 0 && j < 6)
					i+, printf("%d\n", j);
				buffer[i][j - i*22050] = waveData[j];
				
			}
			
			break;
	}
	return 0;
}

int CVICALLBACK OnPowerSwitch (int panel, int control, int event,
						  void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			
			GetCtrlVal(panelHandle, PANEL_BINARYSWITCH, &powerOn);
			SetCtrlVal(panelHandle, PANEL_LED, powerOn);
			SetCtrlAttribute(panelHandle, PANEL_LOAD_Button, ATTR_DIMMED, !powerOn);
			SetCtrlAttribute(panelHandle, PANEL_GRAPH_RAW_DATA, ATTR_DIMMED, !powerOn);
			SetCtrlAttribute(panelHandle, PANEL_GRAPH_Filtred_Data, ATTR_DIMMED, !powerOn);
	}
	return 0;
}

int CVICALLBACK OnPrev (int panel, int control, int event,
						void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			if(starts > 0)
			{
				stops--;
				starts--;
				SetCtrlAttribute(panelHandle, PANEL_Starts_Num, ATTR_DIMMED, 0);
				SetCtrlAttribute(panelHandle, PANEL_Stops_Num, ATTR_DIMMED, 0);
				DeleteGraphPlot(panelHandle, PANEL_GRAPH_RAW_DATA, 1, 1);
				PlotY(panel, PANEL_GRAPH_RAW_DATA, buffer[starts], npoints, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
			}
			
		
			break;
	}
	return 0;
}

int CVICALLBACK OnNext (int panel, int control, int event,
						void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			if(stops < 6)
			{
				starts++;
				stops++;
				SetCtrlAttribute(panelHandle, PANEL_Starts_Num, ATTR_DIMMED, 0);
				SetCtrlAttribute(panelHandle, PANEL_Stops_Num, ATTR_DIMMED, 0);
				DeleteGraphPlot(panelHandle, PANEL_GRAPH_RAW_DATA, 1, 1);
				PlotY(panel, PANEL_GRAPH_RAW_DATA, buffer[starts], npoints, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
			}

		
			break;
	}
	return 0;
}
