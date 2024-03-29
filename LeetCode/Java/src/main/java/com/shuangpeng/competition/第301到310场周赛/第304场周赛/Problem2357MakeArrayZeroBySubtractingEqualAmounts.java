package com.shuangpeng.competition.第301到310场周赛.第304场周赛;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: Problem2357MakeArrayZeroBySubtractingEqualAmounts（使数组中所有元素都等于零）
 * @Date 2022/8/6 5:09 PM
 * @Version 1.0
 */
public class Problem2357MakeArrayZeroBySubtractingEqualAmounts {

    // 比赛时写法
    public int minimumOperations0(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (num > 0) {
                set.add(num);
            }
        }
        return set.size();
    }

    public int minimumOperations(int[] nums) {
        int ans = 0, N = 101;
        boolean[] has = new boolean[N];
        for (int num : nums) {
            if (num > 0 && !has[num]) {
                has[num] = true;
                ans++;
            }
        }
        return ans;
    }
}
