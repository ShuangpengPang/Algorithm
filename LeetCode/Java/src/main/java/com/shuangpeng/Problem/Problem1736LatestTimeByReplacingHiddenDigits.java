package com.shuangpeng.Problem;

public class Problem1736LatestTimeByReplacingHiddenDigits {

    public String maximumTime0(String time) {
        int n = time.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = time.charAt(i);
            if (c == '?') {
                if (i == 0) {
                    if (time.charAt(i + 1) == '?' || time.charAt(i + 1) <= '3') {
                        c = '2';
                    } else {
                        c = '1';
                    }
                } else if (i == 1) {
                    if (time.charAt(i - 1) == '?' || time.charAt(i - 1) == '2') {
                        c = '3';
                    } else {
                        c = '9';
                    }
                } else if (i == 3) {
                    c = '5';
                } else {
                    c = '9';
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    public String maximumTime1(String time) {
        char[] arr = time.toCharArray();
        arr[0] = arr[0] == '?' ? (arr[1] == '?' || arr[1] <= '3' ? '2' : '1') : arr[0];
        arr[1] = arr[1] == '?' ? (arr[0] == '?' || arr[0] == '2' ? '3' : '9') : arr[1];
        arr[3] = arr[3] == '?' ? '5' : arr[3];
        arr[4] = arr[4] == '?' ? '9' : arr[4];
        return new String(arr);
    }

    public String maximumTime(String time) {
        char[] arr = time.toCharArray();
        arr[0] = arr[0] == '?' ? (arr[1] >= '4' && arr[1] <= '9' ? '1' : '2') : arr[0];
        arr[1] = arr[1] == '?' ? (arr[0] == '0' || arr[0] == '1' ? '9' : '3') : arr[1];
        arr[3] = arr[3] == '?' ? '5' : arr[3];
        arr[4] = arr[4] == '?' ? '9' : arr[4];
        return new String(arr);
    }
}
