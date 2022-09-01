package com.shuangpeng.competition.第231到240场周赛.第231场周赛;

public class Problem1784 {

    public boolean checkOnesSegment(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int n = s.length();
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (flag && s.charAt(i) == '1') {
                return false;
            } else if (s.charAt(i) == '0') {
                flag = true;
            }
        }
        return true;
    }
}
