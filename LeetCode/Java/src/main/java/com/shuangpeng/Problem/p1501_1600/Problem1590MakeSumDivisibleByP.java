package com.shuangpeng.Problem.p1501_1600;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1590MakeSumDivisibleByP（使数组和能被P整除）
 * @date 2023/3/10 10:22 AM
 */
public class Problem1590MakeSumDivisibleByP {

    public int minSubarray0(int[] nums, int p) {
        int m = 0;
        for (int num : nums) {
            m = (m + num) % p;
        }
        if (m == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int n = nums.length, ans = n;
        for (int i = 0, s = 0; i < n; i++) {
            s = (s + nums[i]) % p;
            int pre = Math.max(map.getOrDefault(s - m, -n), map.getOrDefault(s + p - m, -n));
            ans = Math.min(ans, i - pre + 1);
            map.put(s, i + 1);
        }
        return ans == n ? -1 : ans;
    }

    public int minSubarray(int[] nums, int p) {
        int m = 0, n = nums.length, ans = n;
        for (int num : nums) {
            m = (m + num) % p;
        }
        if (m == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; i < n; i++) {
            map.put(j, i);
            j = (j + nums[i]) % p;
            ans = Math.min(ans, i - map.getOrDefault((j - m + p) % p, -n) + 1);
        }
        return ans == n ? -1 : ans;
    }
}
