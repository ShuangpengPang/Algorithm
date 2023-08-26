package com.shuangpeng.Problem.p0901_1000;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0948BagOfTokens（令牌放置）
 * @date 2023/3/15 6:19 PM
 */
public class Problem0948BagOfTokens {

    public int bagOfTokensScore0(int[] tokens, int power) {
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

    public int bagOfTokensScore1(int[] tokens, int power) {
        Arrays.sort(tokens);
        int lo = 0, hi = tokens.length - 1, score = 0, ans = 0;
        while (lo <= hi && (power >= tokens[lo] || score > 0)) {
            while (lo <= hi && power >= tokens[lo]) {
                power -= tokens[lo++];
                score++;
            }
            ans = Math.max(ans, score);
            if (score > 0 && lo <= hi) {
                power += tokens[hi--];
                score--;
            }
        }
        return ans;
    }

    public int bagOfTokensScore2(int[] tokens, int power) {
        Arrays.sort(tokens);
        int n = tokens.length, ans = 0;
        for (int s = 0, i = 0, j = n - 1; i <= j && (power >= tokens[i] || s < i);) {
            if (power >= tokens[i]) {
                power -= tokens[i];
                ans = Math.max(ans, i - s + 1);
                i++;
            } else {
                power += tokens[j--];
                s++;
            }
        }
        return ans;
    }

    public int bagOfTokensScore3(int[] tokens, int power) {
        Arrays.sort(tokens);
        int left = 0, right = tokens.length - 1, ans = 0;
        while (left <= right) {
            if (power >= tokens[left]) {
                power -= tokens[left++];
                ans++;
            } else if (left < right && ans > 0) {
                power += tokens[right--];
                ans--;
            } else {
                left++;
            }
        }
        return ans;
    }

    public int bagOfTokensScore4(int[] tokens, int power) {
        Arrays.sort(tokens);
        int n = tokens.length, ans = 0;
        for (int p = 0, i = 0, j = n - 1; i <= j && (p < i || power >= tokens[i]); i++) {
            if (power >= tokens[i]) {
                power -= tokens[i];
                ans = Math.max(ans, i - p + 1);
            } else {
                power += tokens[j--] - tokens[i];
                p++;
            }
        }
        return ans;
    }

    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int n = tokens.length, ans = 0;
        for (int i = 0, j = n, r = 0; r < j && i <= r; i++) {
            while (r < j && power >= tokens[r]) {
                power -= tokens[r++];
            }
            ans = Math.max(ans, r - i);
            power += tokens[--j];
        }
        return ans;
    }
}
