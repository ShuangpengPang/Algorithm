package com.shuangpeng.Problem.p3001_3100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3005CountElementsWithMaximumFrequency（最大频率元素计数）
 * @date 2024/2/5 11:06 AM
 */
public class Problem3005CountElementsWithMaximumFrequency {

    public int maxFrequencyElements0(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        int maxFreq = 0;
        for (int num : nums) {
            maxFreq = Math.max(maxFreq, freq.merge(num, 1, Integer::sum));
        }
        int sum = 0;
        for (int f : freq.values()) {
            if (f == maxFreq) {
                sum += f;
            }
        }
        return sum;
    }

    public int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        int maxFreq = 0, ans = 0;
        for (int num : nums) {
            int f = freq.merge(num, 1, Integer::sum);
            if (f > maxFreq) {
                ans = maxFreq = f;
            } else if (f == maxFreq) {
                ans += f;
            }
        }
        return ans;
    }
}
