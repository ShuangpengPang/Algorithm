package com.shuangpeng.Problem.p0001_0100;

import java.util.HashMap;
import java.util.Map;

public class Problem0065ValidNumber {

    public boolean isNumber0(String s) {
        int n = s.length();
        int i = s.indexOf('e');
        if (i == -1) {
            i = s.indexOf('E');
        }
        if (i != -1) {
            return isInteger(s, i + 1, n) && (isDecimal(s, 0, i) || isInteger(s, 0, i));
        }
        return isDecimal(s, 0, n) || isInteger(s, 0, n);
    }

    private boolean isOnlyDigits(String s, int start, int end) {
        if (start >= end) {
            return false;
        }
        for (int i = start; i < end; i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isInteger(String s, int start, int end) {
        if (start >= end) {
            return false;
        }
        char c = s.charAt(start);
        if (c == '+' || c == '-') {
            return isOnlyDigits(s, start + 1, end);
        }
        return isOnlyDigits(s, start, end);
    }

    private boolean isDecimal(String s, int start, int end) {
        if (start >= end) {
            return false;
        }
        char c = s.charAt(start);
        if (c == '+' || c == '-') {
            start++;
        }
        if (start >= end) {
            return false;
        }
        if (s.charAt(start) == '.') {
            return isOnlyDigits(s, start + 1, end);
        }
        if (s.charAt(end - 1) == '.') {
            return isOnlyDigits(s, start, end - 1);
        }
        int i = s.indexOf('.', start);
        if (i < start || i >= end) {
            return false;
        }
        return isOnlyDigits(s, start, i) && isOnlyDigits(s, i + 1, end);
    }

    /**
     * 开始
     非法
     结束
     符号位
     数字
     左边没有整形的decimal的整数部分
     左边有整数的decimal的整数部分
     e字符
     指数部分符号位
     指数部分整数部分

     */

    private static final int start = 0;
    private static final int sign = 1;
    private static final int digit = 2;
    private static final int noLeftDecimal = 3;
    private static final int leftPartOfDecimal = 4;
    private static final int rightPartOfDecimal = 5;
    private static final int e = 6;
    private static final int eSign = 7;
    private static final int integerOfE = 8;
    private static final int invalid = 9;

    public boolean isNumber1(String s) {
        int n = s.length();
        int state = start;
        for (int i = 0; i < n; i++) {
            state = nextState(s, state, i);
            if (state == invalid) {
                return false;
            }
        }
        return state == digit || state == leftPartOfDecimal
                || state == rightPartOfDecimal || state == integerOfE;
    }

    private int nextState(String s, int state, int i) {
        char c = s.charAt(i);
        switch (state) {
            case start:
                if (c == '+' || c == '-') {
                    return sign;
                } else if (c == '.') {
                    return noLeftDecimal;
                } else if (Character.isDigit(c)) {
                    return digit;
                } else {
                    return invalid;
                }
            case sign:
                if (c == '.') {
                    return noLeftDecimal;
                } else if (Character.isDigit(c)) {
                    return digit;
                } else {
                    return invalid;
                }
            case digit:
                if (c == '.') {
                    return leftPartOfDecimal;
                } else if (Character.isDigit(c)) {
                    return digit;
                } else if (c == 'e' || c == 'E') {
                    return e;
                } else {
                    return invalid;
                }
            case noLeftDecimal:
                if (Character.isDigit(c)) {
                    return rightPartOfDecimal;
                } else {
                    return invalid;
                }
            case leftPartOfDecimal:
                if (c == 'e' || c == 'E') {
                    return e;
                } else if (Character.isDigit(c)) {
                    return rightPartOfDecimal;
                } else {
                    return invalid;
                }
            case rightPartOfDecimal:
                if (c == 'e' || c == 'E') {
                    return e;
                } else if (Character.isDigit(c)) {
                    return rightPartOfDecimal;
                } else {
                    return invalid;
                }
            case e:
                if (c == '+' || c == '-') {
                    return eSign;
                } else if (Character.isDigit(c)) {
                    return integerOfE;
                } else {
                    return invalid;
                }
            case eSign:
                if (Character.isDigit(c)) {
                    return integerOfE;
                } else {
                    return invalid;
                }
            case integerOfE:
                if (Character.isDigit(c)) {
                    return integerOfE;
                } else {
                    return invalid;
                }
            case invalid:
                return invalid;
        }
        return invalid;
    }

    public boolean isNumber(String s) {
        Map<State, Map<ChartType, State>> transfer = new HashMap<>();
        Map<ChartType, State> initialMap = new HashMap<ChartType, State>() {{
            put(ChartType.SIGN, State.NUMBER_SIGN);
            put(ChartType.POINT, State.POINT_WITHOUT_LEFT);
            put(ChartType.DIGIT, State.INTEGER);
        }};
        transfer.put(State.START, initialMap);

        Map<ChartType, State> numberSignMap = new HashMap<ChartType, State>() {{
            put(ChartType.DIGIT, State.INTEGER);
            put(ChartType.POINT, State.POINT_WITHOUT_LEFT);
        }};
        transfer.put(State.NUMBER_SIGN, numberSignMap);

        Map<ChartType, State> integerMap = new HashMap<ChartType, State>() {{
            put(ChartType.DIGIT, State.INTEGER);
            put(ChartType.POINT, State.FRACTION);
            put(ChartType.EXP, State.EXP);
        }};
        transfer.put(State.INTEGER, integerMap);

        Map<ChartType, State> pointWithLeftMap = new HashMap<ChartType, State>() {{
            put(ChartType.DIGIT, State.FRACTION);
        }};
        transfer.put(State.POINT_WITHOUT_LEFT, pointWithLeftMap);

        Map<ChartType, State> fractionMap = new HashMap<ChartType, State>() {{
            put(ChartType.DIGIT, State.FRACTION);
            put(ChartType.EXP, State.EXP);
        }};
        transfer.put(State.FRACTION, fractionMap);

        Map<ChartType, State> expMap = new HashMap<ChartType, State>() {{
            put(ChartType.SIGN, State.EXP_SIGN);
            put(ChartType.DIGIT, State.EXP_INTEGER);
        }};
        transfer.put(State.EXP, expMap);

        Map<ChartType, State> expSignMap = new HashMap<ChartType, State>() {{
            put(ChartType.DIGIT, State.EXP_INTEGER);
        }};
        transfer.put(State.EXP_SIGN, expSignMap);

        Map<ChartType, State> expIntegerMap = new HashMap<ChartType, State>() {{
            put(ChartType.DIGIT, State.EXP_INTEGER);
        }};
        transfer.put(State.EXP_INTEGER, expIntegerMap);

        int n = s.length();
        State state = State.START;
        for (int i = 0; i < n; i++) {
            state = transfer.get(state).getOrDefault(toChartType(s.charAt(i)), null);
            if (state == null) {
                return false;
            }
        }
        return state == State.INTEGER || state == State.FRACTION
                || state == State.EXP_INTEGER;
    }

    private ChartType toChartType(char c) {
        if (c == '+' || c == '-') {
            return ChartType.SIGN;
        }
        if (c >= '0' && c <= '9') {
            return ChartType.DIGIT;
        }
        if (c == '.') {
            return ChartType.POINT;
        }
        if (c == 'e' || c == 'E') {
            return ChartType.EXP;
        }
        return ChartType.ILLEGAL;
    }

    enum State {
        START,
        NUMBER_SIGN,
        INTEGER,
        POINT_WITHOUT_LEFT,
        FRACTION,
        EXP,
        EXP_SIGN,
        EXP_INTEGER
    }

    enum ChartType {
        SIGN,
        DIGIT,
        POINT,
        EXP,
        ILLEGAL
    }
}
