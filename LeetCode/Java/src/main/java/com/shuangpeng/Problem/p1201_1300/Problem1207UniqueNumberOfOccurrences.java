package com.shuangpeng.Problem.p1201_1300;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1207UniqueNumberOfOccurrences（独一无二的出现次数）
 * @date 2024/1/30 9:51 AM
 */
public class Problem1207UniqueNumberOfOccurrences {

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : arr) {
            freq.merge(num, 1, Integer::sum);
        }
        Set<Integer> set = new HashSet<>();
        for (int f : freq.values()) {
            if (!set.add(f)) {
                return false;
            }
        }
        return true;
    }
}
