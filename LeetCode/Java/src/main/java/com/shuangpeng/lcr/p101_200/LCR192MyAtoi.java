package com.shuangpeng.lcr.p101_200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: （LCR192MyAtoi（LCR 192. 把字符串转换成整数 (atoi)）
 * @date 2024/8/18 6:03 PM
 */
public class LCR192MyAtoi {

    public int myAtoi(String str) {
        char[] cs = str.toCharArray();
        int min = Integer.MIN_VALUE, max = Integer.MAX_VALUE;
        long ans = 0;
        int i = 0, n = cs.length;
        while (i < n && cs[i] == ' ') {
            i++;
        }
        if (i == n || Character.isLetter(cs[i])) {
            return 0;
        }
        long sign = 1;
        if (cs[i] == '+') {
            i++;
        } else if (cs[i] == '-') {
            sign = -1;
            i++;
        }
        while (i < n && Character.isDigit(cs[i])) {
            ans = ans * 10 + sign * (cs[i] - '0');
            if (ans < min) {
                return min;
            } else if (ans > max) {
                return max;
            }
            i++;
        }
        return (int) ans;
    }
}
