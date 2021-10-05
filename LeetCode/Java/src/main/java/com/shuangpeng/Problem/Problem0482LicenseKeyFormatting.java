package com.shuangpeng.Problem;

public class Problem0482LicenseKeyFormatting {

    public String licenseKeyFormatting(String s, int k) {
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
}
