package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2546ApplyBitwiseOperationsToMakeStringsEqual（执行逐位运算使字符串相等）
 * @date 2023/11/29 9:39 AM
 */
public class Problem2546ApplyBitwiseOperationsToMakeStringsEqual {

    public boolean makeStringsEqual0(String s, String target) {
        int n = s.length();
        boolean b1 = false, b2 = false;
        for (int i = 0; i < n && (!b1 || !b2); i++) {
            if (s.charAt(i) == '1') {
                b1 = true;
            }
            if (target.charAt(i) == '1') {
                b2 = true;
            }
        }
        return b1 == b2;
    }

    public boolean makeStringsEqual(String s, String target) {
        return s.contains("1") == target.contains("1");
    }
}
