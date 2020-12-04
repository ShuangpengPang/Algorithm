package com.shuangpeng.Problem;

import java.util.HashMap;
import java.util.Map;

public class Problem0008StringToInteger {

//    public static void main(String[] args) {
//        Problem0008StringToInteger a = new Problem0008StringToInteger();
//        a.myAtoi("   -42");
//    }

    public int myAtoi0(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        boolean isStart = false;
        boolean hasSign = false;
        int length = s.length();
        int sign = 1;
        int answer = 0;
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (!isStart && ch == ' ') {
                continue;
            }
            if (!isStart && ch != '-' && ch != '+' && (ch < '0' || ch > '9')) {
                return 0;
            }
            if (!isStart) {
                isStart = true;
            }
            if (ch != '+' && ch != '-' && (ch < '0' || ch > '9')) {
                break;
            }
            if (hasSign && (ch == '+' || ch == '-')) {
                break;
            }
            if (ch == '+') {
                hasSign = true;
                continue;
            }
            if (ch == '-') {
                hasSign = true;
                sign *= -1;
                continue;
            }
            if (!hasSign) {
                hasSign = true;
            }
            int value = ch - '0';
            if (sign == 1 && answer > (Integer.MAX_VALUE - value) / 10) {
                return Integer.MAX_VALUE;
            }
            if (sign == -1
                    && (-answer < Integer.MIN_VALUE / 10 || (-answer == Integer.MIN_VALUE / 10 && value > -(Integer.MIN_VALUE % 10)))) {
                return Integer.MIN_VALUE;
            }
            answer = (answer * 10) + value;
        }
        return sign == 1 ? answer : -answer;
    }

    int start = 0;
    int sign = 1;
    int inNumber = 2;
    int end = 3;

    Map<Integer, int[]> map = new HashMap<Integer, int[]>() {{
        put(start, new int[]{start, sign, inNumber, end});
        put(sign, new int[]{end, end, inNumber, end});
        put(inNumber, new int[]{end, end, inNumber, end});
        put(end, new int[]{end, end, inNumber, end});
    }};

    public int myAtoi(String s) {
        if (s == null) {
            return 0;
        }
        int length = s.length();
        int state = start;
        int answer = 0;
        boolean isPositive = true;
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            state = getNextState(state, getStateIndex(ch));
            if (state == sign) {
                if (ch == '-') {
                    isPositive = false;
                }
            } else if (state == inNumber) {
                int number = ch - '0';
                if (isPositive && answer > (Integer.MAX_VALUE - number) / 10) {
                    answer = Integer.MAX_VALUE;
                    break;
                } else if (!isPositive && (-answer < Integer.MIN_VALUE / 10 || (-answer == Integer.MIN_VALUE / 10 && number > -(Integer.MIN_VALUE % 10)))) {
                    answer = Integer.MIN_VALUE;
                    break;
                }
                answer = answer * 10 + number;
            } else if (state == end) {
                break;
            }
        }
        return isPositive ? answer : -answer;
    }

    public int getNextState(int state, int index) {
        return map.get(state)[index];
    }

    public int getStateIndex(char ch) {
        if (ch == ' ') {
            return start;
        }
        if (ch == '+' || ch == '-') {
            return sign;
        }
        if (ch >= '0' && ch <= '9') {
            return inNumber;
        }
        return end;
    }
}
