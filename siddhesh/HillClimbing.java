package practice;

import java.util.ArrayList;
import java.util.Collection;

class Node{
    int box[][];
    double distance;

    Node(int[][] box, double distance) {
        this.box = box;
        this.distance = distance;
    }
}
public class HillClimbing {

    static ArrayList<Node> allPossibleMoves = new ArrayList<>();
    static ArrayList<Node> closed = new ArrayList<>();

    //for taking the initial board from the user
    public static int board[][] = {{1,2,3},
                                   {5,6,0},
                                   {7,8,4}};

    //for taking the goal board from the user
    static int goalBoard[][] = {{1,2,0},
                                {5,6,3},
                                {7,8,4}};


    public static void main(String args[]) {
        System.out.println("-----------------------------------------The Board Game-------------------------------------------------------");

        System.out.println("----------------------The Initial Board::");
        printBoard(board);
        double distance = calculateEuclidean(board, goalBoard);
        Node initialBoard = new Node(board, distance);
        closed.add(initialBoard);

        System.out.println("------------------------The Goal Board::");
        printBoard(goalBoard);
        System.out.println("-------------------------------------------------------------------------------------------------------------");



        //calling the simpleHillClimbing()
        SHC(board, goalBoard, allPossibleMoves, closed);
    }


    private static void SHC(int[][] board2, int[][] goalBoard2, ArrayList<Node> allPossibleMoves2, ArrayList<Node> closed2) {
        
        while(true) {
 //getting the current board position
 Node current = closed2.get(closed2.size()-1);
 System.out.println("The size of the closed2::"+closed2.size());
 //getting the current board position
 int[][] currentBoard = current.box;
 System.out.println("-----------------The updated board is::");
 printBoard(currentBoard);
 //getting the current distance
 double currentDistance = current.distance;

            ///if we reach the goal print it and return 
            if(currentDistance == 0) {
                System.out.println("---------------------------------------Goal Reached------------------------------------");
                printBoard(currentBoard);
                System.out.println("--------------------------------------------------------------------------------------");
                break;
            }


            //generating all the possble moves
            allPossibleMoves2 = generateMoves(currentBoard,goalBoard2, allPossibleMoves2, closed2);

            //printing all the generated moves
            // System.out.println("-------------All the generated moves with distance");
            // for(int i=0; i<allPossibleMoves.size(); i++) {
            //     printBoard(allPossibleMoves.get(i).box);
            //     System.out.println("The distance is : "+allPossibleMoves.get(i).distance);
                
            // }

            //getting the best node from this
            Node bestNode = getBestMove(allPossibleMoves2, current);

            //printing the best move selected
            System.out.println("=============================================");
            System.out.println("The Best Move Selected is : ");
            printBoard(bestNode.box);
            System.out.println("The distance is : "+bestNode.distance);
            System.out.println("=============================================");

            //adding to the closedlist
           //checking if the best move is better thajn the current board position
           
            //if the best move is better than the current board position then add it to the closed list
            closed2.add(bestNode);
            System.out.println("added to closed");
            allPossibleMoves2.remove(bestNode);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //  }else {
            //     //if the best move is not better than the current board position then break the loop
            //     System.out.println("------------------------------------The Goal Board Position is Not Reached------------------------------------");
            //     printBoard(currentBoard);
            //     System.out.println("------------------------------------------------------------------------------------------------------");
                
            // }
        }
    }


    private static Node getBestMove(ArrayList<Node> allPossibleMoves2, Node current) {
        int min=0;
        for(int i=0; i<allPossibleMoves2.size(); i++) {
            if(allPossibleMoves2.get(i).distance < current.distance) {
                min=i;
            }
        }
        return allPossibleMoves2.get(min);
    }


    private static ArrayList<Node> generateMoves(int[][] currentBoard, int[][] goalBoard2, ArrayList<Node> allPossibleMoves2, ArrayList<Node> closed2) {
        //getting the position of the blank
        int x=0;
        int y=0;
        for(int i=0; i<3;i++) {
            for(int j=0; j<3; j++) {
                if(currentBoard[i][j] == 0) {
                    x = i;
                    y = j;
                    break;
                }
            }
        }


        //defining 2 arrays to store the steps to move the blank
        //and steps are up down left and right
        int xMove[] = {-1,1,0,0};
        int yMove[] = {0,0,-1, 1};

        for(int step=0; step<4; step++) {
            //placing the currentBoard in newBoard 
            int newBoard[][] = new int[3][3];
            for(int i=0; i<3; i++) {
                for(int j=0;j<3; j++) {
                    newBoard[i][j] = currentBoard[i][j];
                }
            }
            if(x+xMove[step] >=0 && x+xMove[step]<3 && y+yMove[step] >= 0 && y+yMove[step] < 3) {
                int temp = newBoard[x][y];
                newBoard[x][y] = newBoard[x+xMove[step]][y+yMove[step]];
                newBoard[x+xMove[step]][y+yMove[step]] = temp;
                double distance = calculateEuclidean(newBoard, goalBoard);
                allPossibleMoves2.add(new Node(newBoard, distance));
            }
        }
        return allPossibleMoves2;
    }


    private static double calculateEuclidean(int[][] board2, int[][] goalBoard2) {
        double dist=0;

        for(int i=0; i<board2.length; i++) {
            for(int j=0; j<board2[0].length; j++) {
                dist += Math.pow((board2[i][j]-goalBoard2[i][j]), 2);
            }
        }
        dist = Math.sqrt(dist);
        return dist;
    }


    private static void printBoard(int[][] board2) {
        System.out.println();

        for(int i=0;i<3; i++) {
            for(int j=0; j<3; j++) {
                if(board2[i][j] == 0) {
                    System.out.print(" - ");
                }else {
                    System.out.print(" "+board2[i][j]+" ");

                }
                if(j == 2){
                    System.out.println();
                }else{
                    System.out.print("|");
                }
            }
        }
    }
    
}
