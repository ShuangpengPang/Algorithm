package com.shuangpeng.Problem.p2801_2900;

import java.util.Comparator;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2895MinimumProcessingTime（最小处理时间）
 * @date 2023/12/24 11:20 PM
 */
public class Problem2895MinimumProcessingTime {

    public int minProcessingTime(List<Integer> processorTime, List<Integer> tasks) {
        processorTime.sort(Comparator.comparingInt(a -> a));
        tasks.sort(Comparator.comparingInt(a -> a));
        int n = processorTime.size(), ans = 0;
        for (int i = 0, j = (n << 2) - 1; i < n; i++, j -= 4) {
            ans = Math.max(ans, processorTime.get(i) + tasks.get(j));
        }
        return ans;
    }
}
