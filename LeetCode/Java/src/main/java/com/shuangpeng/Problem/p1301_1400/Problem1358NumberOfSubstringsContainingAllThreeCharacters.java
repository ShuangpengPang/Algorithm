package com.shuangpeng.Problem.p1301_1400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1358NumberOfSubstringsContainingAllThreeCharacters（包含所有三种字符的子字符串数目）
 * @date 2023/8/8 10:57 AM
 */
public class Problem1358NumberOfSubstringsContainingAllThreeCharacters {

    public int numberOfSubstrings(String s) {
        int n = s.length(), cnt = 0, ans = 0;
        int[] count = new int[3];
        for (int i = 0, j = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (count[c]++ == 0) {
                cnt++;
            }
            while (cnt == 3) {
                if (--count[s.charAt(j++) - 'a'] == 0) {
                    cnt--;
                }
            }
            ans += j;
        }
        return ans;
    }
}
