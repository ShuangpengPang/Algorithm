package com.shuangpeng.Problem.p0401_0500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0415AddStrings（字符串相加）
 * @date 2023/7/17 11:28 AM
 */
public class Problem0415AddStrings {

    public String addStrings(String num1, String num2) {
        StringBuilder sb = new StringBuilder();
        for (int i = num1.length() - 1, j = num2.length() - 1, carry = 0; i >= 0 || j >= 0 || carry > 0; i--, j--) {
            if (i >= 0) {
                carry += num1.charAt(i) - '0';
            }
            if (j >= 0) {
                carry += num2.charAt(j) - '0';
            }
            sb.append(carry % 10);
            carry /= 10;
        }
        return sb.reverse().toString();
    }
}
