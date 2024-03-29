package com.shuangpeng.competition.第290到300场周赛.第295场周赛;

/**
 * @Description: Problem2287RearrangeCharactersToMakeTargetString（重排字符形成目标字符串）
 * @Date 2022/6/24 10:13 AM
 * @Version 1.0
 */
public class Problem2287RearrangeCharactersToMakeTargetString {

    // 比赛时写法
    public int rearrangeCharacters0(String s, String target) {
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt1[s.charAt(i) - 'a'];
        }
        for (int i = 0; i < target.length(); ++i){
            ++cnt2[target.charAt(i) - 'a'];
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 26; ++i) {
            if (cnt2[i] > 0) {
                ans = Math.min(ans, cnt1[i] / cnt2[i]);
            }
        }
        return ans;
    }

    public int rearrangeCharacters(String s, String target) {
        int N = 26;
        int[] cnt1 = new int[N], cnt2 = new int[N];
        int n1 = s.length(), n2 = target.length();
        for (int i = 0; i < n1; i++) {
            cnt1[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < n2; i++) {
            cnt2[target.charAt(i) - 'a']++;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (cnt2[i] != 0) {
                ans = Math.min(ans, cnt1[i] / cnt2[i]);
            }
        }
        return ans;
    }
}
