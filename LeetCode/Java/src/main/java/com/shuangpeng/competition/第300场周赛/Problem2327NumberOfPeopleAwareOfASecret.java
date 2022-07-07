package com.shuangpeng.competition.第300场周赛;

/**
 * @Description: Problem2327NumberOfPeopleAwareOfASecret（知道秘密的人数）
 * @Date 2022/7/7 10:44 PM
 * @Version 1.0
 */
public class Problem2327NumberOfPeopleAwareOfASecret {

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        int M = (int) 1e9 + 7;
        int[] first = new int[n + 1];
        first[1] = 1;
        int ans = 1;
        for (int i = 2; i <= n; i++) {
            int j = i - forget;
            if (j >= 1) {
                ans = (ans - first[j] + M) % M;
            }
            long sum = 0;
            for (int k = Math.max(1, j + 1); k <= i - delay; k++) {
                sum += first[k];
            }
            first[i] = (int) (sum % M);
            ans = (ans + first[i]) % M;
        }
        return ans;
    }
}
