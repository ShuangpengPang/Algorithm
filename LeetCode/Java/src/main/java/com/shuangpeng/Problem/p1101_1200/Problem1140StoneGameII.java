package com.shuangpeng.Problem.p1101_1200;

import java.util.HashMap;
import java.util.Map;

public class Problem1140StoneGameII {

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            preSum[i] = preSum[i - 1] + piles[i - 1];
        }
        return getMaxStone(preSum, 1, 1, new HashMap<>());
    }

    private int getMaxStone(int[] preSum, int i, int m, Map<Integer, Integer> memo) {
        int n = preSum.length;
        if ((m << 1) >= n - i) {
            return preSum[n - 1] - preSum[i - 1];
        }
        int key = i * 101 + m;
        int backup = memo.getOrDefault(key, -1);
        if (backup != -1) {
            return backup;
        }
        int maxValue = 0;
        int sum = preSum[n - 1] - preSum[i - 1];
        for (int j = 1; j <= (m << 1); ++j) {
            maxValue = Math.max(maxValue, sum - getMaxStone(preSum, i + j, Math.max(j, m), memo));
        }
        memo.put(key, maxValue);
        return maxValue;
    }
}
