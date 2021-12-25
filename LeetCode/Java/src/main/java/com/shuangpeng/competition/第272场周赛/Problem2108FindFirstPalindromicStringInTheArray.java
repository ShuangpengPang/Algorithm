package com.shuangpeng.competition.第272场周赛;

public class Problem2108FindFirstPalindromicStringInTheArray {

    // 比赛时写法
    public String firstPalindrome0(String[] words) {
        for (String w : words) {
            int n = w.length();
            boolean valid = true;
            for (int i = 0, j = n - 1; i < j; ++i, --j) {
                if (w.charAt(i) != w.charAt(j)) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                return w;
            }
        }
        return "";
    }
}
