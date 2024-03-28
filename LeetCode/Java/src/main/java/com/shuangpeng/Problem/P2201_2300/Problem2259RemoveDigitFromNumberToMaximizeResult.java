package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2259RemoveDigitFromNumberToMaximizeResult（移除指定数字得到的最大结果）
 * @date 2024/3/27 7:43 PM
 */
public class Problem2259RemoveDigitFromNumberToMaximizeResult {

    public String removeDigit0(String number, char digit) {
        char[] cs = number.toCharArray();
        int n = number.length(), cnt = 0;
        for (char c : cs) {
            if (c == digit) {
                cnt++;
            }
        }
        for (int i = 0, j = 0; i < n; i++) {
            if (cnt == 0 || cs[i] != digit) {
                cs[j++] = cs[i];
            } else if (cnt == 1 || cs[i] < cs[i + 1]) {
                cnt = 0;
            } else {
                cs[j++] = cs[i];
                cnt--;
            }
        }
        return new String(cs, 0, n - 1);
    }

    public String removeDigit(String number, char digit) {
        char[] cs = number.toCharArray();
        int n = cs.length, last = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] == digit) {
                last = i;
                if (i < n - 1 && cs[i] < cs[i + 1]) {
                    break;
                }
            }
        }
        for (int i = last; i < n - 1; i++) {
            cs[i] = cs[i + 1];
        }
        return new String(cs, 0, n - 1);
    }
}
