package com.shuangpeng.Problem.p1701_1800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1720DecodeXORedArray（解码异或后的数组）
 * @date 2024/3/15 11:43 AM
 */
public class Problem1720DecodeXORedArray {

    public int[] decode(int[] encoded, int first) {
        int n = encoded.length + 1;
        int[] ans = new int[n];
        ans[0] = first;
        for (int i = 1; i < n; i++) {
            ans[i] = ans[i - 1] ^ encoded[i - 1];
        }
        return ans;
    }
}
