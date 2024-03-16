package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1544MakeTheStringGreat（整理字符串）
 * @date 2024/3/16 5:16 PM
 */
public class Problem1544MakeTheStringGreat {

    public String makeGood(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length, top = 1;
        for (int i = 1; i < n; i++) {
            if (top > 0 && (cs[top - 1] ^ cs[i]) == 32) {
                top--;
            } else {
                cs[top++] = cs[i];
            }
        }
        return new String(cs, 0, top);
    }
}
