package com.shuangpeng.competition.第290到300场周赛.第298场周赛;

/**
 * @Description: Problem2309GreatestEnglishLetterInUpperAndLowerCase（兼具大小写的最好英文字母）
 * @Date 2022/6/30 5:37 PM
 * @Version 1.0
 */
public class Problem2309GreatestEnglishLetterInUpperAndLowerCase {

    // 比赛时写法
    public String greatestLetter0(String s) {
        int N = 26;
        int[] array = new int[N];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                int j = c - 'A';
                array[j] |= 1;
            } else {
                int j = c - 'a';
                array[j] |= 2;
            }
        }
        for (int i = 25; i >= 0; --i) {
            if (array[i] == 3) {
                return "" + (char) (i + 'A');
            }
        }
        return "";
    }

    public String greatestLetter(String s) {
        int N = 26;
        int[] arr = new int[N];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int j = s.charAt(i);
            if (j >= 'a' && j <= 'z') {
                arr[j - 'a'] |= 1;
            } else {
                arr[j - 'A'] |= 2;
            }
        }
        for (int i = N - 1; i >= 0; i--) {
            if (arr[i] == 3) {
                return "" + (char) (i + 'A');
            }
        }
        return "";
    }
}
