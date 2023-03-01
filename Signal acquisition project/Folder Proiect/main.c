#include <advanlys.h>
#include <utility.h>
#include <formatio.h>
#include <ansi_c.h>
#include <cvirte.h>		
#include <userint.h>
#include "Interfata.h"
#include "toolbox.h"

#define SAMPLE_RATE		0
#define NPOINTS			1
#define hamming 		1
#define hanning 		2

static int panelHandle;
int powerOn = 0;

int waveInfo[2];
BOOL loaded = 0;
int tipFiltru = 0;
double sampleRate = 0.0;
int npoints = 0;
double *waveData = 0;
double *waveDataHamming = 0;
double *waveDataHanning = 0;
double *filtredData = 0;
double *derivata = 0;
double *anvelopa = 0;
int *histogram = 0;
double *axis = 0;
double** buffer = 0;
double** bufferFr = 0;
int N = 2048;
int starts = -1;
int stops = 0;
int startsFr = 0;
int stopsFr = 1;
double maxVal = 0;
int maxIndex = 0;
double minVal = 0;
int minIndex = 0;
double mean = 0;
double median = 0;
double dispersie = 0;
int zeroCrossing = 0;
double *v1 = 0;
double *v2 = 0;
double *v3 = 0;
double *vf1 = 0;
double *vf2 = 0;
double *vf3 = 0;
double *convertedSpectrum1 = 0;
double *convertedSpectrum2 = 0;
double *convertedSpectrum3 = 0;
char *unit1;
char *unit2;
char *unit3;
double df1 = 0;
double df3 = 0;
double df2 = 0;
double freqPeak1 = 0;
double freqPeak2 = 0;
double freqPeak3 = 0;
double powPeak1 = 0;
double powPeak2 = 0;
double powPeak3 = 0;
double zeros[16384];

int panelHandle = 0;
int frecPanel = 0;

int tipFereastra = hamming;

