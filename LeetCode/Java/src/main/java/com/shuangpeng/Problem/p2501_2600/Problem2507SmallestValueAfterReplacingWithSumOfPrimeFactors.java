package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2507SmallestValueAfterReplacingWithSumOfPrimeFactors（使用质因数之和替换后可以取到的最小值）
 * @date 2023/11/22 12:00 AM
 */
public class Problem2507SmallestValueAfterReplacingWithSumOfPrimeFactors {

    public int smallestValue(int n) {
        int t = 0;
        do {
            t = n;
            n = getSum(n);
        } while (n != t);
        return n;
    }

    private int getSum(int n) {
        int sum = 0;
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {
                sum += i;
                n /= i;
            }
        }
        if (n > 1) {
            sum += n;
        }
        return sum;
    }
}
