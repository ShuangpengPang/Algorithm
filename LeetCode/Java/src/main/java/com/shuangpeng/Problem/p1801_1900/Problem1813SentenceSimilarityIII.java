package com.shuangpeng.Problem.p1801_1900;

/**
 * @author shuangpeng
 * @description: 句子相似性III
 * @date 2023/1/16 5:28 PM
 **/
public class Problem1813SentenceSimilarityIII {

    public boolean areSentencesSimilar0(String sentence1, String sentence2) {
        String[] strings1 = sentence1.split(" ");
        String[] strings2 = sentence2.split(" ");
        int n1 = strings1.length;
        int n2 = strings2.length;
        int i1 = 0, i2 = n1 - 1, j1 = 0, j2 = n2 - 1;
        while (i1 <= i2 && j1 <= j2) {
            boolean equal1 = strings1[i1].equals(strings2[j1]);
            boolean equal2 = strings1[i2].equals(strings2[j2]);
            if (!equal1 && !equal2) {
                return false;
            }
            if (equal1) {
                i1++;
                j1++;
            }
            if (equal2) {
                i2--;
                j2--;
            }
        }
        return true;
    }

    public boolean areSentencesSimilar1(String sentence1, String sentence2) {
        if (sentence1.equals(sentence2)) {
            return true;
        }
        if (sentence1.length() == sentence2.length()) {
            return false;
        }
        if (sentence1.length() > sentence2.length()) {
            String temp = sentence1;
            sentence1 = sentence2;
            sentence2 = temp;
        }
        int n1 = sentence1.length(), n2 = sentence2.length();
        int i = 0;
        while (i < n1) {
            if (sentence1.charAt(i) != sentence2.charAt(i)) {
                break;
            }
            i++;
        }
        if (i == n1 && sentence2.charAt(i) == ' ') {
            return true;
        }
        int j = sentence1.length() - 1;
        while (j >= 0) {
            if (sentence1.charAt(j) != sentence2.charAt(j + n2 - n1)) {
                break;
            }
            j--;
        }
        if (j == -1 && sentence2.charAt(n2 - n1 - 1) == ' ') {
            return true;
        }
        return i > j && sentence1.charAt(i - 1) == ' ' && sentence1.charAt(j + 1) == ' ';
    }

    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        String[] s1 = sentence1.split(" "), s2 = sentence2.split(" ");
        int i1 = 0, j1 = s1.length - 1, i2 = 0, j2 = s2.length - 1;
        while (i1 <= j1 && i2 <= j2) {
            if (s1[i1].equals(s2[i2])) {
                i1++;
                i2++;
            } else if (s1[j1].equals(s2[j2])) {
                j1--;
                j2--;
            } else {
                break;
            }
        }
        return i1 > j1 || i2 > j2;
    }
}
