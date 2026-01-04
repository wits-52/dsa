package leetcode;

import java.util.HashMap;
import java.util.Map;

import utils.TestUtil;

public class FourDivisors {
    private Map<Integer, Integer> divisorSumCache = new HashMap<>();

    private int calculateDivisorSum(int n) {
        if (this.divisorSumCache.containsKey(n)) {
            return this.divisorSumCache.get(n);
        }
        int noOfDivisors = 2, divisorSum = 1 + n;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                if (i * i == n) {
                    noOfDivisors++;
                    divisorSum += i;
                } else {
                    noOfDivisors += 2;
                    divisorSum += i;
                    divisorSum += (n/i);
                }
                if (noOfDivisors > 4) {
                    return 0;
                }
            }
        }

        int result = noOfDivisors == 4 ? divisorSum : 0;

        this.divisorSumCache.put(n, result);

        return result;
    }
    public int sumFourDivisors(int[] nums) {
        int fourDivisorSum = 0;

        for (int num: nums) {
            fourDivisorSum += this.calculateDivisorSum(num);
        }

        return fourDivisorSum;
    }

    public static void main(String[] args) {
        FourDivisors solution = new FourDivisors();

        TestUtil.run("Test Case #1", 32, solution.sumFourDivisors(new int[]{21, 4, 7}));
        TestUtil.run("Test Case #2", 64, solution.sumFourDivisors(new int[]{21, 21}));
        TestUtil.run("Test Case #3", 0, solution.sumFourDivisors(new int[]{1, 2, 3, 4, 5}));
    }
}
