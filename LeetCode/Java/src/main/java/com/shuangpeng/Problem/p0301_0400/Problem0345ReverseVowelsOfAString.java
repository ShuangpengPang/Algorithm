package com.shuangpeng.Problem.p0301_0400;

public class Problem0345ReverseVowelsOfAString {

    public String reverseVowels0(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        int left = 0, right = n - 1;
        char[] vowels = {'a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U'};
        while (left < right) {
            while (left < right) {
                boolean flag = true;
                char c = chars[left];
                for (int i = 0; i < vowels.length; ++i) {
                    if (c == vowels[i]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    left++;
                } else {
                    break;
                }
            }
            while (left < right) {
                boolean flag = true;
                char c = chars[right];
                for (int i = 0; i < vowels.length; ++i) {
                    if (c == vowels[i]) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    right--;
                } else {
                    break;
                }
            }
            if (left < right) {
                char temp = chars[left];
                chars[left] = chars[right];
                chars[right] = temp;
                left++;
                right--;
            }
        }
        return new String(chars);
    }

    public String reverseVowels1(String s) {
        char[] vowels = {'a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U'};
        char[] chars = s.toCharArray();
        int i = 0, j = chars.length - 1;
        while (i < j) {
            while (i < j && !isVowel(chars[i], vowels)) {
                i++;
            }
            while (i < j && !isVowel(chars[j], vowels)) {
                j--;
            }
            if (i < j) {
                swap(chars, i, j);
                i++;
                j--;
            }
        }
        return new String(chars);
    }

    public String reverseVowels(String s) {
        char[] vowels = {'a', 'A', 'e', 'E', 'i', 'I', 'o', 'O', 'u', 'U'};
        char[] chars = s.toCharArray();
        int i = 0, j = chars.length - 1;
        while (i < j) {
            boolean isVowels1 = isVowel(chars[i], vowels);
            boolean isVowels2 = isVowel(chars[j], vowels);
            if (isVowels1 && isVowels2) {
                swap(chars, i, j);
                i++;
                j--;
            } else {
                if (!isVowels1) {
                    i++;
                }
                if (!isVowels2) {
                    j--;
                }
            }
        }
        return new String(chars);
    }

    private void swap(char[] chars, int i, int j) {
        char t = chars[i];
        chars[i] = chars[j];
        chars[j] = t;
    }

    private boolean isVowel(char c, char[] chars) {
        for (int i = 0; i < chars.length; ++i) {
            if (c == chars[i]) {
                return true;
            }
        }
        return false;
    }
}
