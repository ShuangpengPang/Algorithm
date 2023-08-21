package com.shuangpeng.Problem.p2801_2900;

import java.util.Comparator;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2824CountPairsWhoseSumIsLessThanTarget（统计和小于目标的下标对数目）
 * @date 2023/8/21 3:46 PM
 */
public class Problem2824CountPairsWhoseSumIsLessThanTarget {

    public int countPairs0(List<Integer> nums, int target) {
        int n = nums.size(), cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums.get(i) + nums.get(j) < target) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public int countPairs(List<Integer> nums, int target) {
        nums.sort(Comparator.comparingInt(a -> a));
        int ans = 0;
        for (int i = 0, j = nums.size() - 1; j >= 0; j--) {
            while (i < j && nums.get(i) + nums.get(j) < target) {
                i++;
            }
            ans += Math.min(i, j);
        }
        return ans;
    }
}
