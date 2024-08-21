package com.shuangpeng.lcr.p101_200;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR164CrackPassword（LCR 164. 破解闯关密码）
 * @date 2024/8/19 6:29 PM
 */
public class LCR164CrackPassword {

    public String crackPassword(int[] password) {
        int n = password.length;
        String[] arr = new String[n];
        Arrays.setAll(arr, i -> String.valueOf(password[i]));
        Arrays.sort(arr, (a, b) -> (a + b).compareTo(b + a));
        StringBuilder sb = new StringBuilder();
        for (String a : arr) {
            sb.append(a);
        }
        return sb.toString();
    }
}
