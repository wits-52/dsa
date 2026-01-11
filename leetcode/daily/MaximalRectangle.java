package leetcode.daily;

import utils.TestUtil;

public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        int[][] connectedOnesToRight = new int[matrix.length][matrix[0].length];
        int maxArea = 0;

        if (matrix[0][matrix[0].length - 1] == '1') {
            connectedOnesToRight[0][matrix[0].length - 1] = 1;
        } else {
            connectedOnesToRight[0][matrix[0].length - 1] = 0;
        }
        
        for (int i =  matrix[0].length - 2; i >= 0; i--) {
            if (matrix[0][i] == '1') {
                connectedOnesToRight[0][i] = 1 + connectedOnesToRight[0][i+1];
            } else {
                connectedOnesToRight[0][i] = 0;
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][matrix[i].length - 1] == '1') {
                connectedOnesToRight[i][matrix[i].length - 1] = 1;
            } else {
                connectedOnesToRight[i][matrix[i].length - 1] = 0;
            }
        }
        for (int i = 1; i < matrix.length; i++) {
            for (int j = matrix[i].length - 2; j>=0 ; j--) {
                if (matrix[i][j] == '1') {
                    connectedOnesToRight[i][j] = 1 + connectedOnesToRight[i][j+1];
                } else {
                    connectedOnesToRight[i][j] = 0;
                }
            }
        }

        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix[i].length - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    int minWidth = connectedOnesToRight[i][j];
                    maxArea = Math.max(maxArea, minWidth);
    
                    for (int k = i; k >= 0; k--) {
                        minWidth = Math.min(minWidth, connectedOnesToRight[k][j]);
                        maxArea = Math.max(maxArea, (i - k + 1) * minWidth);
                    }
                }

            }
        }
        return maxArea;
    }
    public static void main(String[] args) {
        MaximalRectangle solution = new MaximalRectangle();

        TestUtil.run("Test Case #1", 6, solution.maximalRectangle(new char[][]{
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        }));

        TestUtil.run("Test Case #2", 0, solution.maximalRectangle(new char[][]{
            {'0'}
        }));

        TestUtil.run("Test Case #3", 1, solution.maximalRectangle(new char[][]{
            {'1'}
        }));

        TestUtil.run("Test Case #4", 5, solution.maximalRectangle(new char[][]{
            {'1','0','1','0','0'},
            {'1','0','1','0','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        }));
    }
}
