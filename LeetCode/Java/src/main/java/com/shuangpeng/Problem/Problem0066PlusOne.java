package com.shuangpeng.Problem;

public class Problem0066PlusOne {

    public int[] plusOne0(int[] digits) {
        if (digits == null || digits.length == 0) {
            return digits;
        }
        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        int[] answer = new int[digits.length + 1];
        answer[0] = 1;
        return answer;
    }

    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for (int i = n - 1; i >= 0; --i) {
            if (digits[i] < 9) {
                ++digits[i];
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        int[] ans = new int[n + 1];
//        for (int i = n - 1; i >= 0; --i) {
//            ans[i + 1] = digits[i];
//        }
        ans[0] = 1;
        return ans;
    }
}
