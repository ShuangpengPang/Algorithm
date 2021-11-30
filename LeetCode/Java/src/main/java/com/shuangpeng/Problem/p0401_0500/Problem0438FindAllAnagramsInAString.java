package com.shuangpeng.Problem.p0401_0500;

import java.util.ArrayList;
import java.util.List;

public class Problem0438FindAllAnagramsInAString {

    public List<Integer> findAnagrams0(String s, String p) {
        if (s == null || p == null || s.length() * p.length() == 0 || p.length() > s.length()) {
            return new ArrayList<>();
        }
        char[] map = new char[128];
        int pLength = p.length();
        for (int i = 0; i < pLength; i++) {
            map[p.charAt(i)]++;
        }
        int matchCount = 0;
        for (int i = 0; i < map.length; i++) {
            matchCount += map[i] > 0 ? 1 : 0;
        }
        int count = 0;
        List<Integer> answer = new ArrayList<>();
        char[] result = new char[map.length];
        int sLength = s.length();
        for (int i = 0; i < sLength; i++) {
            char ch = s.charAt(i);
            if (map[ch] > 0) {
                result[ch]++;
                if (result[ch] == map[ch]) {
                    count++;
                }
            }
            if (i - pLength >= 0) {
                ch = s.charAt(i - pLength);
                if (map[ch] > 0) {
                    if (result[ch] == map[ch]) {
                        count--;
                    }
                    result[ch]--;
                }
            }
            if (count == matchCount) {
                answer.add(i - pLength + 1);
            }
        }
        return answer;
    }

    public List<Integer> findAnagrams1(String s, String p) {
        int n1 = s.length(), n2 = p.length();
        List<Integer> ans = new ArrayList<>();
        if (n1 < n2) {
            return ans;
        }
        final int N = 26;
        int[] counts = new int[N];
        for (int i = 0; i < n2; ++i) {
            ++counts[p.charAt(i) - 'a'];
        }
        for (int i = 0; i < n1; ++i) {
            --counts[s.charAt(i) - 'a'];
            if (i >= n2) {
                ++counts[s.charAt(i - n2) - 'a'];
            }
            if (i >= n2 - 1 && check(counts)) {
                ans.add(i - n2 + 1);
            }
        }
        return ans;
    }

    private boolean check(int[] counts) {
        int n = counts.length;
        for (int i = 0; i < n; ++i) {
            if (counts[i] != 0) {
                return false;
            }
        }
        return true;
    }

    public List<Integer> findAnagrams2(String s, String p) {
        int n1 = s.length(), n2 = p.length();
        List<Integer> ans = new ArrayList<>();
        if (n1 < n2) {
            return ans;
        }
        final int N = 26;
        int[] counts = new int[N];
        int differ = 0;
        for (int i = 0; i < n2; ++i) {
            int j = p.charAt(i) - 'a';
            if (counts[j] == 0) {
                ++differ;
            }
            ++counts[j];
        }
        for (int i = 0; i < n1; ++i) {
            int j = s.charAt(i) - 'a';
            if (counts[j] == 1) {
                --differ;
            } else if (counts[j] == 0) {
                ++differ;
            }
            --counts[j];
            if (i >= n2) {
                int k = s.charAt(i - n2) - 'a';
                if (counts[k] == -1) {
                    --differ;
                } else if (counts[k] == 0) {
                    ++differ;
                }
                ++counts[k];
            }
            if (differ == 0) {
                ans.add(i - n2 + 1);
            }
        }
        return ans;
    }

    public List<Integer> findAnagrams(String s, String p) {
        int n1 = s.length(), n2 = p.length();
        List<Integer> ans = new ArrayList<>();
        if (n1 < n2) {
            return ans;
        }
        final int N = 26;
        int[] counts = new int[N];
        for (int i = 0; i < n2; ++i) {
            ++counts[p.charAt(i) - 'a'];
        }
        int l = 0, r = 0;
        while (r < n1) {
            int i = s.charAt(r) - 'a';
            if (counts[i] > 0) {
                ++r;
                --counts[i];
                if (r - l == n2) {
                    ans.add(l);
                }
            } else {
                ++counts[s.charAt(l++) - 'a'];
            }
        }
        return ans;
    }
}
