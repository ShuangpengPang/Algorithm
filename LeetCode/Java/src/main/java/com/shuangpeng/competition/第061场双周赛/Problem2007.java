package com.shuangpeng.competition.第061场双周赛;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem2007 {

    // 比赛时写的代码
    public int[] findOriginalArray0(int[] changed) {
        int n = changed.length;
        if ((n & 1) == 1) {
            return new int[0];
        }
        Arrays.sort(changed);
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : changed) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int zero = 0;
        while (zero < n && changed[zero] == 0) {
            ++zero;
        }
        if ((zero & 1) == 1) {
            return new int[0];
        }
        int[] ans = new int[n >> 1];
        for (int i = 0; i < zero >> 1; ++i) {
            ans[i] = 0;
        }
        int j = zero >> 1;
        int i = zero;
        while (j < (n >> 1) && i < n) {
            if (map.containsKey(changed[i])) {
                int count = map.get(changed[i]);
                if (count > map.getOrDefault(changed[i] << 1, 0)) {
                    return new int[0];
                }
                for (int k = 0; k < count; ++k) {
                    ans[j++] = changed[i];
                }
                map.remove(changed[i]);
                if (count == map.get(changed[i] << 1)) {
                    map.remove(changed[i] << 1);
                } else {
                    map.put(changed[i] << 1, map.get(changed[i] << 1) - count);
                }
            }
            ++i;
        }
        return ans;
    }

    public int[] findOriginalArray(int[] changed) {
        Arrays.sort(changed);
        int n = changed.length;
        if ((n & 1) == 1) {
            return new int[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : changed) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int[] ans = new int[n >> 1];
        int index = 0;
        for (int i = 0; i < n; ++i) {
            int value = changed[i];
            int count = map.getOrDefault(value, 0);
            if (count > 0) {
                update(map, value, count);
                int duple = value << 1;
                int count1 = map.getOrDefault(duple, 0);
                if (count1 == 0) {
                    return new int[0];
                }
                update(map, duple, count1);
                ans[index++] = value;
            }
        }
        return ans;
    }

    private void update(Map<Integer, Integer> map, int key, int count) {
        if (count == 1) {
            map.remove(key);
        }
        map.put(key, count - 1);
    }
}
