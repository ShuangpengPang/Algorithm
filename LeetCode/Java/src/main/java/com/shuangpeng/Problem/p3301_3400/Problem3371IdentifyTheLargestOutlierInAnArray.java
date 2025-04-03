package com.shuangpeng.Problem.p3301_3400;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3371IdentifyTheLargestOutlierInAnArray（识别数组中的最大异常值）
 * @date 2025/4/3 11:36
 */
public class Problem3371IdentifyTheLargestOutlierInAnArray {

    public int getLargestOutlier(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int x : nums) {
            sum += x;
            map.merge(x, 1, Integer::sum);
        }
        int ans = Integer.MIN_VALUE;
        for (int x : nums) {
            int s = sum - x;
            if ((s & 1) == 0) {
                int y = s >> 1, c = map.getOrDefault(y, 0);
                if (y != x && c > 0 || c > 1) {
                    ans = Math.max(ans, x);
                }
            }
        }
        return ans;
    }
}
