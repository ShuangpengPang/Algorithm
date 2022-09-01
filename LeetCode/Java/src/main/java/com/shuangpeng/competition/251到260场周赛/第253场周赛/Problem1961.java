package com.shuangpeng.competition.第253场周赛;

public class Problem1961 {

    public boolean isPrefixString0(String s, String[] words) {
        int n = words.length;
        int j = 0, k = 0;
        for (int i = 0; i < s.length(); ++i) {
            String word = words[j];
            if (k >= word.length()) {
                j++;
                k = 0;
            }
            if (j >= n) {
                return false;
            }
            if (s.charAt(i) == words[j].charAt(k)) {
                k++;
            } else {
                return false;
            }
        }
        return words[j].length() == k;
    }

    public boolean isPrefixString1(String s, String[] words) {
        int sn = s.length(), wn = words.length;
        int i = 0, j = 0, k = 0;
        while (i < sn && j < wn) {
            if (s.charAt(i) != words[j].charAt(k)) {
                break;
            }
            k++;
            if (words[j].length() == k) {
                j++;
                k = 0;
            }
            i++;
        }
        return i == sn && k == 0;
    }

    public boolean isPrefixString(String s, String[] words) {
        int sn = s.length();
        int i = 0;
        for (String word : words) {
            int wn = word.length();
            int j = 0;
            while (i < sn && j < wn && s.charAt(i) == word.charAt(j)) {
                i++;
                j++;
            }
            if (j < wn) {
                return false;
            }
            if (i == sn) {
                return true;
            }
        }
        return false;
    }
}
