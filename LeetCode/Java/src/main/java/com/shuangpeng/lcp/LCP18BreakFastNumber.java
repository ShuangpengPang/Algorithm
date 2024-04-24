package com.shuangpeng.lcp;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCP18BreakFastNumber（早餐组合）
 * @date 2024/4/25 12:30 AM
 */
public class LCP18BreakFastNumber {

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
