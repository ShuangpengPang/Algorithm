package com.shuangpeng.Problem;

public class Problem0066PlusOne {

    public int[] plusOne(int[] digits) {
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
}
