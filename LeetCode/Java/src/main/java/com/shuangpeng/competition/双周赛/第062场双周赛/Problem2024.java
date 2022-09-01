package com.shuangpeng.competition.双周赛.第062场双周赛;

public class Problem2024 {

    public int maxConsecutiveAnswers0(String answerKey, int k) {
        return Math.max(maxCount(answerKey, k, 'T'), maxCount(answerKey, k, 'F'));
    }

    private int maxCount(String answerKey, int k, char c) {
        int n = answerKey.length();
        int count = 0;
        int ans = 0;
        for (int i = 0, j = 0; j < n; ++j) {
            if (answerKey.charAt(j) != c) {
                ++count;
            }
            while (count > k) {
                if (answerKey.charAt(i) != c) {
                    --count;
                }
                ++i;
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }

    public int maxConsecutiveAnswers(String answerKey, int k) {
        int n = answerKey.length();
        int numT = 0, numF = 0;
        int ans = 0;
        for (int i = 0, j = 0; j < n; ++j) {
            if (answerKey.charAt(j) == 'T') {
                ++numT;
            } else {
                ++numF;
            }
            while (numT > k && numF > k) {
                if (answerKey.charAt(i) == 'T') {
                    --numT;
                } else {
                    --numF;
                }
                ++i;
            }
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }
}
