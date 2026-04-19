package leetcode.daily;

import utils.TestUtil;

public class MaximumDistanceBetweenAPairOfValues {
    public int maxDistance(int[] nums1, int[] nums2) {
        int i = 0, j = 0, maxDistance = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] <= nums2[j]) {
                maxDistance = Math.max(j - i, maxDistance);
                j++;
            } else {
                i++;
            }
        }

        return maxDistance;
    }

    public static void main(String[] args) {
        MaximumDistanceBetweenAPairOfValues solution = new MaximumDistanceBetweenAPairOfValues();

        TestUtil.run("Test Case #1", 2, solution.maxDistance(new int[]{55,30,5,4,2}, new int[]{100,20,10,10,5}));
        TestUtil.run("Test Case #2", 1, solution.maxDistance(new int[]{2,2,2}, new int[]{10,10,1}));
        TestUtil.run("Test Case #3", 2, solution.maxDistance(new int[]{30,29,19,5}, new int[]{25,25,25,25,25}));
    }
}
