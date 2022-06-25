package com.shuangpeng.competition.第080场双周赛;

import java.util.Arrays;

/**
 * @Description: Problem2300SuccessfulPairsOfSpellsAndPotions（咒语和药水的成功对数）
 * @Date 2022/6/25 4:30 PM
 * @Version 1.0
 */
public class Problem2300SuccessfulPairsOfSpellsAndPotions {

    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        Arrays.sort(potions);
        int n = spells.length, m = potions.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int s = spells[i];
            int left = 0, right = m - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if ((long) s * potions[mid] >= success) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            ans[i] = m - left;
        }
        return ans;
    }
}
