package com.shuangpeng.competition.第084场双周赛;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Problem2364CountNumberOfBadPairs（统计坏数对的数目）
 * @Date 2022/8/20 5:08 PM
 * @Version 1.0
 */
public class Problem2364CountNumberOfBadPairs {

    public long countBadPairs(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int j = nums[i] - i;
            map.put(j, map.getOrDefault(j, 0) + 1);
        }
        long cnt = 0;
        for (int c : map.values()) {
            cnt += (long) c * (c - 1) >> 1;
        }
        return ((long) n * (n - 1) >> 1) - cnt;
    }
}
