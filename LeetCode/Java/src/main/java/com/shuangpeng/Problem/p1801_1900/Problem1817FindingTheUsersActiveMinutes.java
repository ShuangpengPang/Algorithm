package com.shuangpeng.Problem.p1801_1900;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1817FindingTheUsersActiveMinutes（查找用户活跃分钟数）
 * @date 2023/1/20 4:02 PM
 */
public class Problem1817FindingTheUsersActiveMinutes {

    public int[] findingUsersActiveMinutes(int[][] logs, int k) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] log : logs) {
            map.computeIfAbsent(log[0], key -> new HashSet()).add(log[1]);
        }
        int[] ans = new int[k];
        for (Set<Integer> value : map.values()) {
            ans[value.size() - 1]++;
        }
        return ans;
    }
}