int main (int argc, char *argv[])
{
	int error = 0;
	
	/* initialize and load resources */
	nullChk (InitCVIRTE (0, argv, 0));
	errChk (panelHandle = LoadPanel (0, "Interfata.uir", PANEL));
	errChk (frecPanel = LoadPanel (0, "Interfata.uir", PANEL_2));
	
	/* display the panel and run the user interface */
	errChk (DisplayPanel (panelHandle));
	errChk (RunUserInterface ());

Error:
	/* clean up */
	if (panelHandle > 0)
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
			waveDataHamming = (double *) calloc(npoints, sizeof(double));
			waveDataHanning = (double *) calloc(npoints, sizeof(double));
			anvelopa = (double *) calloc(npoints, sizeof(double));
			
			//incarcare din fisierul .txt in memorie (vector)
			FileToArray("waveData.txt", waveData, VAL_DOUBLE, npoints, 1, VAL_GROUPS_TOGETHER, VAL_GROUPS_AS_COLUMNS, VAL_ASCII);
			FileToArray("waveData.txt", waveDataHamming, VAL_DOUBLE, npoints, 1, VAL_GROUPS_TOGETHER, VAL_GROUPS_AS_COLUMNS, VAL_ASCII);
			FileToArray("waveData.txt", waveDataHanning, VAL_DOUBLE, npoints, 1, VAL_GROUPS_TOGETHER, VAL_GROUPS_AS_COLUMNS, VAL_ASCII);
			FileToArray("anvelopa.txt", anvelopa, VAL_DOUBLE, npoints, 1, VAL_GROUPS_TOGETHER, VAL_GROUPS_AS_COLUMNS, VAL_ASCII);
			
			//afisare pe grapf
			DeleteGraphPlot(panelHandle, PANEL_GRAPH_RAW_DATA, -1, VAL_IMMEDIATE_DRAW);
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
			SetCtrlAttribute(panel, PANEL_COMMANDBUTTON_2, ATTR_DIMMED, 0);
			SetCtrlAttribute(panelHandle, PANEL_BINARYSWITCH_2, ATTR_DIMMED, 0);
			
			derivata = (double *) calloc(npoints, sizeof(double));
			
			zeroCrossing = 0;
			for(int j = 0; j < npoints - 1; ++j)
				if(waveData[j] * waveData[j+1] < 0 || waveData[j] == 0)
					zeroCrossing++;
			SetCtrlVal(panelHandle, PANEL_Zero_Crossing_Num, zeroCrossing);
			
			histogram = (int *) calloc(npoints, sizeof(int));
			axis = (double *) calloc(npoints, sizeof(double));
			Histogram(waveData, npoints, minVal, maxVal, histogram, axis, 10); 
			PlotXY(panelHandle, PANEL_GRAPH_HISTOGRAM, axis, histogram, 10, VAL_DOUBLE, VAL_INTEGER, VAL_VERTICAL_BAR, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
			
			
			//pe panoul frecventa
			WindowConst windowConst;
			GetCtrlVal(frecPanel, PANEL_2_RING, &N);
			
			//row_signal
			v1 = (double *) calloc(N, sizeof(double));
			Copy1D(waveData + startsFr * N,  N, v1);
			ScaledWindow(v1, N, HAMMING, &windowConst);
			DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_1, -1, VAL_IMMEDIATE_DRAW);
			PlotY(frecPanel, PANEL_2_GRAPH_1, v1, N, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_GRAY);
			
			//obtinere spectru
			vf1 = (double *) calloc(N/2, sizeof(double));
			convertedSpectrum1 = (double *) calloc(N/2, sizeof(double));
			free(unit1);
			unit1 = (char *) calloc(32, sizeof(char));
			AutoPowerSpectrum(v1, N, 1./sampleRate, vf1, &df1);
			PowerFrequencyEstimate(vf1, N/2, -1, windowConst, df1, 7, &freqPeak3, &powPeak3);
			SpectrumUnitConversion(vf1, N/2, SPECTRUM_POWER, SCALING_MODE_LINEAR, DISPLAY_UNIT_VRMS, df1, windowConst, convertedSpectrum1, unit1);
			DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_6, -1, VAL_IMMEDIATE_DRAW);
			PlotWaveform(frecPanel, PANEL_2_GRAPH_6, convertedSpectrum1, N/2 ,VAL_DOUBLE, 1.0, 0.0, 0.0, df1, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID,  VAL_CONNECTED_POINTS, VAL_DK_GREEN);
			SetCtrlVal(frecPanel, PANEL_2_FR_PEAK_NR_3, freqPeak3);
			SetCtrlVal(frecPanel, PANEL_2_PW_PEAK_NR_3, powPeak3);
			
			//first_filtred_signal
			v2 = (double *) calloc(N, sizeof(double));
			Copy1D(waveData + startsFr * N,  N, v2);
			//aici aplici filtru
			//BUTTERWORTH pt 1/4 din f. inalte
			Bw_HPF(v2, N, sampleRate, sampleRate/4, 5, v2);
			
			ScaledWindow(v2, N, HAMMING, &windowConst);
			DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_2, -1, VAL_IMMEDIATE_DRAW);
			PlotY(frecPanel, PANEL_2_GRAPH_2, v2, N, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_GRAY);
			//obtinere spectru
			vf2 = (double *) calloc(N/2, sizeof(double));
			convertedSpectrum2 = (double *) calloc(N/2, sizeof(double));
			free(unit2);
			unit2 = (char *) calloc(32, sizeof(char));
			AutoPowerSpectrum(v2, N, 1./sampleRate, vf2, &df2);
			PowerFrequencyEstimate(vf2, N/2, -1, windowConst, df2, 7, &freqPeak1, &powPeak1);
			SpectrumUnitConversion(vf2, N/2, SPECTRUM_POWER, SCALING_MODE_LINEAR, DISPLAY_UNIT_VRMS, df2, windowConst, convertedSpectrum2, unit2);
			DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_4, -1, VAL_IMMEDIATE_DRAW);
			PlotWaveform(frecPanel, PANEL_2_GRAPH_4, convertedSpectrum2, N/2 ,VAL_DOUBLE, 1.0, 0.0, 0.0, df2, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID,  VAL_CONNECTED_POINTS, VAL_DK_GREEN);
			SetCtrlVal(frecPanel, PANEL_2_FR_PEAK_NR_1, freqPeak1);
			SetCtrlVal(frecPanel, PANEL_2_PW_PEAK_NR_1, powPeak1);
			
			
			//second_filtred_signal
			v3 = (double *) calloc(N, sizeof(double));
			Copy1D(waveData + startsFr * N,  N, v3);
			//aici aplici filtru 
			//CEBYSHEV de ordin 5 pentru 1/4 din f. joase 
			Ch_LPF(v3, N, sampleRate, sampleRate/4, 0.1, 5, v3);
			
			ScaledWindow(v3, N, HAMMING, &windowConst);
			DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_3, -1, VAL_IMMEDIATE_DRAW);
			PlotY(frecPanel, PANEL_2_GRAPH_3, v3, N, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_GRAY);
			//obtinere spectru
			vf3 = (double *) calloc(N/2, sizeof(double));
			convertedSpectrum3 = (double *) calloc(N/2, sizeof(double));
			free(unit3);
			unit3 = (char *) calloc(32, sizeof(char));
			AutoPowerSpectrum(v3, N, 1./sampleRate, vf3, &df3);
			PowerFrequencyEstimate(vf3, N/2, -1, windowConst, df2, 7, &freqPeak2, &powPeak2);
			SpectrumUnitConversion(vf3, N/2, SPECTRUM_POWER, SCALING_MODE_LINEAR, DISPLAY_UNIT_VRMS, df3, windowConst, convertedSpectrum3, unit3);
			DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_5, -1, VAL_IMMEDIATE_DRAW);
			PlotWaveform(frecPanel, PANEL_2_GRAPH_5, convertedSpectrum3, N/2 ,VAL_DOUBLE, 1.0, 0.0, 0.0, df3, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID,  VAL_CONNECTED_POINTS, VAL_DK_GREEN);
			SetCtrlVal(frecPanel, PANEL_2_FR_PEAK_NR_2, freqPeak2);
			SetCtrlVal(frecPanel, PANEL_2_PW_PEAK_NR_2, powPeak2);
			
			
			
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
				SetCtrlAttribute(panel, PANEL_COMMANDBUTTON, ATTR_DIMMED, 1);
				SetCtrlAttribute(panel, PANEL_COMMANDBUTTON_2, ATTR_DIMMED, 1);
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
			{
				SetCtrlAttribute(panelHandle, PANEL_BUTTON_FILTRARE, ATTR_DIMMED, !powerOn);
				SetCtrlAttribute(panel, PANEL_COMMANDBUTTON_2, ATTR_DIMMED, !powerOn);
			}
			
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
				PlotY(panel, PANEL_GRAPH_FILTRED_DATA, buffer[starts], sampleRate, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
				SetCtrlAttribute (panelHandle, PANEL_GRAPH_FILTRED_DATA, ATTR_XAXIS_OFFSET, starts * sampleRate);
				
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
				PlotY(panel, PANEL_GRAPH_FILTRED_DATA, buffer[starts], sampleRate, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
				SetCtrlAttribute (panelHandle, PANEL_GRAPH_FILTRED_DATA, ATTR_XAXIS_OFFSET, starts * sampleRate);
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
			SetCtrlAttribute(panel, PANEL_COMMANDBUTTON, ATTR_DIMMED, 0);
			stops = 0;
			starts = -1;
			char c1[2];
			c1[0] = '0' + starts, c1[1] = '\0';
			char c2[2];
			c2[0] = '0' + stops, c2[1] = '\0';
			SetCtrlAttribute(panelHandle, PANEL_Starts_Num, ATTR_CTRL_VAL, c1);
			SetCtrlAttribute(panelHandle, PANEL_Stops_Num, ATTR_CTRL_VAL, c2);
			if(loaded)
			{
				
				filtredData = (double *) calloc(npoints, sizeof(double));
				if(tipFiltru == 0) //mediere
				{ 
					int esantion;
					GetCtrlAttribute(panelHandle, PANEL_RING_2, ATTR_CTRL_VAL, &esantion);
					double suma = 0;
					for (int i = 0; i < npoints; i++)
					{
						for (int k = 0; k <= esantion - 1; k++)
						{
							if ((i - k) < 0)
							{
								suma += 0;
							}
							else	
							{
								suma += waveData[i-k];	
							}
						}
						filtredData[i] = suma/esantion;
						suma = 0;
						
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
				Difference(filtredData, npoints, 1, 0, 0, derivata);
				
				DeleteGraphPlot(panelHandle, PANEL_GRAPH_FILTRED_DATA, -1, VAL_IMMEDIATE_DRAW);
				PlotY(panel, PANEL_GRAPH_FILTRED_DATA, filtredData, npoints, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_RED);
				
				buffer = (double**)malloc(6 * sizeof(double*));
				for (int i = 0; i < 6; ++i)
				    buffer[i] = (double*)malloc(sampleRate * sizeof(double));
				int i = 0;
				buffer[0][0] = waveData[0];
				
				for(int j = 1; j < npoints; ++j)
				{
					if(j % (int)sampleRate == 0)
						i++;
					if(i == 6)
						break;
					buffer[i][j - i*(int)sampleRate] = filtredData[j];
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

int CVICALLBACK OnDerivata (int panel, int control, int event,
							void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
		
			if(starts < 0)
				PlotY(panel, PANEL_GRAPH_FILTRED_DATA, derivata, npoints, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_BLUE);
			else
				PlotY(panel, PANEL_GRAPH_FILTRED_DATA, derivata + starts , sampleRate, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_BLUE);
	}
	return 0;
}

int CVICALLBACK OnAnvelopa (int panel, int control, int event,
							void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			FileToArray("anvelopa.txt", anvelopa, VAL_DOUBLE, npoints, 1, VAL_GROUPS_TOGETHER, VAL_GROUPS_AS_COLUMNS, VAL_ASCII);
			PlotY(panel, PANEL_GRAPH_RAW_DATA, anvelopa, npoints, VAL_DOUBLE, VAL_THIN_STEP, VAL_SIMPLE_DOT, VAL_SOLID, 22050, VAL_BLUE);
			break;
	}
	return 0;
}



int CVICALLBACK OnSwitch (int panel, int control, int event,
						  void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			if(panel == panelHandle)
			{
				SetCtrlVal(frecPanel, PANEL_2_BINARYSWITCH, 1);
				DisplayPanel(frecPanel);
				HidePanel(panel);
			}
			else
			{
				SetCtrlVal(panelHandle, PANEL_2_BINARYSWITCH, 0);
				DisplayPanel(panelHandle);
				HidePanel(panel);
			}
			break;
	}
	return 0;
}

int CVICALLBACK OnFrPanel (int panel, int event, void *callbackData,
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
			break;
	}
	return 0;
}

int CVICALLBACK OnFereastra (int panel, int control, int event,
							 void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			if(tipFereastra == hamming)
				tipFereastra = hanning;
			else
				tipFereastra = hamming;
			stopsFr = 1;
			startsFr = 0;
			SetCtrlAttribute(frecPanel, PANEL_2_Starts_Num, ATTR_CTRL_VAL, 0);
			SetCtrlAttribute(frecPanel, PANEL_2_Stops_Num, ATTR_CTRL_VAL, 1);
			Copy1D(waveData + startsFr * N,  N, v1);
			Copy1D(waveData + startsFr * N,  N, v2);
			Copy1D(waveData + startsFr * N,  N, v3);
			//aici aplici filtru pentru v2 si pentru v3
			
			//aici aplici filtru
			//BUTTERWORTH pt 1/4 din f. inalte
			Bw_HPF(v2, N, sampleRate, sampleRate/4, 5, v2);
			
			//aici aplici filtru 
			//CEBYSHEV de ordin 5 pentru 1/4 din f. joase 
			Ch_LPF(v3, N, sampleRate, sampleRate/4, 0.1, 5, v3);
			
			//plot
			WindowConst windowConst;
			switch(tipFereastra)
				{
					case hamming:
						ScaledWindow(v1, N, HAMMING, &windowConst);
						ScaledWindow(v2, N, HAMMING, &windowConst);
						ScaledWindow(v3, N, HAMMING, &windowConst);
						break;
					case hanning:
						ScaledWindow(v1, N, HANNING, &windowConst);
						ScaledWindow(v2, N, HANNING, &windowConst);
						ScaledWindow(v3, N, HANNING, &windowConst);
						break;
				}
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_1, -1, VAL_IMMEDIATE_DRAW);
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_2, -1, VAL_IMMEDIATE_DRAW);
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_3, -1, VAL_IMMEDIATE_DRAW);
				PlotY(frecPanel, PANEL_2_GRAPH_1, v1, N, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_GRAY);
				PlotY(frecPanel, PANEL_2_GRAPH_2, v2, N, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_GRAY);
				PlotY(frecPanel, PANEL_2_GRAPH_3, v3, N, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_GRAY);
				SetCtrlAttribute (frecPanel, PANEL_2_GRAPH_1, ATTR_XAXIS_OFFSET, startsFr * (double)N);
				SetCtrlAttribute (frecPanel, PANEL_2_GRAPH_2, ATTR_XAXIS_OFFSET, startsFr * (double)N);
				SetCtrlAttribute (frecPanel, PANEL_2_GRAPH_3, ATTR_XAXIS_OFFSET, startsFr * (double)N);
				//obtinere spectru
				vf1 = (double *) calloc(N/2, sizeof(double));
				convertedSpectrum1 = (double *) calloc(N/2, sizeof(double));
				free(unit1);
				unit1 = (char *) calloc(32, sizeof(char));
				AutoPowerSpectrum(v1, N, 1./sampleRate, vf1, &df1);
				PowerFrequencyEstimate(vf1, N/2, -1, windowConst, df1, 7, &freqPeak3, &powPeak3);
				SpectrumUnitConversion(vf1, N/2, SPECTRUM_POWER, SCALING_MODE_LINEAR, DISPLAY_UNIT_VRMS, df1, windowConst, convertedSpectrum1, unit1);
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_6, -1, VAL_IMMEDIATE_DRAW);
				PlotWaveform(frecPanel, PANEL_2_GRAPH_6, convertedSpectrum1, N/2 ,VAL_DOUBLE, 1.0, 0.0, 0.0, df1, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID,  VAL_CONNECTED_POINTS, VAL_DK_GREEN);
				SetCtrlVal(frecPanel, PANEL_2_FR_PEAK_NR_3, freqPeak3);
				SetCtrlVal(frecPanel, PANEL_2_PW_PEAK_NR_3, powPeak3);
				//obtinere spectru
				vf2 = (double *) calloc(N, sizeof(double));
				convertedSpectrum2 = (double *) calloc(N, sizeof(double));
				free(unit2);
				unit2 = (char *) calloc(32, sizeof(char));
				AutoPowerSpectrum(v2, N, 1./sampleRate, vf2, &df2);
				PowerFrequencyEstimate(vf2, N, -1, windowConst, df2, 7, &freqPeak1, &powPeak1);
				SpectrumUnitConversion(vf2, N, SPECTRUM_POWER, SCALING_MODE_LINEAR, DISPLAY_UNIT_VRMS, df2, windowConst, convertedSpectrum2, unit2);
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_4, -1, VAL_IMMEDIATE_DRAW);
				PlotWaveform(frecPanel, PANEL_2_GRAPH_4, convertedSpectrum2, N/2 ,VAL_DOUBLE, 1.0, 0.0, 0.0, df2, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID,  VAL_CONNECTED_POINTS, VAL_DK_GREEN);
				SetCtrlVal(frecPanel, PANEL_2_FR_PEAK_NR_1, freqPeak1);
				SetCtrlVal(frecPanel, PANEL_2_PW_PEAK_NR_1, powPeak1);
				//obtinere spectru
				vf3 = (double *) calloc(N, sizeof(double));
				convertedSpectrum3 = (double *) calloc(N, sizeof(double));
				free(unit3);
				unit3 = (char *) calloc(32, sizeof(char));
				AutoPowerSpectrum(v3, N, 1./sampleRate, vf3, &df3);
				PowerFrequencyEstimate(vf3, N, -1, windowConst, df3, 7, &freqPeak2, &powPeak2);
				SpectrumUnitConversion(vf3, N, SPECTRUM_POWER, SCALING_MODE_LINEAR, DISPLAY_UNIT_VRMS, df3, windowConst, convertedSpectrum3, unit3);
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_5, -1, VAL_IMMEDIATE_DRAW);
				PlotWaveform(frecPanel, PANEL_2_GRAPH_5, convertedSpectrum3, N/2 ,VAL_DOUBLE, 1.0, 0.0, 0.0, df3, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID,  VAL_CONNECTED_POINTS, VAL_DK_GREEN);
				SetCtrlVal(frecPanel, PANEL_2_FR_PEAK_NR_2, freqPeak2);
				SetCtrlVal(frecPanel, PANEL_2_PW_PEAK_NR_2, powPeak2);
			break;
	}
	return 0;
}

