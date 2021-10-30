package com.shuangpeng.Problem.p0101_0200;

import java.util.HashMap;
import java.util.Map;

public class Problem0166FractionToRecurringDecimal {

    public String fractionToDecimal0(int numerator, int denominator) {
        if (numerator == 0 || denominator == 0) {
            return "0";
        }
        long a = numerator;
        long b = denominator;
        boolean isPositive = (a > 0 && b > 0) || (a < 0 && b < 0);
        a = a > 0 ? a : -a;
        b = b > 0 ? b : -b;
        long quotient = a / b;
        quotient = isPositive ? quotient : -quotient;
        a = a % b;
        if (a == 0) {
            return Long.toString(quotient);
        }
        Map<Long, Integer> map = new HashMap<>(105);
        StringBuilder builder = new StringBuilder();
        if (!isPositive && quotient == 0) {
            builder.append('-');
        }
        builder.append(quotient).append('.');
        map.put(a, map.size());
        a *= 10;
        while (a != 0) {
            builder.append(a / b);
            a = a % b;
            if (map.containsKey(a)) {
                int count = map.size() - map.get(a);
                builder.insert(builder.length() - count, '(');
                builder.append(')');
                break;
            } else {
                map.put(a, map.size());
            }
            a *= 10;
        }
        return builder.toString();
    }

    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder fraction = new StringBuilder();
        // If either one is negative (not both)
        if (numerator < 0 ^ denominator < 0) {
            fraction.append("-");
        }
        // Convert to Long or else abs(-2147483648) overflows
        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));
        fraction.append(String.valueOf(dividend / divisor));
        long remainder = dividend % divisor;
        if (remainder == 0) {
            return fraction.toString();
        }
        fraction.append(".");
        Map<Long, Integer> map = new HashMap<>();
        while (remainder != 0) {
            if (map.containsKey(remainder)) {
                fraction.insert(map.get(remainder), "(");
                fraction.append(")");
                break;
            }
            map.put(remainder, fraction.length());
            remainder *= 10;
            fraction.append(String.valueOf(remainder / divisor));
            remainder %= divisor;
        }
        return fraction.toString();
    }
}
