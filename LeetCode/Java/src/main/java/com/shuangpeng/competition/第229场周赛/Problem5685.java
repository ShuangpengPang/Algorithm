package com.shuangpeng.competition.第229场周赛;

public class Problem5685 {

    public String mergeAlternately0(String word1, String word2) {
        int i = 0;
        int j = 0;
        int length1 = word1.length();
        int length2 = word2.length();
        StringBuilder builder = new StringBuilder();
        boolean flag = true;
        while (i < length1 || j < length2) {
            if (i >= length1) {
                builder.append(word2.charAt(j));
                j++;
            } else if (j >= length2) {
                builder.append(word1.charAt(i));
                i++;
            } else if (flag) {
                builder.append(word1.charAt(i));
                i++;
                flag = false;
            } else {
                builder.append(word2.charAt(j));
                j++;
                flag = true;
            }
        }
        return builder.toString();
    }

    public String mergeAlternately(String word1, String word2) {
        int n1 = word1.length();
        int n2 = word2.length();
        int p1 = 0, p2 = 0;
        StringBuilder builder = new StringBuilder();
        while (p1 < n1 || p2 < n2) {
            if (p1 < n1) {
                builder.append(word1.charAt(p1++));
            }
            if (p2 < n2) {
                builder.append(word2.charAt(p2++));
            }
        }
        return builder.toString();
    }
}
