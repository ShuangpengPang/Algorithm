package com.shuangpeng.Problem.p0901_1000;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0948BagOfTokens（令牌放置）
 * @date 2023/3/15 6:19 PM
 */
public class Problem0948BagOfTokens {

    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int ans = 0, n = tokens.length;
        for (int i = 0, j = 0, k = n, p = power; i < k && j < k && power >= tokens[i]; i++) {
            while (j < k && p >= tokens[j]) {
                p -= tokens[j];
                j++;
            }
            ans = Math.max(ans, j - i);
            int add = tokens[--k] - tokens[i];
            power += add;
            p += tokens[i] + add;
        }
        return ans;
    }
}
