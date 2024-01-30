package com.shuangpeng.Problem.p2501_2600;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2584SplitTheArrayToMakeCoprimeProducts（分割数组使乘积互质）
 * @date 2024/1/30 10:37 AM
 */
public class Problem2584SplitTheArrayToMakeCoprimeProducts {

    public int findValidSplit0(int[] nums) {
        int n = nums.length;
        List<Integer>[] primes = new List[n];
        Arrays.setAll(primes, i -> new ArrayList());
        Map<Integer, Integer> freq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            for (int j = 2; j * j <= x; j++) {
                if (x % j == 0) {
                    while (x % j == 0) {
                        x /= j;
                    }
                    primes[i].add(j);
                    freq.merge(j, 1, Integer::sum);
                }
            }
            if (x > 1) {
                primes[i].add(x);
                freq.merge(x, 1, Integer::sum);
            }
        }
        Set<Integer> set = new HashSet<>();
        int cnt = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int p : primes[i]) {
                set.add(p);
                if (freq.merge(p, -1, Integer::sum) == 0) {
                    cnt++;
                }
            }
            if (cnt == set.size()) {
                return i;
            }
        }
        return -1;
    }

    public int findValidSplit(int[] nums) {
        int n = nums.length;
        List<Integer>[] primes = new List[n];
        Arrays.setAll(primes, i -> new ArrayList<>());
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i];
            for (int j = 2; j * j <= x; j++) {
                if (x % j == 0) {
                    while (x % j == 0) {
                        x /= j;
                    }
                    if (!map.containsKey(j)) {
                        map.put(j, i);
                    }
                    primes[i].add(j);
                }
            }
            if (x > 1) {
                if (!map.containsKey(x)) {
                    map.put(x, i);
                }
                primes[i].add(x);
            }
        }
        for (int i = 0, last = 0; i < n; i++) {
            if (last < i) {
                return last;
            }
            for (int j : primes[i]) {
                last = Math.max(last, map.get(j));
            }
        }
        return -1;
    }
}
