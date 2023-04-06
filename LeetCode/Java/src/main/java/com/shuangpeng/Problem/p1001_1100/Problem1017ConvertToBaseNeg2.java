package com.shuangpeng.Problem.p1001_1100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1017ConvertToBaseNeg2（负二进制转换）
 * @date 2023/4/6 9:59 AM
 */
public class Problem1017ConvertToBaseNeg2 {

    public String baseNeg2(int n) {
        if (n == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            int v = Math.abs(n) & 1;
            n = (n - v) / -2;
            sb.append(v);
        }
        return sb.reverse().toString();
    }
}
