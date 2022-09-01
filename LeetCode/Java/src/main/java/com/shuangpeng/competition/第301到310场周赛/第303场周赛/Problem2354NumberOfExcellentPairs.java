package com.shuangpeng.competition.第301到310场周赛.第303场周赛;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * @Description: Problem2354NumberOfExcellentPairs（优质数对的数目）
 * @Date 2022/8/1 1:55 PM
 * @Version 1.0
 */
public class Problem2354NumberOfExcellentPairs {

    // 比赛时写法
    public long countExcellentPairs0(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        TreeMap<Integer, Integer> map = new TreeMap<>((a, b) -> b - a);
        for (int num : set) {
            int count = Integer.bitCount(num);
            map.put(count, map.getOrDefault(count, 0) + 1);
        }
        TreeMap<Integer, Long> preSum = new TreeMap<>();
        long count = 0L;
        for (int num : map.keySet()) {
            count += map.get(num);
            preSum.put(num, count);
        }
        long ans = 0L;
        for (int num : map.keySet()) {
            int cnt = map.get(num);
            Integer key = preSum.ceilingKey(k - num);
            if (key != null) {
                ans += cnt * preSum.get(key);
            }
        }
        return ans;
    }

    public long countExcellentPairs1(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int[] cnt = new int[32];
        for (int num : set) {
            cnt[Integer.bitCount(num)]++;
        }
        long ans = 0;
        for (int i = 0; i < 32; i++) {
            if (cnt[i] > 0) {
                int count = 0;
                for (int j = 31; j >= Math.max(k - i, 0); j--) {
                    count += cnt[j];
                }
                ans += (long) cnt[i] * count;
            }
        }
        return ans;
    }

    public long countExcellentPairs(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        int N = 30;
        int[] cnt = new int[N];
        for (int num : nums) {
            if (set.add(num)) {
                cnt[Integer.bitCount(num)]++;
            }
        }
        int s = 0;
        for (int i = k; i < N; i++) {
            s += cnt[i];
        }
        long ans = 0L;
        for (int i = 0; i < N; i++) {
            ans += (long) cnt[i] * s;
            int j = k - i - 1;
            if (j >= 0 && j < N) {
                s += cnt[j];
            }
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem2354NumberOfExcellentPairs a = new Problem2354NumberOfExcellentPairs();
//        int[] nums = {1,2,3,1};
//        a.countExcellentPairs(nums, 3);
//    }
}
