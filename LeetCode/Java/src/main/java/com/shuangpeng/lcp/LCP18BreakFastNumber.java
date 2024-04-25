package com.shuangpeng.lcp;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCP18BreakFastNumber（早餐组合）
 * @date 2024/4/25 12:30 AM
 */
public class LCP18BreakFastNumber {

    public int breakfastNumber0(int[] staple, int[] drinks, int x) {
        Arrays.sort(drinks);
        int N = (int) 1e9 + 7, ans = 0;
        for (int s : staple) {
            int d = x - s, left = 0, right = drinks.length - 1;
            while (left <= right) {
                int mid = left + (right - left >> 1);
                if (drinks[mid] <= d) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            ans = (ans + left) % N;
        }
        return ans;
    }

    public int breakfastNumber(int[] staple, int[] drinks, int x) {
        Arrays.sort(staple);
        Arrays.sort(drinks);
        int n1 = staple.length, n2 = drinks.length;
        int N = (int) 1e9 + 7, ans = 0;
        for (int i = 0, j = n2 - 1; i < n1 && j >= 0; i++) {
            while (j >= 0 && staple[i] + drinks[j] > x) {
                j--;
            }
            ans = (ans + j + 1) % N;
        }
        return ans;
    }
}
