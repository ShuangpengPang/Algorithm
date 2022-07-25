package com.shuangpeng.Problem.p1201_1300;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Problem1224MaximumEqualFrequency（最大相等频率）
 * @Date 2022/7/25 5:05 PM
 * @Version 1.0
 */
public class Problem1224MaximumEqualFrequency {

    public int maxEqualFreq(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        Map<Integer, Integer> freqCount = new HashMap<>();
        int ans = 0, n = nums.length;
        int min = 0, max = 0;
        for (int i = 0; i < n; i++) {
            int freq = freqMap.getOrDefault(nums[i], 0);
            if (freqCount.containsKey(freq)) {
                int count = freqCount.get(freq);
                if (count > 1) {
                    freqCount.put(freq, count - 1);
                } else {
                    freqCount.remove(freq);
                    if (freq == min) {
                        min++;
                    }
                }
            }
            freqMap.put(nums[i], freq + 1);
            freqCount.put(freq + 1, freqCount.getOrDefault(freq + 1, 0) + 1);
            max = Math.max(max, freq + 1);
            if (freq == 0) {
                min = 1;
            }
            if (freqCount.size() == 1) {
                if (freqCount.get(max) == 1 || max == 1) {
                    ans = i + 1;
                }
            } else if (freqCount.size() == 2) {
                min = Integer.MAX_VALUE;
                max = Integer.MIN_VALUE;
                for (int key : freqCount.keySet()) {
                    min = Math.min(min, key);
                    max = Math.max(max, key);
                }
                if (min + 1 == max && freqCount.get(max) == 1 || min == 1 && freqCount.get(min) == 1) {
                    ans = i + 1;
                }
            }
        }
        return ans;
    }
}
