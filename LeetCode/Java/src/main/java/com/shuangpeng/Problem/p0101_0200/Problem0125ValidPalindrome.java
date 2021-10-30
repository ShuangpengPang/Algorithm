package com.shuangpeng.Problem.p0101_0200;

public class Problem0125ValidPalindrome {

    public boolean isPalindrome0(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        int diff = 'A' - 'a';
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            char leftChar = s.charAt(left);
            if (!isAlphanumeric(leftChar)) {
                left++;
                continue;
            }
            char rightChar = s.charAt(right);
            if (!isAlphanumeric(rightChar)) {
                right--;
                continue;
            }
            if (isCharacterEqual(leftChar, rightChar)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

    public boolean isAlphanumeric(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9');
    }

    public boolean isCharacterEqual(char ch1, char ch2) {
        if (ch1 >= '0' && ch1 <= '9' && ch2 >= '0' && ch2 <= '9') {
            return ch1 == ch2;
        } else if (ch1 >= 'a' && ch1 <= 'z' && ch2 >= 'a' && ch2 <= 'z') {
            return ch1 == ch2;
        } else if (ch1 >= 'A' && ch1 <= 'Z' && ch2 >= 'A' && ch2 <= 'Z') {
            return ch1 == ch2;
        } else if (ch1 >= 'a' && ch1 <= 'z' && ch2 >= 'A' && ch2 <= 'Z') {
            return ch1 + 'A' - 'a' == ch2;
        } else if (ch1 >= 'A' && ch1 <= 'Z' && ch2 >= 'a' && ch2 <= 'z') {
            return ch1 + 'a' - 'A' == ch2;
        } else {
            return false;
        }
    }

    public boolean isPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }
        int length = s.length();
        int left = 0;
        int right = length - 1;
        while (left < right) {
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
                continue;
            }
            if (Character.toLowerCase(s.charAt(left)) == Character.toLowerCase(s.charAt(right))) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
}
