package com.shuangpeng.lcr;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR127TrainWays（跳跃训练）
 * @date 2024/5/13 4:58 PM
 */
public class LCR127TrainWays {

    public int trainWays(int num) {
        int N = (int) 1e9 + 7;
        int last1 = 0, last = 1, t = 1;
        for (int i = 0; i < num; i++) {
            t = last;
            last = (last + last1) % N;
            last1 = t;
        }
        return last;
    }
}
