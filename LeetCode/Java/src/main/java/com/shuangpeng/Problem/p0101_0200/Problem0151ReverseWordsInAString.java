package com.shuangpeng.Problem.p0101_0200;

public class Problem0151ReverseWordsInAString {

    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        reverse(chars, 0, n - 1);
        for (int i = 0, j = 1; j < n; ++j) {
            if (chars[j - 1] == ' ' && chars[j] != ' ') {
                i = j;
            } else if (chars[j] == ' ' && chars[j - 1] != ' ') {
                reverse(chars, i, j - 1);
                i = j;
            } else if (j == n - 1 && chars[i] != ' ') {
                reverse(chars, i, j);
            }
        }
        int l = 0;
        for (int j = 0; j < n; ++j) {
            if (chars[j] != ' ') {
                if (j > 0 && chars[j - 1] == ' ' && l > 0) {
                    chars[l++] = ' ';
                }
                chars[l] = chars[j];
                ++l;
            }
        }
        char[] ans = new char[l];
        for (int i = 0; i < l; ++i) {
            ans[i] = chars[i];
        }
        return new String(ans);
    }

    private void reverse(char[] chars, int i, int j) {
        while (i < j) {
            char c = chars[i];
            chars[i] = chars[j];
            chars[j] = c;
            ++i;
            --j;
        }
    }

    public String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        swap(chars, 0, n - 1);
        int l = 0;
        for (int r = 1; r < n; ++r) {
            if (chars[r] != ' ' && chars[r - 1] == ' ') {
                l = r;
            } else if (chars[r] == ' ' && chars[r - 1] != ' ') {
                swap(chars, l, r);
            }
        }
        if (chars[n - 1] != ' ') {
            swap(chars, l, n - 1);
        }
        l = 0;
        for (int r = 0; r < n; ++r) {
            if (chars[r] != ' ') {
                if (l > 0 && )
            }
        }
    }

    private void swap(char[] chars, int i, int j) {
        while (i < j) {
            char c = chars[i];
            chars[i] = chars[j];
            chars[j] = c;
        }
    }

//    public static void main(String[] args) {
//        Problem0151ReverseWordsInAString a = new Problem0151ReverseWordsInAString();
//        a.reverseWords("  hello world  ");
//    }
}
