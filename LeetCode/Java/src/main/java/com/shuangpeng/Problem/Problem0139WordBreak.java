package com.shuangpeng.Problem;

import java.util.*;

public class Problem0139WordBreak {

    public boolean wordBreak0(String s, List<String> wordDict) {
        return wordBreakRecursive(s, wordDict, new HashMap<>(), 0);
    }

    public boolean wordBreakRecursive(String s, List<String> wordDict, Map<Integer, Boolean> map, int start) {
        if (start == s.length()) {
            return true;
        }
        if (map.containsKey(start)) {
            return map.get(start);
        }
        for (int i = 0; i < wordDict.size(); i++) {
            boolean isStartWith = true;
            String word = wordDict.get(i);
            int length = word.length();
            for (int j = 0; j < length; j++) {
                if (start + j >= s.length() || s.charAt(start + j) != word.charAt(j)) {
                    isStartWith = false;
                    break;
                }
            }
            if (isStartWith) {
                if (wordBreakRecursive(s, wordDict, map, start + length)) {
                    map.put(start, true);
                    return true;
                }
            }
        }
        map.put(start, false);
        return false;
    }

//    public static void main(String[] args) {
//        Problem0139WordBreak a = new Problem0139WordBreak();
//        String s = "leetcode";
//        List<String> wordDict = new ArrayList<>();
//        wordDict.add("leet");
//        wordDict.add("code");
//        a.wordBreak(s, wordDict);
//    }

    public boolean wordBreak(String s, List<String> wordDict) {
        int length = s.length();
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;
        Set<String> set = new HashSet<>(wordDict.size());
        for (int i = 0; i < wordDict.size(); i++) {
            set.add(wordDict.get(i));
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = 1; i - j >= -1; j++) {
                if (dp[i - j + 1] && set.contains(s.substring(i - j + 1, i + 1))) {
                    dp[i + 1] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
