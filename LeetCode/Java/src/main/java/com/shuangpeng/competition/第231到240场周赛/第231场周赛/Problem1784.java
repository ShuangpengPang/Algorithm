package com.shuangpeng.competition.第231到240场周赛.第231场周赛;

/**
 * @Description:（检查二进制字符串字段）
 * @Date 2022/10/4 2:24 PM
 **/
public class Problem1784 {

    public boolean checkOnesSegment0(String s) {
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

    public boolean checkOnesSegment1(String s) {
        return !s.contains("01");
    }

    public boolean checkOnesSegment(String s) {
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') {
                if (i > 0 && s.charAt(i - 1) == '0') {
                    return false;
                }
            }
        }
        return true;
    }
}
