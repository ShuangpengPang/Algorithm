package com.shuangpeng.Problem.p1801_1900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1860IncrementalMemoryLeak（增长的内存泄露）
 * @date 2023/10/11 7:20 PM
 */
public class Problem1860IncrementalMemoryLeak {

    public int[] memLeak(int memory1, int memory2) {
        int time = 1;
        while (time <= memory1 || time <= memory2) {
            if (memory1 >= memory2) {
                memory1 -= time;
            } else {
                memory2 -= time;
            }
            time++;
        }
        return new int[]{time, memory1, memory2};
    }
}
