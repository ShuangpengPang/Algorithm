package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3114LatestTimeYouCanObtainAfterReplacingCharacters（替换字符可以得到的最晚时间）
 * @date 2024/4/24 2:06 PM
 */
public class Problem3114LatestTimeYouCanObtainAfterReplacingCharacters {

    public String findLatestTime0(String s) {
        char[] cs = s.toCharArray();
        for (int h = 11; ; h--) {
            if (cs[0] != '?' && cs[0] - '0' != h / 10 || cs[1] != '?' && cs[1] - '0' != h % 10) {
                continue;
            }
            for (int m = 59; m >= 0; m--) {
                if (cs[3] != '?' && cs[3] - '0' != m / 10 || cs[4] != '?' && cs[4] - '0' != m % 10) {
                    continue;
                }
                return String.format("%02d:%02d", h, m);
            }
        }
    }

    public String findLatestTime(String s) {
        char[] cs = s.toCharArray();
        if (cs[0] == '?') {
            cs[0] = cs[1] == '?' || cs[1] <= '1' ? '1' : '0';
        }
        if (cs[1] == '?') {
            cs[1] = cs[0] == '1' ? '1' : '9';
        }
        if (cs[3] == '?') {
            cs[3] = '5';
        }
        if (cs[4] == '?') {
            cs[4] = '9';
        }
        return new String(cs);
    }
}
