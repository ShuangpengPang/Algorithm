package com.shuangpeng.Problem;

public class Problem0678ValidParenthesisString {

    public boolean checkValidString0(String s) {
        int n = s.length();
        int count = 0, sCount = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
            } else {
                sCount++;
            }
            if (count + sCount < 0) {
                return false;
            }
        }
        if (count > sCount) {
            return false;
        }
        count = 0;
        sCount = 0;
        for (int i = n - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == ')') {
                count++;
            } else if (c == '(') {
                count--;
            } else {
                sCount++;
            }
            if (count + sCount < 0) {
                return false;
            }
        }
        return count <= sCount;
    }

    public boolean checkValidString(String s) {
        int n = s.length();
        int minLeft = 0, maxLeft = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                minLeft++;
                maxLeft++;
            } else if (c == ')') {
                minLeft = Math.max(0, minLeft - 1);
                maxLeft--;
            } else {
                minLeft = Math.max(0, minLeft - 1);
                maxLeft++;
            }
            if (maxLeft < 0) {
                return false;
            }
        }
        return minLeft == 0;
    }
}
