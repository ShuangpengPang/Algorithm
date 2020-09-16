package com.shuangpeng;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

// 题目链接：https://mp.weixin.qq.com/s/qnFV9Odi2jzf-ZsAsM6t0A
public class MinCoverSubstring {
    static class Node {
        int index;
        char ch;

        Node(int index, char ch) {
            this.index = index;
            this.ch = ch;
        }
    }

    public static String slideWindowMethod(String s, String t) {
        if (s == null || t == null) {
            return "";
        }
        int n = s.length();
        int cn = t.length();
        if (cn > n) {
            return "";
        }

        Map<Character, Integer> needs = new HashMap<>();
        Map<Character, Integer> windows = new HashMap<>();
        for (int i = 0; i < cn; i++) {
            char c = t.charAt(i);
            needs.compute(c, (k, v) -> v == null ? 1 : v + 1);
        }
        int matches = 0;
        int left = 0;
        int start = 0;
        int end = 0;
        int length = Integer.MAX_VALUE;
        for (int right = 0; right < n; right++) {
            char c = s.charAt(right);
            if (needs.containsKey(c)) {
                windows.compute(c, (k, v) -> v == null ? 1 : v + 1);
                if (needs.get(c) == windows.get(c)) {
                    matches++;
                    while (matches == needs.size()) {
                        if (right - left < length) {
                            start = left;
                            end = right;
                            length = end - start;
                        }
                        char leftChar = s.charAt(left);
                        if (windows.containsKey(leftChar)) {
                            windows.put(leftChar, windows.get(leftChar) - 1);
                            if (windows.get(leftChar) < needs.get(leftChar)) {
                                matches--;
                            }
                        }
                        left++;
                    }
                }
            }
        }
        if (length != Integer.MAX_VALUE) {
            return s.substring(start, end + 1);
        }
        return "";
    }

    public static String slideWindowMethod1(String s, String t) {
        if (s == null || t == null) {
            return "";
        }
        int n = s.length();
        int cn = t.length();
        if (cn > n) {
            return "";
        }
        int[] map = new int[cn];
        Arrays.fill(map, -1);
        int count = 0;
        int startIndex = -1;
        int endIndex = n;
        for (int r = 0; r < n; r++) {
            char c = s.charAt(r);
            int idx = t.indexOf(c);
            if (idx != -1) {
                if (map[idx] == -1) {
                    map[idx] = r;
                    count++;
                    if (count == cn) {
                        int start = n;
                        int mapIndex = 0;
                        for (int i = 0; i < cn; i++) {
                            int index = map[i];
                            if (index != -1 && index < start) {
                                start = index;
                                mapIndex = i;
                            }
                        }
                        if ((r - start) < (endIndex - startIndex)) {
                            startIndex = start;
                            endIndex = r;
                        }
                        map[mapIndex] = -1;
                        count--;
                    }
                } else {
                    map[idx] = r;
                }
            }
        }
        if (startIndex > -1 && endIndex < n) {
            return s.substring(startIndex, endIndex + 1);
        }
        return "";
    }

    public static String slideWindowMethod0(String s, String t) {
        if (s == null || t == null) {
            return "";
        }
        int n = s.length();
        int cn = t.length();
        if (cn > n) {
            return "";
        }
        ArrayList<Node> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (t.indexOf(c) != -1) {
                list.add(new Node(i, c));
            }
        }

        int startIndex = -1;
        int endIndex = -1;
        int currentLength = Integer.MAX_VALUE;

        int size = list.size();
        List<Node> windowList = new ArrayList<>();
        Set<Character> targetSet = new HashSet<>();
        windowList.add(list.get(0));
        targetSet.add(list.get(0).ch);
        for (int right = 1; right < size; right++) {
            Node node = list.get(right);
            windowList.add(node);
            Node firstNode = windowList.get(0);
            if (firstNode.ch == node.ch) {
                windowList.remove(0);
            }
            if (!targetSet.contains(node.ch)) {
                targetSet.add(node.ch);
                if (targetSet.size() == cn) {
                    int start = firstNode.index;
                    int end = windowList.get(windowList.size() - 1).index;
                    int length = end - start + 1;
                    if (length < currentLength) {
                        startIndex = start;
                        endIndex = end;
                        currentLength = length;
                    }
                    windowList.remove(0);
                    targetSet.remove(firstNode.ch);
                }
            }
        }
        if (startIndex != -1 && endIndex != -1) {
            return s.substring(startIndex, endIndex + 1);
        }
        return "";
    }

    public static String getMinCoverSubstring(String s, String t) {
        if (s == null || t == null) {
            return "";
        }
        int n = s.length();
        int cn = t.length();
        if (cn > n) {
            return "";
        }
        ArrayList<Node> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (t.indexOf(c) != -1) {
                list.add(new Node(i, c));
            }
        }

        int startIndex = -1;
        int endIndex = -1;
        int currentLength = Integer.MAX_VALUE;

        int size = list.size();
        for (int i = 0; i < size; i++) {
            Node node = list.get(i);
            String string = "" + node.ch;
            int start = node.index;
            int end = -1;
            for (int j = i + 1; j < size; j++) {
                char ch = list.get(j).ch;
                if (ch == node.ch) {
                    break;
                }
                if (string.indexOf(ch) == -1) {
                    string += ch;
                }
                if (string.length() == cn) {
                    end = list.get(j).index;
                    break;
                }
            }
            if (end != -1 && (end - start + 1) < currentLength) {
                startIndex = start;
                endIndex = end;
                currentLength = end - start + 1;
            }
        }
        if (startIndex != -1 && endIndex != -1) {
            return s.substring(startIndex, endIndex + 1);
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println("result is: " + slideWindowMethod("ADOBECODEBANC", "ABC"));
    }
}
