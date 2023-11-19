package com.shuangpeng.Problem.P2201_2300;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2261KDivisibleElementsSubarrays（含最多K个可整除元素的子数组）
 * @date 2023/11/19 4:29 PM
 */
public class Problem2261KDivisibleElementsSubarrays {

    class Trie {
        Map<Integer, Trie> tries = new HashMap<>();
        boolean isSubarray = false;
    }

    public int countDistinct(int[] nums, int k, int p) {
        int n = nums.length;
        boolean[] divisible = new boolean[n];
        for (int i = 0; i < n; i++) {
            divisible[i] = nums[i] % p == 0;
        }
        int ans = 0;
        Trie root = new Trie();
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            Trie trie = root;
            for (int j = i; j < n && cnt <= k; j++) {
                if (divisible[j]) {
                    cnt++;
                }
                if (cnt <= k) {
                    trie = trie.tries.computeIfAbsent(nums[j], key -> new Trie());
                    if (!trie.isSubarray) {
                        trie.isSubarray = true;
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
}
