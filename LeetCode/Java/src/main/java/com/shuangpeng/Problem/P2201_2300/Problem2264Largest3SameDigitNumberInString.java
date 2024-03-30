package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2264Largest3SameDigitNumberInString（字符串中最大的3位相同数字）
 * @date 2024/3/30 2:12 PM
 */
public class Problem2264Largest3SameDigitNumberInString {

    public String largestGoodInteger(String num) {
        int n = num.length();
        int[] cnt = new int[10];
        int count = 0;
        for (int i = 0; i < 2; i++) {
            int c = num.charAt(i) - '0';
            if (cnt[c]++ == 0) {
                count++;
            }
        }
        int digit = -1;
        for (int i = 2; i < n; i++) {
            int c = num.charAt(i) - '0';
            if (cnt[c]++ == 0) {
                count++;
            }
            if (count == 1 && c > digit) {
                digit = c;
            }
            if (--cnt[num.charAt(i - 2) - '0'] == 0) {
                count--;
            }
        }
        if (digit == -1) {
            return "";
        }
        return digit == 0 ? "000" : Integer.toString(digit * 111);
    }
}