int CVICALLBACK OnPrevFr (int panel, int control, int event,
						  void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			WindowConst windowConst;
			if(startsFr > 0)
			{
				stopsFr--;
				startsFr--;
				SetCtrlAttribute(frecPanel, PANEL_2_Starts_Num, ATTR_CTRL_VAL, startsFr);
				SetCtrlAttribute(frecPanel, PANEL_2_Stops_Num, ATTR_CTRL_VAL, stopsFr);
				Copy1D(waveData + startsFr * N,  N, v1);
				Copy1D(waveData + startsFr * N,  N, v2);
				Copy1D(waveData + startsFr * N,  N, v3);
				//aici aplici filtru pentru v2 si pentru v3
				
				//aici aplici filtru
				//BUTTERWORTH pt 1/4 din f. inalte
				Bw_HPF(v2, N, sampleRate, sampleRate/4, 5, v2);
				
				//aici aplici filtru 
				//CEBYSHEV de ordin 5 pentru 1/4 din f. joase 
				Ch_LPF(v3, N, sampleRate, sampleRate/4, 0.1, 5, v3);
				
				switch(tipFereastra)
				{
					case hamming:
						ScaledWindow(v1, N, HAMMING, &windowConst);
						ScaledWindow(v2, N, HAMMING, &windowConst);
						ScaledWindow(v3, N, HAMMING, &windowConst);
						break;
					case hanning:
						ScaledWindow(v1, N, HANNING, &windowConst);
						ScaledWindow(v2, N, HANNING, &windowConst);
						ScaledWindow(v3, N, HANNING, &windowConst);
						break;
				}
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_1, -1, VAL_IMMEDIATE_DRAW);
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_2, -1, VAL_IMMEDIATE_DRAW);
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_3, -1, VAL_IMMEDIATE_DRAW);
				PlotY(frecPanel, PANEL_2_GRAPH_1, v1, N, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_GRAY);
				PlotY(frecPanel, PANEL_2_GRAPH_2, v2, N, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_GRAY);
				PlotY(frecPanel, PANEL_2_GRAPH_3, v3, N, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_GRAY);
				SetCtrlAttribute (frecPanel, PANEL_2_GRAPH_1, ATTR_XAXIS_OFFSET, startsFr * (double)N);
				SetCtrlAttribute (frecPanel, PANEL_2_GRAPH_2, ATTR_XAXIS_OFFSET, startsFr * (double)N);
				SetCtrlAttribute (frecPanel, PANEL_2_GRAPH_3, ATTR_XAXIS_OFFSET, startsFr * (double)N);
				//obtinere spectru
				vf1 = (double *) calloc(N/2, sizeof(double));
				convertedSpectrum1 = (double *) calloc(N/2, sizeof(double));
				free(unit1);
				unit1 = (char *) calloc(32, sizeof(char));
				AutoPowerSpectrum(v1, N, 1./sampleRate, vf1, &df1);
				PowerFrequencyEstimate(vf1, N/2, -1, windowConst, df1, 7, &freqPeak3, &powPeak3);
				SpectrumUnitConversion(vf1, N/2, SPECTRUM_POWER, SCALING_MODE_LINEAR, DISPLAY_UNIT_VRMS, df1, windowConst, convertedSpectrum1, unit1);
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_6, -1, VAL_IMMEDIATE_DRAW);
				PlotWaveform(frecPanel, PANEL_2_GRAPH_6, convertedSpectrum1, N/2 ,VAL_DOUBLE, 1.0, 0.0, 0.0, df1, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID,  VAL_CONNECTED_POINTS, VAL_DK_GREEN);
				SetCtrlVal(frecPanel, PANEL_2_FR_PEAK_NR_3, freqPeak3);
				SetCtrlVal(frecPanel, PANEL_2_PW_PEAK_NR_3, powPeak3);
				//obtinere spectru
				vf2 = (double *) calloc(N, sizeof(double));
				convertedSpectrum2 = (double *) calloc(N, sizeof(double));
				free(unit2);
				unit2 = (char *) calloc(32, sizeof(char));
				AutoPowerSpectrum(v2, N, 1./sampleRate, vf2, &df2);
				PowerFrequencyEstimate(vf2, N, -1, windowConst, df2, 7, &freqPeak1, &powPeak1);
				SpectrumUnitConversion(vf2, N, SPECTRUM_POWER, SCALING_MODE_LINEAR, DISPLAY_UNIT_VRMS, df2, windowConst, convertedSpectrum2, unit2);
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_4, -1, VAL_IMMEDIATE_DRAW);
				PlotWaveform(frecPanel, PANEL_2_GRAPH_4, convertedSpectrum2, N/2 ,VAL_DOUBLE, 1.0, 0.0, 0.0, df2, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID,  VAL_CONNECTED_POINTS, VAL_DK_GREEN);
				SetCtrlVal(frecPanel, PANEL_2_FR_PEAK_NR_1, freqPeak1);
				SetCtrlVal(frecPanel, PANEL_2_PW_PEAK_NR_1, powPeak1);
				//obtinere spectru
				vf3 = (double *) calloc(N, sizeof(double));
				convertedSpectrum3 = (double *) calloc(N, sizeof(double));
				free(unit3);
				unit3 = (char *) calloc(32, sizeof(char));
				AutoPowerSpectrum(v3, N, 1./sampleRate, vf3, &df3);
				PowerFrequencyEstimate(vf3, N, -1, windowConst, df3, 7, &freqPeak2, &powPeak2);
				SpectrumUnitConversion(vf3, N, SPECTRUM_POWER, SCALING_MODE_LINEAR, DISPLAY_UNIT_VRMS, df3, windowConst, convertedSpectrum3, unit3);
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_5, -1, VAL_IMMEDIATE_DRAW);
				PlotWaveform(frecPanel, PANEL_2_GRAPH_5, convertedSpectrum3, N/2 ,VAL_DOUBLE, 1.0, 0.0, 0.0, df3, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID,  VAL_CONNECTED_POINTS, VAL_DK_GREEN);
				SetCtrlVal(frecPanel, PANEL_2_FR_PEAK_NR_2, freqPeak2);
				SetCtrlVal(frecPanel, PANEL_2_PW_PEAK_NR_2, powPeak2);
			}
			break;
	}
	return 0;
}

