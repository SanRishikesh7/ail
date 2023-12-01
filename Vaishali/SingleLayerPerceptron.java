import java.util.ArrayList;

public class SingleLayerPerceptron {
    static double E = Double.MAX_VALUE;
    static double threshold = 0;
    static int maxCycles = 1000;
    static double c =0.1;

    static int sgn(double net) {
        if (net > 0) {
            return 1;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {

        ArrayList<Double> errors = new ArrayList<Double>();
        int P = 6;
        int n = 2;
        int R = 3;

        double T[][] = { { 0.1, 0.1, -1, 1 }, { 0.2, 0.1, -1, 1 }, { 0.5, 0.1, -1, 2 }, { 0.6, 0.1, -1, 2 },
                { 0.3, 0.3, -1, 3 }, { 0.4, 0.3, -1, 3 } };

        double W[][] = { { -0.1, 0.15, 0.2 }, { -0.2, 0.11, 0.17 }, { 0.17, 0.16, 0.11 } };
        double net = 0;

        int D[][] = { { 1, -1, -1 }, { -1, 1, -1 }, { -1, -1, 1 } };
        int O[] = new int[R];

        int o;
        int cycle = 0;

        while (E > threshold && cycle < maxCycles) {
            cycle++;
            E = 0;
            for (int i = 0; i < P; i++) {
                System.out.println("\npattern: " + i);
                for (int j = 0; j < R; j++) {
                    net = 0;
                    for (int k = 0; k < R; k++) {
                        net += T[i][k] * W[j][k];
                    }
                    o = sgn(net);
                    O[j] = o;
                }
                double temp = 0;
                for (int j = 0; j < R; j++) {
                    int cls = (int) T[i][n + 1];
                    temp += 0.5 * (D[cls - 1][j] - O[j]) * (D[cls - 1][j] - O[j]);
                    System.out.println("error " + j + ": " + (D[cls - 1][j] - O[j]));

                    if ((D[cls - 1][j] - O[j]) != 0) {
                        System.out.println("updaitng weight vector: " + j);
                        for (int k = 0; k < n + 1; k++) {
                            double x = W[j][k];
                            W[j][k] = x + 0.5 * c * (D[cls - 1][j] - O[j]) * T[i][k];
                        }
                    }
                }
                E += temp;
                System.out.println("weight matrix ");
                for (int a = 0; a < n + 1; a++) {
                    for (int l = 0; l < n + 1; l++) {
                        System.out.print(String.format("%.2f  ", W[a][l]));
                    }
                    System.out.println();
                }
            }

            System.out.println("\ncycles: " + cycle);
            System.out.println("error: " + E);
            errors.add(E);
        }
        System.out.println("\ntotal cycles: " + cycle);
        System.out.println("Errors: " + errors + "\n");
    }
}