package com.shuangpeng.Problem.p1601_1700;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description: Problem1665MinimumInitialEnergyToFinishTasks（完成所有任务的最少初始能量）
 * @Date 2022/9/19 4:38 PM
 * @Version 1.0
 */
public class Problem1665MinimumInitialEnergyToFinishTasks {

    public int minimumEffort(int[][] tasks) {
        Arrays.sort(tasks, Comparator.comparingInt(a -> a[0] - a[1]));
        int ans = 0, e = 0;
        for (int[] t : tasks) {
            if (e < t[1]) {
                ans += t[1] - e;
                e = t[1];
            }
            e -= t[0];
        }
        return ans;
    }
}
