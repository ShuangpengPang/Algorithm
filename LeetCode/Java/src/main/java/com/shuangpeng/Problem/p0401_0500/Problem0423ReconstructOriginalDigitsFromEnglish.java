package com.shuangpeng.Problem.p0401_0500;

public class Problem0423ReconstructOriginalDigitsFromEnglish {

    public String originalDigits(String s) {
        String[] strs = {"zero", "two", "six", "seven", "eight", "five", "four", "one", "three", "nine"};
        char[] keys = {'z', 'w', 'x', 's', 'g', 'v', 'f', 'o', 't', 'i'};
        int[] nums = {0, 2, 6, 7, 8, 5, 4, 1, 3, 9};
        int n = s.length();
        int N = 26;
        int[] freq = new int[N];
        for (int i = 0; i < n; ++i) {
            ++freq[s.charAt(i) - 'a'];
        }
        int M = strs.length;
        int[] digits = new int[M];
        for (int i = 0; i < M; ++i) {
            calculate(strs[i], nums[i], keys[i], digits, freq);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; ++i) {
            for (int j = 0; j < digits[i]; ++j) {
                sb.append(i);
            }
        }
        return sb.toString();
    }

    private void calculate(String word, int num, char c, int[] digits, int[] freq) {
        int count = freq[c - 'a'];
        if (count > 0) {
            digits[num] = count;
            minusWord(word, freq, count);
        }
    }

    private void minusWord(String word, int[] freq, int count) {
        int n = word.length();
        for (int i = 0; i < n; ++i) {
            char c = word.charAt(i);
            freq[c - 'a'] -= count;
        }
    }
}
