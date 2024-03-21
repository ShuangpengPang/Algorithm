package com.shuangpeng.Problem.p1901_2000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1903LargestOddNumberInString（字符串中的最大奇数）
 * @date 2024/3/21 2:29 PM
 */
public class Problem1903LargestOddNumberInString {

    public String largestOddNumber(String num) {
        int i = num.length() - 1;
        while (i >= 0 && (num.charAt(i) & 1) == 0) {
            i--;
        }
        return num.substring(0, i + 1);
    }
}
