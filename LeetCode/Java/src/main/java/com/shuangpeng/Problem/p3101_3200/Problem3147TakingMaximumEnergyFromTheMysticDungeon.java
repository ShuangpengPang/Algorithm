package com.shuangpeng.Problem.p3101_3200;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3147TakingMaximumEnergyFromTheMysticDungeon（从魔法师身上吸取的最大能量）
 * @date 2024/5/27 4:10 PM
 */
public class Problem3147TakingMaximumEnergyFromTheMysticDungeon {

    public int maximumEnergy(int[] energy, int k) {
        Deque<Integer> q = new ArrayDeque<>(k + 1);
        int n = energy.length;
        for (int i = 0; i < k; i++) {
            q.offerLast(energy[i]);
        }
        for (int i = k; i < n; i++) {
            q.offerLast(energy[i] + Math.max(0, q.pollFirst()));
        }
        int ans = Integer.MIN_VALUE;
        while (!q.isEmpty()) {
            ans = Math.max(ans, q.pollFirst());
        }
        return ans;
    }
}
