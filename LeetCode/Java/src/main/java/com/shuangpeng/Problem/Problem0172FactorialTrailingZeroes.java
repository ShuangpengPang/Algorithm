package com.shuangpeng.Problem;

public class Problem0172FactorialTrailingZeroes {

    public int trailingZeroes(int n) {
        int base = 5;
        int answer = 0;
        while (n >= base) {
            answer += n / base;
            base *= 5;
        }
        return answer;
    }
}
