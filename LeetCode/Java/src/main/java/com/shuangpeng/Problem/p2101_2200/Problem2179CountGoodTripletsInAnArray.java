package com.shuangpeng.Problem.p2101_2200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2179CountGoodTripletsInAnArray（统计数组中好三元组数目）
 * @date 2022/11/22 4:44 PM
 */
public class Problem2179CountGoodTripletsInAnArray {

    public long goodTriplets(int[] nums1, int[] nums2) {
         int n = nums1.length;
         int[] indices = new int[n + 1];
         for (int i = 0; i < n; i++) {
             indices[nums1[i]] = i + 1;
         }
         long[][] count = new long[n + 1][2];
         long ans = 0L;
         for (int i = n - 1; i >= 0; i--) {
             int j = indices[nums2[i]];
             long[] arr1 = getCount(count, n), arr2 = getCount(count, j);
             long cnt1 = arr1[0] - arr2[0], cnt2 = arr1[1] - arr2[1];
             add(count, j, 1L, 0);
             add(count, j, cnt1, 1);
             ans += cnt2;
         }
         return ans;
    }

    private long[] getCount(long[][] count, int x) {
        int n = count.length;
        long cnt1 = 0L, cnt2 = 0L;
        while (x > 0) {
            cnt1 += count[x][0];
            cnt2 += count[x][1];
            x ^= x & (-x);
        }
        return new long[]{cnt1, cnt2};
    }

    private void add(long[][] count, int x, long val, int i) {
        int n = count.length;
        while (x < n) {
            count[x][i] += val;
            x += x & (-x);
        }
    }
}
