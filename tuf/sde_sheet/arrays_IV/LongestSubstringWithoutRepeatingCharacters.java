package tuf.sde_sheet.arrays_IV;

import java.util.HashMap;
import java.util.Map;

import utils.TestUtil;

public class LongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> previousIndex = new HashMap<>();

        int lastRepeatedIndex = -1, maxLength = 0;

        for (int i = 0; i < s.length(); i++) {
            if (previousIndex.containsKey(s.charAt(i))) {
                lastRepeatedIndex = Math.max(lastRepeatedIndex, previousIndex.get(s.charAt(i)));
            }
            maxLength = Math.max(maxLength, i - lastRepeatedIndex);
            previousIndex.put(s.charAt(i), i);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters solution = new LongestSubstringWithoutRepeatingCharacters();

        TestUtil.run("Test Case #1", 3, solution.lengthOfLongestSubstring("abcabcbb"));
        TestUtil.run("Test Case #2", 1, solution.lengthOfLongestSubstring("bbbbb"));
        TestUtil.run("Test Case #3", 3, solution.lengthOfLongestSubstring("pwwkew"));
        TestUtil.run("Test Case #4", 7, solution.lengthOfLongestSubstring("abcdefcabg"));
        TestUtil.run("Test Case #5", 0, solution.lengthOfLongestSubstring(""));
    }
}
