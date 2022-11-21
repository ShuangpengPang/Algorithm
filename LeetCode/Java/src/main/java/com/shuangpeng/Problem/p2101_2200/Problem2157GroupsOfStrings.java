package com.shuangpeng.Problem.p2101_2200;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2157GroupsOfStrings（字符串分组）
 * @date 2022/11/21 11:50 AM
 */
public class Problem2157GroupsOfStrings {

    public int[] groupStrings(String[] words) {
        Map<Integer, Integer> size = new HashMap<>(), parent = new HashMap<>();
        for (String w : words) {
            int m = w.length(), h = 0;
            for (int i = 0; i < m; i++) {
                h |= 1 << (w.charAt(i) - 'a');
            }
            parent.put(h, h);
            size.put(h, size.getOrDefault(h, 0) + 1);
        }
        for (int h : size.keySet()) {
            for (int i = 0; i < 26; i++) {
                if (((h >> i) & 1) == 0) {
                    union(parent, size, h, h | 1 << i);
                } else {
                    for (int j = 0; j < 26; j++) {
                        if (((h >> j) & 1) == 0) {
                            union(parent, size, h, h ^ (1 << i | 1 << j));
                        }
                    }
                }
            }
        }
        int g = 0, s = 0;
        for (Map.Entry<Integer, Integer> entry : parent.entrySet()) {
            int k = entry.getKey(), v = entry.getValue();
            if (k == v) {
                g++;
                s = Math.max(s, size.get(k));
            }
        }
        return new int[]{g, s};
    }

    private int find(Map<Integer, Integer> parent, int x) {
        int p = parent.getOrDefault(x, -1);
        if (p != -1 && x != p) {
            p = find(parent, p);
            parent.put(x, p);
        }
        return p;
    }

    private void union(Map<Integer, Integer> parent, Map<Integer, Integer> size, int x, int y) {
        int p1 = find(parent, x), p2 = find(parent, y);
        if (p1 != -1 && p2 != -1 && p1 != p2) {
            int s = size.get(p1) + size.get(p2);
            parent.put(p2, p1);
            size.put(p1, s);
        }
    }
}
