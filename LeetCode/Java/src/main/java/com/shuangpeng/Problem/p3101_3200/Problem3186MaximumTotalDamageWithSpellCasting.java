package com.shuangpeng.Problem.p3101_3200;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3186MaximumTotalDamageWithSpellCasting（施咒的最大总伤害）
 * @date 2024/6/18 12:10 PM
 */
public class Problem3186MaximumTotalDamageWithSpellCasting {

    public long maximumTotalDamage(int[] power) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int p : power) {
            map.merge(p, 1, Integer::sum);
        }
        List<Integer> list = new ArrayList<>(map.keySet());
        list.sort(Comparator.comparingInt(e -> e));
        int size = list.size();
        long[] dp = new long[size];
        for (int i = 0, j = 0; i < size; i++) {
            while (list.get(j) < list.get(i) - 2) {
                j++;
            }
            dp[i] = Math.max(i > 0 ? dp[i - 1] : 0, (j > 0 ? dp[j - 1] : 0) + (long) list.get(i) * map.get(list.get(i)));
        }
        return dp[size - 1];
    }
}
