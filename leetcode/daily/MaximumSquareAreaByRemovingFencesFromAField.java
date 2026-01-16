package leetcode.daily;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import utils.TestUtil;

public class MaximumSquareAreaByRemovingFencesFromAField {
    private Set<Integer> createSetOfUniqieSubarraySumsOfArray(int[] nums) {
        Set<Integer> uniqueSubarraySum = new HashSet<>();

        int[] leftSums = new int[nums.length + 1];

        for (int i = 1; i <= nums.length; i++) {
            leftSums[i] = leftSums[i-1] + nums[i-1];
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = leftSums[j+1] - leftSums[i];

                uniqueSubarraySum.add(sum);
            }
        }

        return uniqueSubarraySum;
    }
    private int findMaxCommonSubarraySum(int[] nums, Set<Integer> uniqueSubarraySums) {
        int[] leftSums = new int[nums.length + 1];
        int maxCommonSum = -1;

        for (int i = 1; i <= nums.length; i++) {
            leftSums[i] = leftSums[i-1] + nums[i-1];
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = leftSums[j+1] - leftSums[i];

                if (uniqueSubarraySums.contains(sum)) {
                    maxCommonSum = Math.max(sum, maxCommonSum);
                }
            }
        }

        return maxCommonSum;
    }
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        int[] horizontalSums = new int[hFences.length + 1], verticalSums = new int[vFences.length + 1];

        Arrays.sort(hFences);
        Arrays.sort(vFences);

        horizontalSums[0] = hFences[0] - 1;
        verticalSums[0] = vFences[0] - 1;

        horizontalSums[hFences.length] = m - hFences[hFences.length - 1];
        verticalSums[vFences.length] = n - vFences[vFences.length - 1];

        for (int i = 1; i < hFences.length; i++) {
            horizontalSums[i] = hFences[i] - hFences[i-1];
        }
        for (int i = 1; i < vFences.length; i++) {
            verticalSums[i] = vFences[i] - vFences[i-1];
        }

        Set<Integer> uniqueHorizontalSubarraySums = this.createSetOfUniqieSubarraySumsOfArray(horizontalSums);
        int maxCommonSum = this.findMaxCommonSubarraySum(verticalSums, uniqueHorizontalSubarraySums);

        if (maxCommonSum == -1) {
            return -1;
        } else {
            long area = (long) maxCommonSum * (long) maxCommonSum;

            return (int)(area % 1000000007);
        }
    }

    public static void main(String[] args) {
        MaximumSquareAreaByRemovingFencesFromAField solution = new MaximumSquareAreaByRemovingFencesFromAField();

        TestUtil.run("Test Case #1", 4, solution.maximizeSquareArea(4, 3, new int[]{2,3}, new int[]{2}));
        TestUtil.run("Test Case #2", -1, solution.maximizeSquareArea(6, 7, new int[]{2}, new int[]{4}));
        TestUtil.run("Test Case #3", 4, solution.maximizeSquareArea(3, 9, new int[]{2}, new int[]{8,6,5,4}));
        TestUtil.run("Test Case #4", 9, solution.maximizeSquareArea(6, 4, new int[]{3}, new int[]{3,2}));
        TestUtil.run("Test Case #5", 9, solution.maximizeSquareArea(7, 4, new int[]{2,3,6,5}, new int[]{3,2}));
    }
}
