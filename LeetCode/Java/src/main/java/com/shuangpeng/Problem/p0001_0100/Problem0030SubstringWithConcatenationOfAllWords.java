package com.shuangpeng.Problem.p0001_0100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem0030SubstringWithConcatenationOfAllWords {

    public List<Integer> findSubstring(String s, String[] words) {
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
}
