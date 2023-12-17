package com.shuangpeng.Problem.p2801_2900;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2870MinimumNumberOfOperationsToMakeArrayEmpty（使数组为空的最少操作次数）
 * @date 2023/12/17 5:35 PM
 */
public class Problem2870MinimumNumberOfOperationsToMakeArrayEmpty {

    public int minOperations(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.merge(num, 1, Integer::sum);
        }
        int ans = 0;
        for (int cnt : map.values()) {
            if (cnt == 1) {
                return -1;
            }
            ans += (cnt + 2) / 3;
        }
        return ans;
    }
}
