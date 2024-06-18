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
        long first = 0, second = 0, third = 0;
        for (int i = 0; i < size; i++) {
            int num = list.get(i);
            long sum = (i > 0 && list.get(i - 1) < num - 2 ? first : (i > 1 && list.get(i - 2) < num - 2 ? second : third)) + (long) num * map.get(num);
            third = second;
            second = first;
            first = Math.max(first, sum);
        }
        return first;
    }
}
