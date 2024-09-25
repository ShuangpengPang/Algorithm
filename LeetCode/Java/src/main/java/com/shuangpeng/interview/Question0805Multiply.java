package com.shuangpeng.interview;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0805Multiply（面试题 08.05. 递归乘法）
 * @date 2024/9/25 3:55 PM
 */
public class Question0805Multiply {

    public int multiply(int A, int B) {
        return dfs(A, B);
    }

    private int dfs(int a, int b) {
        if (b == 0) {
            return 0;
        }
        return (b & 1) == 1 ? a + dfs(a, b ^ 1) : dfs(a << 1, b >> 1);
    }
}
