package com.shuangpeng.Problem.p1801_1900;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1814CountNicePairsInAnArray（统计一个数组中好对子的数目）
 * @date 2023/1/17 11:11 AM
 */
public class Problem1814CountNicePairsInAnArray {

    public int countNicePairs0(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int key = num - reverse(num);
            map.put(key, map.getOrDefault(key, 0) + 1);
        }
        long ans = 0L, M = (long) 1e9 + 7;
        for (int count : map.values()) {
            ans = (ans + ((long) count * (count - 1) >> 1)) % M;
        }
        return (int) ans;
    }

    private int reverse(int x) {
        int ans = 0;
        while (x > 0) {
            ans = ans * 10 + x % 10;
            x /= 10;
        }
        return ans;
    }

    public int countNicePairs(int[] nums) {
        int ans = 0, M = (int) 1e9 + 7;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int x = num, r = 0;
            while (x > 0) {
                r = r * 10 + x % 10;
                x /= 10;
            }
            int count = map.getOrDefault(num - r, 0);
            map.put(num - r, count + 1);
            ans = (ans + count) % M;
        }
        return ans;
    }
}
