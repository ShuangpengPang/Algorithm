package com.shuangpeng.Problem.p3301_3400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3340CheckBalancedString（检查平衡字符串）
 * @date 2024/11/26 2:39 PM
 */
public class Problem3340CheckBalancedString {

    public boolean isBalanced(String num) {
        char[] cs = num.toCharArray();
        int n = cs.length, diff = 0;
        for (int i = 0; i < n; i++) {
            diff += (i & 1) == 0 ? cs[i] - '0' : -(cs[i] - '0');
        }
        return diff == 0;
    }
}
