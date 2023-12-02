package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class NonAI {

    //first taking the input for the x and 0 positions
    //calculating the score for this position
    //then taking the target
    //then generating all he possible positions of the target 
    //and calculating the score of each position
    private static int matrix[];
    private static int xCount;
    private static int yCount;
    private static int target;
    public static Scanner scan ;

    public static void main(String args[]) {
        //taking the input
        matrix = new int[9];
        
        scan = new Scanner(System.in);
        input();

        //writing function to calculate the score
        System.out.println("The score of the current board is :: " + calculate(matrix));

        //Taking the target 
        System.out.println("Enter the target player :: ");
        target = scan.nextInt();

        //generate all the possible moves the target can play
        List<int[]> moves = new ArrayList<>();
        moves = generateMoves(matrix, target);


        System.out.println("The generated moves are ::");
        for(int ans[]: moves) {
            System.out.println(Arrays.toString(ans));
        }

        System.out.println("The scores of all the generated matrices are ::");
        for(int move[]: moves) {
            System.out.println(Arrays.toString(move));
            System.out.println("The score :: " + calculate(move));
        }
    }

    private static List<int[]> generateMoves(int[] matrix2, int target) {
        List<int[]> movesMatrix = new ArrayList<>();
        for(int i=0; i<9; i++) {
            int temp[] = Arrays.copyOf(matrix2, 9);
            if(temp[i] == 0) {
                temp[i] = target;
                movesMatrix.add(temp);
            }
        }
        
        return movesMatrix;
    }

    private static int calculate(int matrix[]) {
        int score =0;
        int base=1;
        for(int i = matrix.length-1; i>=0; i--) {
            score += matrix[i] * base;
            base *= 3;

        }
        return score;
        
    }
    

    private static void input() {
        System.out.println("Enter the number of x in the current board position ::");
        int x = scan.nextInt();

        System.out.println("Enter the number of y in the current board poisition ::");
        int o = scan.nextInt();

        //accepting the x and filling the matrix
        while(x>0) {
            System.out.println("Enter the position of X :: ");
            int pos = scan.nextInt();

            if(matrix[pos] != 0) {
                System.out.println("Enter the positon again this position is taken");
                continue;
            }
            matrix[pos] = 1;
            x--;
        }

        //accepting the o and filling the matrix
        while(o>0) {
            System.out.println("Ente rthe positon for the O ::");
            int pos = scan.nextInt();

            if(matrix[pos] != 0) {
                System.out.println("Enter the position again this position is taken");
                continue;
            }

            matrix[pos] = 2;
            o--;
        }

        //printing the matrix
        System.out.println("The matrix representation is ::");
        System.out.println(Arrays.toString(matrix));

        
    }   
    
}
