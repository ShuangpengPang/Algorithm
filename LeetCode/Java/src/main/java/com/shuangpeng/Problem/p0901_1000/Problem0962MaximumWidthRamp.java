package com.shuangpeng.Problem.p0901_1000;

import javafx.util.Pair;

import java.util.TreeSet;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0962MaximumWidthRamp（最大宽度坡）
 * @date 2023/3/19 11:38 PM
 */
public class Problem0962MaximumWidthRamp {

    public int maxWidthRamp(int[] nums) {
        TreeSet<Pair<Integer, Integer>> set = new TreeSet<>((a, b) -> {
            int aNum = a.getKey(), aIndex = a.getValue();
            int bNum = b.getKey(), bIndex = b.getValue();
            if (aNum != bNum) {
                return aNum - bNum;
            }
            return bIndex - aIndex;
        });
        int n = nums.length, ans = 0;
        for (int i = 0; i < n; i++) {
            Pair<Integer, Integer> pair = set.floor(new Pair<>(nums[i], -1));
            if (pair != null) {
                ans = Math.max(ans, i - pair.getValue());
            } else {
                set.add(new Pair<>(nums[i], i));
            }
        }
        return ans;
    }
}
