package com.shuangpeng.Problem.p3401_3500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3432CountPartitionsWithEvenSumDifference（统计元素和差值为偶数的分区方案）
 * @date 2025/3/12 10:25
 */
public class Problem3432CountPartitionsWithEvenSumDifference {

    public int countPartitions(int[] nums) {
        int s = 0;
        for (int num : nums) {
            s += num;
        }
        return (s & 1) == 1 ? 0 : nums.length - 1;
    }
}
