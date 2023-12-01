#include <iostream>
#include <vector>

using namespace std;
// Design a Tic-tac-toe game that is played between two players on a 3 x 3 grid.
int main()
{
    vector<vector<int>> v;
    vector<int> initial;

    initial.push_back(0);
    initial.push_back(0);
    initial.push_back(2);
    initial.push_back(1);
    initial.push_back(0);
    initial.push_back(0);
    initial.push_back(0);
    initial.push_back(0);
    initial.push_back(0);

    for (int i = 1; i <= 7; i++)
    {
        v.push_back(initial);
    }

    for (auto x : v)
    {
        cout << x[0] << " " << x[1] << " " << x[2] << " " << x[3] << " " << x[4] << " " << x[5] << " " << x[6] << endl;
    }
}