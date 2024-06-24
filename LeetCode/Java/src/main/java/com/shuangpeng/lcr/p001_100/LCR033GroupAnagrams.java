package com.shuangpeng.lcr.p001_100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR033GroupAnagrams（字母异位词分组）
 * @date 2024/6/19 11:13 AM
 */
public class LCR033GroupAnagrams {

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] cs = s.toCharArray();
            Arrays.sort(cs);
            String key = new String(cs);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
