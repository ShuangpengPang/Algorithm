package com.shuangpeng.Problem.p3001_3100;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3016MinimumNumberOfPushesToTypeWordII（输入单词需要的最少按键次数II）
 * @date 2024/2/5 9:50 AM
 */
public class Problem3016MinimumNumberOfPushesToTypeWordII {

    public int minimumPushes(String word) {
        int[] cnt = new int[26];
        for (int i = 0; i < word.length(); i++) {
            cnt[word.charAt(i) - 'a']++;
        }
        Arrays.sort(cnt);
        int ans = 0;
        for (int i = 25, c = 1; i >= 0; i -= 8, c++) {
            for (int j = Math.max(0, i - 7); j <= i; j++) {
                ans += c * cnt[j];
            }
        }
        return ans;
    }
}
