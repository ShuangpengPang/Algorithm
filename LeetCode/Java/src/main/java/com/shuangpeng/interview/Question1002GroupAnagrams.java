package com.shuangpeng.interview;

import java.util.*;

public class Question1002GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        final int N = 26;
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] array = new int[N];
            for (char c : str.toCharArray()) {
                array[c - 'a']++;
            }
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < N; i++) {
                if (array[i] > 0) {
                    builder.append((char) ('a' + i)).append(array[i]);
                }
            }
            String key = builder.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }
}
