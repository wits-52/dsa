package leetcode.daily;

import java.util.Arrays;

import utils.TestUtil;

public class MaximizeAreaOfSquareHoleInGrid {
    private int findMaxConsecutiveNums(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);

        int maxConsecutive = 1;
        int currentConsecutive = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1] + 1) {
                currentConsecutive++;
                maxConsecutive = Math.max(maxConsecutive, currentConsecutive);
            } else {
                currentConsecutive = 1;
            }
        }

        return maxConsecutive;
    }
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        int maxConsecutiveHBars = this.findMaxConsecutiveNums(hBars) + 1;
        int maxConsecutiveVBars = this.findMaxConsecutiveNums(vBars) + 1;

        int minConsecutive = Math.min(maxConsecutiveHBars, maxConsecutiveVBars);

        return minConsecutive * minConsecutive;
    }

    public static void main(String[] args) {
        MaximizeAreaOfSquareHoleInGrid solution = new MaximizeAreaOfSquareHoleInGrid();

        TestUtil.run("Test Case #1", 4, solution.maximizeSquareHoleArea(2, 1, new int[]{2,3}, new int[]{2}));
        TestUtil.run("Test Case #2", 4, solution.maximizeSquareHoleArea(1, 1, new int[]{2}, new int[]{2}));
        TestUtil.run("Test Case #3", 4, solution.maximizeSquareHoleArea(2, 3, new int[]{2,3}, new int[]{2,4}));
        TestUtil.run("Test Case #4", 9, solution.maximizeSquareHoleArea(3, 2, new int[]{3,2,4}, new int[]{3,4}));
    }
}
