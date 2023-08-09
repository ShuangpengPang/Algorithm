package com.shuangpeng.Problem.p1201_1300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1281SubtractTheProductAndSumOfDigitsOfAnInteger（整数的各位积和之差）
 * @date 2023/8/9 10:32 AM
 */
public class Problem1281SubtractTheProductAndSumOfDigitsOfAnInteger {

    public int subtractProductAndSum(int n) {
        int product = 1, sum = 0;
        while (n != 0) {
            product *= n % 10;
            sum += n % 10;
            n /= 10;
        }
        return product - sum;
    }
}
