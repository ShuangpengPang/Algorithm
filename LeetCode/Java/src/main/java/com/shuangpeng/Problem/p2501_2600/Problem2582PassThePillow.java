package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2582PassThePillow（递枕头）
 * @date 2023/9/26 11:20 AM
 */
public class Problem2582PassThePillow {

    public int passThePillow0(int n, int time) {
        int c = time / (n - 1), m = time % (n - 1);
        return (c & 1) == 0 ? 1 + m : n - m;
    }

    public int passThePillow(int n, int time) {
        time = time % (n - 1 << 1);
        return 1 + Math.min(time, (n - 1 << 1) - time);
    }
}
