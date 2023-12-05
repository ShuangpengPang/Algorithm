package com.shuangpeng.Problem.p2501_2600;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2588CountTheNumberOfBeautifulSubarrays（统计美丽子数组数目）
 * @date 2023/12/5 2:43 PM
 */
public class Problem2588CountTheNumberOfBeautifulSubarrays {

    public long beautifulSubarrays(int[] nums) {
        long ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
            ans += map.merge(xor, 1, Integer::sum) - 1;
        }
        return ans;
    }
}
