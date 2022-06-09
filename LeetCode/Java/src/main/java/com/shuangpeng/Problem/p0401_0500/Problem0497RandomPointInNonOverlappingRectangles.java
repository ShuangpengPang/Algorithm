package com.shuangpeng.Problem.p0401_0500;

import java.util.Random;

/**
 * @Description: Problem0497RandomPointInNonOverlappingRectangles（非重叠矩形中的随机点）
 * @Date 2022/6/9 11:15 AM
 * @Version 1.0
 */
public class Problem0497RandomPointInNonOverlappingRectangles {

    class Solution {

        Random random;
        int[][] rects;
        int[] preSum;
        int n;

        public Solution(int[][] rects) {
            this.rects = rects;
            random = new Random();
            n = rects.length;
            preSum = new int[n];
            preSum[0] = getCount(rects[0]);
            for (int i = 1; i < n; ++i) {
                preSum[i] = preSum[i - 1] + getCount(rects[i]);
            }
        }

        public int[] pick() {
            int num = random.nextInt(preSum[n - 1]) + 1;
            int left = 0, right = n - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (preSum[mid] >= num) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            int index = num - (left == 0 ? 0 : preSum[left - 1]) - 1;
            int[] rect = rects[left];
            int col = rect[2] - rect[0] + 1;
            int r = index / col, c = index % col;
            int x = rect[0], y = rect[3];
            return new int[]{x + c, y - r};
        }

        private int getCount(int[] rect) {
            return (rect[3] - rect[1] + 1) * (rect[2] - rect[0] + 1);
        }
    }

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(rects);
 * int[] param_1 = obj.pick();
 */
}
