#include <iostream>
#include <vector>
#include <cmath>

using namespace std;

class Node
{
public:
    Node *parent;
    vector<vector<int>> board;
    int hdash;
    int g_value;
    int f_value;

    Node *next;

    Node(vector<vector<int>> board, int hdash, int g_value)
    {
        this->board = board;
        this->hdash = hdash;
        this->g_value = g_value;
        this->f_value = g_value + hdash;
        this->parent = NULL;
        this->next = NULL;
    }
};

Node *openHead = NULL;
Node *closedHead = NULL;

vector<vector<int>> goalState = {{1, 2, 3},
                                 {8, 0, 4},
                                 {7, 6, 5}};

void addToOpen(Node *board)
{
    if (openHead == NULL || board->hdash <= openHead->hdash)
    {
        board->next = openHead;
        openHead = board;
        return;
    }

    Node *temp = openHead;
    while (temp->next != NULL && temp->next->hdash < board->hdash)
    {
        temp = temp->next;
    }

    board->next = temp->next;
    temp->next = board;
}

Node *popOpen()
{
    Node *temp = openHead;
    openHead = openHead->next;
    return temp;
}

int calculateEuclideanDistance(vector<vector<int>> board, vector<vector<int>> goal)
{
    int dist = 0;
    int misplacedTiles = 0;
    int i, j;
    for (i = 0; i < 3; i++)
    {
        for (j = 0; j < 3; j++)
        {
            if (board[i][j] != goal[i][j])
            {
                misplacedTiles++;
            }
        }
    }

    return misplacedTiles;
}

void pushToOpen(Node *parent, vector<vector<int>> board, int ed)
{
    Node *newBoard = new Node(board, ed, (parent->g_value + 1));
    newBoard->parent = parent;

    addToOpen(newBoard);
    return;
}

void addToClosed(Node *board)
{
    if (closedHead == NULL)
    {
        board->next = closedHead;
        closedHead = board;
        return;
    }

    Node *temp = closedHead;

    while (temp->next != NULL)
    {
        temp = temp->next;
    }

    temp->next = board;
    board->next = NULL;
    return;
}

void printBoard(const vector<vector<int>> &board)
{
    for (const auto &row : board)
    {
        cout << "| ";
        for (const auto &val : row)
        {
            cout << val << " | ";
        }
        cout << endl;
    }
}

void printLinkedList(Node *head)
{
    Node *temp = head;
    while (temp != NULL)
    {
        printBoard(temp->board);
        cout << "Euclidean distance is: " << temp->hdash;
        cout << endl;
        temp = temp->next;
    }
}

bool isValidMove(int x, int y)
{
    return (x >= 0 && x < 3 && y >= 0 && y < 3);
}

void generatePossibleMoves(Node *parentBoard, int gapRow, int gapCol)
{
    int dx[] = {-1, 1, 0, 0};
    int dy[] = {0, 0, -1, 1};

    int initialEd = calculateEuclideanDistance(parentBoard->board, goalState);

    cout << "The euclidean distance of initial board is: " << initialEd << endl;

    // Generate possible moves by swapping the gap with adjacent tiles.
    for (int k = 0; k < 4; k++)
    {
        int newRow = gapRow + dx[k];
        int newCol = gapCol + dy[k];

        if (isValidMove(newRow, newCol))
        {
            // Create a copy of the current board and swap the tiles to generate the new move.
            vector<vector<int>> newMove = parentBoard->board;
            swap(newMove[gapRow][gapCol], newMove[newRow][newCol]);
            int ed = calculateEuclideanDistance(newMove, goalState);
            pushToOpen(parentBoard, newMove, ed);
        }
    }
}

pair<int, int> findGap(vector<vector<int>> board)
{
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            if (board[i][j] == 0)
            {
                return make_pair(i, j);
            }
        }
    }
}

bool checkGoalState(vector<vector<int>> board)
{
    for (int i = 0; i < 3; i++)
    {
        for (int j = 0; j < 3; j++)
        {
            if (board[i][j] != goalState[i][j])
            {
                return false;
            }
        }
    }
    return true;
}

int main()
{

    vector<vector<int>> initialBoard;
    int gapRow, gapCol;

    cout << "Enter initial board position (3x3 grid):" << endl;
    for (int i = 0; i < 3; i++)
    {
        vector<int> row;
        for (int j = 0; j < 3; j++)
        {
            int val;
            cin >> val;
            row.push_back(val);
        }
        initialBoard.push_back(row);
    }

    cout << "Initial Board:" << endl;
    printBoard(initialBoard);

    int initialEuclidean = calculateEuclideanDistance(initialBoard, goalState);
    Node *initialBoardNode = new Node(initialBoard, initialEuclidean, 0);
    openHead = initialBoardNode;

    while (openHead != NULL)
    {
        Node *popOpenLinkedList = popOpen();

        if (checkGoalState(popOpenLinkedList->board))
        {
            addToClosed(popOpenLinkedList);
            break;
        }
        else
        {
            addToClosed(popOpenLinkedList);
            pair<int, int> newGap = findGap(popOpenLinkedList->board);
            gapRow = newGap.first;
            gapCol = newGap.second;
            generatePossibleMoves(popOpenLinkedList, gapRow, gapCol);
        }
    }

    cout << "Open linked list is: " << endl;
    printLinkedList(openHead);
    cout << "Closed linked list is: " << endl;
    printLinkedList(closedHead);

    return 0;
}