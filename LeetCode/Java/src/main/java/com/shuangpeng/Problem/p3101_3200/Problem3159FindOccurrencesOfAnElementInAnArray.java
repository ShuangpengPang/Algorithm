package com.shuangpeng.Problem.p3101_3200;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3159FindOccurrencesOfAnElementInAnArray（查询数组中元素的出现位置）
 * @date 2024/6/1 6:34 PM
 */
public class Problem3159FindOccurrencesOfAnElementInAnArray {

    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        int n = nums.length, m = queries.length, count = 0;
        int[] indices = new int[n];
        Arrays.fill(indices, -1);
        for (int i = 0; i < n; i++) {
            if (nums[i] == x) {
                indices[count++] = i;
            }
        }
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            ans[i] = queries[i] > n ? -1 : indices[queries[i] - 1];
        }
        return ans;
    }
}
