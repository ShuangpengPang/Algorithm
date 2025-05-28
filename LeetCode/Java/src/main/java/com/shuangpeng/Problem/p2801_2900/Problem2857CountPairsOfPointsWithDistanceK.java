package com.shuangpeng.Problem.p2801_2900;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: Algorithm
 * @description: Problem2857CountPairsOfPointsWithDistanceK（统计距离为K的点对）
 * @author: ShuangPengPang
 * @create: 2025-05-28 13:47
 */
public class Problem2857CountPairsOfPointsWithDistanceK {

    public int countPairs(List<List<Integer>> coordinates, int k) {
        int ans = 0;
        Map<Long, Integer> map = new HashMap<>();
        for (List<Integer> p : coordinates) {
            long x = p.get(0), y = p.get(1);
            for (int i = 0, j = k; i <= k; i++, j--) {
                ans += map.getOrDefault((x ^ i) | (y ^ j) << 32, 0);
            }
            map.merge(x | y << 32, 1, Integer::sum);
        }
        return ans;
    }
}
