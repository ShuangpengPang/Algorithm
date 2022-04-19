package com.shuangpeng.Problem.p0801_0900;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description: Problem0819MostCommonWord
 * @Date 2022/4/19 5:01 PM
 * @Version 1.0
 */
public class Problem0819MostCommonWord {

    public String mostCommonWord0(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>();
        for (String s : banned) {
            set.add(s);
        }
        paragraph = paragraph.toLowerCase();
        int n = paragraph.length();
        int i = 0;
        int mostCount = 0;
        String ans = "";
        Map<String, Integer> map = new HashMap<>();
        while (i < n) {
            char c = paragraph.charAt(i);
            if (c >= 'a' && c <= 'z') {
                int j = i;
                while (j < n && Character.isLetter(paragraph.charAt(j))) {
                    ++j;
                }
                String s = paragraph.substring(i, j);
                if (!set.contains(s)) {
                    int count = map.getOrDefault(s, 0) + 1;
                    map.put(s, count);
                    if (count > mostCount) {
                        mostCount = count;
                        ans = s;
                    }
                }
                i = j + 1;
            } else {
                ++i;
            }
        }
        return ans;
    }

    public String mostCommonWord1(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>();
        for (String s : banned) {
            set.add(s);
        }
        Map<String, Integer> map = new HashMap<>();
        int maxFrequency = 0;
        String ans = "";
        StringBuilder sb = new StringBuilder();
        int n = paragraph.length();
        for (int i = 0; i <= n; ++i) {
            if (i < n && Character.isLetter(paragraph.charAt(i))) {
                sb.append(Character.toLowerCase(paragraph.charAt(i)));
            } else if (sb.length() > 0) {
                String s = sb.toString();
                if (!set.contains(s)) {
                    int frequency = map.getOrDefault(s, 0) + 1;
                    map.put(s, frequency);
                    if (frequency > maxFrequency) {
                        maxFrequency = frequency;
                        ans = s;
                    }
                }
                sb.setLength(0);
            }
        }
        return ans;
    }

    public String mostCommonWord(String paragraph, String[] banned) {
        int n = paragraph.length();
        paragraph = paragraph.toLowerCase();
        Set<String> set = new HashSet<>();
        for (String s : banned) {
            set.add(s);
        }
        Map<String, Integer> frequencies = new HashMap<>();
        int maxFrequency = 0;
        String ans = "";
        for (int i = 0, j = 0; j <= n; ++j) {
            if (j == n || paragraph.charAt(j) < 'a' || paragraph.charAt(j) > 'z') {
                if (i < j) {
                    String s = paragraph.substring(i, j);
                    if (!set.contains(s)) {
                        int frequency = frequencies.getOrDefault(s, 0) + 1;
                        frequencies.put(s, frequency);
                        if (frequency > maxFrequency) {
                            maxFrequency = frequency;
                            ans = s;
                        }
                    }
                }
                i = j + 1;
            }
        }
        return ans;
    }
}
