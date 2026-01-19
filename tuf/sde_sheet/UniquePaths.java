package tuf.sde_sheet;

import utils.TestUtil;

public class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] noOfPathFrom = new int[m][n];

        noOfPathFrom[m-1][n-1] = 1;

        for (int i = 0; i < m; i++) {
            noOfPathFrom[i][n-1] = 1;
        }
        for (int j = 0; j < n; j++) {
            noOfPathFrom[m-1][j] = 1;
        }

        for (int i = m-2; i >= 0; i--) {
            for (int j = n-2; j >= 0; j--) {
                noOfPathFrom[i][j] = noOfPathFrom[i+1][j] + noOfPathFrom[i][j+1];
            }
        }

        return noOfPathFrom[0][0];
    }
    public int uniquePaths(int m, int n, boolean optimiseSpace) {
        if (!optimiseSpace) {
            return this.uniquePaths(m, n);
        }
        int[][] noOfPathFrom = new int[1][n];

        for (int j = 0; j < n; j++) {
            noOfPathFrom[0][j] = 1;
        }

        for (int i = m-2; i >= 0; i--) {
            for (int j = n-2; j >= 0; j--) {
                noOfPathFrom[0][j] = noOfPathFrom[0][j] + noOfPathFrom[0][j+1];
            }
        }

        return noOfPathFrom[0][0];
    }

    public static void main(String[] args) {
        UniquePaths solution = new UniquePaths();

        TestUtil.run("Test Case #1", 28, solution.uniquePaths(3, 7, true));
        TestUtil.run("Test Case #2", 3, solution.uniquePaths(3, 2, true));
    }
}
