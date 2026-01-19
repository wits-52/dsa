package tuf.sde_sheet;

import java.util.HashMap;
import java.util.Map;

import utils.TestUtil;

public class MajorityElement {
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num: nums) {
            if (freq.containsKey(num)) {
                freq.put(num, freq.get(num) + 1);
            } else {
                freq.put(num, 1);
            }

            if (freq.get(num) > nums.length / 2) {
                return num;
            }
        }

        return -1;
    }
    public int majorityElement(int[] nums, boolean linearTimeConstantTimeFlag) {
        if (!linearTimeConstantTimeFlag) return majorityElement(nums);

        int count = 1, element = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == element) {
                count++;
            } else {
                count--;
            }
            if (count < 0) {
                element = nums[i];
                count = 1;
            }
        }

        return element;
    }

    public static void main(String[] args) {
        MajorityElement solution = new MajorityElement();

        TestUtil.run("Test Case #1", 3, solution.majorityElement(new int[] {3, 2, 3}, true));
        TestUtil.run("Test Case #2", 2, solution.majorityElement(new int[] {2,2,1,1,1,2,2}, true));
        TestUtil.run("Test Case #3", 2, solution.majorityElement(new int[] {1,1,2,2,1,2,2}, true));
    }
}
