package leetcode.daily;

import utils.TestUtil;

public class MinimumASCIIDeleteSumforTwoStrings {
    private int calculateStringWeight(String s) {
        int weight = 0;
        for (int i = 0; i < s.length(); i++) {
            weight += s.charAt(i);
        }

        return weight;
    }
    private int max(int a, int b, int c) {
        return Math.max(Math.max(a, b), c);
    }
    public int minimumDeleteSum(String s1, String s2) {
        int weightSum = this.calculateStringWeight(s1) + this.calculateStringWeight(s2);
        int rows = s2.length(), cols = s1.length();
        int[][] maxCommonSubsequenceWeight = new int[rows][cols];

        maxCommonSubsequenceWeight[rows - 1][cols - 1] = s2.charAt(rows - 1) == s1.charAt(cols - 1) ? s1.charAt(cols - 1) : 0;
        
        for (int col = cols - 2; col >= 0; col--) {
            if (s2.charAt(rows - 1) != s1.charAt(col)) {
                maxCommonSubsequenceWeight[rows - 1][col] = maxCommonSubsequenceWeight[rows - 1][col + 1];
            } else {
                maxCommonSubsequenceWeight[rows - 1][col] = s1.charAt(col);
            }
        }

        for (int row = rows - 2; row >= 0; row--) {
            if (s2.charAt(row) == s1.charAt(cols - 1)) {
                maxCommonSubsequenceWeight[row][cols - 1] = s1.charAt(cols - 1);
            } else {
                maxCommonSubsequenceWeight[row][cols - 1] = maxCommonSubsequenceWeight[row + 1][cols - 1];

            }
        }

        for (int row = rows - 2; row >= 0; row--) {
            for (int col = cols - 2; col >= 0; col--) {
                if (s2.charAt(row) == s1.charAt(col)) {
                    maxCommonSubsequenceWeight[row][col] = this.max(
                        s2.charAt(row) + maxCommonSubsequenceWeight[row+1][col+1],
                        maxCommonSubsequenceWeight[row+1][col],
                        maxCommonSubsequenceWeight[row][col+1]
                    );
                } else {
                    maxCommonSubsequenceWeight[row][col] = Math.max(
                        maxCommonSubsequenceWeight[row+1][col],
                        maxCommonSubsequenceWeight[row][col+1]
                    );
                }
            }
        }

        return weightSum - (2*maxCommonSubsequenceWeight[0][0]);
    }

    public static void main(String[] args) {
        MinimumASCIIDeleteSumforTwoStrings solution = new MinimumASCIIDeleteSumforTwoStrings();

        TestUtil.require("Test Case #1", 231, solution.minimumDeleteSum("sea", "eat"));
        TestUtil.require("Test Case #2", 403, solution.minimumDeleteSum("delete", "leet"));
    }
}
