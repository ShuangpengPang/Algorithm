package com.shuangpeng.lcr.p101_200;

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
