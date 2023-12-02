import java.util.*;

class Node {
    int[][] parentBoard;
    int[][] initialBoardPos;
    int ga;
    int fa;
    Node next;

    Node(int[][] initial, int[][] mvpos, int ftemp, int gtemp) {
        parentBoard = new int[3][3];
        initialBoardPos = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                parentBoard[i][j] = initial[i][j];
                initialBoardPos[i][j] = mvpos[i][j];
            }
        }
        ga = gtemp;
        fa = ftemp;
        next = null;
    }

    
}

public class Temp {
    //  static Node open = null;

    //creating the ArrayList for storing the board whose all the possible moves have been generated
    static ArrayList<Node> closed = new ArrayList<>();

    static int tempBoard[] = new int[9];
    static Scanner scan = new Scanner(System.in);
    static int board[][] = new int[3][3];
    static int goalBoard[][] = new int[3][3];
    static ArrayList<int[][]> allBoardPos = new ArrayList<>();

    //creating and returning the node
    static Node createNode(int[][] parent, int[][] initialBoard, int ftemp, int gtemp) {
        return new Node(parent, initialBoard, ftemp, gtemp);
    }

    //inserting the node in the open list
    public static Node insertNode(Node node,Node initialNode) {
        //inserting the Node in the open list
         Node temp = node;
        if(node == null) {
            System.out.println("The node is null that is why adding to start");
            node = initialNode;
        }else if(node.fa > initialNode.fa) {
            initialNode.next = node;
            node = initialNode;
        }
        else {
           
            while(temp.next != null && temp.next.fa < initialNode.fa) {
                temp = temp.next;
            }
            temp.next = initialNode;
        }

       return node;

    }

    
    public static void main(String[] args) {

        System.out.println("-----------------The Board Game-----------------------");
        System.out.println("Please enter the position where you want the gap to be: ");
        int gap = scan.nextInt();


        //iterate of the map and fill the board with the values from the map and the gap
        //print the board
        System.out.println("Enter the next value for the position : ");
         for(int i=0; i<9; i++) {
            
            if(i == gap){
                System.out.print(i+" : 0");
                tempBoard[i] = 0;

            }else {
                System.out.print(i+" : ");
                tempBoard[i] = scan.nextInt();
            }
         }

         //filling the board with the values from the tempBoard
         int k = 0;
         for(int i=0; i<3; i++) {
             for(int j=0; j<3; j++) {
                 board[i][j] = tempBoard[k];
                 k++;
             }
         }

         System.out.println("------------------------------------The Original Board Position is ------------------------------------");

        printBoardMove(board);

        System.out.println("-------------------------------------------------------------------------------------------------------");

        System.out.println("Please enter the position where you want the gap to be: ");
        gap = scan.nextInt();

        //iterate of the map and fill the board with the values from the map and the gap
        //print the board
        System.out.println("Enter the next value for the position : ");
         for(int i=0; i<9; i++) {
            
            if(i == gap){
                System.out.print(i+" : 0");
                tempBoard[i] = 0;

            }else {
                System.out.print(i+" : ");
                tempBoard[i] = scan.nextInt();
            }
         }

         //filling the board with the values from the tempBoard
             k = 0;
            for(int i=0; i<3; i++) {
                for(int j=0; j<3; j++) {
                    goalBoard[i][j] = tempBoard[k];
                    k++;
                }
            }

        
            System.out.println("------------------------------------The Goal Board Position is ------------------------------------");
            printBoardMove(goalBoard);


            //First adding the initial board position to the open list
            int initialParent[][] = {{0,0,0},{0,0,0},{0,0,0}};
            int ga=0;
            Node open = new Node(initialParent, board, calculateDistance(board, goalBoard)+ga, ga);

            while(open != null) {
                Node temp = open;
                open = open.next;
                
                //adding it to the closed list
                closed.add(temp);

                //checking if the board is the goal board
                if(checkIfGoalBoard(temp.initialBoardPos)) {
                    System.out.println("The goal board is found");
                    printBoardMove(temp.initialBoardPos);
                    break;
                }

                //generating all the possible moves for the board
                allBoardPos = generateAllPossibleMoves(temp.initialBoardPos);
                
                //inserting all the possible moves in the open list
                open = heuristic(open, closed, allBoardPos, temp.ga+1, temp.initialBoardPos, goalBoard);
            }

            //adding the initial node to the open list
           
             
               // printOpenList(open);
        //     allBoardPos = generateAllPossibleMoves(board);
        // //printing the all possible moves
        // System.out.println("All Possible Moves are : ");
        // for(int i=0; i<allBoardPos.size(); i++) {
        //     printBoardMove(allBoardPos.get(i));
        // }

        //Displaying the closed list 
        System.out.println("The closed list is : ");
        for(int i=0; i<closed.size(); i++) {
            printBoardMove(closed.get(i).initialBoardPos);
        }

    }

