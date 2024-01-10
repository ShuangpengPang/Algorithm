package com.shuangpeng.Problem.p2901_3000;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2948MakeLexicographicallySmallestArrayBySwappingElements（交换得到字典序最小的数组）
 * @date 2024/1/10 12:47 PM
 */
public class Problem2948MakeLexicographicallySmallestArrayBySwappingElements {

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, Comparator.comparingInt(a -> nums[a]));
        int[] ans = new int[n];
        for (int i = 0, j = 0; i < n; i = j) {
            j++;
            while (j < n && nums[ids[j]] - nums[ids[j - 1]] <= limit) {
                j++;
            }
            Integer[] subIds = Arrays.copyOfRange(ids, i, j);
            Arrays.sort(subIds);
            for (int k = i; k < j; k++) {
                ans[subIds[k - i]] = nums[ids[k]];
            }
        }
        return ans;
    }
}
