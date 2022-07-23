package com.shuangpeng.competition.第302场周赛;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: Problem2343QueryKthSmallestTrimmedNumber（裁剪数字后查询第K小的数字）
 * @Date 2022/7/23 12:02 PM
 * @Version 1.0
 */
public class Problem2343QueryKthSmallestTrimmedNumber {

    public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int m = nums.length, n = queries.length, len = nums[0].length();
        int[] ans = new int[n];
        for (int q = 0; q  < n; q++) {
            int k = queries[q][0], t = queries[q][1];
            String[] str = new String[m];
            for (int i = 0; i < m; i++) {
                str[i] = nums[i].substring(len - t);
            }
            List<Integer> indices = new ArrayList<>(m);
            for (int i = 0; i < m; i++) {
                indices.add(i);
            }
            for (int i = 0; i < t && indices.size() > 1; i++) {
                List<Integer>[] lists = new List[10];
                Arrays.setAll(lists, e -> new ArrayList<>());
                for (int j : indices) {
                    lists[str[j].charAt(i) - '0'].add(j);
                }
                int idx = 0;
                while (idx < 10) {
                    if (k > lists[idx].size()) {
                        k -= lists[idx].size();
                    } else {
                        break;
                    }
                    idx++;
                }
                indices = lists[idx];
            }
            ans[q] = indices.get(k - 1);
        }
        return ans;
    }
}
