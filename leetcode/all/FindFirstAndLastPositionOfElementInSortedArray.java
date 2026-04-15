package leetcode.all;

public class FindFirstAndLastPositionOfElementInSortedArray {
    private int[] searchRange(int[] nums, int target) {
        int[] indexes = new int[]{-1, -1};
        if (nums.length == 0) return indexes;

        int start = 0, end = nums.length - 1;

        while (start < end) {
            int mid = (start + end) / 2;

            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                end = mid;
            }
        }

        if (nums[start] != target) {
            return indexes;
        } else {
            indexes[0] = start;
        }

        start = 0; end = nums.length - 1;
        while (start < end) {
            int mid = (int)Math.ceil((start + end) / 2.0);

            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid;
            }
        }

        indexes[1] = start;

        return indexes;
    }
}
