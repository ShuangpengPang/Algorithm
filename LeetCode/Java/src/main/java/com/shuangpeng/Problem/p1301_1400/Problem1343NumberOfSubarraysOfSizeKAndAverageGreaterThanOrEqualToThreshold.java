package com.shuangpeng.Problem.p1301_1400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1343NumberOfSubarraysOfSizeKAndAverageGreaterThanOrEqualToThreshold（大小为 K 且平均值大于等于阈值的子数组数目）
 * @date 2023/8/2 6:12 PM
 */
public class Problem1343NumberOfSubarraysOfSizeKAndAverageGreaterThanOrEqualToThreshold {

    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int ans = 0, n = arr.length;
        for (int i = 0, sum = 0; i < n; i++) {
            sum += arr[i];
            if (i >= k) {
                sum -= arr[i - k];
            }
            if (i >= k - 1 && sum / k >= threshold) {
                ans++;
            }
        }
        return ans;
    }
}
