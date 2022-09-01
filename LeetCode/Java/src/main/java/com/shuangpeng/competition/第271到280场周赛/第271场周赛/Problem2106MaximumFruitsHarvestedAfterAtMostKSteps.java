package com.shuangpeng.competition.第271到280场周赛.第271场周赛;

public class Problem2106MaximumFruitsHarvestedAfterAtMostKSteps {

    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            preSum[i + 1] = preSum[i] + fruits[i][1];
        }
        int index = binarySearch(fruits, startPos);
        int leftIndex = binarySearch(fruits, startPos - k - 1);
        int rightIndex = binarySearch(fruits, startPos + k) - 1;
        int ans = 0;
        int right = rightIndex;
        for (int left = index - 1; left >= leftIndex; --left) {
            int length = k - ((startPos - fruits[left][0]) << 1);
            while (right >= index && fruits[right][0] - startPos > length) {
                --right;
            }
            ans = Math.max(ans, preSum[right + 1] - preSum[left]);
        }
        int left = leftIndex;
        for (right = index; right <= rightIndex; ++right) {
            int length = k - ((fruits[right][0] - startPos) << 1);
            while (left < index && startPos - fruits[left][0] > length) {
                ++left;
            }
            ans = Math.max(ans, preSum[right + 1] - preSum[left]);
        }
        return ans;
    }

    private int binarySearch(int[][] fruits, int position) {
        int left = 0, right = fruits.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int p = fruits[mid][0];
            if (p > position) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
