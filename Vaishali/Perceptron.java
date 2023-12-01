import java.util.ArrayList;
import java.util.Scanner;

public class Perceptron {
    static int sgn(float net) {
        if (net > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of input vectors:");
        int n = sc.nextInt();
        System.out.println("Enter number of features:");
        int m = sc.nextInt();
        float X[][] = new float[n][m];
        float W[] = new float[m];
        System.out.println("Enter input vectors:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                X[i][j] = sc.nextFloat();
            }
        }
        System.out.println("Enter weight vector:");
        for (int j = 0; j < m; j++) {
            W[j] = sc.nextFloat();
        }
        int d[] = new int[n];

        System.out.println("Enter desired vector:");
        for (int j = 0; j < n; j++) {
            d[j] = sc.nextInt();
        }
        ArrayList<Double> errors = new ArrayList<Double>();

        float c = 1, net;
        int o;
        double E = Integer.MAX_VALUE;
        int cycle = 0;

        while (Math.abs(E) > 0) {

            cycle++;
            E = 0;

            for (int i = 0; i < n; i++) {

                net = 0;

                for (int j = 0; j < m; j++) {
                    net += W[j] * X[i][j];
                }

                o = sgn(net);

                E = E + Math.abs((d[i] - o));

                if (d[i] - o != 0) {
                    System.out.println("\nupdated weight vector:");
                    for (int j = 0; j < m; j++) {
                        W[j] = W[j] + c * (d[i] - o) * X[i][j];
                        System.out.print(W[j] + " ");
                    }
                    System.out.println("\n");
                }
            }
            System.out.println("error: " + E);
            errors.add(E);
        }
        System.out.println("\nnumber of cycles required: " + cycle);
        System.out.println("Error vector: " + errors);
        sc.close();
    }
}
// 1 -2 1.5 0
// 1 -0.5 -2 -1.5
// 0 1 -1 1.5

// 1 -1 0 0.5

// 1 -1 1