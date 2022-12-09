package com.shuangpeng.Problem.p0401_0500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0459RepeatedSubstringPattern（重复的子字符串）
 * @date 2022/12/9 7:57 PM
 */
public class Problem0459RepeatedSubstringPattern {

    public boolean repeatedSubstringPattern(String s) {
        int n = s.length(), h = n >> 1;
        for (int i = 1; i <= h; i++) {
            if (n % i != 0) {
                continue;
            }
            int c = n / i;
            boolean valid = true;
            for (int j = 1; j < c && valid; j++) {
                for (int k = i * (j - 1); k < i * j; k++) {
                    if (s.charAt(k) != s.charAt(k + i)) {
                        valid = false;
                        break;
                    }
                }
            }
            if (valid) {
                return true;
            }
        }
        return false;
    }
}
