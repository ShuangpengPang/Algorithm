package com.shuangpeng.Problem;

public class Problem0171ExcelSheetColumnNumber {

    public int titleToNumber0(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int length = s.length();
        int answer = 0;
        for (int i = 0; i < length; i++) {
            answer *= 26;
            answer += s.charAt(i) - 'A' + 1;
        }
        return answer;
    }

    public int titleToNumber(String columnTitle) {
        int number = 0;
        for (char c : columnTitle.toCharArray()) {
            number = number * 26 + c - 'A' + 1;
        }
        return number;
    }
}
