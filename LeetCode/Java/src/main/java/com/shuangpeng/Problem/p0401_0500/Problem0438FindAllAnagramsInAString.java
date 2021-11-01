package com.shuangpeng.Problem.p0401_0500;

import java.util.ArrayList;
import java.util.List;

public class Problem0438FindAllAnagramsInAString {

    public List<Integer> findAnagrams(String s, String p) {
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
}
