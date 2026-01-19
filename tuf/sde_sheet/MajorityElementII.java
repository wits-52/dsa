package tuf.sde_sheet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utils.TestUtil;

public class MajorityElementII {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (int num: nums) {
            if (!freqMap.containsKey(num) || freqMap.get(num) <= nums.length / 3) {
                if (freqMap.containsKey(num)) {
                    freqMap.put(num, freqMap.get(num) + 1);
                } else {
                    freqMap.put(num, 1);
                }
    
                if (freqMap.get(num) > nums.length / 3) {
                    result.add(num);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        MajorityElementII solution = new MajorityElementII();

        TestUtil.run("Test Case #1", new int[]{3}, solution.majorityElement(new int[]{3,2,3}).stream().mapToInt(i -> i).toArray());
        TestUtil.run("Test Case #2", new int[]{1}, solution.majorityElement(new int[]{1}).stream().mapToInt(i -> i).toArray());
        TestUtil.run("Test Case #3", new int[]{1,2}, solution.majorityElement(new int[]{1,2}).stream().mapToInt(i -> i).toArray());
        TestUtil.run("Test Case #4", new int[]{2}, solution.majorityElement(new int[]{2,2}).stream().mapToInt(i -> i).toArray());
    }
}
