package com.shuangpeng.Problem.p1801_1900;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1865FindingPairsWithACertainSum（找出和为指定值的下标对）
 * @date 2023/10/17 1:10 PM
 */
public class Problem1865FindingPairsWithACertainSum {

    class FindSumPairs {

        int[] nums1, nums2;
        Map<Integer, Integer> map;

        public FindSumPairs(int[] nums1, int[] nums2) {
            this.nums1 = nums1;
            this.nums2 = nums2;
            map = new HashMap<>();
            for (int num : nums2) {
                map.merge(num, 1, Integer::sum);
            }
        }

        public void add(int index, int val) {
            map.merge(nums2[index], -1, Integer::sum);
            nums2[index] += val;
            map.merge(nums2[index], 1, Integer::sum);
        }

        public int count(int tot) {
            int cnt = 0;
            for (int num : nums1) {
                cnt += map.getOrDefault(tot - num, 0);
            }
            return cnt;
        }
    }

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */
}

class Problem1865FindingPairsWithACertainSum0 {

    class FindSumPairs {
        HashMap<Integer, Integer> nums2_map = new HashMap<>();
        int[] nums1;
        int[] nums2;
        public FindSumPairs(int[] nums1, int[] nums2) {
            Arrays.sort(nums1);
            this.nums1 = nums1;
            this.nums2 = nums2;
            for (int i : nums2) {
                nums2_map.merge(i, 1, Integer::sum);
            }
        }

        public void add(int index, int val) {
            nums2_map.merge(nums2[index], -1, Integer::sum);
            nums2[index] += val;
            nums2_map.merge(nums2[index], 1, Integer::sum);
        }

        public int count(int tot) {
            int res = 0;
            for (int i : nums1) {
                if (i >= tot) {
                    break;
                }
                res += nums2_map.getOrDefault(tot - i, 0);
            }
            return res;
        }
    }
}
