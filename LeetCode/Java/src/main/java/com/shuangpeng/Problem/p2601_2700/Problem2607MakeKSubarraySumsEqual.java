package com.shuangpeng.Problem.p2601_2700;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2607MakeKSubarraySumsEqual（使子数组元素和相等）
 * @date 2023/12/9 9:37 PM
 */
public class Problem2607MakeKSubarraySumsEqual {

    public long makeSubKSumEqual(int[] arr, int k) {
        int[] parent = new int[k];
        Arrays.setAll(parent, i -> i);
        for (int i = 0, j = arr.length % k; i < k; j++, i++) {
            union(parent, i, j % k);
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.computeIfAbsent(find(parent, i % k), key -> new ArrayList<>()).add(arr[i]);
        }
        long ans = 0;
        for (List<Integer> list : map.values()) {
            list.sort(Comparator.comparingInt(a -> a));
            ans += getCount(list);
        }
        return ans;
    }

    private long getCount(List<Integer> list) {
        int n = list.size();
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + list.get(i);
        }
        long ans = preSum[n], left = 0;
        for (int i = 1; i <= n; i++) {
            long num = list.get(i - 1);
            ans = Math.min(ans, left * num - preSum[i - 1] + (preSum[n] - preSum[i - 1] - (n - left) * num));
            left++;
        }
        return ans;
    }

    private int find(int[] parent, int x) {
        return parent[x] = parent[x] == x ? x : find(parent, parent[x]);
    }

    private void union(int[] parent, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px != py) {
            parent[py] = px;
        }
    }
}

class Problem2607MakeKSubarraySumsEqual0 {

    public long makeSubKSumEqual(int[] arr, int k) {
        int n = arr.length, g = gcd(n, k), m = n / g;
        long ans = 0;
        for (int i = 0; i < g; i++) {
            int[] nums = new int[m];
            for (int j = 0, id = i; j < m; j++, id += g) {
                nums[j] = arr[id];
            }
            Arrays.sort(nums);
            int t = nums[m >> 1];
            for (int j = 0; j < m; j++) {
                ans += Math.abs(nums[j] - t);
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }
}
