package com.shuangpeng.Problem.p1401_1500;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1488AvoidFloodInTheCity（避免洪水泛滥）
 * @date 2023/8/28 7:21 PM
 */
public class Problem1488AvoidFloodInTheCity {

    public int[] avoidFlood0(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (rains[i] > 0) {
                if (map.containsKey(rains[i])) {
                    int p = map.get(rains[i]) + 1;
                    while (p < i && (rains[p] > 0 || ans[p] > 0)) {
                        p++;
                    }
                    if (p == i) {
                        return new int[0];
                    }
                    ans[p] = rains[i];
                }
                map.put(rains[i], i);
                ans[i] = -1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (ans[i] == 0) {
                ans[i] = 1;
            }
        }
        return ans;
    }

    public int[] avoidFlood1(int[] rains) {
        int n = rains.length;
        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        Map<Integer, Integer> map = new HashMap<>();
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            if (rains[i] > 0) {
                if (map.containsKey(rains[i])) {
                    Integer idx = set.ceiling(map.get(rains[i]));
                    if (idx == null) {
                        return new int[0];
                    }
                    ans[idx] = rains[i];
                    set.remove(idx);
                }
                map.put(rains[i], i);
                ans[i] = -1;
            } else {
                set.add(i);
            }
        }
        return ans;
    }

    public int[] avoidFlood(int[] rains) {
        int n = rains.length;
        int[] parent = new int[n], ans = new int[n];
        Arrays.setAll(parent, i -> i);
        Arrays.fill(ans, -1);
        Map<Integer, Integer> last = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (rains[i] > 0) {
                Integer idx = last.put(rains[i], i);
                if (idx == null) {
                    continue;
                }
                idx = find(parent, idx);
                while (idx < i && (rains[idx] > 0 || ans[idx] != -1)) {
                    parent[idx] = idx + 1;
                    idx = find(parent, idx);
                }
                if (idx >= i) {
                    return new int[0];
                }
                ans[idx] = rains[i];
            }
        }
        for (int i = 0; i < n; i++) {
            if (ans[i] == -1 && rains[i] == 0) {
                ans[i] = 1;
            }
        }
        return ans;
    }

    private int find(int[] parent, int x) {
        return parent[x] = parent[x] == x ? x : find(parent, parent[x]);
    }
}
