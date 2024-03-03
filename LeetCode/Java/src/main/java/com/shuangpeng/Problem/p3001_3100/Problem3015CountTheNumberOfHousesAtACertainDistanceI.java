package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3015CountTheNumberOfHousesAtACertainDistanceI（按距离统计房屋对数目I）
 * @date 2024/3/2 3:48 PM
 */
public class Problem3015CountTheNumberOfHousesAtACertainDistanceI {

    public int[] countOfPairs(int n, int x, int y) {
        if (x > y) {
            int t = x;
            x = y;
            y = t;
        }
        int[] diff = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            diff[1] += 2;
            diff[i]--;
            diff[n - i + 1]--;
        }
        if (x + 1 < y) {
            helper(diff, n, x, y);
            helper(diff, n, n - y + 1, n - x + 1);
        }
        int[] ans = new int[n];
        for (int i = 1, sum = 0; i <= n; i++) {
            sum += diff[i];
            ans[i - 1] = sum;
        }
        return ans;
    }

    private void helper(int[] diff, int n, int x, int y) {
        int k = (x + y + 1 >> 1) + 1;
        for (int i = 1; i < x; i++) {
            diff[y - i]--;
            diff[n - i + 1]++;
            diff[x - i + 1]++;
            diff[x - i + n - y + 2]--;
            diff[k - i]--;
            diff[y - i]++;
            diff[x - i + 2]++;
            diff[x - i + y - k + 2]--;
        }
        int m = x + y + 1 >> 1;
        for (int i = x; i < m; i++) {
            diff[y - i]--;
            diff[n - i + 1]++;
            diff[i - x + 1]++;
            diff[i - x + n - y + 2]--;
            int j = (2 * i - x + 1 + y) / 2 + 1;
            if (j < y) {
                diff[j - i]--;
                diff[y - i]++;
                diff[i - x + 2]++;
                diff[i - x + y - j + 2]--;
            }
        }
    }
}
