package tuf.sde_sheet.arrays;

import utils.TestUtil;

public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0], tempSum = 0;

        for (int i = 0; i < nums.length; i++) {
            tempSum += nums[i];

            if (tempSum > maxSum) {
                maxSum = tempSum;
            }

            if (tempSum < 0) {
                tempSum = 0;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        MaximumSubarray solution = new MaximumSubarray();

        TestUtil.run("Test Case #1", 6, solution.maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        TestUtil.run("Test Case #2", 1, solution.maxSubArray(new int[]{1}));
        TestUtil.run("Test Case #3", -3, solution.maxSubArray(new int[]{-5,-10,-3}));
        TestUtil.run("Test Case #4", 0, solution.maxSubArray(new int[]{-5,-10,0,-1}));
        TestUtil.run("Test Case #5", 23, solution.maxSubArray(new int[]{5,4,-1,7,8}));
    }
}
