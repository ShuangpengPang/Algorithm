package com.shuangpeng.Problem.p3401_3500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3403FindTheLexicographicallyLargestStringFromTheBoxI（从盒子中找出字典序最大的字符串I）
 * @date 2025/4/9 11:34
 */
public class Problem3403FindTheLexicographicallyLargestStringFromTheBoxI {

    public String answerString(String word, int numFriends) {
        if (numFriends == 1) {
            return word;
        }
        String ans = "";
        int n = word.length();
        for (int i = 0; i < n; i++) {
            String s = word.substring(i, Math.min(n - numFriends + i + 1, n));
            if (s.compareTo(ans) > 0) {
                ans = s;
            }
        }
        return ans;
    }
}
