package com.shuangpeng.Problem.p0001_0100;

public class Problem0013RomanToInteger {

    public int romanToInt(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        int i = 0;
        int sum = 0;
        while (i < length) {
            char ch = s.charAt(i);
            char next = '$';
            if (i < length - 1) {
                next = s.charAt(i + 1);
            }
            int value = getValue(ch);
            int nextValue = getValue(next);
            if (value >= nextValue) {
                sum += value;
                i++;
            } else {
                sum += (nextValue - value);
                i += 2;
            }
        }
        return sum;
    }

    public int getValue(char ch) {
        if (ch == 'I') {
            return 1;
        } else if (ch == 'V') {
            return 5;
        } else if (ch == 'X') {
            return 10;
        } else if (ch == 'L') {
            return 50;
        } else if (ch == 'C') {
            return 100;
        } else if (ch == 'D') {
            return 500;
        } else if (ch == 'M') {
            return 1000;
        } else {
            return 0;
        }
    }
}
