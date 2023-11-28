package com.shuangpeng.Problem.p2501_2600;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2537CountTheNumberOfGoodSubarrays（统计好子数组的数目）
 * @date 2023/11/28 11:09 PM
 */
public class Problem2537CountTheNumberOfGoodSubarrays {

    public long countGood(int[] nums, int k) {
        int n = nums.length;
        long ans = 0, c = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; i < n && (j < n || c >= k); i++) {
            while (j < n && c < k) {
                c += map.merge(nums[j++], 1, Integer::sum) - 1;
            }
            if (c >= k) {
                ans += n - j + 1;
            }
            c -= map.merge(nums[i], -1, Integer::sum);
        }
        return ans;
    }
}
