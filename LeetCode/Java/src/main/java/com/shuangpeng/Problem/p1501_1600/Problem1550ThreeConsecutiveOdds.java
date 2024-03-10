package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1550ThreeConsecutiveOdds（存在连续三个奇数的数组）
 * @date 2024/3/11 12:24 AM
 */
public class Problem1550ThreeConsecutiveOdds {

    public boolean threeConsecutiveOdds(int[] arr) {
        int cnt = 0;
        for (int num : arr) {
            cnt = (cnt + 1) * (num & 1);
            if (cnt == 3) {
                return true;
            }
        }
        return false;
    }
}
