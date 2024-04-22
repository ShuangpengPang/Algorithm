package com.shuangpeng.Problem.p3001_3100;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3074AppleRedistributionIntoBoxes（重新分装苹果）
 * @date 2024/4/22 7:26 PM
 */
public class Problem3074AppleRedistributionIntoBoxes {

    public int minimumBoxes0(int[] apple, int[] capacity) {
        Arrays.sort(capacity);
        int m = capacity.length, j = m;
        for (int a : apple) {
            while (a > 0) {
                if (j == m || capacity[j] == 0) {
                    j--;
                }
                int c = Math.min(a, capacity[j]);
                a -= c;
                capacity[j] -= c;
            }
        }
        return m - j;
    }

    public int minimumBoxes(int[] apple, int[] capacity) {
        Arrays.sort(capacity);
        int sum = 0, j = capacity.length - 1, cnt = 0;
        for (int a : apple) {
            sum += a;
        }
        while (sum > 0) {
            sum -= capacity[j--];
            cnt++;
        }
        return cnt;
    }
}
