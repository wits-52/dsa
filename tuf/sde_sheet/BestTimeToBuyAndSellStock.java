package tuf.sde_sheet;

import utils.TestUtil;

public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) {
            return 0;
        }
        int maxProfit = 0, max = prices[prices.length - 1];

        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);

            maxProfit = Math.max(max - prices[i], maxProfit);
        }

        return maxProfit;
    }
    public static void main(String[] args) {
        BestTimeToBuyAndSellStock solution = new BestTimeToBuyAndSellStock();

        TestUtil.run("Test Case #1", 5, solution.maxProfit(new int[]{7,1,5,3,6,4}));
        TestUtil.run("Test Case #2", 0, solution.maxProfit(new int[]{7,6,4,3,1}));
    }
}
