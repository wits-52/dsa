package tuf.sde_sheet;

import utils.TestUtil;

public class RotateImage {
    private void swap(int[][] matrix, int i, int j, int x, int y) {
        matrix[i][j] = matrix[i][j] + matrix[x][y];
        matrix[x][y] = matrix[i][j]  - matrix[x][y];
        matrix[i][j] = matrix[i][j] - matrix[x][y];
    }
    private void transpose(int[][] matrix) {
        if (matrix.length == 0 || (matrix.length != matrix[0].length)) {
            return;
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix.length; j++) {
                this.swap(matrix, i, j, j, i);
            }
        }
    }
    private void mirrorImage(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length / 2; j++) {
                this.swap(matrix, i, j, i, matrix[i].length - 1 - j);
            }
        }
    }
    public void rotate(int[][] matrix) {
        this.transpose(matrix);
        this.mirrorImage(matrix);
    }

    public static void main(String[] args) {
        RotateImage solution = new RotateImage();

        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        solution.rotate(matrix);
        TestUtil.run("Test Case #1", new int[][]{{7,4,1},{8,5,2},{9,6,3}}, matrix);

        matrix = new int[][]{{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
        solution.rotate(matrix);
        TestUtil.run("Test Case #2", new int[][]{{15,13,2,5},{14,3,4,1},{12,6,8,9},{16,7,10,11}}, matrix);
    }
}
