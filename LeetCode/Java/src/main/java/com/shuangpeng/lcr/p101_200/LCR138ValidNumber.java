package com.shuangpeng.lcr.p101_200;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR138ValidNumber（LCR 138. 有效数字）
 * @date 2024/7/24 3:39 PM
 */
public class LCR138ValidNumber {

    public boolean validNumber(String s) {
        char[] cs = s.toCharArray();
        State cur = State.Start;
        for (char c : cs) {
            if (c == ' ') {
                if (cur == State.Start || cur == State.FirstSpace) {
                    cur = State.FirstSpace;
                } else if (cur == State.FirstInteger || cur == State.Float || cur == State.SecondInteger || cur == State.SecondSpace) {
                    cur = State.SecondSpace;
                } else {
                    return false;
                }
            } else if (c == '+' || c == '-') {
                if (cur == State.Start || cur == State.FirstSpace) {
                    cur = State.FirstSign;
                } else if (cur == State.E) {
                    cur = State.SecondSign;
                } else {
                    return false;
                }
            } else if (c >= '0' && c <= '9') {
                if (cur == State.Start || cur == State.FirstSpace || cur == State.FirstSign || cur == State.FirstInteger) {
                    cur = State.FirstInteger;
                } else if (cur == State.Dot || cur == State.Float) {
                    cur = State.Float;
                } else if (cur == State.E || cur == State.SecondSign || cur == State.SecondInteger) {
                    cur = State.SecondInteger;
                } else {
                    return false;
                }
            } else if (c == '.') {
                if (cur == State.Start || cur == State.FirstSpace || cur == State.FirstSign) {
                    cur = State.Dot;
                } else if (cur == State.FirstInteger) {
                    cur = State.Float;
                } else {
                    return false;
                }
            } else if (c == 'e' || c == 'E') {
                if (cur == State.FirstInteger || cur == State.Float) {
                    cur = State.E;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return cur == State.FirstInteger || cur == State.Float || cur == State.SecondInteger || cur == State.SecondSpace;
    }

    enum State {
        Start,
        FirstSpace,
        FirstSign,
        FirstInteger,
        Dot,
        Float,
        E,
        SecondSign,
        SecondInteger,
        SecondSpace
    }
}

class LCR138ValidNumber0 {

    private static final Map<State, Map<CharType, State>> transfer = new HashMap<>();

    static {
        Map<CharType, State> startMap = new HashMap<>();
        startMap.put(CharType.Space, State.Start);
        startMap.put(CharType.Sign, State.IntegerSign);
        startMap.put(CharType.Number, State.Integer);
        startMap.put(CharType.Point, State.PointWithoutInteger);
        transfer.put(State.Start, startMap);

        Map<CharType, State> integerSignMap = new HashMap<>();
        integerSignMap.put(CharType.Number, State.Integer);
        integerSignMap.put(CharType.Point, State.PointWithoutInteger);
        transfer.put(State.IntegerSign, integerSignMap);

        Map<CharType, State> integerMap = new HashMap<>();
        integerMap.put(CharType.Number, State.Integer);
        integerMap.put(CharType.Point, State.Point);
        integerMap.put(CharType.E, State.E);
        integerMap.put(CharType.Space, State.End);
        transfer.put(State.Integer, integerMap);

        Map<CharType, State> pointWithoutIntegerMap = new HashMap<>();
        pointWithoutIntegerMap.put(CharType.Number, State.Fraction);
        transfer.put(State.PointWithoutInteger, pointWithoutIntegerMap);

        Map<CharType, State> pointMap = new HashMap<>();
        pointMap.put(CharType.Number, State.Fraction);
        pointMap.put(CharType.E, State.E);
        pointMap.put(CharType.Space, State.End);
        transfer.put(State.Point, pointMap);

        Map<CharType, State> fractionMap = new HashMap<>();
        fractionMap.put(CharType.Number, State.Fraction);
        fractionMap.put(CharType.E, State.E);
        fractionMap.put(CharType.Space, State.End);
        transfer.put(State.Fraction, fractionMap);

        Map<CharType, State> eMap = new HashMap<>();
        eMap.put(CharType.Sign, State.ExpSign);
        eMap.put(CharType.Number, State.ExpInteger);
        transfer.put(State.E, eMap);

        Map<CharType, State> expSignMap = new HashMap<>();
        expSignMap.put(CharType.Number, State.ExpInteger);
        transfer.put(State.ExpSign, expSignMap);

        Map<CharType, State> expIntegerMap = new HashMap<>();
        expIntegerMap.put(CharType.Number, State.ExpInteger);
        expIntegerMap.put(CharType.Space, State.End);
        transfer.put(State.ExpInteger, expIntegerMap);

        Map<CharType, State> endMap = new HashMap<>();
        endMap.put(CharType.Space, State.End);
        transfer.put(State.End, endMap);
    }

    public boolean validNumber(String s) {
        State state = State.Start;
        for (char c : s.toCharArray()) {
            CharType type = convertToCharType(c);
            Map<CharType, State> map = transfer.get(state);
            if (!map.containsKey(type)) {
                return false;
            }
            state = map.get(type);
        }
        return state == State.Integer || state == State.Fraction || state == State.Point || state == State.ExpInteger || state == State.End;
    }

    private CharType convertToCharType(char c) {
        switch (c) {
            case ' ':
                return CharType.Space;
            case '+':
            case '-':
                return CharType.Sign;
            case '.':
                return CharType.Point;
            case 'e':
            case 'E':
                return CharType.E;
            default:
                if (c >= '0' && c <= '9') {
                    return CharType.Number;
                }
        }
        return CharType.Invalid;
    }

    enum State {
        Start,
        IntegerSign,
        Integer,
        PointWithoutInteger,
        Point,
        Fraction,
        E,
        ExpSign,
        ExpInteger,
        End
    }

    enum CharType {
        Space,
        Sign,
        Number,
        Point,
        E,
        Invalid
    }
}
