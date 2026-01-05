package leetcode;

import utils.TestUtil;

public class MaximumMatrixSum {
    public long maxMatrixSum(int[][] matrix) {
        long absoluteSum = 0, minAbsolute = Math.abs(matrix[0][0]);
        int negativeCount = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                absoluteSum += Math.abs(matrix[i][j]);

                if (matrix[i][j] < 0) {
                    negativeCount++;
                }
                if (Math.abs(matrix[i][j]) < minAbsolute) {
                    minAbsolute = Math.abs(matrix[i][j]);
                }
            }
        }

        return (negativeCount % 2 == 0) ? absoluteSum : absoluteSum - (2 * minAbsolute);
    }

    public static void main(String[] args) {
        MaximumMatrixSum solution = new MaximumMatrixSum();

        TestUtil.require("Test Case #1", 4L, solution.maxMatrixSum(new int[][]{{1,-1},{-1,1}}));
        TestUtil.require("Test Case #2", 16L, solution.maxMatrixSum(new int[][]{{1,2,3},{-1,-2,-3},{1,2,3}}));
    }
}
