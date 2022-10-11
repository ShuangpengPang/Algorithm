package com.shuangpeng.competition.第231到240场周赛.第232场周赛;

/**
 * @Description:（仅执行一次字符串交换能否使两个字符串相等）
 * @Date 2022/10/11 6:18 PM
 **/
public class Problem1790 {

    public boolean areAlmostEqual0(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int n = s1.length();
        int s = -1, t = -1, count = 0;
        for (int i = 0; i < n; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
                if (count > 2) {
                    return false;
                }
                if (s == -1) {
                    s = i;
                } else {
                    t = i;
                }
            }
        }
        if (count == 1) {
            return false;
        }
        return count == 0 || (s1.charAt(s) == s2.charAt(t) && s1.charAt(t) == s2.charAt(s));
    }

    public boolean areAlmostEqual(String s1, String s2) {
        int n = s1.length();
        char c1 = 0, c2 = 0, c3 = 0, c4 = 0;
        for (int i = 0; i < n; i++) {
            char ch1 = s1.charAt(i), ch2 = s2.charAt(i);
            if (ch1 != ch2) {
                if (c1 == 0) {
                    c1 = ch1;
                    c2 = ch2;
                } else if (c3 == 0) {
                    c3 = ch1;
                    c4 = ch2;
                } else {
                    return false;
                }
            }
        }
        return c1 == 0 || c3 != 0 && c1 == c4 && c2 == c3;
    }
}
