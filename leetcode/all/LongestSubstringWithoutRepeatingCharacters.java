package leetcode.all;

import java.util.Map;
import java.util.HashMap;

public class LongestSubstringWithoutRepeatingCharacters {
    private int lengthOfLongestSubstring(String s) {
        int maxLength = 0;

        Map<Character, Integer> lastSeenAt = new HashMap<>();
        int start = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int lastSeen = lastSeenAt.getOrDefault(c, -1);

            int currentLength = i - Math.max(start, lastSeen);

            maxLength = Math.max(maxLength, currentLength);
            lastSeenAt.put(c, i);
            start = Math.max(start, lastSeen);
        }

        return maxLength;
    }
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters solution = new LongestSubstringWithoutRepeatingCharacters();

        System.out.println(solution.lengthOfLongestSubstring("abcabcb"));
        System.out.println(solution.lengthOfLongestSubstring("bbbbb"));
        System.out.println(solution.lengthOfLongestSubstring("pwwkew"));
        System.out.println(solution.lengthOfLongestSubstring(""));
        System.out.println(solution.lengthOfLongestSubstring("abcdaxyz"));
        System.out.println(solution.lengthOfLongestSubstring("abcdaba"));
    }
}