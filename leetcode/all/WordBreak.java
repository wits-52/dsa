package leetcode.all;

import java.util.Arrays;
import java.util.List;

public class WordBreak {
    class Trie {
        Trie[] branches = new Trie[26];
        boolean[] wordEndsHere = new boolean[26];
    }
    int[][] dp = new int[][]{};
    public void addToTrie(String word, Trie t, int idx) {
        if (idx >= word.length()) return;

        char c = word.charAt(idx);

        if (t.branches[c - 'a'] == null) {
            t.branches[c - 'a'] = new Trie();
        }


        if (idx == word.length() - 1) {
            t.wordEndsHere[c - 'a'] = true;
        } else {
            addToTrie(word, t.branches[c - 'a'], idx + 1);
        }
    }
    private boolean wordBreakPossible(Trie t, String s, int idx, Trie rootTrie, int trieDepth) {
        if(dp[idx][trieDepth] != 0) {
            return dp[idx][trieDepth] == 1;
        }
        if (idx == s.length() - 1) {
            boolean result = t.branches[s.charAt(idx) - 'a'] != null && t.wordEndsHere[s.charAt(idx) - 'a'];
            dp[idx][trieDepth] = result ? 1 : 2;

            return result;
        }
        char c = s.charAt(idx);

        if (t.branches[c - 'a'] == null) {
            dp[idx][trieDepth] = 2;
            return false;
        }
        if (t.wordEndsHere[c - 'a']) {
            boolean result =  this.wordBreakPossible(t.branches[c - 'a'], s, idx + 1, rootTrie, trieDepth + 1) || 
            this.wordBreakPossible(rootTrie, s, idx + 1, rootTrie, 0);

            dp[idx][trieDepth] = result ? 1 : 2;

            return result;
        }
        boolean result = this.wordBreakPossible(t.branches[c - 'a'], s, idx + 1, rootTrie, trieDepth +1);

        dp[idx][trieDepth] = result ? 1 : 2;

        return result;
    }
    public boolean wordBreak(String s, List<String> wordDict) {
        Trie trie = new Trie();
        dp = new int[s.length() + 1][s.length() + 1];
        for (String word: wordDict) {
            this.addToTrie(word, trie, 0);
        }

        return this.wordBreakPossible(trie, s, 0, trie, 0);
    }

    public static void main(String[] args) {
        WordBreak solution = new WordBreak();

        System.out.println(solution.wordBreak("abcd", Arrays.asList(new String[]{"a","abc","b","cd"})));


        System.out.println(solution.wordBreak("leetcode", Arrays.asList(new String[]{"leet","code"})));

        System.out.println(solution.wordBreak("applepenapple", Arrays.asList(new String[]{"apple","pen"})));

        System.out.println(solution.wordBreak("catsandog", Arrays.asList(new String[]{"cats","dog","sand","and","cat"})));

        System.out.println(solution.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
        Arrays.asList(new String[]{"a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"})));

    }
}
