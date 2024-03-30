package com.shuangpeng.Problem.p1401_1500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1486XorOperationInAnArray（数组异或操作）
 * @date 2024/3/4 11:58 PM
 */
public class Problem1486XorOperationInAnArray {

    public int xorOperation0(int n, int start) {
        int ans = 0, max = start + (n << 1);
        for (int i = start; i < max; i += 2) {
            ans ^= i;
        }
        return ans;
    }

    public int xorOperation(int n, int start) {
        int s = start >> 1, e = s + n - 1;
        return (getResult(e) ^ (s > 1 ? getResult(s - 1) : 0)) << 1 | ((start & 1) & (n & 1));
    }

    private int getResult(int x) {
        int[] map = {x, 1, x + 1, 0};
        return map[x & 3];
    }
}
