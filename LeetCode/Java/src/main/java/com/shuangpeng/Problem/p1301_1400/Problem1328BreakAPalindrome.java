package com.shuangpeng.Problem.p1301_1400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1328BreakAPalindrome（破坏回文串）
 * @date 2023/7/12 11:20 AM
 */
public class Problem1328BreakAPalindrome {

    public String breakPalindrome(String palindrome) {
        int n = palindrome.length();
        if (n <= 1) {
            return "";
        }
        char[] cs = palindrome.toCharArray();
        int i = 0, h = n >> 1;
        while (i < h && cs[i] == 'a') {
            i++;
        }
        if (i == h) {
            cs[n - 1] = 'b';
        } else {
            cs[i] = 'a';
        }
        return new String(cs);
    }
}
