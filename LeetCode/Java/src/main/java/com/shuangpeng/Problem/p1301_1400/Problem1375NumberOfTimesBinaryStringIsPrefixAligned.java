package com.shuangpeng.Problem.p1301_1400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1375NumberOfTimesBinaryStringIsPrefixAligned（二进制字符串前缀一致的次数）
 * @date 2023/6/14 10:42 AM
 */
public class Problem1375NumberOfTimesBinaryStringIsPrefixAligned {

    public int numTimesAllBlue0(int[] flips) {
        int n = flips.length, res = 0, sum = 0, exceptd = 0;
        for (int i = 0; i < n; ++i) {
            sum += flips[i];
            exceptd += i + 1;
            res += sum == exceptd ? 1 : 0;
        }
        return res;
    }

    public int numTimesAllBlue(int[] flips) {
        int max = 0, cnt = 0, n = flips.length;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, flips[i]);
            if (max == i + 1) {
                cnt++;
            }
        }
        return cnt;
    }
}
