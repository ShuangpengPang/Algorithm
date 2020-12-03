package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.List;

public class Problem0647PalindromicSubstrings {

//    public static void main(String[] args) {
//        Problem0647PalindromicSubstrings a = new Problem0647PalindromicSubstrings();
//        a.countSubstrings("aaa");
//    }

    public int countSubstrings0(String s) {
        int answer = 0;
        int length = s.length();
        List<Integer> list = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            int size = list.size();
            for (int j = 0; j < size; j++) {
                int start = list.get(j) - 1;
                if (start >= 0 && s.charAt(start) == ch) {
                    list.set(j, start);
                } else {
                    list.set(j, -1);
                }
            }
            if (i > 0 && s.charAt(i - 1) == ch) {
                list.add(i - 1);
            }
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) == -1) {
                    list.remove(j);
                    j = -1;
                }
            }
            list.add(i);
            answer += list.size();
        }
        return answer;
    }

    public int countSubstrings1(String s) {
        int answer = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            answer += countPalindromic(s, i, i);
            answer += countPalindromic(s, i, i + 1);
        }
        return answer;
    }

    public int countPalindromic(String s, int i, int j) {
        int count = 0;
        int length = s.length();
        while (i >= 0 && j < length && s.charAt(i) == s.charAt(j)) {
            count++;
            i--;
            j++;
        }
        return count;
    }

    public int countSubstrings2(String s) {
        int answer = 0;
        int length = s.length();
        for (int i = 0; i < 2 * length - 1; i++) {
            int j = i / 2;
            int k = j + (i % 2);
            while (j >= 0 && k < length && s.charAt(j) == s.charAt(k)) {
                answer++;
                j--;
                k++;
            }
        }
        return answer;
    }

    public int countSubstrings3(String s) {
        int n = s.length();
        char[] chars = new char[2 * n + 1];
        int length = chars.length;
        for (int i = 0; i < length; i++) {
            if ((i & 1) == 0) {
                chars[i] = '#';
            } else {
                chars[i] = s.charAt(i / 2);
            }
        }
        int[] radius = new int[length];
        int center = 0;
        for (int i = 1; i < length; i++) {
            int j = 2 * center - i;
            if (i < center + radius[center] && j - radius[j] > center - radius[center]) {
                radius[i] = radius[j];
            } else if (i < center + radius[center] && j - radius[j] < center - radius[center]) {
                radius[i] = center + radius[center] - i;
            } else {
                int r = 0;
                while (i - r >= 0 && i + r < length && chars[i - r] == chars[i + r]) {
                    r++;
                }
                radius[i] = r - 1;
                center = i;
            }
        }
        int answer = 0;
        for (int i = 0; i < length; i++) {
            answer += (radius[i] + 1) >> 1;
        }
        return answer;
    }

    public int countSubstrings(String s) {
        if (s == null) {
            return 0;
        }
        int length = s.length() * 2 + 3;
        char[] chars = new char[length];
        chars[0] = '!';
        chars[length - 1] = '$';
        for (int i = 1; i < length - 1; i++) {
            if (((i - 1) & 1) == 0) {
                chars[i] = '#';
            } else {
                chars[i] = s.charAt((i - 1) / 2);
            }
        }
        int[] radius = new int[length];
        int answer = 0;
        radius[0] = 1;
        int center = 0;
        for (int i = 1; i < length - 1; i++) {
            if (i < center + radius[center] - 1) {
                radius[i] = Math.min(radius[2 * center - i], center + radius[center] - i);
            }
            radius[i] = radius[i] == 0 ? 1 : radius[i];
            while (chars[i - radius[i]] == chars[i + radius[i]]) {
                radius[i]++;
            }
            if (i + radius[i] > center + radius[center]) {
                center = i;
            }
            answer += (radius[i] >> 1);
        }
        return answer;
    }
}
