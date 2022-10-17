package com.shuangpeng.Problem.p1801_1900;

/**
 * @Description:（使字符串有序的最少操作次数）
 * @Date 2022/10/17 5:06 PM
 **/
public class Problem1830MinimumNumberOfOperationsToMakeStringSorted {

    static int M = (int) 1e9 + 7, N = 3000;
    static int[][] c = new int[N + 1][N + 1];
    static {
        c[0][0] = 1;
        for (int i = 1; i <= N; i++) {
            c[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                c[i][j] = (c[i - 1][j] + c[i - 1][j - 1]) % M;
            }
        }
    }

    public int makeStringSorted(String s) {
        int[] cnt = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            int total = n - i - 1;
            for (int j = 0; j < ch; j++) {
                if (cnt[j] > 0) {
                    cnt[j]--;
                    int t = total;
                    long res = 1;
                    for (int k = 0; k < 26; k++) {
                        if (cnt[k] > 0) {
                            res = (res * c[t][cnt[k]]) % M;
                            t -= cnt[k];
                        }
                    }
                    ans = (ans + res) % M;
                    cnt[j]++;
                }
            }
            cnt[ch]--;
        }
        return (int) ans;
    }
}
