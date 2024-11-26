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

class Solution {

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int n = nums.length;
        int bits = 32 - Integer.numberOfLeadingZeros(n);
        int mask = (1 << bits) - 1;

        long[] keys = new long[n];


        for (int i = 0; i < n; i++)
            keys[i] = ((long) nums[i] << bits) + i;

        Arrays.sort(keys);

        int groupId = 0;
        int[] groupBase = new int[n];
        int[] groups = new int[n];

        for (int i = 1; i < n; i++) {
            if ((keys[i] >> bits) - (keys[i - 1] >> bits) > limit)
                groupBase[++groupId] = i;
            groups[(int) (keys[i] & mask)] = groupId;
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            groupId = groups[i];
            int index = groupBase[groupId]++;
            ans[i] = (int) (keys[index] >> bits);
        }

        return ans;
    }
}