    private static Node heuristic(Node open, ArrayList<Node> closed2, ArrayList<int[][]> allBoardPos2, int ga,
            int[][] initialBoardPos, int[][] goalBoard2) {

                for(int[][] tempBoard: allBoardPos2){
                    int tempf = ga + calculateDistance(tempBoard, goalBoard2);
                    Node temp = open;

                    System.out.println("The board that we are looking at is : ");
                    System.out.println("The f value is : "+tempf+" The g value is : "+ga);
                    for(int i=0; i<3; i++) {
                        for(int j=0; j<3; j++) {
                            System.out.print(tempBoard[i][j]+" ");
                        }
                        System.out.println();
                    }


                    boolean check = false;
                    //check if the open open and close are empty
                    if(temp == null) {
                        open = insertNode(open, createNode(initialBoardPos, tempBoard, tempf, ga));
                        System.out.println(open.fa);
                    }else {
                        while(temp != null) {
                            if(Arrays.deepEquals(tempBoard, temp.initialBoardPos)) {
                                if(temp.fa > tempf) {
                                    temp.fa = tempf;
                                    temp.ga = ga;
                                    temp.parentBoard = initialBoardPos;
                                }
                                check = true;
                                break;
                            }
                            temp = temp.next;
                        }

                        //checking for the closed list
                        if(checkIfBoardInClosedList(tempBoard)) {
                            check = true;
                        }

                        if(!check) {
                            open = insertNode(open, createNode(initialBoardPos, tempBoard, tempf, ga));
                             System.out.println("The Parent is:");
                        printBoardMove(open.parentBoard);
                        System.out.println("The Child is:");
                        printBoardMove(open.initialBoardPos);
                        }else {
                            check = false;
                        }
                    }

                }
        return open;
    }

    private static boolean checkIfBoardInClosedList(int[][] is) {
        for(int i=0; i<closed.size(); i++) {
            if(Arrays.deepEquals(closed.get(i).initialBoardPos, is)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkIfGoalBoard(int[][] initialBoardPos) {
        if(Arrays.deepEquals(initialBoardPos, goalBoard)) {
            return true;
        }
        return false;
    }

    //print open list
    public static void printOpenList(Node open) {
        Node temp = open;
        if(temp == null) {
            System.out.println("The open list is empty");
            return;
        }
        while(temp != null) {
            printBoardMove(temp.initialBoardPos);
            temp = temp.next;
        }
    }


private static boolean checkIfpresentInOpenList(int[][] board,Node temp, Node open) {
    //calculating the f(n) and g(n) for the board and checking if the board is present in the open list
    
    int gtemp = temp.ga;
    int ftemp = gtemp + (int)calculateDistance(board, goalBoard);
    Node newNode = createNode(temp.initialBoardPos, board, ftemp, gtemp);

    //checking if the board is present in the open list
    Node tempOpen = open;
    while(tempOpen != null) {
        if(Arrays.deepEquals(tempOpen.initialBoardPos, board)) {
            //checking if the f(n) of the board is less than the f(n) of the board in the open list
            if(ftemp < tempOpen.next.fa) {
                //removing the node from the open list
                Node temp1 = tempOpen.next;
                tempOpen.next = tempOpen.next.next;
                temp1.next = null;
                //adding the new node to the open list
                 insertNode(open, newNode);
            }
            return true;
            
        }
        tempOpen = tempOpen.next;

    }
    return false;
}
    //for calculating the euclidean distance
    private static int calculateDistance(int[][] board, int[][] a) {
        //calulating the euclidean distance and returing it
        int dist = 0;
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                dist += Math.pow((board[i][j] - a[i][j]), 2);
            }
        }
        dist = (int) Math.sqrt(dist);
        // System.out.println("The distance is : "+dist);
        return dist;
    }




    private static void printBoardMove(int[][] board) {
        //print the 8-puzzle board
        //design it like board
        System.out.println();
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(board[i][j] == 0) {
                    System.out.print(" - ");
                }else {
                    System.out.print(" "+board[i][j]+" ");
                }
                if(j == 2) {
                    System.out.println();
                }else {
                    System.out.print("|");
                }
            }
        }
        
    }

     //taking the origianl board as an input and generating all the possible boards
        //from it and storing it in the Arraylist named allBoardPos

        


    private static ArrayList<int[][]> generateAllPossibleMoves(int[][] board) {

        /*
         * 1. find the gap position
         * 2. find the possible moves
         * 3. generate the new board
         * 4. add the new board to the arraylist
         * 5. return the arraylist
         * 
         */

        int gapPos[] = findGapPosition(board);
        int gapRow = gapPos[0];
        int gapCol = gapPos[1];

        int ti[]={0,-1,1,0}; // both ti[] and tj[] will be used to check left,right,up and down from current position
        int tj[]={1,0,0,-1}; // both ti[] and tj[] will be used to check left,right,up and down from current position

        ArrayList<int[][]> allPossibleMoves = new ArrayList<>();

        for(int i=0; i<4; i++) {
            int[][] newBoard = new int[3][3];
            for(int j=0; j<3; j++) {
                for(int k=0; k<3; k++) {
                    newBoard[j][k] = board[j][k];
                }
            }

            if(gapRow+ti[i] >= 0 && gapRow+ti[i] < 3 && gapCol+tj[i] >= 0 && gapCol+tj[i] < 3) {
                int temp = newBoard[gapRow+ti[i]][gapCol+tj[i]];
                newBoard[gapRow+ti[i]][gapCol+tj[i]] = 0;
                newBoard[gapRow][gapCol] = temp;
                allPossibleMoves.add(newBoard);
            }
        }
    
        return allPossibleMoves;
    }


    private static int[] findGapPosition(int[][] board2) {
        // TODO Auto-generated method stub
        //find the gap position
        int gapPos[] = new int[2];
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(board2[i][j] == 0) {
                    gapPos[0] = i;
                    gapPos[1] = j;
                    return gapPos;
                }
            }
        }
        return null;
    }
}
