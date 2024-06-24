package com.shuangpeng.lcr.p001_100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR019ValidPalindrome（验证回文串II）
 * @date 2024/4/29 11:44 AM
 */
public class LCR019ValidPalindrome {

    public boolean validPalindrome(String s) {
        char[] cs = s.toCharArray();
        for (int i = 0, j = cs.length - 1; i < j; i++, j--) {
            if (cs[i] != cs[j]) {
                return check(cs, i + 1, j) || check(cs, i, j - 1);
            }
        }
        return true;
    }

    private boolean check(char[] cs, int s, int e) {
        for (int i = s, j = e; i < j; i++, j--) {
            if (cs[i] != cs[j]) {
                return false;
            }
        }
        return true;
    }
}
