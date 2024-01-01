package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2904ShortestAndLexicographicallySmallestBeautifulString（最短且字典序最小的美丽子字符串）
 * @date 2024/1/1 11:35 PM
 */
public class Problem2904ShortestAndLexicographicallySmallestBeautifulString {

    public String shortestBeautifulSubstring(String s, int k) {
        String ans = "";
        int n = s.length(), cnt = 0;
        for (int i = 0, j = 0; j < n; j++) {
            if (s.charAt(j) == '1') {
                if (++cnt == k) {
                    while (s.charAt(i) == '0') {
                        i++;
                    }
                    String str = s.substring(i, j + 1);
                    if (ans.isEmpty() || ans.length() > str.length() || ans.length() == str.length() && ans.compareTo(str) > 0) {
                        ans = str;
                    }
                    i++;
                    cnt--;
                }
            }
        }
        return ans;
    }
}
