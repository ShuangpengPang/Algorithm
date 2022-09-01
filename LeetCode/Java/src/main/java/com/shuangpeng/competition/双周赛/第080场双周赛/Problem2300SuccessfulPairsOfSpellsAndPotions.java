package com.shuangpeng.competition.双周赛.第080场双周赛;

import java.util.Arrays;

/**
 * @Description: Problem2300SuccessfulPairsOfSpellsAndPotions（咒语和药水的成功对数）
 * @Date 2022/6/25 4:30 PM
 * @Version 1.0
 */
public class Problem2300SuccessfulPairsOfSpellsAndPotions {

    // 比赛时写法
    public int[] successfulPairs0(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = spells.length, m = potions.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            int left = 0, right = m - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if ((long) spells[i] * potions[mid] < success) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            ans[i] = m - left;
        }
        return ans;
    }

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int maxPotion = 0;
        for (int p : potions) {
            maxPotion = Math.max(maxPotion, p);
        }
        int[] map = new int[maxPotion + 1];
        for (int p : potions) {
            map[p]++;
        }
        for (int i = 1; i <= maxPotion; i++) {
            map[i] += map[i - 1];
        }
        int n = spells.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            long t = (success + spells[i] - 1) / spells[i] - 1;
            if (t < maxPotion) {
                ans[i] = map[maxPotion] - map[(int) t];
            }
        }
        return ans;
    }
}
