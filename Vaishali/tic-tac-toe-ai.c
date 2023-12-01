#include <stdio.h>
#include <math.h>
int draw = 0, turn;
char xo[3][3];

int rowScore(int r, int c)
{
    for (int i = 0; i < 3; i++)
    {
        if (turn == 1 && xo[r][i] == 'o')
            return 0;
        if (turn == 2 && xo[r][i] == 'x')
            return 0;
    }
    printf("row: %d ", 1);
    return 1;
}
int colScore(int r, int c)
{
    for (int i = 0; i < 3; i++)
    {
        if (turn == 1 && xo[i][c] == 'o')
            return 0;
        if (turn == 2 && xo[i][c] == 'x')
            return 0;
    }
    printf("col: %d ", 1);
    return 1;
}
int diaScore(int r, int c)
{
    int x = 0;
    if (r == c)
    {
        if (turn == 1 && ((xo[0][0] != 'o' && xo[1][1] != 'o') && xo[2][2] != 'o'))
            x++;
        if (turn == 2 && ((xo[0][0] != 'x' && xo[1][1] != 'x') && xo[2][2] != 'x'))
            x++;
    }
    if ((r == 1 && c == 1) || (abs(r - c) == 2))
    {
        if (turn == 1 && ((xo[0][2] != 'o' && xo[1][1] != 'o') && xo[2][0] != 'o'))
            x++;
        if (turn == 2 && ((xo[0][2] != 'x' && xo[1][1] != 'x') && xo[2][0] != 'x'))
            x++;
    }
    printf("dia: %d ", x);
    return x;
}
int main()
{
    int n = 9, k = 0, c1, c2;
    int arr[9];
    printf("Enter board position: ");
    do
    {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                scanf(" %c", &xo[i][j]);

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (xo[i][j] == 'x')
                    arr[k++] = 1;
                else if (xo[i][j] == 'o')
                    arr[k++] = 2;
                else if (xo[i][j] == '-')
                    arr[k++] = 0;

        c1 = 0, c2 = 0;
        for (int i = 0; i < n; i++)
            if (arr[i] == 1)
                c1++;
            else if (arr[i] == 2)
                c2++;

        if (abs(c1 - c2) != 1 && abs(c1 - c2) != 0)
        {
            printf("Enter valid board position: %d %d", c1, c2);
        }
        k = 0;

    } while (abs(c1 - c2) != 1 && abs(c1 - c2) != 0);

    if (abs(c1 - c2) != 0)
    {
        turn = 1;
    }
    printf("vector: ");
    for (int i = 0; i < n; i++)
        printf("%d ", arr[i]);
    printf("\n");
    (c1 > c2) ? (turn = 2) : (turn = 1);

    int spaces = 9 - (c1 + c2);
    int m[spaces][9], pos = 0, win[3][3], res[spaces];
    int cnt = 0, final = 1;

    for (int i = 0; i < spaces; i++)
    {
        for (int j = 0; j < 9; j++)
        {
            if (arr[j] == 0)
                cnt++;
            if (final == cnt && arr[j] == 0)
            {
                pos = j;
                m[i][j] = turn;
            }
            else
                m[i][j] = arr[j];
        }

        cnt = 0;
        int r = pos / 3;
        int c = pos % 3;
        res[i] = rowScore(r, c) + colScore(r, c) + diaScore(r, c);
        printf("\n");
        final++;
    }

    printf("\nScores: ");
    for (int i = 0; i < spaces; i++)
        printf("%d ", res[i]);
}