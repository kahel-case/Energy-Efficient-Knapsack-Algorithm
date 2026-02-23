package res;

import javafx.scene.Node;

public class KnapsackAlgorithm {

    // THIS ALGORITHM MIGHT BURN YOUR HOUSE DOWN IF CAPACITY IS GREATER THAN 400!!!
    public static int defaultSelection(int capacity, int[] weight, int[] value, int n) {
        if (n == 0 || capacity == 0) {
            return 0;
        }
        if (weight[n - 1] > capacity) {
            return defaultSelection(capacity, weight, value, n - 1);
        }
        else {
            return Math.max(value[n - 1] + defaultSelection(capacity - weight[n - 1], weight, value, n - 1), defaultSelection(capacity, weight, value, n - 1));
        }
    }

    // Modified Version of the Bottom-Up Approach
    // taken from https://www.geeksforgeeks.org/dsa/0-1-knapsack-problem-dp-10/
    public static int enhancedSelection(int capacity, int[] weight, int[] value, int n, Node[] nodes) {

        int[][] dp = new int[n + 1][capacity + 1];

        // THIS IS THE BOTTOM-UP APPROACH
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {

                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                }
                else if (weight[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            value[i - 1] + dp[i - 1][w - weight[i - 1]],
                            dp[i - 1][w]
                    );
                }
                else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // CLUSTER HEAD SELECTION
        int w = capacity;
        for (int i = n; i > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                // GREEN
                nodes[i - 1].setStyle("-fx-background-color: #2bff00; -fx-font-size: 1px;");
                w -= weight[i - 1];
            } else {
                // RED
                nodes[i - 1].setStyle("-fx-background-color: #ff0000; -fx-font-size: 1px;");
            }
        }

        return dp[n][capacity];
    }
}
