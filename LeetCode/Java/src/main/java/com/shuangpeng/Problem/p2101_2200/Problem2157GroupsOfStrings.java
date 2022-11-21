package com.shuangpeng.Problem.p2101_2200;

import java.util.Arrays;
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

class Problem2157GroupsOfStrings0 {

    public int[] groupStrings(String[] words) {
        int n = words.length;
        int[] parent = new int[n];
        Arrays.setAll(parent, i -> i);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int h = getHash(words[i]);
            int j = map.getOrDefault(h, -1);
            if (j != -1) {
                union(parent, i, j);
            } else {
                map.put(h, i);
            }
            for (int k = 0; k < 26; k++) {
                if (((h >> k) & 1) == 1) {
                    int h1 = h ^ (1 << k);
                    if (map.containsKey(h1)) {
                        union(parent, i, map.get(h1));
                    } else {
                        map.put(h1, i);
                    }
                }
            }
        }
        Map<Integer, Integer> size = new HashMap<>();
        int maxSize = 0;
        for (int i = 0; i < n; i++) {
            int p = find(parent, i);
            int j = size.getOrDefault(p, 0) + 1;
            size.put(p, j);
            maxSize = Math.max(maxSize, j);
        }
        return new int[]{size.size(), maxSize};
    }

    private int getHash(String w) {
        int h = 0, n = w.length();
        for (int i = 0; i < n; i++) {
            h |= 1 << (w.charAt(i) - 'a');
        }
        return h;
    }

    private void union(int[] parent, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px < py) {
            parent[py] = px;
        } else if (py < px) {
            parent[px] = py;
        }
    }

    private int find(int[] parent, int x) {
        return parent[x] = x == parent[x] ? x : find(parent, parent[x]);
    }
}