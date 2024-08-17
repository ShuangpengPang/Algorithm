package com.shuangpeng.lcr.p101_200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR165CrackNumber（LCR 165. 解密数字）
 * @date 2024/8/17 2:55 PM
 */
public class LCR165CrackNumber {

    public int crackNumber(int ciphertext) {
        char[] cs = String.valueOf(ciphertext).toCharArray();
        int n = cs.length, a = 1, b = 1;
        for (int i = 1; i < n; i++) {
            int t = b;
            if (cs[i - 1] == '1' || cs[i - 1] == '2' && cs[i] <= '5') {
                b += a;
            }
            a = t;
        }
        return b;
    }
}
