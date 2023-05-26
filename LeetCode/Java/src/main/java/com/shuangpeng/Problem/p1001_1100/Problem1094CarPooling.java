package com.shuangpeng.Problem.p1001_1100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1094CarPooling（拼车）
 * @date 2023/5/26 3:26 PM
 */
public class Problem1094CarPooling {

    public boolean carPooling(int[][] trips, int capacity) {
        int N = 1000;
        int[] diff = new int[N + 1];
        for (int[] t : trips) {
            diff[t[1]] += t[0];
            diff[t[2]] -= t[0];
        }
        for (int i = 0, cnt = 0; i <= N; i++) {
            cnt += diff[i];
            if (cnt > capacity) {
                return false;
            }
        }
        return true;
    }
}
