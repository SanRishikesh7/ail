import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        // For the input data
        ArrayList<double[]> inputData = new ArrayList<>();
        inputData.add(new double[] { 1, -2, 1.5, 0 });
        inputData.add(new double[] { 1, -0.5, -2, 1.5 });
        inputData.add(new double[] { 0, 1, -1, 1.5 });

        // Weight Wo
        double[] weightO = new double[] { 1, -1, 0, 0.5 };

        // Desired output for each input
        double[] desiredOutput = new double[] { 1, -1, 1 };

        // Learning rate (c)
        double c = 1.0;
        long startTime = System.currentTimeMillis();
        int epochs = 0;

        double e = Double.MAX_VALUE;
        while (e != 0) {
            e = 0;
            for (int i = 0; i < inputData.size(); i++) {
                double[] input = inputData.get(i);
                double net = 0;
                double output = 0;
                for (int j = 0; j < input.length; j++) {
                    net += input[j] * weightO[j];
                }

                output = sgn(net);

                double error = desiredOutput[i] - output;
                if (error != 0.0) {
                    for (int j = 0; j < input.length; j++) {
                        weightO[j] += c * error * input[j];
                    }
                } else {
                    System.out.print("The temp weights are:");
                    for (int j = 0; j < weightO.length; j++) {
                        System.out.print(weightO[j] + " ");
                    }
                    System.out.println();
                }

                e += Math.abs(error);
            }
            epochs++;
            System.out.println("Epoch " + epochs + " Error: " + e);
            double mse = e / inputData.size();
            System.out.println("Mean Squared Error: " + mse);

        }

        long endTime = System.currentTimeMillis(); // Record the end time
        long trainingTime = endTime - startTime;

        System.out.println("=====================================");
        System.out.println("The Final weights are: ");
        for (int i = 0; i < weightO.length; i++) {
            System.out.print(weightO[i] + " ");
        }
        System.out.println("\n=====================================");
        System.out.println("Training time (milliseconds): " + trainingTime);
        System.out.println("Number of epochs required: " + epochs);
        System.out.println("=====================================");
    }

    private static double sgn(double net) {
        if (net >= 0.0)
            return 1.0;

        return -1.0;
    }
}
