package com.shuangpeng.Problem.p0901_1000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0984StringWithoutAAAOrBBB（不含AAA或BBB的字符串）
 * @date 2023/4/17 6:11 PM
 */
public class Problem0984StringWithoutAAAOrBBB {

    public String strWithout3a3b(int a, int b) {
        int n = a + b;
        int c = a, d = b;
        char c1 = 'a', c2 = 'b';
        if (a < b) {
            c = b;
            d = a;
            c1 = 'b';
            c2 = 'a';
        }
        int cnt = Math.min(d, c - d);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cnt; i++) {
            sb.append(c1).append(c1).append(c2);
        }
        for (int i = cnt; i < d; i++) {
            sb.append(c1).append(c2);
        }
        int l = c - d - cnt;
        for (int i = 0; i < l; i++) {
            sb.append(c1);
        }
        return sb.toString();
    }
}
