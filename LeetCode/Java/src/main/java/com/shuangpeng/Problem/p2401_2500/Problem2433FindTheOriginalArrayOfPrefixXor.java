package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2433FindTheOriginalArrayOfPrefixXor（找出前缀异或的原始数组）
 * @date 2023/11/20 4:33 PM
 */
public class Problem2433FindTheOriginalArrayOfPrefixXor {

    public int[] findArray(int[] pref) {
        int n = pref.length;
        int[] ans = new int[n];
        ans[0] = pref[0];
        for (int i = 1; i < n; i++) {
            ans[i] = pref[i] ^ pref[i - 1];
        }
        return ans;
    }
}
