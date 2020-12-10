package com.shuangpeng.Problem;

public class Problem0029DivideTwoIntegers {

//    public static void main(String[] args) {
//        Problem0029DivideTwoIntegers a = new Problem0029DivideTwoIntegers();
//        int i = a.divide(51, 2);
//        int j = i;
//    }

    public int divide0(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        if (dividend == 0) {
            return 0;
        }
        long a = dividend;
        long b = divisor;
        boolean isPosition = true;
        if ((a < 0 && b > 0) || (a > 0 && b < 0)) {
            isPosition = false;
        }
        if (a < 0) {
            a = -a;
        }
        if (b < 0) {
            b = -b;
        }
        if (a < b) {
            return 0;
        }
        if (b == 1) {
            long result = isPosition ? a : -a;
            if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            return (int) result;
        }
        int quotient = 1;
        int count = 0;
        int result = 0;
        while (a >= b || count > 0) {
            while (a >= b) {
                a -= b;
                result += quotient;
                count++;
                b <<= 1;
                quotient <<= 1;
            }
            if (count > 0) {
                b >>= 1;
                quotient >>= 1;
                count--;
            }
        }
        return isPosition ? result : -result;
    }

    public int divide1(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        if (dividend == 0) {
            return 0;
        }
        long a = dividend;
        long b = divisor;
        boolean isPositive = (a > 0 && b > 0) || (a < 0 && b < 0);
        a = a > 0 ? a : -a;
        b = b > 0 ? b : -b;
        long result = 0;
        if (b == 1) {
            result = a;
        } else {
            result = divideRecurse(a, b);
        }
        result = isPositive ? result : -result;
        return result >= Integer.MIN_VALUE && result <= Integer.MAX_VALUE ? (int) result : Integer.MAX_VALUE;
    }

    public long divideRecurse(long a, long b) {
        if (a < b) {
            return 0;
        }
        long quotient = 1;
        long temp = b;
        while (a >= temp + temp) {
            temp += temp;
            quotient += quotient;
        }
        return quotient + divideRecurse(a - temp, b);
    }

    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        if (dividend == 0) {
            return 0;
        }
        long a = dividend;
        long b = divisor;
        boolean isPositive = (a > 0 && b > 0) || (a < 0 && b < 0);
        a = a > 0 ? a : -a;
        b = b > 0 ? b : -b;
        long quotient = 0;
        if (b == 1) {
            quotient = a;
        } else {
            while (a >= b) {
                long temp = b;
                long result = 1;
                while (a >= temp + temp) {
                    temp += temp;
                    result += result;
                }
                a -= temp;
                quotient += result;
            }
        }
        quotient = isPositive ? quotient : -quotient;
        return quotient > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) quotient;
    }
}
