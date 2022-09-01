package com.shuangpeng.competition.双周赛.第056场双周赛;

public class Problem1927 {

    public boolean sumGame(String num) {
        int sum1 = 0, sum2 = 0, c1 = 0, c2 = 0;
        int n = num.length();
        int half = (n - 1) >> 1;
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            if (i <= half) {
                if (c == '?') {
                    c1++;
                } else {
                    sum1 += (c - '0');
                }
            } else {
                if (c == '?') {
                    c2++;
                } else {
                    sum2 += (c - '0');
                }
            }
        }
        int diff = sum2 - sum1;
        int c = c1 - c2;
        if (diff < 0) {
            diff = -diff;
            c = -c;
        }
        if (c < 0 || ((c & 1) == 1)) {
            return true;
        }
        return diff != c / 2 * 9;
    }
}
