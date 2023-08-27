package com.shuangpeng.Problem.p1401_1500;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1481LeastNumberOfUniqueIntegersAfterKRemovals（不同整数的最少数目）
 * @date 2023/8/27 8:03 PM
 */
public class Problem1481LeastNumberOfUniqueIntegersAfterKRemovals {

    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.merge(num, 1, Integer::sum);
        }
        int n = map.size(), i = 0;
        int[] count = new int[n];
        for (int cnt : map.values()) {
            count[i++] = cnt;
        }
        Arrays.sort(count);
        int ans = n;
        for (int j = 0; j < n; j++) {
            if (k >= count[j]) {
                k -= count[j];
                ans--;
            } else {
                break;
            }
        }
        return ans;
    }
}
