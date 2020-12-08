package com.shuangpeng.Problem;

public class Problem0557ReverseWordsInAStringIII {

//    Input: "Let's take LeetCode contest"
//    Output: "s'teL ekat edoCteeL tsetnoc"

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int length = s.length();
        char[] chars = new char[length];
        int left = -1;
        for (int right = 0; right <= length; right++) {
            if (right == length || s.charAt(right) == ' ') {
                for (int i = right - 1; i >= left + 1; i--) {
                    chars[left + right - i] = s.charAt(i);
                }
                if (right < length) {
                    chars[right] = ' ';
                }
                left = right;
            }
        }
        return String.valueOf(chars);
    }
}
