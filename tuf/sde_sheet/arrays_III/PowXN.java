package tuf.sde_sheet.arrays_III;

import utils.TestUtil;

public class PowXN {
    public double myPow(double x, int n) {
        if (n == 0) return 1;
        if (n == 1) return x;
        if (x == 1) return 1.000000d;
        if (x == -1) return n%2 == 0 ? 1 : -1;
        if (n == -2147483648) return 0.00000d;

        if (n < 0) {
            n *= -1;
            return this.myPow(1/x, n);
        }

        return this.myPow(x*x, n/2) * this.myPow(x, n%2);
    }
    public static void main(String[] args) {
        PowXN solution = new PowXN();

        TestUtil.run("Test Case #1", 1024.00000d, solution.myPow(2.00000d, 10));
        TestUtil.run("Test Case #2", Math.abs(9.26100d - solution.myPow(2.10000d, 3)) < 0.000001d);
        TestUtil.run("Test Case #3", 0.25000d, solution.myPow(2.00000d, -2));
    }
}
