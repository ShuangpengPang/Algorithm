package com.shuangpeng.Problem.p2401_2500;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2404MostFrequentEvenElement（出现最频繁的偶数元素）
 * @date 2023/4/17 2:42 PM
 */
public class Problem2404MostFrequentEvenElement {

    public int mostFrequentEven(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = -1, freq = 0;
        for (int num : nums) {
            if ((num & 1) == 0) {
                int f = map.getOrDefault(num, 0) + 1;
                if (f >= freq) {
                    ans = f > freq ? num : Math.min(ans, num);
                    freq = f;
                }
                map.put(num, f);
            }
        }
        return ans;
    }
}
