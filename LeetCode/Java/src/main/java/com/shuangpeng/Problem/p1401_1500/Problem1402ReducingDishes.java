package com.shuangpeng.Problem.p1401_1500;

import java.util.Arrays;

/**
 * @Description: Problem1402ReducingDishes（做菜顺序）
 * @Date 2022/8/11 7:22 PM
 * @Version 1.0
 */
public class Problem1402ReducingDishes {

    public int maxSatisfaction0(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int cnt = 0, n = satisfaction.length;
        for (int s : satisfaction) {
            if (s >= 0) {
                cnt++;
            }
        }
        int ans = 0, sum = 0;
        for (int i = n - 1; i >= n - cnt; i--) {
            sum += satisfaction[i];
            ans += satisfaction[i] * (cnt + i - n + 1);
        }
        for (int i = n - cnt - 1, s = 0; i >= 0 && s + satisfaction[i] + sum > 0; i--) {
            ans += s + satisfaction[i] + sum;
            s += satisfaction[i];
        }
        return ans;
    }

    public int maxSatisfaction(int[] satisfaction) {
        Arrays.sort(satisfaction);
        int n = satisfaction.length, ans = 0;
        for (int i = n - 1, sum = 0; i >= 0 && sum + satisfaction[i] > 0; i--) {
            sum += satisfaction[i];
            ans += sum;
        }
        return ans;
    }
}

