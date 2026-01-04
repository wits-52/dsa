package neetcode;

import utils.TestUtil;

public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] charMap = new int[26];

        for (int i = 0; i < s.length(); i++) {
            charMap[(int)s.charAt(i) - (int)'a']++;
            charMap[(int)t.charAt(i) - (int)'a']--;
        }

        for (int c: charMap) {
            if (c != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        ValidAnagram solution = new ValidAnagram();

        TestUtil.run("Test Case #1", true, solution.isAnagram("racecar", "carrace"));
        TestUtil.run("Test Case #2", false, solution.isAnagram("jar", "jam"));
        TestUtil.run("Test Case #3", true, solution.isAnagram("anagram", "nagaram"));
        TestUtil.run("Test Case #4", false, solution.isAnagram("rat", "car"));
    }
}
