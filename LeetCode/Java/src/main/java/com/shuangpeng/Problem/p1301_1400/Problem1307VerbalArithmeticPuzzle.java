package com.shuangpeng.Problem.p1301_1400;

import java.util.*;

/**
 * @Description: Problem1307VerbalArithmeticPuzzle（口算难题）
 * @Date 2022/7/30 3:37 PM
 * @Version 1.0
 */
public class Problem1307VerbalArithmeticPuzzle {

    Map<Character, Integer> map, map1, map2;
    boolean[] used;
    List<Character> resultList, wordList;
    Set<Character> nonZeroSet;

    // TLE
    public boolean isSolvable(String[] words, String result) {
        map = new HashMap<>();
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        used = new boolean[10];
        nonZeroSet = new HashSet<>();
        for (String w : words) {
            if (w.length() > 1) {
                nonZeroSet.add(w.charAt(0));
            }
            calculate(map1, w);
        }
        if (result.length() > 1) {
            nonZeroSet.add(result.charAt(0));
        }
        calculate(map2, result);
        for (char c : map1.keySet()) {
            map.put(c, -1);
        }
        for (char c : map2.keySet()) {
            map.put(c, -1);
        }
        resultList = new ArrayList<>(map2.keySet());
        wordList = new ArrayList<>();
        for (char c : map1.keySet()) {
            if (!map2.containsKey(c)) {
                wordList.add(c);
            }
        }
        return check(0, 0);
    }

    private boolean check(int idx, int sum) {
        if (idx < resultList.size()) {
            char c = resultList.get(idx);
            for (int i = 0; i < 10; i++) {
                if (used[i] || i == 0 && nonZeroSet.contains(c)) {
                    continue;
                }
                used[i] = true;
                map.put(c, i);
                if (check(idx + 1, sum + i * map2.get(c))) {
                    return true;
                }
                used[i] = false;
                map.put(c, -1);
            }
            return false;
        }
        for (char c : resultList) {
            sum -= map.get(c) * map1.getOrDefault(c, 0);
        }
        return dfs(0, sum);
    }

    private boolean dfs(int idx, int sum) {
        if (sum < 0) {
            return false;
        }
        if (idx == wordList.size()) {
            return sum == 0;
        }
        char c = wordList.get(idx);
        for (int i = 0; i < 10; i++) {
            if (used[i] || i == 0 && nonZeroSet.contains(c)) {
                continue;
            }
            used[i] = true;
            map.put(c, i);
            if (dfs(idx + 1, sum - i * map1.get(c))) {
                return true;
            }
            map.put(c, -1);
            used[i] = false;
        }
        return false;
    }

    private void calculate(Map<Character, Integer> map, String w) {
        int n = w.length(), base = 1;
        for (int i = n - 1; i >= 0; i--) {
            char c = w.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + base);
            base *= 10;
        }
    }
}

class Problem1307VerbalArithmeticPuzzle0 {

    Map<Character, Integer> map;
    Set<Character> nonZeroSet;
    boolean[] used;
    String[] words;
    String result;

    public boolean isSolvable(String[] words, String result) {
        this.words = words;
        this.result = result;
        map = new HashMap<>();
        nonZeroSet = new HashSet();
        used = new boolean[10];
        int maxLen = 0;
        for (String w : words) {
            if (w.length() > 1) {
                nonZeroSet.add(w.charAt(0));
            }
            maxLen = Math.max(maxLen, w.length());
        }
        if (maxLen > result.length()) {
            return false;
        }
        if (result.length() > 1) {
            nonZeroSet.add(result.charAt(0));
        }
        return dfs(0, 0, 0);
    }

    private boolean dfs(int idx1, int idx2, int sum) {
        if (idx1 == result.length()) {
            return sum == 0 && (map.get(result.charAt(0)) != 0 || result.length() == 1);
        }
        if (idx2 < words.length) {
            String w = words[idx2];
            int i = w.length() - idx1 - 1;
            if (i < 0) {
                return dfs(idx1, idx2 + 1, sum);
            }
            char c = w.charAt(i);
            if (map.containsKey(c)) {
                return dfs(idx1, idx2 + 1, sum + map.get(c));
            }
            for (int j = 0; j < 10; j++) {
                if (!used[j]) {
                    if (j == 0 && nonZeroSet.contains(c)) {
                        continue;
                    }
                    used[j] = true;
                    map.put(c, j);
                    if (dfs(idx1, idx2 + 1, sum + j)) {
                        return true;
                    }
                    map.remove(c);
                    used[j] = false;
                }
            }
            return false;
        }
        int n = result.length();
        int mod = sum % 10;
        char c = result.charAt(n - idx1 - 1);
        if (map.containsKey(c)) {
            if (map.get(c) != mod || mod == 0 && nonZeroSet.contains(c)) {
                return false;
            }
            return dfs(idx1 + 1, 0, sum / 10);
        }
        if (used[mod]) {
            return false;
        }
        used[mod] = true;
        map.put(c, mod);
        boolean ans = dfs(idx1 + 1, 0, sum / 10);
        map.remove(c);
        used[mod] = false;
        return ans;
    }

//    public static void main(String[] args) {
//        Problem1307VerbalArithmeticPuzzle0 a = new Problem1307VerbalArithmeticPuzzle0();
//        String[] words = {"AB","CD","EF"};
//        String result = "GHIJ";
//        boolean ans = a.isSolvable(words, result);
//        int i = 1;
//    }
}