int CVICALLBACK OnNextFr (int panel, int control, int event,
						  void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			WindowConst windowConst;
			if(stopsFr < npoints/N + 1)
			{
				startsFr++;
				stopsFr++;
				SetCtrlAttribute(frecPanel, PANEL_2_Starts_Num, ATTR_CTRL_VAL, startsFr);
				SetCtrlAttribute(frecPanel, PANEL_2_Stops_Num, ATTR_CTRL_VAL, stopsFr);
				if(npoints - N * startsFr >= N)
				{
					Copy1D(waveData + startsFr * N,  N, v1);
					Copy1D(waveData + startsFr * N,  N, v2);
					Copy1D(waveData + startsFr * N,  N, v3);
				}
				else
				{//zero-padding
					Copy1D(zeros,  N, v1);
					Copy1D(zeros,  N, v2);
					Copy1D(zeros,  N, v3);
					Copy1D(waveData + startsFr * N, npoints - N * startsFr, v1);
					Copy1D(waveData + startsFr * N, npoints - N * startsFr, v2);
					Copy1D(waveData + startsFr * N, npoints - N * startsFr, v3);
				}
				//aici aplici filtru pentru v2 si pentru v3
				
				//aici aplici filtru
				//BUTTERWORTH pt 1/4 din f. inalte
				Bw_HPF(v2, N, sampleRate, sampleRate/4, 5, v2);
				
				//aici aplici filtru 
				//CEBYSHEV de ordin 5 pentru 1/4 din f. joase 
				Ch_LPF(v3, N, sampleRate, sampleRate/4, 0.1, 5, v3);
					
				switch(tipFereastra)
				{
					case hamming:
						ScaledWindow(v1, N, HAMMING, &windowConst);
						ScaledWindow(v2, N, HAMMING, &windowConst);
						ScaledWindow(v3, N, HAMMING, &windowConst);
						break;
					case hanning:
						ScaledWindow(v1, N, HANNING, &windowConst);
						ScaledWindow(v2, N, HANNING, &windowConst);
						ScaledWindow(v3, N, HANNING, &windowConst);
						break;
				}
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_1, -1, VAL_IMMEDIATE_DRAW);
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_2, -1, VAL_IMMEDIATE_DRAW);
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_3, -1, VAL_IMMEDIATE_DRAW);
				PlotY(frecPanel, PANEL_2_GRAPH_1, v1, N, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_GRAY);
				PlotY(frecPanel, PANEL_2_GRAPH_2, v2, N, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_GRAY);
				PlotY(frecPanel, PANEL_2_GRAPH_3, v3, N, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_GRAY);
				SetCtrlAttribute (frecPanel, PANEL_2_GRAPH_1, ATTR_XAXIS_OFFSET, startsFr * (double)N);
				SetCtrlAttribute (frecPanel, PANEL_2_GRAPH_2, ATTR_XAXIS_OFFSET, startsFr * (double)N);
				SetCtrlAttribute (frecPanel, PANEL_2_GRAPH_3, ATTR_XAXIS_OFFSET, startsFr * (double)N);
				//obtinere spectru
				vf1 = (double *) calloc(N/2, sizeof(double));
				convertedSpectrum1 = (double *) calloc(N/2, sizeof(double));
				free(unit1);
				unit1 = (char *) calloc(32, sizeof(char));
				AutoPowerSpectrum(v1, N, 1./sampleRate, vf1, &df1);
				PowerFrequencyEstimate(vf1, N/2, -1, windowConst, df1, 7, &freqPeak3, &powPeak3);
				SpectrumUnitConversion(vf1, N/2, SPECTRUM_POWER, SCALING_MODE_LINEAR, DISPLAY_UNIT_VRMS, df1, windowConst, convertedSpectrum1, unit1);
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_6, -1, VAL_IMMEDIATE_DRAW);
				PlotWaveform(frecPanel, PANEL_2_GRAPH_6, convertedSpectrum1, N/2 ,VAL_DOUBLE, 1.0, 0.0, 0.0, df1, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID,  VAL_CONNECTED_POINTS, VAL_DK_GREEN);
				SetCtrlVal(frecPanel, PANEL_2_FR_PEAK_NR_3, freqPeak3);
				SetCtrlVal(frecPanel, PANEL_2_PW_PEAK_NR_3, powPeak3);
				//obtinere spectru
				vf2 = (double *) calloc(N, sizeof(double));
				convertedSpectrum2 = (double *) calloc(N, sizeof(double));
				free(unit2);
				unit2 = (char *) calloc(32, sizeof(char));
				AutoPowerSpectrum(v2, N, 1./sampleRate, vf2, &df2);
				PowerFrequencyEstimate(vf2, N, -1, windowConst, df2, 7, &freqPeak1, &powPeak1);
				SpectrumUnitConversion(vf2, N, SPECTRUM_POWER, SCALING_MODE_LINEAR, DISPLAY_UNIT_VRMS, df2, windowConst, convertedSpectrum2, unit2);
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_4, -1, VAL_IMMEDIATE_DRAW);
				PlotWaveform(frecPanel, PANEL_2_GRAPH_4, convertedSpectrum2, N/2 ,VAL_DOUBLE, 1.0, 0.0, 0.0, df2, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID,  VAL_CONNECTED_POINTS, VAL_DK_GREEN);
				SetCtrlVal(frecPanel, PANEL_2_FR_PEAK_NR_1, freqPeak1);
				SetCtrlVal(frecPanel, PANEL_2_PW_PEAK_NR_1, powPeak1);
				//obtinere spectru
				vf3 = (double *) calloc(N, sizeof(double));
				convertedSpectrum3 = (double *) calloc(N, sizeof(double));
				free(unit3);
				unit3 = (char *) calloc(32, sizeof(char));
				AutoPowerSpectrum(v3, N, 1./sampleRate, vf3, &df3);
				PowerFrequencyEstimate(vf3, N, -1, windowConst, df3, 7, &freqPeak2, &powPeak2);
				SpectrumUnitConversion(vf3, N, SPECTRUM_POWER, SCALING_MODE_LINEAR, DISPLAY_UNIT_VRMS, df3, windowConst, convertedSpectrum3, unit3);
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_5, -1, VAL_IMMEDIATE_DRAW);
				PlotWaveform(frecPanel, PANEL_2_GRAPH_5, convertedSpectrum3, N/2 ,VAL_DOUBLE, 1.0, 0.0, 0.0, df3, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID,  VAL_CONNECTED_POINTS, VAL_DK_GREEN);
				SetCtrlVal(frecPanel, PANEL_2_FR_PEAK_NR_2, freqPeak2);
				SetCtrlVal(frecPanel, PANEL_2_PW_PEAK_NR_2, powPeak2);
			}
			break;
	}
	return 0;
}



