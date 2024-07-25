package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3210FindTheEncryptedString（找出加密后的字符串）
 * @date 2024/7/25 11:19 PM
 */
public class Problem3210FindTheEncryptedString {

    public String getEncryptedString(String s, int k) {
        k %= s.length();
        return s.substring(k) + s.substring(0, k);
    }
}
