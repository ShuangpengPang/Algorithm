package com.shuangpeng.competition.双周赛.第080场双周赛;

/**
 * @Description: Problem2299StrongPasswordCheckerII（强密码检验器II）
 * @Date 2022/6/25 4:13 PM
 * @Version 1.0
 */
public class Problem2299StrongPasswordCheckerII {

    // 比赛时写法
    public boolean strongPasswordCheckerII0(String password) {
        int n = password.length();
        if (n < 8) {
            return false;
        }
        boolean hasLower = false, hasUpper = false, hasNum = false, hasOther = false;
        String s = "!@#$%^&*()-+";
        for (int i = 0; i < n; ++i) {
            char c = password.charAt(i);
            if (i > 0 && c == password.charAt(i - 1)) {
                return false;
            }
            if (c >= 'a' && c <= 'z') {
                hasLower = true;
            } else if (c >= 'A' && c <= 'Z') {
                hasUpper = true;
            } else if (c >= '0' && c <= '9') {
                hasNum = true;
            } else if (s.indexOf(c) != -1) {
                hasOther = true;
            }
        }
        return hasLower && hasUpper && hasNum && hasOther;
    }

    public boolean strongPasswordCheckerII(String password) {
        int n = password.length();
        if (n < 8) {
            return false;
        }
        String s = "!@#$%^&*()-+";
        int result = 0;
        for (int i = 0; i < n; i++) {
            char c = password.charAt(i);
            if (i > 0 && c == password.charAt(i - 1)) {
                return false;
            }
            if (c >= 'a' && c <= 'z') {
                result |= 1;
            } else if (c >= 'A' && c <= 'Z') {
                result |= 2;
            } else if (c >= '0' && c <= '9') {
                result |= 4;
            } else if (s.indexOf(c) != -1) {
                result |= 8;
            }
        }
        return result == 15;
    }
}
