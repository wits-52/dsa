package solutions;

import utils.TestUtil;

public class NoOfWaysToPaint {
    long[] sameEnds, differentEnd;

    private void calculateWaysForNRowsModM(int n, long m) {
        this.sameEnds[1] = 6 % m;
        this.differentEnd[1] = 6 % m;

        for (int i = 2; i <= n; i++) {
            this.sameEnds[i] = (3L * this.sameEnds[i-1] + 2L * this.differentEnd[i-1]) % m;
            this.differentEnd[i] = (2L * this.sameEnds[i-1] + 2L * this.differentEnd[i-1]) % m;
        }
    }
    public int numOfWays(int n) {
        long m = 1000000007L;
        this.sameEnds = new long[n+1];
        this.differentEnd = new long[n+1];

        this.calculateWaysForNRowsModM(n, m);

        return ((int)((this.sameEnds[n] + this.differentEnd[n]) % m));
    }

    public static void main(String[] args) {
        NoOfWaysToPaint solution = new NoOfWaysToPaint();

        TestUtil.run("Test case #1", 12, solution.numOfWays(1));
        TestUtil.run("Test case #2", 54, solution.numOfWays(2));
        TestUtil.run("Test case #3", 246, solution.numOfWays(3));
        TestUtil.run("Test case #4", 30228214, solution.numOfWays(5000));
    }
}
