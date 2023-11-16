package com.shuangpeng.Problem.P2201_2300;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2260MinimumConsecutiveCardsToPickUp（必须拿起的最小连续卡牌数）
 * @date 2023/11/16 11:48 PM
 */
public class Problem2260MinimumConsecutiveCardsToPickUp {

    public int minimumCardPickup(int[] cards) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = cards.length, N = Integer.MAX_VALUE >> 1, ans = N;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, i - map.getOrDefault(cards[i], -N) + 1);
            map.put(cards[i], i);
        }
        return ans == N ? -1 : ans;
    }
}
