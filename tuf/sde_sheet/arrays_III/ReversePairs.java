package tuf.sde_sheet.arrays_III;

import utils.TestUtil;

public class ReversePairs {
    private int noOfReversePairs = 0;
    public int reversePairs(int[] nums) {
        int reversePairs = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i]/2.0 > nums[j]) {
                    reversePairs++;
                }
            }
        }

        return reversePairs;
    }
    private int bSearch(int[] arr, long target) {
        int start = 0, end = arr.length - 1;

        while (start < end) {
            int mid = (start+end) / 2;

            if ((long)arr[mid] <= target) {
                start = mid+1;
            } else {
                end = mid;
            }
        }

        return (long)arr[start] > target ? start : start + 1;
    }
    private int[] merge(int[] left, int[] right) {
        int[] merged = new int[left.length + right.length];

        int leftCounter = 0, rightCounter = 0, counter = 0;

        for (int r: right) {
            int idx = this.bSearch(left, 2l*r);

            this.noOfReversePairs += left.length - idx;
        }
        while (leftCounter < left.length && rightCounter < right.length) {
            if (left[leftCounter] < right[rightCounter]) {
                merged[counter++] = left[leftCounter++];
            } else {
                merged[counter++] = right[rightCounter++];
            }
        }
        while (leftCounter < left.length) {
            merged[counter++] = left[leftCounter++];
        }
        while (rightCounter < right.length) {
            merged[counter++] = right[rightCounter++];
        }

        return merged;
    }
    private int[] mergeSort(int[] nums, int start, int end) {
        if (start == end) {
            return new int[]{nums[start]};
        }
        int mid = (start+end)/2;
        int[] left = this.mergeSort(nums, start, mid);
        int[] right = this.mergeSort(nums, mid+1, end);

        return this.merge(left, right);
    }
    public int reversePairs(int[] nums, boolean useMergeSort) {
        if (!useMergeSort) return this.reversePairs(nums);

        this.noOfReversePairs = 0;

        this.mergeSort(nums, 0, nums.length - 1);

        return this.noOfReversePairs;
    }

    public static void main(String[] args) {
        ReversePairs solution = new ReversePairs();

        TestUtil.run("Test Case #1", 2, solution.reversePairs(new int[]{1,3,2,3,1}, true));
        TestUtil.run("Test Case #2", 3, solution.reversePairs(new int[]{2,4,3,5,1}, true));
        TestUtil.run("Test Case #3", 0, solution.reversePairs(new int[]{2147483647,2147483647,2147483647,2147483647,2147483647,2147483647}, true));
    }
}
