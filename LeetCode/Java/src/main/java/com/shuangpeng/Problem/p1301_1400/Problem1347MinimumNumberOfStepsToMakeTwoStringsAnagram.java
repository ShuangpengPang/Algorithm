package com.shuangpeng.Problem.p1301_1400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1347MinimumNumberOfStepsToMakeTwoStringsAnagram（制造字母异位词的最小步骤数）
 * @date 2023/8/2 7:04 PM
 */
public class Problem1347MinimumNumberOfStepsToMakeTwoStringsAnagram {

    public int minSteps(String s, String t) {
        int n = s.length();
        int[] cnt = new int[26];
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
            cnt[t.charAt(i) - 'a']--;
        }
        int ans = 0;
        for (int i = 0; i < 26; i++) {
            ans += Math.max(0, cnt[i]);
        }
        return ans;
    }
}
