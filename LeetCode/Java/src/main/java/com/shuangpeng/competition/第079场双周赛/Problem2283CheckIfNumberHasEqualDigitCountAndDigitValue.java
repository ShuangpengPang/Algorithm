package com.shuangpeng.competition.第079场双周赛;

/**
 * @Description: Problem2283CheckIfNumberHasEqualDigitCountAndDigitValue（判断一个数字的数字计数是否等于数位的值）
 * @Date 2022/6/24 11:02 AM
 * @Version 1.0
 */
public class Problem2283CheckIfNumberHasEqualDigitCountAndDigitValue {

    // 比赛时写法
    public boolean digitCount0(String num) {
        int n = num.length();
        int[] cnt = new int[n];
        for (int i = 0; i < n; ++i) {
            int j = num.charAt(i) - '0';
            if (j < n) {
                ++cnt[j];
            }
        }
        for (int i = 0; i < n; ++i) {
            if (cnt[i] != (num.charAt(i) - '0')) {
                return false;
            }
        }
        return true;
    }

    public boolean digitCount(String num) {
        int[] cnt = new int[10];
        int n = num.length();
        for (int i = 0; i < n; i++) {
            cnt[num.charAt(i) - '0']++;
        }
        for (int i = 0; i < n; i++) {
            if (cnt[i] != num.charAt(i) - '0') {
                return false;
            }
        }
        return true;
    }
}
