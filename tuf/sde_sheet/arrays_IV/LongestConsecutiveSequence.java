package tuf.sde_sheet.arrays_IV;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

import utils.TestUtil;

public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        int max = 0;
        Set<Integer> existingNums = new HashSet<>(), chainLeaders = new HashSet<>();
        Map<Integer, Integer> numFollowingLeader = new HashMap<>();

        for (int num: nums) {
            existingNums.add(num);
            int chainLeader = num;

            if (existingNums.contains(num + 1)) {
                chainLeaders.remove(num + 1);
            }

            if (existingNums.contains(num - 1)) {
                chainLeaders.remove(num);
                chainLeader = numFollowingLeader.get(num-1);
            }

            numFollowingLeader.put(num+1, chainLeader);
            numFollowingLeader.put(num, chainLeader);

            chainLeaders.add(chainLeader);

        }

        for (int leader: chainLeaders) {
            if (numFollowingLeader.get(leader) == leader) {
                int chainLength = 0;
                for (int i = leader; existingNums.contains(i); i++) {
                    chainLength++;
                }
                max = Math.max(chainLength, max);

            }
        }

        return max;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence solution = new LongestConsecutiveSequence();

        TestUtil.TestSuite suite = new TestUtil.TestSuite();
        suite.only("Test Case #4", 6, solution.longestConsecutive(new int[]{4,1,3,2,6,5}));
        
        suite.test("Test Case #1", 4, solution.longestConsecutive(new int[]{100,4,200,1,3,2}));
        suite.only("Test Case #2", 9, solution.longestConsecutive(new int[]{0,3,7,2,5,8,4,6,0,1}));
        suite.test("Test Case #3", 10, solution.longestConsecutive(new int[]{0,1,2,3,4,5,6,7,8,9}));

        
        suite.run();
    }
}
