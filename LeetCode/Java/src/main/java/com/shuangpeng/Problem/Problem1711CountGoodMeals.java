package com.shuangpeng.Problem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem1711CountGoodMeals {

    public int countPairs0(int[] deliciousness) {
        int n = deliciousness.length;
        int mod = (int) (1e9 + 7);
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < n; i++) {
            int item = deliciousness[i];
            max = Math.max(max, item);
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
        max <<= 1;
        int maxValue = (int) Math.pow(2, 21);
        int answer = 0;
        for (int i = 1; i <= maxValue && i <= max; i <<= 1) {
            Set<Integer> visited = new HashSet<>();
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int a = entry.getKey();
                int b = i - a;
                if (visited.contains(a)) {
                    continue;
                }
                visited.add(a);
                visited.add(b);
                long count = entry.getValue();
                if (a == b && count > 1) {
                    answer += (count * (count - 1) >> 1) % mod;
                } else if (a != b && map.containsKey(b)) {
                    answer += (count * (long) map.get(b)) % mod;
                }
            }
        }
        return answer;
    }

    public int countPairs(int[] deliciousness) {
        int mod = (int) 1e9 + 7;
        int n = deliciousness.length;
        int maxValue = 0;
        for (int i = 0; i < n; i++) {
            maxValue = Math.max(maxValue, deliciousness[i]);
        }
        int maxSum = maxValue << 1;
        Map<Integer, Integer> map = new HashMap<>();
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= maxSum; j <<= 1) {
                int count = map.getOrDefault(j - deliciousness[i], 0);
                answer += count;
                answer %= mod;
            }
            map.put(deliciousness[i], map.getOrDefault(deliciousness[i], 0) + 1);
        }
        return answer;
    }
}
