package tuf.sde_sheet;

import utils.TestUtil;

public class CountInversionsOfAnArray {
    private int inversionCount = 0;
    private int[] mergeSortAndCountIntervals(int[] arr, int start, int end) {
        if (start == end) {
            return new int[]{arr[start]};
        }
        int mid = (start + end) / 2, leftCounter = 0, rightCounter = 0, i = 0;

        int[] left = this.mergeSortAndCountIntervals(arr, start, mid);
        int[] right = this.mergeSortAndCountIntervals(arr, mid+1, end);
        int[] sortedArr = new int[left.length + right.length];

        while (leftCounter < left.length && rightCounter < right.length) {
            if (left[leftCounter] > right[rightCounter]) {
                this.inversionCount += left.length - leftCounter;
                sortedArr[i++] = right[rightCounter++];
            } else {
                sortedArr[i++] = left[leftCounter++];
            }
        }

        while (leftCounter < left.length) {
            sortedArr[i++] = left[leftCounter++];
        }
        while (rightCounter < right.length) {
            sortedArr[i++] = right[rightCounter++];
        }

        return sortedArr;
    }
    public int inversionCount(int arr[]) {
        this.inversionCount = 0;

        this.mergeSortAndCountIntervals(arr, 0, arr.length - 1);

        return this.inversionCount;
    }

    public static void main(String[] args) {
        CountInversionsOfAnArray solution = new CountInversionsOfAnArray();

        TestUtil.run("Test Case #1", 3, solution.inversionCount(new int[]{2,4,1,3,5}));
        TestUtil.run("Test Case #2", 0, solution.inversionCount(new int[]{2,3,4,5,6}));
        TestUtil.run("Test Case #3", 0, solution.inversionCount(new int[]{10,10,10}));
        TestUtil.run("Test Case #4", 6, solution.inversionCount(new int[]{10,9,8,7}));
    }
}
