package com.shuangpeng.competition.双周赛.第066场双周赛;

import java.util.HashMap;
import java.util.Map;

public class Problem2085CountCommonWordsWithOneOccurrence {

    // 比赛时写法
    public int countWords0(String[] words1, String[] words2) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();
        for (String str : words1) {
            map1.put(str, map1.getOrDefault(str, 0) + 1);
        }
        for (String str : words2) {
            map2.put(str, map2.getOrDefault(str, 0) + 1);
        }
        int ans = 0;
        for (String str : map1.keySet()) {
            if (map1.get(str) == 1 && map2.getOrDefault(str, 0) == 1) {
                ++ans;
            }
        }
        return ans;
    }

    public int countWords(String[] words1, String[] words2) {
        Map<String, Integer> map1 = new HashMap<>();
        for (String word : words1) {
            map1.put(word, map1.getOrDefault(word, 0) + 1);
        }
        Map<String, Integer> map2 = new HashMap<>();
        for (String word : words2) {
            map2.put(word, map2.getOrDefault(word, 0) + 1);
        }
        int ans = 0;
        for (String key : map1.keySet()) {
            if (map1.get(key) == 1 && map2.getOrDefault(key, 0) == 1) {
                ++ans;
            }
        }
        return ans;
    }
}
