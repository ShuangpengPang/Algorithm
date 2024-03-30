package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2269FindTheKBeautyOfANumber（找到一个数字的K美丽值）
 * @date 2024/3/29 3:36 PM
 */
public class Problem2269FindTheKBeautyOfANumber {

    public int divisorSubstrings0(int num, int k) {
        String s = Integer.toString(num);
        int n = s.length(), cnt = 0;
        for (int i = 0; i <= n - k; i++) {
            int v = Integer.parseInt(s.substring(i, i + k));
            if (v != 0 && num % v == 0) {
                cnt++;
            }
        }
        return cnt;
    }

    public int divisorSubstrings1(int num, int k) {
        long[] cnt = new long[2];
        long mod = (long) Math.pow(10, k);
        String s = String.valueOf(num);
        s.chars().limit(k - 1).forEach(e -> cnt[1] = cnt[1] * 10 + e - '0');
        s.chars().skip(k - 1).forEach(e -> {
            cnt[1] = (cnt[1] * 10 + e - '0') % mod;
            if (cnt[1] > 0 && num % cnt[1] == 0) {
                cnt[0]++;
            }
        });
        return (int) cnt[0];
    }

    public int divisorSubstrings(int num, int k) {
        long m = (long) Math.pow(10, k), t = num;
        int cnt = 0;
        while (t * 10 >= m) {
            int v = (int) (t % m);
            if (v != 0 && num % v == 0) {
                cnt++;
            }
            t /= 10;
        }
        return cnt;
    }
}
