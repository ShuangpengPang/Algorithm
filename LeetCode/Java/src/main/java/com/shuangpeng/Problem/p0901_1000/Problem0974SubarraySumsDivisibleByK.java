package com.shuangpeng.Problem.p0901_1000;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0974SubarraySumsDivisibleByK（和可被K整除的子数组）
 * @date 2023/3/28 7:58 PM
 */
public class Problem0974SubarraySumsDivisibleByK {

    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int sum = 0, ans = 0;
        for (int num : nums) {
            sum = ((sum + num) % k + k) % k;
            int count = map.getOrDefault(sum, 0);
            ans += count;
            map.put(sum, count + 1);
        }
        return ans;
    }
}
