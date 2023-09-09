package com.shuangpeng.Problem.p1601_1700;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1679MaxNumberOfKSumPairs（K和数对的最大数目）
 * @date 2023/9/9 6:06 PM
 */
public class Problem1679MaxNumberOfKSumPairs {

    public int maxOperations(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num : nums) {
            int cnt = map.getOrDefault(k - num, 0);
            if (cnt > 0) {
                ans++;
                map.put(k - num, cnt - 1);
            } else {
                map.merge(num, 1, Integer::sum);
            }
        }
        return ans;
    }
}
