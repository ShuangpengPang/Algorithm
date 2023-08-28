package com.shuangpeng.Problem.p1401_1500;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1488AvoidFloodInTheCity（避免洪水泛滥）
 * @date 2023/8/28 7:21 PM
 */
public class Problem1488AvoidFloodInTheCity {

    public int[] avoidFlood(int[] rains) {
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
}
