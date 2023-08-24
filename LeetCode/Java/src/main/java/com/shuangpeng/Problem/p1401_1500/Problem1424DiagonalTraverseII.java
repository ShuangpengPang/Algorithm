package com.shuangpeng.Problem.p1401_1500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1424DiagonalTraverseII（对角线遍历II）
 * @date 2023/8/24 5:36 PM
 */
public class Problem1424DiagonalTraverseII {

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int m = nums.size(), n = 0, M = 0;
        for (List<Integer> list : nums) {
            n = Math.max(n, list.size());
            M += list.size();
        }
        int N = m + n - 1;
        List<Integer>[] lists = new List[N];
        Arrays.setAll(lists, i -> new ArrayList<>());
        for (int i = m - 1; i >= 0; i--) {
            List<Integer> list = nums.get(i);
            for (int j = 0; j < list.size(); j++) {
                lists[i + j].add(list.get(j));
            }
        }
        int[] ans = new int[M];
        for (int i = 0, idx = 0; i < N; i++) {
            for (int num : lists[i]) {
                ans[idx++] = num;
            }
        }
        return ans;
    }
}
