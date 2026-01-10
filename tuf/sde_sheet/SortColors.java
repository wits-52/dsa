package tuf.sde_sheet;

import utils.TestUtil;

public class SortColors {
    private void swap(int[] nums, int i, int j) {
        nums[i] = nums[i] + nums[j];
        nums[j] = nums[i] - nums[j];
        nums[i] = nums[i] - nums[j];
    }
    public void sortColors(int[] nums) {
        int zeros = 0, twos = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 && i > zeros) {
                swap(nums, i, zeros++);
                i--;
            } else if (nums[i] == 2 && i < nums.length - 1 - twos) {
                this.swap(nums, i, nums.length - 1 - twos++);
                i--;
            }
        }
    }

    public static void main(String[] args) {
        SortColors solution = new SortColors();

        int[] nums;

        nums = new int[]{2,0,2,1,1,0};
        solution.sortColors(nums);
        TestUtil.run("Test Case #1", new int[]{0,0,1,1,2,2}, nums);

        nums = new int[]{2,0,1};
        solution.sortColors(nums);
        TestUtil.run("Test Case #2", new int[]{0,1,2}, nums);


        nums = new int[]{2,2,2,2};
        solution.sortColors(nums);
        TestUtil.run("Test Case #2", new int[]{2,2,2,2}, nums);


        nums = new int[]{0,0};
        solution.sortColors(nums);
        TestUtil.run("Test Case #2", new int[]{0,0}, nums);

        nums = new int[]{2,1};
        solution.sortColors(nums);
        TestUtil.run("Test Case #2", new int[]{1,2}, nums);

        nums = new int[]{1,0};
        solution.sortColors(nums);
        TestUtil.run("Test Case #2", new int[]{0,1}, nums);

        nums = new int[]{2,0};
        solution.sortColors(nums);
        TestUtil.run("Test Case #2", new int[]{0,2}, nums);
    }
}
