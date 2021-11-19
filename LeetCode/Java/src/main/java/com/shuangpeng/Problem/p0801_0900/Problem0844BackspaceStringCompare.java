package com.shuangpeng.Problem.p0801_0900;

public class Problem0844BackspaceStringCompare {

    public boolean backspaceCompare0(String s, String t) {
        int m = s.length(), n = t.length();
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < m; ++i) {
            char c = s.charAt(i);
            if (c == '#') {
                if (sb1.length() > 0) {
                    sb1.deleteCharAt(sb1.length() - 1);
                }
            } else {
                sb1.append(c);
            }
        }
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            char c = t.charAt(i);
            if (c == '#') {
                if (sb2.length() > 0) {
                    sb2.deleteCharAt(sb2.length() - 1);
                }
            } else {
                sb2.append(c);
            }
        }
        return sb1.toString().equals(sb2.toString());
    }

    public boolean backspaceCompare(String s, String t) {
        int i = s.length() - 1, j = t.length() - 1;
        int s1 = 0, s2 = 0;
        while (i >= 0 || j >= 0) {
            if (i < 0) {
                return check(t, j, s2);
            } else if (j < 0) {
                return check(s, i, s1);
            }
            char c1 = s.charAt(i), c2 = t.charAt(j);
            if (c1 == '#') {
                ++s1;
                --i;
            } else if (s1 > 0) {
                --s1;
                --i;
            } else if (c2 == '#') {
                ++s2;
                --j;
            } else if (s2 > 0) {
                --s2;
                --j;
            } else if (c1 == c2) {
                --i;
                --j;
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean check(String s, int i, int c) {
        while (i >= 0) {
            char ch = s.charAt(i);
            if (ch == '#') {
                ++c;
            } else if (c > 0) {
                --c;
            } else {
                return false;
            }
            --i;
        }
        return true;
    }
}
