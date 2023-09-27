package com.shuangpeng.Problem.p1701_1800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1737ChangeMinimumCharactersToSatisfyOneOfThreeConditions（满足三条件之一需改变的最少字符数）
 * @date 2023/9/26 7:59 PM
 */
public class Problem1737ChangeMinimumCharactersToSatisfyOneOfThreeConditions {

    public int minCharacters(String a, String b) {
        int[] cnt1 = new int[26], cnt2 = new int[26];
        int m1 = Integer.MAX_VALUE, m2 = Integer.MAX_VALUE;
        int n1 = a.length(), n2 = b.length();
        for (int i = 0; i < n1; i++) {
            m1 = Math.min(m1, n1 - ++cnt1[a.charAt(i) - 'a']);
        }
        for (int i = 0; i < n2; i++) {
            m2 = Math.min(m2, n2 - ++cnt2[b.charAt(i) - 'a']);
        }
        return Math.min(m1 + m2, Math.min(lessThanCount(cnt1, cnt2, n1), lessThanCount(cnt2, cnt1, n2)));
    }

    private int lessThanCount(int[] cnt1, int[] cnt2, int n) {
        int ans = Integer.MAX_VALUE;
        for (int i = 0, c = 0; i < 25; i++) {
            c += cnt2[i];
            n -= cnt1[i];
            ans = Math.min(ans, c + n);
        }
        return ans;
    }
}
