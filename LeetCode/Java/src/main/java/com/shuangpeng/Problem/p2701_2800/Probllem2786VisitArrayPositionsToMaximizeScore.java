package com.shuangpeng.Problem.p2701_2800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Probllem2786VisitArrayPositionsToMaximizeScore（访问数组中的位置使分数最大）
 * @date 2023/12/15 11:24 PM
 */
public class Probllem2786VisitArrayPositionsToMaximizeScore {

    public long maxScore(int[] nums, int x) {
        int n = nums.length;
        long N = Long.MIN_VALUE >> 1, even = N, odd = N;
        if ((nums[0] & 1) == 0) {
            even = nums[0];
        } else {
            odd = nums[0];
        }
        for (int i = 1; i < n; i++) {
            if ((nums[i] & 1) == 0) {
                even = nums[i] + Math.max(even, odd - x);
            } else {
                odd = nums[i] + Math.max(odd, even - x);
            }
        }
        return Math.max(even, odd);
    }
}
