package com.shuangpeng.competition.第249场周赛;

import java.util.*;

public class Problem1930 {

    public int countPalindromicSubsequence0(String s) {
        Map<Character, Integer> posMap = new HashMap<>();
        Map<Character, Set<Character>> resultMap = new HashMap<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (!posMap.containsKey(c)) {
                int count = 1;
                for (int j = i + 1; j < n; j++) {
                    if (s.charAt(j) == c) {
                        count++;
                        if (count >= 3) {
                            Set<Character> set = new HashSet<>();
                            set.add(c);
                            resultMap.put(c, set);
                            break;
                        }
                    }
                }
            } else {
                Set<Character> set = resultMap.getOrDefault(c, new HashSet<>());
                if (set.size() < 26) {
                    for (int j = posMap.get(c) + 1; j < i; j++) {
                        set.add(s.charAt(j));
                    }
                    resultMap.put(c, set);
                }
            }
            posMap.put(c, i);
        }
        int answer = 0;
        for (Map.Entry<Character, Set<Character>> entry : resultMap.entrySet()) {
            answer += entry.getValue().size();
        }
        return answer;
    }

    public int countPalindromicSubsequence1(String s) {
        int N = 26;
        int[][] pos = new int[N][2];
        Set<Integer>[] sets = new Set[N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(pos[i], -1);
            sets[i] = new HashSet<>();
        }
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int j = s.charAt(i) - 'a';
            if (pos[j][0] == -1) {
                pos[j][0] = i;
            } else {
                pos[j][1] = i;
            }
        }
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            for (int j = 0; j < N; j++) {
                if (pos[j][0] < i && i < pos[j][1]) {
                    sets[j].add(c);
                }
            }
        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer += sets[i].size();
        }
        return answer;
    }

    public int countPalindromicSubsequence2(String s) {
        int n = s.length();
        int answer = 0;
        for (char c = 'a'; c <= 'z'; c++) {
            int left = 0;
            while (left < n) {
                if (s.charAt(left) == c) {
                    break;
                }
                left++;
            }
            int right = n - 1;
            while (right >= 0) {
                if (s.charAt(right) == c) {
                    break;
                }
                right--;
            }
            if (right - left < 2) {
                continue;
            }
            Set<Character> set = new HashSet<>();
            for (int k = left + 1; k < right; k++) {
                set.add(s.charAt(k));
            }
            answer += set.size();
        }
        return answer;
    }

    public int countPalindromicSubsequence(String s) {
        int n = s.length();
        int[] preSet = new int[n];
        int state = 0;
        for (int i = 0; i < n; i++) {
            state |= (1 << (s.charAt(i) - 'a'));
            preSet[i] = state;
        }
        state = 0;
        int[] sufSet = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            state |= (1 << (s.charAt(i) - 'a'));
            sufSet[i] = state;
        }
        int N = 26;
        int[] set = new int[N];
        for (int i = 1; i < n - 1; i++) {
            set[s.charAt(i) - 'a'] |= (preSet[i - 1] & sufSet[i + 1]);
        }
        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer += Integer.bitCount(set[i]);
        }
        return answer;
    }
}
