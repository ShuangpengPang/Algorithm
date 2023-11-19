package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2241DesignAnATMMachine（设计一个ATM机器）
 * @date 2023/11/19 11:38 PM
 */
public class Problem2241DesignAnATMMachine {

    class ATM {

        long[] cnt;
        int[] map = {20, 50, 100, 200, 500};

        public ATM() {
            cnt = new long[5];
        }

        public void deposit(int[] banknotesCount) {
            for (int i = 0; i < 5; i++) {
                cnt[i] += banknotesCount[i];
            }
        }

        public int[] withdraw(int amount) {
            int[] ans = new int[5];
            for (int i = 4; i >= 0 && amount > 0; i--) {
                ans[i] = (int) Math.min(cnt[i], amount / map[i]);
                amount -= ans[i] * map[i];
            }
            if (amount != 0) {
                return new int[]{-1};
            }
            for (int i = 0; i < 5; i++) {
                cnt[i] -= ans[i];
            }
            return ans;
        }
    }
}
