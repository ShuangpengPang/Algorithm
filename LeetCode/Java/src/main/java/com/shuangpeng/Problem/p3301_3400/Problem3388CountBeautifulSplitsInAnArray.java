package com.shuangpeng.Problem.p3301_3400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3388CountBeautifulSplitsInAnArray（统计数组中的美丽分割）
 * @date 2025/4/7 18:46
 */
public class Problem3388CountBeautifulSplitsInAnArray {

    public int beautifulSplits(int[] nums) {
        int n = nums.length, ans = 0;
        int[] z = new int[n], a = new int[n];
        zeta(nums, 0, z);
        for (int i = n - 2; i > 0; i--) {
            zeta(nums, i, a);
            for (int j = i + 1; j < n; j++) {
                if (a[j] >= j - i || z[i] >= i && i << 1 <= j) {
                    ans++;
                }
            }
        }
        return ans;
    }

    private void zeta(int[] nums, int s, int[] z) {
        int n = nums.length;
        for (int l = s, r = s, i = s + 1; i < n; i++) {
            int m = Math.max(0, Math.min(r - i + 1, z[s + i - l]));
            while (i + m < n && nums[i + m] == nums[s + m]) {
                m++;
            }
            z[i] = m;
            if (i + m - 1 > r) {
                r = i + m - 1;
                l = i;
            }
        }
    }
}

class Problem3388CountBeautifulSplitsInAnArray0 {

    public int beautifulSplits(int[] nums) {
        int n = nums.length;
        int[][] lcp = new int[n + 1][n + 1];
        for (int i = n - 2; i >= 0; i--) {
            for (int j = n - 1; j > i; j--) {
                lcp[i][j] = nums[i] == nums[j] ? lcp[i + 1][j + 1] + 1 : 0;
            }
        }
        int ans = 0;
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (i << 1 <= j && lcp[0][i] >= i || lcp[i][j] >= j - i) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
