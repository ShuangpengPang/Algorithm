package com.shuangpeng.Problem.p2501_2600;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2517MaximumTastinessOfCandyBasket（礼盒的最大甜蜜度）
 * @date 2023/6/1 10:34 AM
 */
public class Problem2517MaximumTastinessOfCandyBasket {

    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int n = price.length;
        int left = 0, right = (price[n - 1] - price[0]) / (k - 1);
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (check(price, mid, k)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }

    private boolean check(int[] price, int d, int k) {
        int cnt = 1, p = price[0], n = price.length;
        for (int i = 1; i < n && cnt < k; i++) {
            if (price[i] - p >= d) {
                p = price[i];
                cnt++;
            }
        }
        return cnt == k;
    }
}
