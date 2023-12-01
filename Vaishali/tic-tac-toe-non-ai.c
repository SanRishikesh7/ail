#include <stdio.h>
#include <math.h>

int main()
{
    char xo[3][3];
    int arr[9];
    int n = 9, k = 0, c1 = 0, c2 = 0, p = 8, sum = 0;
    printf("Enter board position: ");
    do
    {
        c1 = 0, c2 = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                scanf(" %c", &xo[i][j]);

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (xo[i][j] == 'x')
                {
                    arr[k++] = 1;
                    c1++;
                }
                else if (xo[i][j] == 'o')
                {
                    arr[k++] = 2;
                    c2++;
                }
                else if (xo[i][j] == '-')
                    arr[k++] = 0;

        if (abs(c1 - c2) != 1)
        {
            printf("Enter valid xo position: ");
        }

    } while (abs(c1 - c2) != 1);

    printf("\nVector: ");
    for (int i = 0; i < 9; i++)
    {
        sum += arr[i] * pow(3, p--);
        printf("%d ", arr[i]);
    }
    printf("\nIndex: %d", sum);
}