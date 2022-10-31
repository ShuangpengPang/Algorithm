package com.shuangpeng.Problem.p0301_0400;

/**
 * @Description: Problem0342PowerOfFour（4的幂）
 * @Date 2022/10/31 4:25 PM
 * @Version 1.0
 */
public class Problem0342PowerOfFour {

    public boolean isPowerOfFour(int n) {
        if (n < 1) {
            return false;
        }
        int m = n % 10;
        return (n & (n - 1)) == 0 && (m == 4 || m == 6 || m == 1);
    }
}
