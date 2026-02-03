package tuf.sde_sheet.arrays;

import utils.TestUtil;

public class NextPermutation {
    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] + nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] = nums[i] - nums[j];
    }
    private int findPivotPoint(int[] nums) {
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                return i;
            }
        }

        return -1;
    }
    private int findFirstLargerFromEndTill(int[] nums, int minIdx) {
        for (int i = nums.length - 1; i > minIdx; i--) {
            if (nums[i] > nums[minIdx]) {
                return i;
            }
        }

        return -1;
    }
    private void flipSubarray(int[] nums, int start, int end) {
        if (end <= start) {
            return;
        }
        int subArrayLength = end - start + 1;

        for (int i = 0; i < subArrayLength / 2; i++) {
            this.swap(nums, start + i, end - i);
        }
    }
    public void nextPermutation(int[] nums) {
        int pivotIdx = this.findPivotPoint(nums);

        if (pivotIdx == -1) {
            this.flipSubarray(nums, 0, nums.length - 1);
        } else {
            int largerThanPivotIdx = this.findFirstLargerFromEndTill(nums, pivotIdx);

            this.swap(nums, pivotIdx, largerThanPivotIdx);

            this.flipSubarray(nums, pivotIdx + 1, nums.length - 1);
        }
    }

    public static void main(String[] args) {
        NextPermutation solution = new NextPermutation();

        int[] nums = new int[]{1,2,3};
        solution.nextPermutation(nums);
        TestUtil.run("Test Case #1", new int[]{1,3,2}, nums);

        nums = new int[]{3,2,1};
        solution.nextPermutation(nums);
        TestUtil.run("Test Case #2", new int[]{1,2,3}, nums);

        nums = new int[]{1,1,5};
        solution.nextPermutation(nums);
        TestUtil.run("Test Case #3", new int[]{1,5,1}, nums);

        nums = new int[]{1,3,2};
        solution.nextPermutation(nums);
        TestUtil.run("Test Case #4", new int[]{2,1,3}, nums);

        nums = new int[]{1,4,3,2,1};
        solution.nextPermutation(nums);
        TestUtil.run("Test Case #5", new int[]{2,1,1,3,4}, nums);

        nums = new int[]{9,1,4,3,2,1};
        solution.nextPermutation(nums);
        TestUtil.run("Test Case #6", new int[]{9,2,1,1,3,4}, nums);

        nums = new int[]{9,1,1};
        solution.nextPermutation(nums);
        TestUtil.run("Test Case #7", new int[]{1,1,9}, nums);
    }
}
