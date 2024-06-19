package com.shuangpeng.lcr;

import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR035FindMinDifference（最小时间差）
 * @date 2024/6/19 10:45 AM
 */
public class LCR035FindMinDifference {

    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            String time = timePoints.get(i);
            arr[i] = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));
        }
        Arrays.sort(arr);
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            ans = Math.min(ans, arr[i] - arr[i - 1]);
        }
        ans = Math.min(ans, arr[0] + 60 * 24 - arr[n - 1]);
        return ans;
    }
}
