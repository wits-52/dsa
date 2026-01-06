package leetcode.daily;

import utils.MatrixUtil;
import utils.PerfUtil;
import utils.TestUtil;
import utils.PerfUtil.PerfResult;

public class NoOfWaysToPaint {
    private final int MOD = 1000000007;
    long[] sameEnds, differentEnd;

    private int calculateWaysForNRowsModM(int n, int m) {
        this.sameEnds = new long[n+1];
        this.differentEnd = new long[n+1];
        this.sameEnds[1] = 6 % m;
        this.differentEnd[1] = 6 % m;

        for (int i = 2; i <= n; i++) {
            this.sameEnds[i] = (3L * this.sameEnds[i-1] + 2L * this.differentEnd[i-1]) % m;
            this.differentEnd[i] = (2L * this.sameEnds[i-1] + 2L * this.differentEnd[i-1]) % m;
        }

        return ((int)((this.sameEnds[n] + this.differentEnd[n]) % m));
    }
    private int exponentialCalculation(int n, int m) {
        try {
            int[][] matrixM = new int[][]{{3, 2}, {2, 2}};
            int[][] baseMatric = new int[][]{{6}, {6}};

            int[][] result = MatrixUtil.multiply(
                MatrixUtil.powerModM(matrixM, n - 1, m),
                baseMatric,
                m
            );

            return (result[0][0] + result[1][0]) % m;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    public int numOfWays(int n) {
        return this.calculateWaysForNRowsModM(n, MOD);

    }
    public int numOfWays(int n, boolean useMatixExponentiation) {
        if (useMatixExponentiation) {
            return exponentialCalculation(n, MOD);
        }
        return numOfWays(n);
    }

    public static void main(String[] args) {
        NoOfWaysToPaint solution = new NoOfWaysToPaint();

        TestUtil.run("O(n)Test case #1", 12, solution.numOfWays(1));
        TestUtil.run("O(n)Test case #2", 54, solution.numOfWays(2));
        TestUtil.run("O(n)Test case #3", 246, solution.numOfWays(3));
        TestUtil.run("O(n)Test case #4", 30228214, solution.numOfWays(5000));


        TestUtil.run("O(logN)Test case #1", 12, solution.numOfWays(1, true));
        TestUtil.run("O(logN)Test case #2", 54, solution.numOfWays(2, true));
        TestUtil.run("O(logN)Test case #3", 246, solution.numOfWays(3, true));
        TestUtil.run("O(logN)Test case #4", 30228214, solution.numOfWays(5000, true));

        try {
            System.out.println("PerfUtil Result using linear time complexity: ");
            PerfResult<Integer> r1 = PerfUtil.run(() -> solution.numOfWays(10000), true);
            System.out.println("PerfUtil Result using Matric exponentiation: ");
            PerfResult<Integer> r2 = PerfUtil.run(() -> solution.numOfWays(10000, true), true);
            TestUtil.run("Test #9: n = 10000", r1.result, r2.result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
