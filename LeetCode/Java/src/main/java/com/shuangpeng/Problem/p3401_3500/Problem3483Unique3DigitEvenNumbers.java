package com.shuangpeng.Problem.p3401_3500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3483Unique3DigitEvenNumbers（不同三位偶数的数目）
 * @date 2025/4/1 10:34
 */
public class Problem3483Unique3DigitEvenNumbers {

    public int totalNumbers(int[] digits) {
        int[] cnt = new int[10];
        for (int d : digits) {
            cnt[d]++;
        }
        int ans = 0;
        for (int i = 0; i < 10; i += 2) {
            if (cnt[i] > 0) {
                cnt[i]--;
                for (int j = 0; j < 10; j++) {
                    if (cnt[j] > 0) {
                        cnt[j]--;
                        for (int k = 1; k < 10; k++) {
                            if (cnt[k] > 0) {
                                ans++;
                            }
                        }
                        cnt[j]++;
                    }
                }
                cnt[i]++;
            }
        }
        return ans;
    }
}
