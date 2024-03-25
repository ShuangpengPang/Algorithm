package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3084CountSubstringsStartingAndEndingWithGivenCharacter（统计以给定字符开头和结尾的子字符串总数）
 * @date 2024/3/25 11:57 PM
 */
public class Problem3084CountSubstringsStartingAndEndingWithGivenCharacter {

    public long countSubstrings0(String s, char c) {
        long cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                cnt++;
            }
        }
        s.chars();
        return cnt * (cnt + 1) >> 1;
    }

    public long countSubstrings(String s, char c) {
        long ans = 0, cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c) {
                ans += ++cnt;
            }
        }
        return ans;
    }
}
