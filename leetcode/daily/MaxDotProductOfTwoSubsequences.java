package leetcode.daily;

import utils.TestUtil;

public class MaxDotProductOfTwoSubsequences {
    private boolean allNegative(int[] nums) {
        for (int n: nums) {
            if (n >= 0) {
                return false;
            }
        }
        return true;
    }
    private boolean allPositive(int[] nums) {
        for (int n: nums) {
            if (n <= 0) {
                return false;
            }
        }
        return true;
    }
    private int min(int[] nums) {
        int val = nums[0];
        for (int n: nums) {
            if (n < val) {
                val = n;
            }
        }
        return val;
    }
    private int max(int[] nums) {
        int val = nums[0];
        for (int n: nums) {
            if (n > val) {
                val = n;
            }
        }
        return val;
    }
    public int maxDotProduct(int[] nums1, int[] nums2) {
        if (allNegative(nums1) && allPositive(nums2)) {
            return max(nums1) * min(nums2);
        }
        if (allNegative(nums2) && allPositive(nums1)) {
            return max(nums2) * min(nums1);
        }

        int cols = nums1.length, rows = nums2.length;

        int[][] maxDotProd = new int[rows][cols];

        maxDotProd[rows - 1][cols - 1] = Math.max(nums2[rows - 1] * nums1[cols - 1], 0);

        for (int row = rows - 2; row >= 0; row--) {
            maxDotProd[row][cols - 1] = Math.max(
                nums2[row] * nums1[cols - 1],
                maxDotProd[row + 1][cols - 1]
            );
        }
        for (int col = cols - 2; col >= 0; col--) {
            maxDotProd[rows - 1][col] = Math.max(
                maxDotProd[rows - 1][col + 1],
                nums2[rows - 1] * nums1[col]
            );
        }
        for (int i = rows - 2; i >= 0; i--) {
            for (int j = cols - 2; j >=0; j--) {
                maxDotProd[i][j] = Math.max(
                    Math.max(
                        maxDotProd[i + 1][j],
                        (nums2[i] * nums1[j]) + maxDotProd[i+1][j+1]
                    ),
                    maxDotProd[i][j+1]
                );
            }
        }

        return maxDotProd[0][0];
    }

    public static void main(String[] args) {
        MaxDotProductOfTwoSubsequences solution = new MaxDotProductOfTwoSubsequences();

        TestUtil.run("Test Case #1", 18, solution.maxDotProduct(new int[]{2,1,-2,5}, new int[]{3,0,-6}));
        TestUtil.run("Test Case #2", 21, solution.maxDotProduct(new int[]{3,-2}, new int[]{2,-6,7}));
        TestUtil.run("Test Case #3", -1, solution.maxDotProduct(new int[]{-1,-1}, new int[]{1,1}));
        TestUtil.run("Test Case #4", 6, solution.maxDotProduct(new int[]{2}, new int[]{3}));
        TestUtil.run("Test Case #5", -6, solution.maxDotProduct(new int[]{-1}, new int[]{6}));
        TestUtil.run("Test Case #6", 0, solution.maxDotProduct(new int[]{-1}, new int[]{6,0}));
        TestUtil.run("Test Case #7", -3, solution.maxDotProduct(new int[]{-5,-1,-2}, new int[]{3,3,5,5}));
        TestUtil.run("Test Case #8", 28, solution.maxDotProduct(new int[]{5,-4,-3}, new int[]{-4,-3,0,-4,2}));
    }
}
