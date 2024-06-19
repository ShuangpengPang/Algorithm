package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3178FindTheChildWhoHasTheBallAfterKSeconds（找出K秒后拿着球的孩子）
 * @date 2024/6/17 7:43 PM
 */
public class Problem3178FindTheChildWhoHasTheBallAfterKSeconds {

    public int numberOfChild(int n, int k) {
        int m = k % (n - 1 << 1);
        return m < n ? m : n - 1 - (m - n + 1);
    }
}
