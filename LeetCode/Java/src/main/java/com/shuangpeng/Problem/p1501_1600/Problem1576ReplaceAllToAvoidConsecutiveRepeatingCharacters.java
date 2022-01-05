package com.shuangpeng.Problem.p1501_1600;

public class Problem1576ReplaceAllToAvoidConsecutiveRepeatingCharacters {

    public String modifyString0(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        for (int i = 0; i < n; ++i) {
            char c = chars[i];
            if (c == '?') {
                char c1 = i == 0 ? (char) ('a' - 1) : chars[i - 1];
                char c2 = i == n - 1 ? (char) ('z' + 1) : chars[i + 1];
                int j = 0;
                while ((char) ('a' + j) == c1 || (char) ('a' + j) == c2) {
                    ++j;
                }
                chars[i] = (char) ('a' + j);
            }
        }
        return new String(chars);
    }

    public String modifyString(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        for (int i = 0; i < n; ++i) {
            if (chars[i] == '?') {
                for (char c = 'a'; c <= 'c'; ++c) {
                    if ((i > 0 && chars[i - 1] == c) || (i < n - 1 && chars[i + 1] == c)) {
                        continue;
                    }
                    chars[i] = c;
                    break;
                }
            }
        }
        return new String(chars);
    }
}
