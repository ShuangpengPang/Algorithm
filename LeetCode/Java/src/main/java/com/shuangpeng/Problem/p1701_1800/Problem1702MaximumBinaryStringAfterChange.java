package com.shuangpeng.Problem.p1701_1800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1702MaximumBinaryStringAfterChange（修改后的最大二进制字符串）
 * @date 2024/4/10 10:26 AM
 */
public class Problem1702MaximumBinaryStringAfterChange {

    public String maximumBinaryString0(String binary) {
        char[] cs = binary.toCharArray();
        int n = cs.length, cnt = 0;
        boolean hasZero = false;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '0') {
                hasZero = true;
            } else if (hasZero) {
                cnt++;
            }
        }
        if (!hasZero) {
            return binary;
        }
        for (int i = 0; i < n; i++) {
            cs[i] = i == n - cnt - 1 ? '0' : '1';
        }
        return new String(cs);
    }

    public String maximumBinaryString(String binary) {
        char[] cs = binary.toCharArray();
        int n = cs.length, j = 0;
        for (int i = 0; i < n; i++) {
            if (cs[i] == '0') {
                while ((j <= i) || j < n && cs[j] == '1') {
                    j++;
                }
                if (j < n) {
                    cs[i] = cs[j] = '1';
                    cs[i + 1] = '0';
                }
            }
        }
        return new String(cs);
    }
}
