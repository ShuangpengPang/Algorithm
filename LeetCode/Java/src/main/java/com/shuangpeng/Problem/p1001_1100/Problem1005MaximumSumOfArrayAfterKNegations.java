package com.shuangpeng.Problem.p1001_1100;

import java.util.*;

public class Problem1005MaximumSumOfArrayAfterKNegations {

    public int largestSumAfterKNegations0(int[] nums, int k) {
        int minAbs = Integer.MAX_VALUE;
        List<Integer> negative = new ArrayList<>();
        int sum = 0;
        for (int num : nums) {
            minAbs = Math.min(minAbs, Math.abs(num));
            if (num > 0) {
                sum += num;
            } else if (num < 0) {
                negative.add(num);
            }
        }
        int n = negative.size();
        if (k < n) {
            negative.sort(Comparator.comparingInt(a -> a));
            for (int i = 0; i < k; ++i) {
                sum -= negative.get(i);
            }
            for (int i = k; i < n; ++i) {
                sum += negative.get(i);
            }
        } else {
            for (int i = 0; i < n; ++i) {
                sum -= negative.get(i);
            }
            k -= n;
            if ((k & 1) == 1) {
                sum -= minAbs << 1;
            }
        }
        return sum;
    }

    public int largestSumAfterKNegations(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            sum += num;
        }
        for (int num = -100; num < 0 && k > 0; ++num) {
            if (map.containsKey(num)) {
                int ops = Math.min(k, map.get(num));
                sum -= (num << 1) * ops;
                map.put(-num, map.getOrDefault(-num, 0) + ops);
                map.put(num, map.get(num) - ops);
                k -= ops;
            }
        }
        if (k > 0 && (k & 1) == 1 && !map.containsKey(0)) {
            for (int i = 1; i <= 100; ++i) {
                if (map.containsKey(i)) {
                    sum -= i << 1;
                    break;
                }
            }
        }
        return sum;
    }
}
