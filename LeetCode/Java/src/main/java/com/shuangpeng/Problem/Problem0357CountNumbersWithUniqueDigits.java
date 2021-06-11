package com.shuangpeng.Problem;

public class Problem0357CountNumbersWithUniqueDigits {

    public int countNumbersWithUniqueDigits0(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                sum += 1;
                continue;
            }
            if (i == 1) {
                sum += 9;
                continue;
            }
            if (i > 10) {
                break;
            }
            int j = i - 1;
            int k = 9;
            int s = 9;
            while (j > 0) {
                s *= k;
                k--;
                j--;
            }
            sum += s;
        }
        return sum;
    }

    public int countNumbersWithUniqueDigits1(int n) {
        if (n <= 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        n = n > 10 ? 10 : n;
        int k = 9;
        int i = 9;
        int sum = 10;
        while (n > 1) {
            k *= i;
            sum += k;
            n--;
            i--;
        }
        return sum;
    }

    public int countNumbersWithUniqueDigits(int n) {
        if (n <= 0) {
            return 1;
        }
        if (n == 1) {
            return 10;
        }
        n = n > 10 ? 10 : n;
        int k = 9;
        int sum = 10;
        for (int i = 9; n > 1; i--) {
            k *= i;
            sum += k;
            n--;
        }
        return sum;
    }
}
