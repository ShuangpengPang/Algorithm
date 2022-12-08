package com.shuangpeng.Problem.p1701_1800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1780CheckIfNumberIsASumOfPowersOfThree（判断一个数字是否可以表示成三的幂的和）
 * @date 2022/12/9 12:07 AM
 */
public class Problem1780CheckIfNumberIsASumOfPowersOfThree {

    public boolean checkPowersOfThree(int n) {
        while (n > 0) {
            if (n % 3 == 2) {
                return false;
            }
            n /= 3;
        }
        return true;
    }
}
