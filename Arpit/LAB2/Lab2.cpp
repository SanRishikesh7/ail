#include <iostream>
#include <vector>

using namespace std;

void printBoard(vector<vector<int>> &v)
{
    for (const auto &i : v)
    {
        cout << "| ";
        for (const auto &j : i)
        {
            cout << j << " | ";
        }
        cout << endl;
    }

    cout << endl;
}

void takeBoardPosition(vector<vector<int>> &v)
{
    cout << "Enter vector values: " << endl;
    int i, j;
    for (i = 0; i < 3; i++)
    {
        cout << "Enter " << i + 1 << "row elements: " << endl;
        vector<int> temp;
        for (j = 0; j < 3; j++)
        {
            int elem;
            cin >> elem;
            temp.push_back(elem);
        }
        v.push_back(temp);
    }
}

vector<int> createPossibleMoves(vector<vector<int>> &v)
{
    int i, j;
    vector<int> temp;
    for (i = 0; i < 3; i++)
    {

        for (j = 0; j < 3; j++)
        {
            if (((i == 0 && j == 0) || (i == 2 && j == 2) || (i == 0 && j == 2) || (i == 2 && j == 0)) && (v[i][j] == 0))
            {
                temp.push_back(i);
                temp.push_back(j);
                temp.push_back(2);
                return temp;
            }
            else if ((i == j) && (v[i][j] == 0))
            {
                temp.push_back(i);
                temp.push_back(j);
                temp.push_back(4);
                return temp;
            }
            else
            {
                temp.push_back(i);
                temp.push_back(j);
                temp.push_back(3);
                return temp;
            }
        }
    }
}

void printMoves(vector<int> &v, vector<vector<int>> &initial)
{
    int moves = v[2];
    cout << "Printing all possible moves:" << endl;
}

int main()
{
    vector<vector<int>> initial;

    takeBoardPosition(initial);
    printBoard(initial);

    vector<int> moves = createPossibleMoves(initial);

    cout << "Value 0 is at: ";
    for (auto i : moves)
    {
        cout << i << " ";
    }
    cout << endl;

    cout << "Printing all possible moves" << endl;
    printMoves(moves, initial);
}
