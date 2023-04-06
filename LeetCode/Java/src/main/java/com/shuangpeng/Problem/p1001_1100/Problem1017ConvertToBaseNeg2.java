package com.shuangpeng.Problem.p1001_1100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1017ConvertToBaseNeg2（负二进制转换）
 * @date 2023/4/6 9:59 AM
 */
public class Problem1017ConvertToBaseNeg2 {

    public String baseNeg20(int n) {
        if (n == 0) {
            return "0";
        }
        int[] bits = new int[32];
        for (int i = 0; i < 32 && n != 0; i++) {
            if ((n & 1) == 1) {
                bits[i]++;
                if ((i & 1) == 1) {
                    bits[i + 1]++;
                }
            }
            n >>= 1;
        }
        int carry = 0;
        for (int i = 0; i < 32; i++) {
            carry += bits[i];
            bits[i] = carry & 1;
            carry = (carry - bits[i]) / -2;
        }
        int pos = 31;
        while (pos >= 0 && bits[pos] == 0) {
            pos--;
        }
        StringBuilder sb = new StringBuilder();
        while (pos >= 0) {
            sb.append(bits[pos--]);
        }
        return sb.toString();
    }

    public String baseNeg21(int n) {
        if (n == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (n != 0) {
            int v = n & 1;
            n = (n - v) / -2;
            sb.append(v);
        }
        return sb.reverse().toString();
    }

    public String baseNeg2(int n) {
        int M = 0x55555555;
        return Integer.toBinaryString(M ^ (M - n));
    }

//    public static void main(String[] args) {
//        int n = ~0;
//        int i = 1;
//    }
}
