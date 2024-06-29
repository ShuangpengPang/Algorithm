package com.shuangpeng.lcr.p001_100;

import java.util.Random;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR071PickIndex（按权重随机选择）
 * @date 2024/6/29 5:12 PM
 */
public class LCR071PickIndex {

    class Solution {

        private int[] preSum;
        private Random random;
        private int sum;

        public Solution(int[] w) {
            random = new Random();
            int n = w.length;
            preSum = new int[n];
            preSum[0] = w[0];
            for (int i = 1; i < n; i++) {
                preSum[i] = preSum[i - 1] + w[i];
            }
            sum = preSum[n - 1];
        }

        public int pickIndex() {
            int t = random.nextInt(sum) + 1;
            int left = 0, right = preSum.length - 1;
            while (left <= right) {
                int mid = left + (right - left >> 1);
                if (preSum[mid] < t) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            return left;
        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(w);
 * int param_1 = obj.pickIndex();
 */
}
