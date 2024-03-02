package com.shuangpeng.Problem.p3001_3100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3020FindTheMaximumNumberOfElementsInSubset（子集中元素的最大数量）
 * @date 2024/3/1 7:49 PM
 */
public class Problem3020FindTheMaximumNumberOfElementsInSubset {

    public int maximumLength0(int[] nums) {
        Map<Long, Integer> map = new HashMap<>();
        for (long num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        int ans = 0;
        for (long num : map.keySet()) {
            if (num == 1) {
                int cnt = map.get(num);
                ans = Math.max(ans, cnt + (cnt & 1) - 1);
                continue;
            }
            int cnt = 1;
            for (long i = num, j = i * i; map.get(i) > 1 && map.getOrDefault(j, 0) > 0; i = j, j = j * j) {
                cnt += 2;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

    public int maximumLength(int[] nums) {
        Map<Long, Integer> map = new HashMap<>();
        for (long num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        Integer c = map.remove(1L);
        int ans = c == null ? 0 : c - 1 | 1;
        for (long num : map.keySet()) {
            int cnt = 0;
            for (; map.getOrDefault(num, 0) > 1; num *= num) {
                cnt += 2;
            }
            ans = Math.max(ans, cnt + map.getOrDefault(num, -1));
        }
        return ans;
    }
}
