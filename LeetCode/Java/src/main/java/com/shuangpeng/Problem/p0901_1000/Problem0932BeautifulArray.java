package com.shuangpeng.Problem.p0901_1000;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0932BeautifulArray（漂亮数组）
 * @date 2023/3/10 3:19 PM
 */
public class Problem0932BeautifulArray {

    int[] tmp;

    public int[] beautifulArray(int n) {
        int[] nums = new int[n];
        tmp = new int[n];
        Arrays.setAll(nums, i -> i + 1);
        divideAndConquer(nums, 0, n - 1);
        return nums;
    }

    private void divideAndConquer(int[] nums, int s, int e) {
        if (s >= e) {
            return;
        }
        int n = e - s + 1, m = s + ((n + 1) >> 1);
        for (int i = s, j = s, k = m; i <= e; i++) {
            if (((i - s) & 1) == 0) {
                tmp[j++] = nums[i];
            } else {
                tmp[k++] = nums[i];
            }
        }
        System.arraycopy(tmp, s, nums, s, n);
        divideAndConquer(nums, s, m - 1);
        divideAndConquer(nums, m, e);
    }
}

class Problem0932BeautifulArray0 {

    Map<Integer, int[]> memo;

    public int[] beautifulArray(int n) {
        memo = new HashMap<>();
        return divideAndConquer(n);
    }

    private int[] divideAndConquer(int n) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }
        int[] ans = new int[n];
        memo.put(n, ans);
        if (n == 1) {
            ans[0] = 1;
            return ans;
        }
        int i = 0;
        for (int num : divideAndConquer((n + 1) >> 1)) {
            ans[i++] = (num << 1) - 1;
        }
        for (int num : divideAndConquer(n >> 1)) {
            ans[i++] = num << 1;
        }
        return ans;
    }
}

class Problem0932BeautifulArray1 {

    public int[] beautifulArray(int n) {
        int[][] dp = new int[n + 1][];
        dp[1] = new int[]{1};
        for (int i = 2; i <= n; i++) {
            dp[i] = new int[i];
            int left = i + 1 >> 1, right = i >> 1;
            for (int j = 0; j < left; j++) {
                dp[i][j] = (dp[left][j] << 1) - 1;
            }
            for (int j = 0; j < right; j++) {
                dp[i][j + left] = dp[right][j] << 1;
            }
        }
        return dp[n];
    }
}
