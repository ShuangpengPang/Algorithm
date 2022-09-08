package com.shuangpeng.Problem.p0601_0700;

/**
 * @Description: Problem0667BeautifulArrangementII（优美的排列II）
 * @Date 2022/9/8 10:14 AM
 * @Version 1.0
 */
public class Problem0667BeautifulArrangementII {

    public int[] constructArray0(int n, int k) {
        int[] ans = new int[n];
        for (int i = 0; i < n - k; i++) {
            ans[i] = i + 1;
        }
        for (int i = n - k, l = n - k + 1, r = n, f = 0; i < n; i++) {
            if ((f & 1) == 0) {
                ans[i] = r--;
            } else {
                ans[i] = l++;
            }
            f ^= 1;
        }
        return ans;
    }

    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        for (int i = 0; i < n - k; i++) {
            ans[i] = i + 1;
        }
        for (int idx = n - k, i = n, j = n - k + 1; i >= j; i--, j++) {
            ans[idx++] = i;
            if (i > j) {
                ans[idx++] = j;
            }
        }
        return ans;
    }
}
