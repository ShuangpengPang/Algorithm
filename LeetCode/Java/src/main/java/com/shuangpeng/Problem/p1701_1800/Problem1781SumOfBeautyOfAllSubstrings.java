package com.shuangpeng.Problem.p1701_1800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1781SumOfBeautyOfAllSubstrings（所有子字符串美丽值之和）
 * @date 2022/12/12 10:28 AM
 */
public class Problem1781SumOfBeautyOfAllSubstrings {

    public int beautySum0(String s) {
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

    public int beautySum1(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < n; i++) {
            int[] cnt = new int[26];
            int max = 0;
            for (int j = i; j < n; j++) {
                int c = s.charAt(j) - 'a';
                cnt[c]++;
                max = Math.max(max, cnt[c]);
                int min = n;
                for (int k = 0; k < 26; k++) {
                    min = Math.min(min, cnt[k] == 0 ? n : cnt[k]);
                }
                ans += max - min;
            }
        }
        return ans;
    }

    public int beautySum(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < n; i++) {
            int[] cnt = new int[26], freq = new int[n - i + 1];
            int max = 0, min = n;
            for (int j = i; j < n; j++) {
                int c = s.charAt(j) - 'a';
                cnt[c]++;
                freq[cnt[c]]++;
                max = Math.max(max, cnt[c]);
                min = Math.min(min, cnt[c]);
                if (--freq[cnt[c] - 1] == 0 && min == cnt[c] - 1) {
                    min++;
                }
                ans += max - min;
            }
        }
        return ans;
    }
}
