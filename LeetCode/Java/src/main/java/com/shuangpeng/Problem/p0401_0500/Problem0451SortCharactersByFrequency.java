package com.shuangpeng.Problem.p0401_0500;

import java.util.*;

public class Problem0451SortCharactersByFrequency {

    public String frequencySort0(String s) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        queue.addAll(map.entrySet());
        Iterator<Map.Entry<Character, Integer>> iterator = queue.iterator();
        StringBuilder builder = new StringBuilder();
        while (iterator.hasNext()) {
            Map.Entry<Character, Integer> entry = iterator.next();
            for (int i = 0; i < entry.getValue(); i++) {
                builder.append(entry.getKey());
            }
        }
        return builder.toString();
    }

    public String frequencySort1(String s) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        int size = list.size();
        quickSort(list, 0, size - 1);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            Map.Entry<Character, Integer> entry = list.get(i);
            for (int j = 0; j < entry.getValue(); j++) {
                builder.append(entry.getKey());
            }
        }
        return builder.toString();
    }

    private void quickSort(List<Map.Entry<Character, Integer>> list, int start, int end) {
        if (start >= end) {
            return;
        }
        Map.Entry<Character, Integer> entry = list.get(end);
        int p = start;
        for (int i = start; i < end; i++) {
            if (list.get(i).getValue() >= entry.getValue()) {
                swap(list, p, i);
                p++;
            }
        }
        swap(list, p, end);
        quickSort(list, start, p - 1);
        quickSort(list, p + 1, end);
    }

    private void swap(List<Map.Entry<Character, Integer>> list, int i, int j) {
        if (i == j) {
            return;
        }
        Map.Entry<Character, Integer> temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    public String frequencySort(String s) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();
        int maxFreq = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
            maxFreq = Math.max(maxFreq, map.get(c));
        }
        List<Character>[] bucket = new List[maxFreq + 1];
        for (int i = 0; i <= maxFreq; i++) {
            bucket[i] = new ArrayList<>();
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            bucket[entry.getValue()].add(entry.getKey());
        }
        StringBuilder builder = new StringBuilder();
        for (int i = maxFreq; i >= 1; i--) {
            for (char c : bucket[i]) {
                for (int j = 0; j < i; j++) {
                    builder.append(c);
                }
            }
        }
        return builder.toString();
    }

//    public static void main(String[] args) {
//        Problem0451SortCharactersByFrequency a = new Problem0451SortCharactersByFrequency();
//        a.frequencySort("tree");
//    }
}
