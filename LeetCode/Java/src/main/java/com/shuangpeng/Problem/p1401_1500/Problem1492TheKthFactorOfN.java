package com.shuangpeng.Problem.p1401_1500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1492TheKthFactorOfN（n的第K个因子）
 * @date 2023/8/27 8:16 PM
 */
public class Problem1492TheKthFactorOfN {

    public int kthFactor0(int n, int k) {
        int ans = 0;
        for (int i = 0; i < k && ans <= n; i++) {
            ans++;
            while (ans <= n && n % ans != 0) {
                ans++;
            }
        }
        return ans <= n ? ans : -1;
    }

    public int kthFactor1(int n, int k) {
        for (int i = 1; i <= n; i++) {
            if (n % i == 0 && --k == 0) {
                return i;
            }
        }
        return -1;
    }

    public int kthFactor(int n, int k) {
        int ans = 1;
        while (ans * ans <= n) {
            if (n % ans == 0 && --k == 0) {
                return ans;
            }
            ans++;
        }
        ans--;
        ans = ans * ans == n ? ans - 1 : ans;
        while (ans > 0) {
            if (n % ans == 0 && --k == 0) {
                return n / ans;
            }
            ans--;
        }
        return -1;
    }
}
