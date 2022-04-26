package com.shuangpeng.Problem.p0901_1000;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: Problem0952LargestComponentSizeByCommonFactor
 * @Date 2022/4/26 11:28 AM
 * @Version 1.0
 */
public class Problem0952LargestComponentSizeByCommonFactor {

    public int largestComponentSize(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            int num = nums[i];
            int temp = num;
            int j = 2;
            while (temp >= j * j) {
                if (temp % j == 0) {
                    map.putIfAbsent(j, new ArrayList<>());
                    map.get(j).add(i);
                    while (temp % j == 0) {
                        temp /= j;
                    }
                }
                ++j;
            }
            if (temp > 1) {
                map.putIfAbsent(temp, new ArrayList<>());
                map.get(temp).add(i);
            }
        }
        int[] parent = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; ++i) {
            parent[i] = i;
            size[i] = 1;
        }
        int ans = 1;
        for (int factor : map.keySet()) {
            List<Integer> nodes = map.get(factor);
            int m = nodes.size();
            int first = nodes.get(0);
            ans = Math.max(ans, size[find(parent, first)]);
            for (int i = 1; i < m; ++i) {
                int count = union(parent, size, first, nodes.get(i));
                ans = Math.max(ans, count);
            }
        }
        return ans;
    }

    private int union(int[] parent, int[] size, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px != py) {
            if (size[px] > size[py]) {
                parent[py] = px;
                size[px] += size[py];
            } else {
                parent[px] = py;
                size[py] += size[px];
            }
        }
        return size[find(parent, px)];
    }

    private int find(int[] parent, int x) {
        return parent[x] = (x == parent[x] ? x : find(parent, parent[x]));
    }
}
