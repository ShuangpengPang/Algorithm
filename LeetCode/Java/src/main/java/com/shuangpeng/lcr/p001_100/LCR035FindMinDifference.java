package com.shuangpeng.lcr.p001_100;

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
        int n = timePoints.size(), N = 60 * 24;
        if (n > N) {
            return 0;
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            String time = timePoints.get(i);
            arr[i] = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));
        }
        Arrays.sort(arr);
        int ans = arr[0] + N - arr[n - 1];
        for (int i = 1; i < n; i++) {
            ans = Math.min(ans, arr[i] - arr[i - 1]);
        }
        return ans;
    }
}
