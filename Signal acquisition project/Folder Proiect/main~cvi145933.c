#include <advanlys.h>
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

int plotHandle;
int waveInfo[2];
BOOL loaded = 0;
int tipFiltru = 0;
double sampleRate = 0.0;
int npoints = 0;
double *waveData = 0;
double *filtredData = 0;
int *histogram = 0;
double *axis = 0;
double** buffer = 0;
int starts = -1;
int stops = 0;
double maxVal = 0;
int maxIndex = 0;
double minVal = 0;
int minIndex = 0;
double mean = 0;
double median = 0;
double dispersie = 0;
int zeroCrossing = 0;
int xBuffer[22050];

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
			loaded = 1;
			SetCtrlAttribute(panelHandle, PANEL_BUTTON_FILTRARE, ATTR_DIMMED, 0);
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
			
			//determinare min/max, medie, mediana, dispersie
			MaxMin1D(waveData, npoints, &maxVal, &maxIndex, &minVal, &minIndex);
			Median(waveData, npoints, &median);
			StdDev(waveData, npoints, &mean, &dispersie);
		
			
			SetCtrlVal(panelHandle, PANEL_Minim_Num, minVal);
			SetCtrlVal(panelHandle, PANEL_Maxim_Num, maxVal);
			SetCtrlVal(panelHandle, PANEL_Indice_Minim_Num, minIndex);
			SetCtrlVal(panelHandle, PANEL_Indice_Maxim_Num, maxIndex);
			SetCtrlVal(panelHandle, PANEL_Medie_Num, mean);
			SetCtrlVal(panelHandle, PANEL_Mediana_Num, median);
			SetCtrlVal(panelHandle, PANEL_Dispersia_Num, dispersie);
			SetCtrlAttribute(panelHandle, PANEL_Maxim_Num, ATTR_DIMMED, 0);
			SetCtrlAttribute(panelHandle, PANEL_Medie_Num, ATTR_DIMMED, 0);
			SetCtrlAttribute(panelHandle, PANEL_Mediana_Num, ATTR_DIMMED, 0);
			SetCtrlAttribute(panelHandle, PANEL_Minim_Num, ATTR_DIMMED, 0);
			SetCtrlAttribute(panelHandle, PANEL_Indice_Maxim_Num, ATTR_DIMMED, 0);
			SetCtrlAttribute(panelHandle, PANEL_Indice_Minim_Num, ATTR_DIMMED, 0);
			SetCtrlAttribute(panelHandle, PANEL_Dispersia_Num, ATTR_DIMMED, 0);
			SetCtrlAttribute(panelHandle, PANEL_Zero_Crossing_Num, ATTR_DIMMED, 0);
		
			
			
			zeroCrossing = 0;
			for(int j = 0; j < npoints - 1; ++j)
				if(waveData[j] * waveData[j+1] < 0 || waveData[j] == 0)
					zeroCrossing++;
			SetCtrlVal(panelHandle, PANEL_Zero_Crossing_Num, zeroCrossing);
			
			histogram = (int *) calloc(npoints, sizeof(int));
			axis = (double *) calloc(npoints, sizeof(double));
			Histogram(waveData, npoints, minVal, maxVal, histogram, axis, 10); 
			PlotXY(panelHandle, PANEL_GRAPH_HISTOGRAM, axis, histogram, 10, VAL_DOUBLE, VAL_INTEGER, VAL_VERTICAL_BAR, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
		
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
			SetCtrlAttribute(panelHandle, PANEL_GRAPH_FILTRED_DATA, ATTR_DIMMED, !powerOn);
			SetCtrlAttribute(panelHandle, PANEL_GRAPH_HISTOGRAM, ATTR_DIMMED, !powerOn);
			SetCtrlAttribute(panelHandle, PANEL_RING, ATTR_DIMMED, !powerOn);
			SetCtrlAttribute(panelHandle, PANEL_RING_2, ATTR_DIMMED, 0);
			if(powerOn == 0)
			{
				SetCtrlAttribute(panelHandle, PANEL_NEXT_BUTTON, ATTR_DIMMED, 1);
				SetCtrlAttribute(panelHandle, PANEL_PREV_BUTTON, ATTR_DIMMED, 1);
				SetCtrlAttribute(panelHandle, PANEL_Starts_Num, ATTR_DIMMED, 1);
				SetCtrlAttribute(panelHandle, PANEL_Stops_Num, ATTR_DIMMED, 1);
				SetCtrlAttribute(panelHandle, PANEL_Maxim_Num, ATTR_DIMMED, 1);
				SetCtrlAttribute(panelHandle, PANEL_Medie_Num, ATTR_DIMMED, 1);
				SetCtrlAttribute(panelHandle, PANEL_Mediana_Num, ATTR_DIMMED, 1);
				SetCtrlAttribute(panelHandle, PANEL_Minim_Num, ATTR_DIMMED, 1);
				SetCtrlAttribute(panelHandle, PANEL_Indice_Maxim_Num, ATTR_DIMMED, 1);
				SetCtrlAttribute(panelHandle, PANEL_Indice_Minim_Num, ATTR_DIMMED, 1);
				SetCtrlAttribute(panelHandle, PANEL_Dispersia_Num, ATTR_DIMMED, 1);
				SetCtrlAttribute(panelHandle, PANEL_Zero_Crossing_Num, ATTR_DIMMED, 1);
				SetCtrlAttribute(panelHandle, PANEL_RING_2, ATTR_DIMMED, 1);
				SetCtrlAttribute(panelHandle, PANEL_NUMERIC_ALPHA, ATTR_DIMMED, 1);
			}
			else
			{
				if(tipFiltru ==  0)
				{
					SetCtrlAttribute(panelHandle, PANEL_RING_2, ATTR_DIMMED, 0);
					SetCtrlAttribute(panelHandle, PANEL_NUMERIC_ALPHA, ATTR_DIMMED, 1);
				}
				else
				{
					SetCtrlAttribute(panelHandle, PANEL_RING_2, ATTR_DIMMED, 1);
					SetCtrlAttribute(panelHandle, PANEL_NUMERIC_ALPHA, ATTR_DIMMED, 0);
				}
			}
			if(loaded)
				SetCtrlAttribute(panelHandle, PANEL_BUTTON_FILTRARE, ATTR_DIMMED, !powerOn);
			
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
				char c1[2];
				c1[0] = '0' + starts, c1[1] = '\0';
				char c2[2];
				c2[0] = '0' + stops, c2[1] = '\0';
				SetCtrlAttribute(panelHandle, PANEL_Starts_Num, ATTR_CTRL_VAL, c1);
				SetCtrlAttribute(panelHandle, PANEL_Stops_Num, ATTR_CTRL_VAL, c2);
				SetCtrlAttribute(panelHandle, PANEL_Starts_Num, ATTR_DIMMED, 0);
				SetCtrlAttribute(panelHandle, PANEL_Stops_Num, ATTR_DIMMED, 0);
				DeleteGraphPlot(panelHandle, PANEL_GRAPH_FILTRED_DATA, -1, VAL_IMMEDIATE_DRAW);
				PlotY(panel, PANEL_GRAPH_FILTRED_DATA, buffer[starts], 22050, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
				if(starts != 0)
					for(int i = 0; i < 22050; ++i)
						xBuffer[i] = i + stops * 22050;
				
				SetPlotAttribute(panelHandle, PANEL_GRAPH_FILTRED_DATA, PlotY, ATTR_PLOT_XDATA, xBuffer);
				
			}
			if(starts == 0)
			{
				stops--;
				starts--;
				SetCtrlAttribute(panelHandle, PANEL_Starts_Num, ATTR_DIMMED, 1);
				SetCtrlAttribute(panelHandle, PANEL_Stops_Num, ATTR_DIMMED, 1);
				SetCtrlAttribute(panelHandle, PANEL_Starts_Num, ATTR_CTRL_VAL, "");
				SetCtrlAttribute(panelHandle, PANEL_Stops_Num, ATTR_CTRL_VAL, "");
				DeleteGraphPlot(panelHandle, PANEL_GRAPH_FILTRED_DATA, -1, VAL_IMMEDIATE_DRAW);
				PlotY(panel, PANEL_GRAPH_FILTRED_DATA, filtredData, npoints, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
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
				char c1[2];
				c1[0] = '0' + starts, c1[1] = '\0';
				char c2[2];
				c2[0] = '0' + stops, c2[1] = '\0';
				SetCtrlAttribute(panelHandle, PANEL_Starts_Num, ATTR_CTRL_VAL, c1);
				SetCtrlAttribute(panelHandle, PANEL_Stops_Num, ATTR_CTRL_VAL, c2);
				SetCtrlAttribute(panelHandle, PANEL_Starts_Num, ATTR_DIMMED, 0);
				SetCtrlAttribute(panelHandle, PANEL_Stops_Num, ATTR_DIMMED, 0);
				DeleteGraphPlot(panelHandle, PANEL_GRAPH_FILTRED_DATA, -1, VAL_IMMEDIATE_DRAW);
				plotHandle = PlotY(panel, PANEL_GRAPH_FILTRED_DATA, buffer[starts], 22050, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
				if(starts != 0)
					for(int i = 0; i < 22050; ++i)
						xBuffer[i] = i + stops * 22050;
				
				SetPlotAttribute(panelHandle, PANEL_GRAPH_FILTRED_DATA, plotHandle, ATTR_PLOT_XDATA, xBuffer);
			}
		
			break;
	}
	return 0;
}

int CVICALLBACK OnFilter (int panel, int control, int event,
						  void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			if(loaded)
			{
				filtredData = (double *) calloc(npoints, sizeof(double));
				if(tipFiltru == 0) //mediere
				{ 
					int esantion;
					GetCtrlAttribute(panelHandle, PANEL_RING_2, ATTR_CTRL_VAL, &esantion);
					double suma = 0;
					int fi = 0;
					int li = esantion;
					for(int i = 0; i < npoints; ++i)
					{ 
						suma += waveData[i];
						
						if(i % esantion == 0 && i != 0)
						{
							for(int j = fi; j < li; ++j)
								filtredData[j] = suma/(double)esantion;
							fi = i;
							li = i + esantion;
							suma = 0;
						}
					}
				}
				else			   //aplha
				{
					double alpha = 0.1;
					GetCtrlVal(panelHandle, PANEL_NUMERIC_ALPHA, &alpha);
					filtredData[0] = waveData[0];
					for(int i = 1; i < npoints; ++i)
						filtredData[i] = (1 - alpha) * filtredData[i-1] + alpha * waveData[i];
				}
				
				DeleteGraphPlot(panelHandle, PANEL_GRAPH_FILTRED_DATA, -1, VAL_IMMEDIATE_DRAW);
				PlotY(panel, PANEL_GRAPH_FILTRED_DATA, filtredData, npoints, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
				
				buffer = (double**)malloc(6 * sizeof(double*));
				for (int i = 0; i < 6; ++i)
				    buffer[i] = (double*)malloc(22050 * sizeof(double));
				int i = 0;
				buffer[0][0] = waveData[0];
				
				for(int j = 1; j < npoints; ++j)
				{
					if(j % 22050 == 0)
						i++;
					if(i == 6)
						break;
					buffer[i][j - i*22050] = filtredData[j];
				}
				SetCtrlAttribute(panelHandle, PANEL_NEXT_BUTTON, ATTR_DIMMED, 0);
				SetCtrlAttribute(panelHandle, PANEL_PREV_BUTTON, ATTR_DIMMED, 0);
			}
			break;
	}
	return 0;
}

int CVICALLBACK OnChooseFilter (int panel, int control, int event,
								void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			GetCtrlAttribute(panelHandle, PANEL_RING, ATTR_CTRL_INDEX, &tipFiltru);
			if(tipFiltru ==  0)
			{
				SetCtrlAttribute(panelHandle, PANEL_RING_2, ATTR_DIMMED, 0);
				SetCtrlAttribute(panelHandle, PANEL_NUMERIC_ALPHA, ATTR_DIMMED, 1);
			}
			else
			{
				SetCtrlAttribute(panelHandle, PANEL_RING_2, ATTR_DIMMED, 1);
				SetCtrlAttribute(panelHandle, PANEL_NUMERIC_ALPHA, ATTR_DIMMED, 0);
			}
			break;
	}
	return 0;
}
