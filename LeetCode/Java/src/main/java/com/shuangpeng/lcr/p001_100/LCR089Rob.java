package com.shuangpeng.lcr.p001_100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR089Rob（打家劫舍）
 * @date 2024/7/4 7:01 PM
 */
public class LCR089Rob {

    public int rob(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            int t = a;
            a = b + num;
            b = Math.max(b, t);
        }
        return Math.max(a, b);
    }
}
