package com.shuangpeng.Problem;

import java.util.HashMap;
import java.util.Map;

public class Problem0242ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }
        int length = s.length();
        Map<Character, Integer> map = new HashMap<>(26);
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        for (int i = 0; i < length; i++) {
            char ch = t.charAt(i);
            if (!map.containsKey(ch)) {
                return false;
            }
            map.put(ch, map.get(ch) - 1);
            if (map.get(ch) < 0) {
                return false;
            }
        }
        return true;
    }
}
