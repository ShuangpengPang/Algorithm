package com.shuangpeng.Problem.p0001_0100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: (串联所有单词的子串）
 * @Date 2022/6/23 1:20 PM
 **/
public class Problem0030SubstringWithConcatenationOfAllWords {

    public List<Integer> findSubstring0(String s, String[] words) {
        int sN = s.length(), wN = words[0].length();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < wN; ++i) {
            int left = i, right = i;
            Map<String, Integer> temp = new HashMap<>();
            int count = 0;
            while (right + wN <= sN) {
                String word = s.substring(right, right + wN);
                if (!map.containsKey(word)) {
                    right += wN;
                    left = right;
                    temp.clear();
                    count = 0;
                } else {
                    ++count;
                    temp.put(word, temp.getOrDefault(word, 0) + 1);
                    while (temp.get(word) > map.get(word)) {
                        String w = s.substring(left, left + wN);
                        temp.put(w, temp.get(w) - 1);
                        left += wN;
                        --count;
                    }
                    if (count == words.length) {
                        ans.add(left);
                    }
                    right += wN;
                }
            }
        }
        return ans;
    }

    public List<Integer> findSubstring1(String s, String[] words) {
        int n = s.length(), m = words.length, len = words[0].length();
        if (n < m * len) {
            return new ArrayList<>();
        }
        long[] hash = new long[n - len + 1];
        long MOD = 13131313131313L;
        long M = 1;
        for (int i = 0; i < len - 1; ++i) {
            M = M * 26 % MOD;
        }
        long h = 0;
        for (int i = 0; i < len; ++i) {
            h = (h * 26 + s.charAt(i) - 'a') % MOD;
        }
        hash[0] = h;
        for (int i = 1; i <= n - len; ++i) {
            long v = (hash[i - 1] - (s.charAt(i - 1) - 'a') * M % MOD + MOD) % MOD;
            hash[i] = (v * 26 + s.charAt(i + len - 1) - 'a') % MOD;
        }
        Map<Long, Integer> map = new HashMap<>();
        for (String w : words) {
            long v = toHash(w);
            map.put(v, map.getOrDefault(v, 0) + 1);
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i <= n - m * len; ++i) {
            Map<Long, Integer> temp = new HashMap<>();
            boolean valid = true;
            for (int j = 0; j < m; ++j) {
                long v = hash[i + j * len];
                int count = temp.getOrDefault(v, 0) + 1;
                if (count > map.getOrDefault(v, 0)) {
                    valid = false;
                    break;
                }
                temp.put(v, count);
            }
            if (valid) {
                ans.add(i);
            }
        }
        return ans;
    }

    private long toHash(String w) {
        long MOD = 13131313131313L;
        int n = w.length();
        long h = 0;
        for (int i = 0; i < n; ++i) {
            h = (h * 26 + w.charAt(i) - 'a') % MOD;
        }
        return h;
    }

    public List<Integer> findSubstring(String s, String[] words) {
        int m = words.length, n = words[0].length(), ls = s.length();
        int L = m * n;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (i + L > ls) {
                break;
            }
            Map<String, Integer> map = new HashMap<>();
            for (int j = i; j < i + L; j += n) {
                String w = s.substring(j, j + n);
                map.put(w, map.getOrDefault(w, 0) + 1);
            }
            for (String w : words) {
                map.put(w, map.getOrDefault(w, 0) - 1);
                if (map.get(w) == 0) {
                    map.remove(w);
                }
            }
            for (int j = i; j + L <= ls; j += n) {
                if (j != i) {
                    String w1 = s.substring(j + L - n, j + L);
                    map.put(w1, map.getOrDefault(w1, 0) + 1);
                    if (map.get(w1) == 0) {
                        map.remove(w1);
                    }
                    String w2 = s.substring(j - n, j);
                    map.put(w2, map.getOrDefault(w2, 0) - 1);
                    if (map.get(w2) == 0) {
                        map.remove(w2);
                    }
                }
                if (map.isEmpty()) {
                    ans.add(j);
                }
            }
        }
        return ans;
    }
}
