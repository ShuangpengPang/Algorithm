package com.shuangpeng.Problem;

public class Problem0038CountAndSay {

    public String countAndSay0(int n) {
        if (n < 1) {
            return "";
        }
        return recurse(n);
    }

    public String recurse(int n) {
        if (n == 1) {
            return "1";
        }
        String string = recurse(n - 1);
        int length = string.length();
        char ch = string.charAt(0);
        StringBuilder builder = new StringBuilder();
        int count = 1;
        for (int i = 1; i <= length; i++) {
            if (i == length || string.charAt(i) != ch) {
                builder.append(count).append(ch);
                if (i < length) {
                    ch = string.charAt(i);
                }
                count = 1;
            } else {
                count++;
            }
        }
        return builder.toString();
    }

    public String countAndSay1(int n) {
        if (n < 1) {
            return "";
        }
        String str = "1";
        for (int i = 2; i <= n; i++) {
            StringBuilder builder = new StringBuilder();
            int length = str.length();
            char ch = str.charAt(0);
            int count = 1;
            for (int j = 1; j <= length; j++) {
                if (j == length || str.charAt(j) != ch) {
                    builder.append(count).append(ch);
                    if (j < length) {
                        ch = str.charAt(j);
                        count = 1;
                    }
                } else {
                    count++;
                }
            }
            str = builder.toString();
        }
        return str;
    }

    static final int N = 31;
    static String[] table = new String[N];
    static {
        table[1] = "1";
        for (int i = 2; i < N; ++i) {
            int length = table[i - 1].length();
            char c = table[i - 1].charAt(0);
            int count = 1;
            StringBuilder sb = new StringBuilder();
            for (int j = 1; j < length; ++j) {
                if (table[i - 1].charAt(j) == c) {
                    ++count;
                } else {
                    sb.append(count).append(c);
                    c = table[i - 1].charAt(j);
                    count = 1;
                }
            }
            sb.append(count).append(c);
            table[i] = sb.toString();
        }
    }

    public String countAndSay(int n) {
        return table[n];
    }
}
