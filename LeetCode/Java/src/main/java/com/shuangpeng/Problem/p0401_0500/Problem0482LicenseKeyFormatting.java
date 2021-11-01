package com.shuangpeng.Problem.p0401_0500;

public class Problem0482LicenseKeyFormatting {

    public String licenseKeyFormatting0(String s, int k) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        int i = n - 1;
        while (i >= 0) {
            int j = 0;
            while (j < k && i >= 0) {
                char c = s.charAt(i);
                if (c >= 'a' && c <= 'z') {
                    c = (char) ('A' + c - 'a');
                }
                if (c == '-') {
                    --i;
                    continue;
                }
                sb.append(c);
                --i;
                ++j;
            }
            while (i >= 0 && s.charAt(i) == '-') {
                --i;
            }
            if (i >= 0) {
                sb.append('-');
            }
        }
        return sb.reverse().toString();
    }

    public String licenseKeyFormatting(String s, int k) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = n - 1; i >= 0; --i) {
            char c = s.charAt(i);
            if (c != '-') {
                if (count == k) {
                    sb.append('-');
                    count = 0;
                }
                ++count;
                sb.append(c);
            }
        }
        return sb.reverse().toString().toUpperCase();
    }
}
