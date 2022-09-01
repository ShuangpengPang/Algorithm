package com.shuangpeng.competition.第231到240场周赛.第232场周赛;

public class Problem1790 {

    public boolean areAlmostEqual(String s1, String s2) {
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
}
