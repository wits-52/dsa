package leetcode.all;

import java.util.*;

public class GroupAnagrams {
    private List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagramGroups = new HashMap<>();
        for (String s: strs) {
            int[] freq = new int[26];

            for (int i = 0; i < s.length(); i++) {
                freq[s.charAt(i) - 'a']++;
            }
            StringBuilder codeBuilder = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                codeBuilder.append(freq[i]).append(';');
            }
            String code = codeBuilder.toString();

            anagramGroups
            .computeIfAbsent(code, k -> new ArrayList<>())
            .add(s);
        }

        return new ArrayList<>(anagramGroups.values());
    }
    public static void main(String[] args) {
        
    }
}
