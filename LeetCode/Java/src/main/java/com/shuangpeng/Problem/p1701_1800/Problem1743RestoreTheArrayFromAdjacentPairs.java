package com.shuangpeng.Problem.p1701_1800;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem1743RestoreTheArrayFromAdjacentPairs {

    public int[] restoreArray(int[][] adjacentPairs) {
        int n = adjacentPairs.length + 1;
        Map<Integer, List<Integer>> map = new HashMap<>(n);
        for (int[] pair : adjacentPairs) {
            int u = pair[0];
            int v = pair[1];
            List<Integer> nu = map.getOrDefault(u, new ArrayList<>());
            nu.add(v);
            map.put(u, nu);
            List<Integer> nv = map.getOrDefault(v, new ArrayList<>());
            nv.add(u);
            map.put(v, nv);
        }
        int k = 0;
        for (int key : map.keySet()) {
            if (map.get(key).size() == 1) {
                k = key;
                break;
            }
        }
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = k;
            List<Integer> neighbors = map.get(k);
            for (int neighbor : neighbors) {
                k = neighbor;
                if (i == 0 || k != array[i - 1]) {
                    break;
                }
            }
        }
        return array;
    }
}
