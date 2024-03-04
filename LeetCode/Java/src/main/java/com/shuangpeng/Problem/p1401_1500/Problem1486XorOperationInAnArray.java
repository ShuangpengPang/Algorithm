package com.shuangpeng.Problem.p1401_1500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1486XorOperationInAnArray（数组异或操作）
 * @date 2024/3/4 11:58 PM
 */
public class Problem1486XorOperationInAnArray {

    public int xorOperation(int n, int start) {
        int ans = 0, max = start + (n << 1);
        for (int i = start; i < max; i += 2) {
            ans ^= i;
        }
        return ans;
    }
}
