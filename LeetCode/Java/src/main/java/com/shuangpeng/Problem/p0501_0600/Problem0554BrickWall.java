package com.shuangpeng.Problem.p0501_0600;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: Problem0554BrickWall（砖墙）
 * @Date 2022/8/19 10:50 AM
 * @Version 1.0
 */
public class Problem0554BrickWall {

    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        int size = wall.size();
        int ans = size;
        for (List<Integer> list : wall) {
            int n = list.size();
            int sum = 0;
            for (int i = 0; i < n - 1; i++) {
                sum += list.get(i);
                int cnt = map.getOrDefault(sum, 0) + 1;
                ans = Math.min(ans, size - cnt);
                map.put(sum, cnt);
            }
        }
        return ans;
    }
}
