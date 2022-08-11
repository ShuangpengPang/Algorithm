package com.shuangpeng.Problem.p1401_1500;

/**
 * @Description: Problem1417ReformatTheString（重新格式化字符串）
 * @Date 2022/8/11 10:00 AM
 * @Version 1.0
 */
public class Problem1417ReformatTheString {

    public String reformat0(String s) {
        int n = s.length();
        StringBuilder sb1 = new StringBuilder(), sb2 = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                sb1.append(c);
            } else {
                sb2.append(c);
            }
        }
        if (sb1.length() < sb2.length()) {
            StringBuilder tmp = sb1;
            sb1 = sb2;
            sb2 = tmp;
        }
        int n1 = sb1.length(), n2 = sb2.length();
        if (n1 - n2 > 1) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n2; i++) {
            sb.append(sb1.charAt(i));
            sb.append(sb2.charAt(i));
        }
        if (n1 > n2) {
            sb.append(sb1.charAt(n1 - 1));
        }
        return sb.toString();
    }

    public String reformat1(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length, digits = 0;
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(cs[i])) {
                digits++;
            }
        }
        int letters = n - digits;
        if (Math.abs(digits - letters) > 1) {
            return "";
        }
        boolean flag = digits > letters;
        for (int i = 0, j = 1; i < n; i += 2) {
            if (Character.isDigit(cs[i]) != flag) {
                while (Character.isDigit(cs[j]) != flag) {
                    j += 2;
                }
                swap(cs, i, j);
            }
        }
        return new String(cs);
    }

    private void swap(char[] cs, int i, int j) {
        char c = cs[i];
        cs[i] = cs[j];
        cs[j] = c;
    }

    public String reformat(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length, digits = 0;
        for (char c : cs) {
            if (c >= '0' && c <= '9') {
                digits++;
            }
        }
        int letters = n - digits;
        if (Math.abs(digits - letters) > 1) {
            return "";
        }
        int idx1 = 0, idx2 = 1;
        if (letters > digits) {
            idx1 = 1;
            idx2 = 0;
        }
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                cs[idx1] = c;
                idx1 += 2;
            } else {
                cs[idx2] = c;
                idx2 += 2;
            }
        }
        return new String(cs);
    }
}

