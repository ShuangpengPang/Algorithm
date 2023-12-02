package com.shuangpeng.Problem.p2501_2600;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2572CountTheNumberOfSquareFreeSubsets（无平方子集计数）
 * @date 2023/12/2 10:51 PM
 */
public class Problem2572CountTheNumberOfSquareFreeSubsets {

    private static int[] masks = new int[31];
    static {
        for (int i = 2; i <= 30; i++) {
            int x = i, m = 0;
            for (int j = 2; j * j <= x; j++) {
                if (x % j == 0) {
                    if (x / j % j == 0) {
                        m = -1;
                        break;
                    }
                    m |= 1 << j;
                    x /= j;
                }
            }
            if (m != -1 && x > 1) {
                m |= 1 << x;
            }
            masks[i] = m;
        }
    }

    public int squareFreeSubsets(int[] nums) {
        int[] cnt = new int[31];
        for (int num : nums) {
            if (masks[num] != -1) {
                cnt[num]++;
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0, N = (int) 1e9 + 7;
        for (int i = 2; i <= 30; i++) {
            if (cnt[i] > 0) {
                int m = masks[i];
                ans = (ans + cnt[i]) % N;
                map.put(m, (map.getOrDefault(m, 0) + cnt[i]) % N);
                Set<Integer> set = new HashSet<>(map.keySet());
                for (int key : set) {
                    int count = (int) (((long) cnt[i] * map.get(key)) % N);
                    if ((key & m) == 0) {
                        map.put(key | m, (map.getOrDefault(key | m, 0) + count) % N);
                        ans = (ans + count) % N;
                    }
                }
            }
        }
        for (int i = 0; i < cnt[1]; i++) {
            ans = ((ans << 1) + 1) % N;
        }
        return ans;
    }
}