int CVICALLBACK OnN (int panel, int control, int event,
					 void *callbackData, int eventData1, int eventData2)
{
	switch (event)
	{
		case EVENT_COMMIT:
			GetCtrlVal(frecPanel, PANEL_2_RING, &N);
			startsFr = 0;
			stopsFr = 1;
			SetCtrlAttribute(frecPanel, PANEL_2_Starts_Num, ATTR_CTRL_VAL, startsFr);
			SetCtrlAttribute(frecPanel, PANEL_2_Stops_Num, ATTR_CTRL_VAL, stopsFr);
			free(v1);
			free(v2);
			free(v3);
			v1 = (double *) calloc(N, sizeof(double));
			v2 = (double *) calloc(N, sizeof(double));
			v3 = (double *) calloc(N, sizeof(double));
			Copy1D(waveData + startsFr * N,  N, v1);
			Copy1D(waveData + startsFr * N,  N, v2);
			Copy1D(waveData + startsFr * N,  N, v3);
			//aici aplici filtru pentru v2 si pentru v3
			
			//aici aplici filtru
			//BUTTERWORTH pt 1/4 din f. inalte
			Bw_HPF(v2, N, sampleRate, sampleRate/4, 5, v2);
			
			//aici aplici filtru 
			//CEBYSHEV de ordin 5 pentru 1/4 din f. joase 
			Ch_LPF(v3, N, sampleRate, sampleRate/4, 0.1, 5, v3);
			
			//plot
			WindowConst windowConst;
			switch(tipFereastra)
				{
					case hamming:
						ScaledWindow(v1, N, HAMMING, &windowConst);
						ScaledWindow(v2, N, HAMMING, &windowConst);
						ScaledWindow(v3, N, HAMMING, &windowConst);
						break;
					case hanning:
						ScaledWindow(v1, N, HANNING, &windowConst);
						ScaledWindow(v2, N, HANNING, &windowConst);
						ScaledWindow(v3, N, HANNING, &windowConst);
						break;
				}
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_1, -1, VAL_IMMEDIATE_DRAW);
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_2, -1, VAL_IMMEDIATE_DRAW);
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_3, -1, VAL_IMMEDIATE_DRAW);
				PlotY(frecPanel, PANEL_2_GRAPH_1, v1, N, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_GRAY);
				PlotY(frecPanel, PANEL_2_GRAPH_2, v2, N, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_GRAY);
				PlotY(frecPanel, PANEL_2_GRAPH_3, v3, N, VAL_DOUBLE, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID, VAL_CONNECTED_POINTS, VAL_GRAY);
				SetCtrlAttribute (frecPanel, PANEL_2_GRAPH_1, ATTR_XAXIS_OFFSET, startsFr * (double)N);
				SetCtrlAttribute (frecPanel, PANEL_2_GRAPH_2, ATTR_XAXIS_OFFSET, startsFr * (double)N);
				SetCtrlAttribute (frecPanel, PANEL_2_GRAPH_3, ATTR_XAXIS_OFFSET, startsFr * (double)N);
				//obtinere spectru
				vf1 = (double *) calloc(N/2, sizeof(double));
				convertedSpectrum1 = (double *) calloc(N/2, sizeof(double));
				free(unit1);
				unit1 = (char *) calloc(32, sizeof(char));
				AutoPowerSpectrum(v1, N, 1./sampleRate, vf1, &df1);
				PowerFrequencyEstimate(vf1, N/2, -1, windowConst, df1, 7, &freqPeak3, &powPeak3);
				SpectrumUnitConversion(vf1, N/2, SPECTRUM_POWER, SCALING_MODE_LINEAR, DISPLAY_UNIT_VRMS, df1, windowConst, convertedSpectrum1, unit1);
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_6, -1, VAL_IMMEDIATE_DRAW);
				PlotWaveform(frecPanel, PANEL_2_GRAPH_6, convertedSpectrum1, N/2 ,VAL_DOUBLE, 1.0, 0.0, 0.0, df1, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID,  VAL_CONNECTED_POINTS, VAL_DK_GREEN);
				SetCtrlVal(frecPanel, PANEL_2_FR_PEAK_NR_3, freqPeak3);
				SetCtrlVal(frecPanel, PANEL_2_PW_PEAK_NR_3, powPeak3);
				//obtinere spectru
				vf2 = (double *) calloc(N, sizeof(double));
				convertedSpectrum2 = (double *) calloc(N, sizeof(double));
				free(unit2);
				unit2 = (char *) calloc(32, sizeof(char));
				AutoPowerSpectrum(v2, N, 1./sampleRate, vf2, &df2);
				PowerFrequencyEstimate(vf2, N, -1, windowConst, df2, 7, &freqPeak1, &powPeak1);
				SpectrumUnitConversion(vf2, N, SPECTRUM_POWER, SCALING_MODE_LINEAR, DISPLAY_UNIT_VRMS, df2, windowConst, convertedSpectrum2, unit2);
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_4, -1, VAL_IMMEDIATE_DRAW);
				PlotWaveform(frecPanel, PANEL_2_GRAPH_4, convertedSpectrum2, N/2 ,VAL_DOUBLE, 1.0, 0.0, 0.0, df2, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID,  VAL_CONNECTED_POINTS, VAL_DK_GREEN);
				SetCtrlVal(frecPanel, PANEL_2_FR_PEAK_NR_1, freqPeak1);
				SetCtrlVal(frecPanel, PANEL_2_PW_PEAK_NR_1, powPeak1);
				//obtinere spectru
				vf3 = (double *) calloc(N, sizeof(double));
				convertedSpectrum3 = (double *) calloc(N, sizeof(double));
				free(unit3);
				unit3 = (char *) calloc(32, sizeof(char));
				AutoPowerSpectrum(v3, N, 1./sampleRate, vf3, &df3);
				PowerFrequencyEstimate(vf3, N, -1, windowConst, df3, 7, &freqPeak2, &powPeak2);
				SpectrumUnitConversion(vf3, N, SPECTRUM_POWER, SCALING_MODE_LINEAR, DISPLAY_UNIT_VRMS, df3, windowConst, convertedSpectrum3, unit3);
				DeleteGraphPlot(frecPanel, PANEL_2_GRAPH_5, -1, VAL_IMMEDIATE_DRAW);
				PlotWaveform(frecPanel, PANEL_2_GRAPH_5, convertedSpectrum3, N/2 ,VAL_DOUBLE, 1.0, 0.0, 0.0, df3, VAL_THIN_LINE, VAL_EMPTY_SQUARE, VAL_SOLID,  VAL_CONNECTED_POINTS, VAL_DK_GREEN);
				SetCtrlVal(frecPanel, PANEL_2_FR_PEAK_NR_2, freqPeak2);
				SetCtrlVal(frecPanel, PANEL_2_PW_PEAK_NR_2, powPeak2);
				
			break;
	}
	return 0;
}
