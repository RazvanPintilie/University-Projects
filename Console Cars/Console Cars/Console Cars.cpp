#include <iostream>
#include <windows.h>
#include <time.h>
#include <stdlib.h>
#include <string.h>

using namespace std;

char a[100][100];
int okk = 1, d = 400, r[100], q[100], score = 0, speed = 10;

int test(int i, int j)
{
    for (int p = 1; p <= 100; p++)
    {
        if ((a[i + 1][j] == 'X') || (a[i][j] == 'X') || (a[i][j + 1] == 'X') || (a[i][j - 1] == 'X') || (a[i - 1][j] == 'X') || (a[i - 2][j] == 'X') || (a[i - 2][j + 1] == 'X') || (a[i - 2][j - 1] == 'X'))
            return 1;
    }
    if ((j == 1) || (j == 11))
        return 1;

}

void built()
{
    for (int i = 1; i <= 16; i++)
        for (int j = 1; j <= 12; j++)
        {
            a[i][j] = ' ';
            a[i][1] = 'X';
            a[i][11] = 'X';
        }
}

void show(char a[100][100])
{
    cout << endl << endl << "     Score:" << score << endl << "      " << speed << " Km/h." << endl << endl;
    for (int i = 15; i >= 1; i--)
    {
        cout << "        ";
        for (int j = 1; j <= 11; j++)
            cout << a[i][j] << " ";
        cout << endl;
    }
    cout << endl;
}

void out()
{
    Sleep(500);
    system("cls");
    okk = 0;
    Beep(1100, 800);
    cout << endl << endl << "         You lost!" << endl << endl;
    cout << endl << "     Score:" << score << endl << endl;
}

void car(int i, int j)
{
    a[i][j] = 'M';
    a[i + 1][j] = 'A';
    a[i][j + 1] = 'M';
    a[i][j - 1] = 'M';
    a[i - 1][j] = 'M';
    a[i - 2][j] = 'M';
    a[i - 2][j + 1] = 'M';
    a[i - 2][j - 1] = 'M';
}

void steer_r(int i, int j)
{
    a[i][j - 1] = ' ';
    a[i - 1][j] = ' ';
    a[i - 2][j - 1] = ' ';
    a[i + 1][j] = ' ';
}

void steer_l(int i, int j)
{
    a[i][j + 1] = ' ';
    a[i - 1][j] = ' ';
    a[i + 1][j] = ' ';
    a[i - 2][j + 1] = ' ';
}

void movee()
{

    for (int p = 1; p <= 16; p++)
        for (int q = 2; q <= 10; q++)
            if (a[p][q] == 'X')
            {
                a[p][q] = ' ';
                a[p - 1][q] = 'X';
            }
    int t = 0;
    int bk = 1;
    for (int b = 15; b >= 11; b--)
        for (int c = 2; c <= 10; c++)
        {
            if (a[b][c] == 'X')
                bk = 0;
        }
    if (bk == 1)
        t = rand() % 10;
    if (t <= 10)
        a[15][t] = 'X';
}

void win()
{
    Sleep(500);
    system("cls");
    Beep(350, 1000);
    Beep(400, 700);
    cout << endl << endl << "         You won!" << endl << endl;
    cout << endl << "     Score:" << score << endl << endl;
}


int main()
{
    srand(time(0));
    char start;
    int i = 4, j = 6;
    built();
    car(i, j);
    cout << endl << "     Welcome to this awesome game!" << endl << "     To START, press 's', than 'ENTER', after this mesage disappear!";
    Sleep(4500);
    system("cls");
    do
    {
        cout << endl << "                  ";
        cin >> start;
    } while (start != 's');
    while (okk == 1)
    {
        if (d > 20)
        {
            d -= 2;
            speed++;
        }
        system("cls");
        show(a);
        Beep(400, 200);
        if (test(i, j) == 1)
            out();
        movee();
        Sleep(d);
        score++;
        if (score == 1000)
        {
            win();
            break;
        }
        if (GetAsyncKeyState(VK_RIGHT))
        {
            system("cls");
            steer_r(i, j);
            j++;
            if (test(i, j) == 1)
            {
                car(i, j);
                show(a);
                out();
                break;
            }
            car(i, j);
            show(a);
            Sleep(d);
        }
        else if (GetAsyncKeyState(VK_LEFT))
        {
            system("cls");
            steer_l(i, j);
            j--;
            if (test(i, j) == 1)
            {
                car(i, j);
                show(a);
                out();
                break;
            }
            car(i, j);
            show(a);
            Sleep(d);
        }
    }
    system("pause");
    return 0;
}
