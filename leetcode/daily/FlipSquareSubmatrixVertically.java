package leetcode.daily;

import utils.TestUtil;

public class FlipSquareSubmatrixVertically {
    private int[][] reverseSubmatrix(int[][] grid, int x, int y, int k) {
        for (int i = 0; i < k / 2; i++) {
            for (int j = 0; j < k; j++) {
                grid[x + i][y + j] = grid[x + i][y + j] + grid[x + k - i - 1][y + j];
                grid[x + k - i - 1][y + j] = grid[x + i][y + j] - grid[x + k - i - 1][y + j];
                grid[x + i][y + j] = grid[x + i][y + j] - grid[x + k - i - 1][y + j];
            }
        }

        return grid;
    }

    public static void main(String[] args) {
        FlipSquareSubmatrixVertically solution = new FlipSquareSubmatrixVertically();

        TestUtil.run(
            "Test #1",
            solution.reverseSubmatrix(
                    new int[][] { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } },
                    1,
                    0,
                    3
                ),
            new int[][] { { 1, 2, 3, 4 }, { 13, 14, 15, 8 }, { 9, 10, 11, 12 }, { 5, 6, 7, 16 } }
        );

        TestUtil.run(
            "Test #2",
            solution.reverseSubmatrix(
                    new int[][] { {3,4,2,3}, {2,3,4,2} },
                    0,
                    2,
                    2
                ),
            new int[][] { { 3,4,4,2 }, {2,3,2,3} }
        );
    }
}
