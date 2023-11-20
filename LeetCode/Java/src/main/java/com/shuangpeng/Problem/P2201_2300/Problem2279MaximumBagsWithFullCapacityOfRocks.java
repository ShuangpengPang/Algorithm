package com.shuangpeng.Problem.P2201_2300;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2279MaximumBagsWithFullCapacityOfRocks（装满石头的背包的最大数量）
 * @date 2023/11/20 10:13 AM
 */
public class Problem2279MaximumBagsWithFullCapacityOfRocks {

    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int n = capacity.length;
        for (int i = 0; i < n; i++) {
            capacity[i] -= rocks[i];
        }
        Arrays.sort(capacity);
        int ans = 0;
        while (ans < n && additionalRocks >= 0) {
            if (capacity[ans] > additionalRocks) {
                return ans;
            }
            additionalRocks -= capacity[ans];
            ans++;
        }
        return ans;
    }
}
