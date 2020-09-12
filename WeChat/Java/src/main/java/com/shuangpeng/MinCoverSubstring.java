package com.shuangpeng;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

// 题目链接：https://mp.weixin.qq.com/s/qnFV9Odi2jzf-ZsAsM6t0A
public class MinCoverSubstring {
    public static String getMinCoverSubstring(String s, String t) {
        if (s == null || t == null) {
            return "";
        }
        int n = s.length();
        int cn = t.length();
        if (cn > n) {
            return "";
        }
        char[] appearChars = new char[cn];
        Map<Integer, Character> map = new LinkedHashMap<>();
        Map<Integer, Character> tempMap = new LinkedHashMap<>();
        int startIndex = -1;
        int endIndex = -1;
        int count = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (map.containsValue(c)) {
                Iterator<Map.Entry<Integer, Character>> iterator = map.entrySet().iterator();
                if (iterator.next().getValue() == c) {
                    iterator.remove();
                }
                map.put(i, c);
            } else if (t.indexOf(c) != -1) {
                if (count < cn) {
                    appearChars[count++] = c;
                    if (count == cn) {
                        Iterator<Map.Entry<Integer, Character>> iterator = map.entrySet().iterator();
                        startIndex = iterator.next().getKey();
                        endIndex = i;
                    }
                    map.put(i, c);
                } else {

                }
            }
        }
        if (startIndex != -1 && endIndex != -1) {
            return s.substring(startIndex, endIndex + 1);
        }
        return "";
    }

    public static void handleMap(Map<Integer, Character> map, Map<Integer, Character> tempMap,
                                 String s, String t, int index, char c, int currentLength) {
        tempMap.put(index, c);
        int start = -1;
        Iterator<Map.Entry<Integer, Character>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Character> entry = iterator.next();
            char ch = entry.getValue();
            if (!tempMap.containsValue(ch)) {
                start = entry.getKey();
                break;
            }
        }
        if (start != -1) {

        }
    }

    public static void main(String[] args) {
        System.out.println("result is: " + getMinCoverSubstring("ADOBECODEBANC", "ABC"));
    }
}
