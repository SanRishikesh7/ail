import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

class Point {
    double x, y;

    Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
}

public class fuzzyFunctionDesign {
    static double membership(Point p) {
        return Math.sqrt(Math.pow((0.5 - p.x), 2) + Math.pow((0.5 - p.y), 2));
    }

    // static double fun(double p) {
    // return 5 / Math.max(p, 5) - p / Math.max(p, 5);
    // }

    static double membership(double p) {
        return Math.abs(5 / Math.max(p, 5) - p / Math.max(p, 5));
    }

    static double fuzzyMembershipCalculation(double d, double C) {
        // problem 1
        if (C * d == 0 || C * d <= 0.3) {
            return 1;
        } else if (C * d > 0.3) {
            return 1 - C * d;
        } else if (C * d >= 1) {
            return 0;
        }
        // problem 2
        if (C * d == 0) {
            return 1;
        } else if (C * d > 0) {
            return 1 - C * d;
        } else if (C * d >= 1) {
            return 0;
        }
        // problem 3
        // if (C * d >= 0 && C * d <= 0.1) {
        // return 1;
        // } else if (C * d > 0.1 && C * d < 1) {
        // return 1 - C * d;
        // } else if (C * d >= 1) {
        // return 0;
        // }
        return -1;
    }

    public static void main(String[] args) {
        ArrayList<Double> membershipvalues = new ArrayList<Double>();
        // double[] X = { 0.45, 0.48, 0.52, 0.55, 0.8, 0.2, 0.6, 0.1, 0.5, 0.3, 0.5,
        // 0.4, 0.2, 0.7, 0.3, 1, 1, 0.6, 0.5, 0,
        // 0.3, 0.2, 0.3 };
        // double[] Y = { 0.45, 0.48, 0.52, 0.55, 0.9, 0.3, 0.7, 0.1, 0.5, 0.5, 0.2,
        // 0.4, 0.8, 0.3, 1, 0.2, 1, 0.3, 0.2, 1,
        // 0.7, 0.3, 0.4 };
        double X[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int n = X.length;
        double C = 1;
        for (int i = 0; i < n; i++) {
            // Point p = new Point(X[i], Y[i]);
            // double d = membership(p);
            double d = membership(X[i]);
            // System.out.println("d: " + d);
            double membership = fuzzyMembershipCalculation(d, C);
            // System.out.println("Membership of pattern (" + p.x + "," + p.y + ")is: " +
            // decfor.format(membership));
            membershipvalues.add(membership);
        }
        System.out.println(membershipvalues);
    }
}
