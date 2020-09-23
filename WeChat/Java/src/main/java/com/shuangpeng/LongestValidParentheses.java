package com.shuangpeng;

public class LongestValidParentheses {

    // "(()(((()"

    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int start = 0;
        int end = chars.length - 1;
        int left = start;
        int leftCount = 0;
        int rightCount = 0;
        int right = end;
        int max = 0;
        while (left <= right) {
            char leftChar = chars[left];
            char rightChar = chars[right];
            if (leftChar == '(' && rightChar == ')') {
                left++;
                right--;
                leftCount++;
                rightCount++;
            } else if (leftCount == ')') {
                if (leftCount == 0) {
                    max = Math.max(max, left - start);
                    left++;
                    start = left;

                    right = end;
                    rightCount = 0;
                } else {
                    left++;
                    leftCount--;
                }
            } else {
                if (rightCount == 0) {
                    max = Math.max(max, end - right);
                    right--;
                    end = right;

                    left = start;
                    leftCount = 0;
                } else {
                    right--;
                    rightCount--;
                }
            }
        }
        if (leftCount == rightCount && left != right) {
            max = Math.max(max, end - start + 1);
        }
        return max;
    }

    public int longestValidParentheses0(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] chars = s.toCharArray();
        int left = 0;
        int max = 0;
        int previousLength = 0;
        int[] stack = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c == '(') {
                stack[left++] = i;
            } else if (left > 0) {
                left--;
                int leftIndex = stack[left];
                if (left > 0) {
                    leftIndex = stack[left - 1];
                } else {
                    leftIndex = leftIndex - previousLength - 1;
                    previousLength = i - leftIndex;
                }
                max = Math.max(max, i - leftIndex);
            } else {
                previousLength = 0;
            }
        }
        return max;
    }
}
