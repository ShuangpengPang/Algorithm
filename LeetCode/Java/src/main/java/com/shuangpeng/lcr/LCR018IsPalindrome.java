package com.shuangpeng.lcr;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR018IsPalindrome（验证回文串）
 * @date 2024/4/29 11:58 AM
 */
public class LCR018IsPalindrome {

    public boolean isPalindrome(String s) {
        char[] cs = s.toCharArray();
        int i = 0, j = cs.length - 1;
        while (i < j) {
            if (!Character.isLetterOrDigit(cs[i])) {
                i++;
                continue;
            } else if (!Character.isLetterOrDigit(cs[j])) {
                j--;
                continue;
            }
            if ((cs[i] ^ cs[j]) != 0 && (cs[i] ^ cs[j]) != 32) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
