package res;

public class KnapsackAlgorithm {
    public static int knapsack(int capacity, int[] weight, int[] value, int n)
    {
        // Base Case
        if (n == 0 || capacity == 0)
            return 0;

        // If weight of the nth item is more than Knapsack capacity,
        // then this item cannot be included in the optimal solution
        if (weight[n - 1] > capacity)
            return knapsack(capacity, weight, value, n - 1);

            // Return the maximum of two cases:
            // (1) nth item included
            // (2) not included
        else
            return Math.max(value[n - 1]
                            + knapsack(capacity - weight[n - 1], weight, value, n - 1),
                              knapsack(capacity, weight, value, n - 1));
    }
    // Driver code
    public static void main(String[] args)
    {
        int[] profit = new int[] { 250, 442, 1100, 44, 600 };
        int[] weight = new int[] { 67, 45, 140, 51, 150 };
        int capacity = 300;
        int n = profit.length;
        System.out.println(knapsack(capacity, weight, profit, n));
    }
}
