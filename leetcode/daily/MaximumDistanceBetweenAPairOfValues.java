package leetcode.daily;

import utils.TestUtil;

public class MaximumDistanceBetweenAPairOfValues {
    public int maxDistance(int[] nums1, int[] nums2) {
        int num2Start = 0, maxDistance = 0;

        for (int i = 0; i < nums1.length && i < nums2.length; i++) {
            int start = Math.max(i, num2Start), end = nums2.length - 1;

            while (start < end) {
                int mid = (int)Math.ceil((start + end) / 2.0);

                if (nums2[mid] < nums1[i]) {
                    end = mid - 1;
                } else {
                    start = mid;
                }
            }

            if (nums2[start] >= nums1[i]) {
                num2Start = start;

                maxDistance = Math.max(maxDistance, start - i);
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
