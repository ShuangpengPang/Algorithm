package com.shuangpeng.Problem.p1701_1800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1781SumOfBeautyOfAllSubstrings（所有子字符串美丽值之和）
 * @date 2022/12/12 10:28 AM
 */
public class Problem1781SumOfBeautyOfAllSubstrings {

    public int beautySum(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < n; i++) {
            int[] cnt = new int[26];
            for (int j = i; j < n; j++) {
                cnt[s.charAt(j) - 'a']++;
                int min = n, max = 0;
                for (int k = 0; k < 26; k++) {
                    min = Math.min(min, cnt[k] == 0 ? n : cnt[k]);
                    max = Math.max(max, cnt[k]);
                }
                ans += max - min;
            }
        }
        return ans;
    }
}
