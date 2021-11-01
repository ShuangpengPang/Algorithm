package com.shuangpeng.Problem.p0401_0500;

public class Problem0443StringCompression {

    public int compress0(char[] chars) {
        int n = chars.length;
        int i = 0;
        while (i < n) {
            int j = i;
            char c = chars[j];
            i++;
            while (i < n && chars[i] == c) {
                chars[i] = ' ';
                i++;
            }
            int count = i - j;
            if (count > 1) {
                int k = 1;
                while (count >= k) {
                    k *= 10;
                }
                k /= 10;
                while (k > 0) {
                    chars[++j] = (char) ('0' + (count / k));
                    count %= k;
                    k /= 10;
                }
            }
        }
        int l = 0;
        for (int r = 0; r < n; ++r) {
            if (chars[r] != ' ') {
                swap(chars, l, r);
                l++;
            }
        }
        return l;
    }

    private void swap(char[] chars, int i, int j) {
        if (i != j) {
            char t = chars[i];
            chars[i] = chars[j];
            chars[j] = t;
        }
    }

    public int compress1(char[] chars) {
        int n = chars.length;
        int left = 0, write = 0;
        for (int read = 0; read < n; ++read) {
            if (read == n - 1 || chars[read] != chars[read + 1]) {
                chars[write++] = chars[left];
                int count = read - left + 1;
                if (count > 1) {
                    int start = write;
                    while (count > 0) {
                        chars[write++] = (char) ('0' + count % 10);
                        count /= 10;
                    }
                    reverse(chars, start, write - 1);
                }
                left = read + 1;
            }
        }
        return write;
    }

    private void reverse(char[] chars, int i, int j) {
        while (i < j) {
            char t = chars[i];
            chars[i] = chars[j];
            chars[j] = t;
            i++;
            j--;
        }
    }

    public int compress(char[] chars) {
        int n = chars.length;
        int i = 0, j = 0;
        while (i < n) {
            int idx = i + 1;
            while (idx < n && chars[idx] == chars[i]) {
                idx++;
            }
            chars[j++] = chars[i];
            int count = idx - i;
            if (count > 1) {
                int s = j;
                while (count > 0) {
                    chars[j++] = (char) ('0' + count % 10);
                    count /= 10;
                    reverse(chars, s, j - 1);
                }
            }
            i = idx;
        }
        return j;
    }
}
