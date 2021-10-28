package com.shuangpeng.Problem.p0001_0100;

public class Problem0050PowXN {

//    2.00000
//            -2147483648

    public double myPow0(double x, int n) {
        if (x == 0 || x == 1) {
            return 1;
        }
        if (n == 0) {
            return 1;
        }
        boolean inverse = false;
        if (n < 0) {
            n = -n;
            inverse = true;
        }
        double answer = x;
        while (n > 1) {
            answer *= x;
            n--;
        }
        return inverse ? 1 / answer : answer;
    }

    public double myPow1(double x, int n) {
        return recurse(x, n);
    }

    public double recurse(double x, int n) {
        if (x == 0 || x == 1) {
            return x;
        }
        if (n == 0) {
            return 1;
        }
        boolean reverse = false;
        int temp = n;
        if (temp < 0) {
            temp = -temp;
            reverse = true;
        }
        double answer = x;
        int count = 1;
        while (count * 2 <= temp) {
            answer = answer * answer;
            count = 2 * count;
        }
        if (reverse) {
            return 1 / answer * recurse(x, n + count);
        } else {
            return answer * recurse(x, n - count);
        }
    }

    public double myPow2(double x, int n) {
        if (x == 0 || x == 1) {
            return x;
        }
        if (n == 0) {
            return 1;
        }
        return n > 0 ? quickMul(x, n) : 1 / quickMul(x, -n);
    }

    public double quickMul(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double result = quickMul(x, n / 2);
        if ((n & 1) == 0) {
            return result * result;
        } else {
            return result * result * x;
        }
    }

    public double myPow(double x, int n) {
        if (x == 0 || x == 1) {
            return x;
        }
        if (n == 0) {
            return 1;
        }
        long N = n;
        boolean reverse = false;
        if (N < 0) {
            N = -N;
            reverse = true;
        }
        double power = x;
        double answer = 1;
        while (N > 0) {
            if ((N & 1) == 1) {
                answer *= power;
            }
            N >>= 1;
            power *= power;
        }
        return reverse ? 1 / answer : answer;
    }
}
