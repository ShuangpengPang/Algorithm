package com.shuangpeng.Problem.p1801_1900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1864MinimumNumberOfSwapsToMakeTheBinaryStringAlternating（构成交替字符串需要的最小交换次数）
 * @date 2023/10/12 4:18 PM
 */
public class Problem1864MinimumNumberOfSwapsToMakeTheBinaryStringAlternating {

    public int minSwaps(String s) {
        char[] cs = s.toCharArray();
        int ans = Math.min(swap(cs, 0), swap(cs, 1));
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private int swap(char[] cs, int t) {
        int n = cs.length, d = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            int c = cs[i] - '0';
            if ((c ^ (i & 1)) != t) {
                int v = c & 1;
                cnt += v;
                d += 1 - (v << 1);
            }
        }
        return d == 0 ? cnt : Integer.MAX_VALUE;
    }
}
