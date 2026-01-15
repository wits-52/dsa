package tuf.sde_sheet;

import utils.TestUtil;

public class FindTheDuplicateNumber {
    private int peigonHoleBinarySearch(int[] nums) {
        int end = nums.length - 1, start = 1;

        while (start < end) {
            int mid = (start + end) / 2;

            int lessThanEqualToMidCount = 0;

            for (int n: nums) {
                if (n <= mid) lessThanEqualToMidCount++;
            }

            if (lessThanEqualToMidCount <= mid) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }
    private int markingArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);

            if (nums[num] < 0) {
                return num;
            }

            nums[num] *= -1;
        }
        return -1;
    }
    private int tortoiseFastSlowPointer(int[] nums) {
        int slow = nums[0], fast = nums[nums[0]];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
    public int findDuplicate(int[] nums) {
        return this.tortoiseFastSlowPointer(nums);
    }

    public static void main(String[] args) {
        FindTheDuplicateNumber solution = new FindTheDuplicateNumber();

        TestUtil.run("Test Case #1", 2, solution.findDuplicate(new int[]{1,3,4,2,2}));
        TestUtil.run("Test Case #2", 3, solution.findDuplicate(new int[]{3,1,3,4,2}));
        TestUtil.run("Test Case #3", 3, solution.findDuplicate(new int[]{3,3,3,3,3}));
    }
}
