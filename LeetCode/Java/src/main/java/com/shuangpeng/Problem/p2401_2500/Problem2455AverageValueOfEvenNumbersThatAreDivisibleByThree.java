package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2455AverageValueOfEvenNumbersThatAreDivisibleByThree（可被三整除的偶数的平均值）
 * @date 2023/5/29 12:12 PM
 */
public class Problem2455AverageValueOfEvenNumbersThatAreDivisibleByThree {

    public int averageValue(int[] nums) {
        int sum = 0, cnt = 0;
        for (int num : nums) {
            if (num % 6 == 0) {
                sum += num;
                cnt++;
            }
        }
        return cnt == 0 ? 0 : sum / cnt;
    }
}
