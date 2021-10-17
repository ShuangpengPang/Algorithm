package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.List;

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

    public int divide2(int dividend, int divisor) {
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

    public int divide3(int dividend, int divisor) {
        long a = dividend, b = divisor;
        boolean sign = true;
        if (a < 0) {
            a = -a;
            sign = !sign;
        }
        if (b < 0) {
            b = -b;
            sign = !sign;
        }
        long ans = 0;
        while (a >= b) {
            long temp = b;
            int count = 1;
            a -= temp;
            temp <<= 1;
            ++ans;
            while (a >= temp) {
                a -= temp;
                count <<= 1;
                temp <<= 1;
                ans += count;
            }
        }
        return (int) (sign ? (ans > Integer.MAX_VALUE ? Integer.MAX_VALUE : ans) : -ans);
    }

    public int divide4(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return dividend;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        boolean sign = true;
        if (dividend > 0) {
            sign = !sign;
            dividend = -dividend;
        }
        if (divisor > 0) {
            sign = !sign;
            divisor = -divisor;
        }
        int left = 1, right = Integer.MAX_VALUE, ans = 0;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (check(dividend, divisor, mid)) {
                ans = mid;
                if (ans == Integer.MAX_VALUE) {
                    break;
                }
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return sign ? ans : -ans;
    }

    private boolean check(int x, int y, int z) {
        // x <= y * z;
        int sum = 0, add = y;
        while (z > 0) {
            if ((z & 1) == 1) {
                if (sum < x - add) {
                    return false;
                }
                sum += add;
            }
            if (z > 1) {
                if (add < x - add) {
                    return false;
                }
            }
            add += add;
            z >>= 1;
        }
        return true;
    }

    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == 1) {
                return dividend;
            }
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            }
        }
        if (divisor == Integer.MIN_VALUE) {
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        }
        boolean sign = true;
        if (dividend > 0) {
            dividend = -dividend;
            sign = !sign;
        }
        if (divisor > 0) {
            divisor = -divisor;
            sign = !sign;
        }
        List<Integer> list = new ArrayList<>();
        list.add(divisor);
        int sum = divisor;
        while (sum >= dividend - sum) {
            sum <<= 1;
            list.add(sum);
        }
        int ans = 0;
        for (int i = list.size() - 1; i >= 0; --i) {
            if (list.get(i) >= dividend) {
                ans += 1 << i;
                dividend -= list.get(i);
            }
        }
        return sign ? ans : -ans;
    }
}
