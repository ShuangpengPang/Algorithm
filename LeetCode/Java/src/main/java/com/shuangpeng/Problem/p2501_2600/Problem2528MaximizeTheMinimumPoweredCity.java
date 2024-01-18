package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2528MaximizeTheMinimumPoweredCity（最大化城市的最小电量）
 * @date 2024/1/18 4:28 PM
 */
public class Problem2528MaximizeTheMinimumPoweredCity {

    public long maxPower(int[] stations, int r, int k) {
        long sum = 0;
        int n = stations.length;
        for (int i = 0; i < r; i++) {
            sum += stations[i];
        }
        long[] powers = new long[n];
        long minPower = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (i + r < n) {
                sum += stations[i + r];
            }
            if (i - r - 1 >= 0) {
                sum -= stations[i - r - 1];
            }
            powers[i] = sum;
            minPower = Math.min(minPower, sum);
        }
        long left = 0, right = minPower + k;
        while (left <= right) {
            long mid = left + (right - left >> 1);
            if (check(powers, mid, r, k)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }

    private boolean check(long[] powers, long min, int r, int k) {
        int n = powers.length, right = (r << 1) + 1;
        int[] diff = new int[n];
        for (int i = 0, s = 0; i < n; i++) {
            s += diff[i];
            long sum = powers[i] + s;
            if (sum + k < min) {
                return false;
            }
            if (sum < min) {
                int cnt = (int) (min - sum);
                k -= cnt;
                s += cnt;
                if (i + right < n) {
                    diff[i + right] -= cnt;
                }
            }
        }
        return true;
    }
}
