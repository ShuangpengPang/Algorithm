package com.shuangpeng.Problem.p0901_1000;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0950RevealCardsInIncreasingOrder（按递增顺序显示卡牌）
 * @date 2023/3/17 11:05 AM
 */
public class Problem0950RevealCardsInIncreasingOrder {

    public int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);
        int n = deck.length;
        int[] ans = new int[n];
        boolean[] visited = new boolean[n];
        for (int i = 0, j = 0; i < n; i++) {
            ans[j] = deck[i];
            if (i == n - 1) {
                break;
            }
            visited[j] = true;
            while (visited[j]) {
                j = (j + 1) % n;
            }
            j = (j + 1) % n;
            while (visited[j]) {
                j = (j + 1) % n;
            }
        }
        return ans;
    }
}
