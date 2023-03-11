package com.shuangpeng.Problem.p0901_1000;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0939MinimumAreaRectangle（最小面积矩形）
 * @date 2023/3/11 11:39 AM
 */
public class Problem0939MinimumAreaRectangle {

    public int minAreaRect(int[][] points) {
        Map<Integer, List<Integer>> xMap = new HashMap<>();
        Map<Integer, Set<Integer>> yMap = new HashMap<>();
        for (int[] p : points) {
            xMap.computeIfAbsent(p[0], k -> new ArrayList<>()).add(p[1]);
            yMap.computeIfAbsent(p[1], k -> new HashSet<>()).add(p[0]);
        }
        int ans = Integer.MAX_VALUE;
        for (Map.Entry<Integer, List<Integer>> e1 : xMap.entrySet()) {
            List<Integer> yList = e1.getValue();
            int x1 = e1.getKey(), size = yList.size();
            for (int i = 0; i < size; i++) {
                int y1 = yList.get(i);
                for (int j = 0; j < size; j++) {
                    int y2 = yList.get(j);
                    if (y1 >= y2) {
                        continue;
                    }
                    int height = y2 - y1;
                    Set<Integer> xSet = yMap.get(y2);
                    for (int x2 : yMap.get(y1)) {
                        if (x1 < x2 && xSet.contains(x2)) {
                            ans = Math.min(ans, Math.abs(x1 - x2) * height);
                        }
                    }
                }
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
