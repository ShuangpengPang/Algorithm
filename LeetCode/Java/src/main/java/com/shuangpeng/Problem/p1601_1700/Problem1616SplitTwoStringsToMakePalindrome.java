package com.shuangpeng.Problem.p1601_1700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1616SplitTwoStringsToMakePalindrome（分割两个字符串得到回文串）
 * @date 2023/4/21 7:48 PM
 */
public class Problem1616SplitTwoStringsToMakePalindrome {

    public boolean checkPalindromeFormation0(String a, String b) {
        char[] cs1 = a.toCharArray(), cs2 = b.toCharArray();
        return check(cs1, cs2) || check(cs2, cs1);
    }

    private boolean check(char[] cs1, char[] cs2) {
        char[] cs = cs1;
        boolean ans = true;
        for (int i = 0, j = cs.length - 1; i < j; i++, j--) {
            if (cs[i] != cs2[j]) {
                if (cs2[i] != cs2[j]) {
                    ans = false;
                    break;
                }
                cs = cs2;
            }
        }
        if (ans) {
            return true;
        }
        cs = cs2;
        for (int i = 0, j = cs.length - 1; i < j; i++, j--) {
            if (cs1[i] != cs[j]) {
                if (cs1[i] != cs1[j]) {
                    return false;
                }
                cs = cs1;
            }
        }
        return true;
    }

    public boolean checkPalindromeFormation(String a, String b) {
        char[] cs1 = a.toCharArray(), cs2 = b.toCharArray();
        int n = cs1.length, left = (n - 1) >> 1, right = n - left - 1;
        boolean f1 = true, f2 = true;
        while (left >= 0) {
            f1 = f1 && cs1[left] == cs1[right];
            f2 = f2 && cs2[left] == cs2[right];
            if (!f1 && !f2) {
                break;
            }
            left--;
            right++;
        }
        f1 = f2 = true;
        for (int i = 0, j = n - 1; i <= left && (f1 || f2); i++, j--) {
            f1 = f1 && cs1[i] == cs2[j];
            f2 = f2 && cs2[i] == cs1[j];
        }
        return f1 || f2;
    }
}
