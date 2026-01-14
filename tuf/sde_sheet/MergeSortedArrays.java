package tuf.sde_sheet;

import utils.TestUtil;

public class MergeSortedArrays {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int idx = nums1.length - 1;

        while (n > 0 && m > 0) {
            if (nums1[m-1] > nums2[n-1]) {
                nums1[idx] = nums1[m-1];
                m--;
            } else {
                nums1[idx] = nums2[n-1];
                n--;
            }
            idx--;
        }

        while (n > 0) {
            nums1[idx--] = nums2[--n];
        }

        while (m > 0) {
            nums1[idx--] = nums1[--m];
        }
    }

    public static void main(String[] args) {
        MergeSortedArrays solution = new MergeSortedArrays();

        int[] nums;

        nums = new int[]{1,2,3,0,0,0};
        solution.merge(nums, 3, new int[]{2,5,6}, 3);
        TestUtil.run("Test Case #1", new int[]{1,2,2,3,5,6}, nums);

        nums = new int[]{1};
        solution.merge(nums, 1, new int[]{}, 0);
        TestUtil.run("Test Case #2", new int[]{1}, nums);

        nums = new int[]{0};
        solution.merge(nums, 0, new int[]{1}, 1);
        TestUtil.run("Test Case #3", new int[]{1}, nums);
    }
}
