package com.shuangpeng;

import java.util.ArrayList;
import java.util.List;

public class Problem0017LetterCombinations {

    public static void main(String[] args) {
        Problem0017LetterCombinations a = new Problem0017LetterCombinations();
        a.letterCombinations("23");
    }

    public List<String> letterCombinations0(String digits) {
        if (digits == null) {
            return null;
        }
        int length = digits.length();
        List<String> result = new ArrayList<>();
        StringBuffer stringBuffer = new StringBuffer();
        int[] selected = new int[length];
        int i = 0;
        while (i >= 0 && i < length) {
            int number = digits.charAt(i) - '0';
            int index = 3 * (number - 2);
            if (number > 7) {
                index++;
            }
            if ((selected[i] < 3) || ((number == 7 || number == 9) && selected[i] < 4)) {
                index = index + selected[i]++;
                stringBuffer.append((char) ('a' + index));
                if (i == length - 1) {
                    result.add(stringBuffer.toString());
                    stringBuffer.deleteCharAt(i);
                }
                if (i < length - 1) {
                    i++;
                }
            } else {
                selected[i] = 0;
                i--;
                if (i >= 0) {
                    stringBuffer.deleteCharAt(i);
                }
            }
        }
        return result;
    }

    public List<String> letterCombinations(String digits) {
        if (digits == null) {
            return null;
        }
        result = new ArrayList<>();
        if (digits.length() == 0) {
            return result;
        }
        backtrack(digits, 0, new StringBuilder());
        return result;
    }

    List<String> result;

    // 回溯算法
    public void backtrack(String digits, int count, StringBuilder stringBuilder) {
        if (count == digits.length()) {
            result.add(stringBuilder.toString());
            return;
        }
        char c = digits.charAt(count);
        int number = c - '0';
        int index = 3 * (number - 2);
        if (number > 7) {
            index++;
        }
        int length = 3;
        if (number == 7 || number == 9) {
            length = 4;
        }
        for (int i = 0; i < length; i++) {
            stringBuilder.append((char) ('a' + index + i));
            backtrack(digits, count + 1, stringBuilder);
            stringBuilder.deleteCharAt(count);
        }
    }
}
