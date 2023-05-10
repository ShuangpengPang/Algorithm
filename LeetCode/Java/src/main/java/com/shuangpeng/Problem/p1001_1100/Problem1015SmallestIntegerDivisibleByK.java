package com.shuangpeng.Problem.p1001_1100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1015SmallestIntegerDivisibleByK（可被K整除的最小整数）
 * @date 2023/5/10 10:04 AM
 */
public class Problem1015SmallestIntegerDivisibleByK {

    public int smallestRepunitDivByK0(int k) {
        if (k == 1) {
            return 1;
        }
        boolean[] mod = new boolean[k];
        int len = 0, s = 0, m = 1;
        while (!mod[m]) {
            len++;
            s = (s + m) % k;
            if (s == 0) {
                return len;
            }
            mod[m] = true;
            m = m * 10 % k;
        }
        boolean[] sum = new boolean[k];
        while (s != 0 && !sum[s]) {
            sum[s] = true;
            len++;
            s = (s + m) % k;
            m = m * 10 % k;
        }
        return s == 0 ? len : -1;
    }

    public int smallestRepunitDivByK1(int k) {
        boolean[] mod = new boolean[k];
        for (int i = 0, n = 0; !mod[n]; i++) {
            mod[n] = true;
            n = (n * 10 + 1) % k;
            if (n == 0) {
                return i + 1;
            }
        }
        return -1;
    }

    public int smallestRepunitDivByK2(int k) {
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }
        int ans = 0, s = 0;
        do {
            s = (s * 10 + 1) % k;
            ans++;
        } while (s != 0);
        return ans;
    }

    public int smallestRepunitDivByK(int k) {
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }
        int s = 1 % k, ans = 1;
        while (s != 0) {
            s = (s * 10 + 1) % k;
            ans++;
        }
        return ans;
    }
}
