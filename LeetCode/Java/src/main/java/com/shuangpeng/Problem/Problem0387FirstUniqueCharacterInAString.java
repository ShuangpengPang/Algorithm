package com.shuangpeng.Problem;

import java.util.*;

public class Problem0387FirstUniqueCharacterInAString {

//    loveleetcode

//    public static void main(String[] args) {
//        Problem0387FirstUniqueCharacterInAString a = new Problem0387FirstUniqueCharacterInAString();
//        String s = "loveleetcode";
//        a.firstUniqChar(s);
//    }

    public int firstUniqChar0(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        Map<Character, int[]> map = new HashMap<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                int[] array = map.get(c);
                array[0]++;
            } else {
                map.put(c, new int[]{1, i});
            }
        }
        int answer = Integer.MAX_VALUE;
        for (char c : map.keySet()) {
            if (map.get(c)[0] == 1 && map.get(c)[1] < answer) {
                answer = map.get(c)[1];
            }
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    public int firstUniqChar1(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        Map<Character, Integer> map = new HashMap<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < length; i++) {
            if (map.get(s.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }

    public int firstUniqChar2(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        Map<Character, Integer> map = new HashMap<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, -1);
            } else {
                map.put(c, i);
            }
        }
        int answer = Integer.MAX_VALUE;
        for (char c : map.keySet()) {
            if (map.get(c) != -1) {
                answer = Math.min(answer, map.get(c));
            }
        }
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    class KeyValuePair {
        char c;
        int index;

        public KeyValuePair(char c, int index) {
            this.c = c;
            this.index = index;
        }
    }

    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        Map<Character, Integer> map = new HashMap<>();
        Deque<KeyValuePair> list = new LinkedList<>();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, -1);
                while (!list.isEmpty() && map.get(list.peekFirst().c) == -1) {
                    list.pollFirst();
                }
            } else {
                map.put(c, 1);
                list.offer(new KeyValuePair(c, i));
            }
        }
        return list.isEmpty() ? -1 : list.getFirst().index;
    }
}
