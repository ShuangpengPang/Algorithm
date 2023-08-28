package com.shuangpeng.Problem.p1401_1500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1482MinimumNumberOfDaysToMakeMBouquets（制作m束花所需的最少天数）
 * @date 2023/8/28 6:33 PM
 */
public class Problem1482MinimumNumberOfDaysToMakeMBouquets {

    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if (n < (long) m * k) {
            return -1;
        }
        int left = 0, right = 0;
        for (int d : bloomDay) {
            right = Math.max(right, d);
        }
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (!check(bloomDay, mid, m, k)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int[] bloomDay, int day, int m, int k) {
        int n = bloomDay.length, i = 0, cnt = 0;
        while (i < n && cnt < m) {
            while (i < n && bloomDay[i] > day) {
                i++;
            }
            int j = i;
            while (j < n && bloomDay[j] <= day) {
                j++;
            }
            cnt += (j - i) / k;
            i = j + 1;
        }
        return cnt >= m;
    }
}
