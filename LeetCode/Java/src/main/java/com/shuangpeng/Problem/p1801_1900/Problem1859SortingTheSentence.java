package com.shuangpeng.Problem.p1801_1900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1859SortingTheSentence（将句子排序）
 * @date 2024/3/19 7:24 PM
 */
public class Problem1859SortingTheSentence {

    public String sortSentence(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length, i = 0;
        String[] arr = new String[9];
        while (i < n) {
            int j = i;
            while (i < n && cs[i] != ' ') {
                i++;
            }
            arr[cs[i - 1] - '1'] = s.substring(j, i - 1);
            i++;
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 9 && arr[j] != null; j++) {
            if (j > 0) {
                sb.append(' ');
            }
            sb.append(arr[j]);
        }
        return sb.toString();
    }
}
