package com.shuangpeng.Problem.p0901_1000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0923ThreeSumWithMultiplicity（三数之和的多种可能）
 * @date 2022/11/25 11:43 PM
 */
public class Problem0923ThreeSumWithMultiplicity {

    public int threeSumMulti(int[] arr, int target) {
        int n = arr.length, N = 100;
        int[] cnt = new int[N + 1];
        long ans = 0L;
        for (int num : arr) {
            cnt[num]++;
        }
        for (int i = 0; i <= N && i <= target / 3; i++) {
            if (cnt[i] == 0) {
                continue;
            }
            for (int j = i; j <= N; j++) {
                int k = target - i - j;
                if (k < j) {
                    break;
                }
                if (cnt[j] == 0 || (i == j && cnt[i] == 1) || k > N || cnt[k] == 0) {
                    continue;
                }
                if (i < j && j < k) {
                    ans += (long) cnt[i] * cnt[j] * cnt[k];
                } else if (i == j && j == k) {
                    if (cnt[i] >= 3) {
                        ans += (long) cnt[i] * (cnt[i] - 1) * (cnt[i] - 2) / 6;
                    }
                } else if (i == j) {
                    if (cnt[i] >= 2) {
                        ans += (long) cnt[i] * (cnt[i] - 1) * cnt[k] / 2;
                    }
                } else {
                    if (cnt[j] >= 2) {
                        ans += (long) cnt[i] * cnt[j] * (cnt[j] - 1) / 2;
                    }
                }
            }
        }
        return (int) (ans % (long) (1e9 + 7));
    }
}
