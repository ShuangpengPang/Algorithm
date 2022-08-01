package com.shuangpeng.competition.第303场周赛;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: Problem2354NumberOfExcellentPairs（优质数对的数目）
 * @Date 2022/8/1 1:55 PM
 * @Version 1.0
 */
public class Problem2354NumberOfExcellentPairs {

    public long countExcellentPairs(int[] nums, int k) {
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

//    public static void main(String[] args) {
//        Problem2354NumberOfExcellentPairs a = new Problem2354NumberOfExcellentPairs();
//        int[] nums = {1,2,3,1};
//        a.countExcellentPairs(nums, 3);
//    }
}
