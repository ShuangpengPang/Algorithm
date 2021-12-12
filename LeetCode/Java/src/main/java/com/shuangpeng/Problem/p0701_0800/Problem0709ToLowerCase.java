package com.shuangpeng.Problem.p0701_0800;

public class Problem0709ToLowerCase {

    public String toLowerCase(String s) {
        int n = s.length();
        char[] chars = new char[n];
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            c = c >= 'A' && c <= 'Z' ? (char) (c + 'a' - 'A') : c;
            chars[i] = c;
        }
        return new String(chars);
    }
}
