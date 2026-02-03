package tuf.sde_sheet.arrays_III;

import utils.TestUtil;

public class SearchA2dMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int start = 0, rows = matrix.length, cols = matrix[0].length, end = (rows * cols) - 1;

        while (start < end) {
            int mid = (start + end) / 2;

            int row = mid / cols;
            int col = mid % cols;

            if (target == matrix[row][col]) {
                return true;
            }
            if (target > matrix[row][col]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        int row = start / cols, col = start % cols;
        return matrix[row][col] == target;
    }

    public static void main(String[] args) {
        SearchA2dMatrix solution = new SearchA2dMatrix();

        TestUtil.run(
            "Test Case #1",
            true,
            solution.searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 3)
        );
        TestUtil.run(
            "Test Case #2",
            false,
            solution.searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 13)
        );
        TestUtil.run(
            "Test Case #3",
            true,
            solution.searchMatrix(new int[][]{{1}}, 1)
        );
    }
}
