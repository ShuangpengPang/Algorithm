package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2531MakeNumberOfDistinctCharactersEqual（使字符串总不同字符的数目相等）
 * @date 2023/11/26 4:36 PM
 */
public class Problem2531MakeNumberOfDistinctCharactersEqual {

    public boolean isItPossible(String word1, String word2) {
        int n1 = word1.length(), n2 = word2.length(), c1 = 0, c2 = 0;
        int[] cnt1 = new int[26], cnt2 = new int[26];
        for (int i = 0; i < n1; i++) {
            if (cnt1[word1.charAt(i) - 'a']++ == 0) {
                c1++;
            }
        }
        for (int i = 0; i < n2; i++) {
            if (cnt2[word2.charAt(i) - 'a']++ == 0) {
                c2++;
            }
        }
        for (int i = 0; i < 26; i++) {
            if (cnt1[i] > 0) {
                int t = c1 - (cnt1[i] > 1 ? 0 : 1);
                cnt1[i]--;
                for (int j = 0; j < 26; j++) {
                    int t1 = t;
                    if (cnt2[j] > 0) {
                        int t2 = c2 - (cnt2[j] > 1 ? 0 : 1);
                        cnt2[j]--;
                        if (cnt1[j] == 0) {
                            t1++;
                        }
                        if (cnt2[i] == 0) {
                            t2++;
                        }
                        cnt2[j]++;
                        if (t1 == t2) {
                            return true;
                        }
                    }
                }
                cnt1[i]++;
            }
        }
        return false;
    }
}
