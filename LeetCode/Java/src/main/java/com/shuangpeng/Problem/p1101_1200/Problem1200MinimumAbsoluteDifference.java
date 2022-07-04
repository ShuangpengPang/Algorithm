package com.shuangpeng.Problem.p1101_1200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: Problem1200MinimumAbsoluteDifference（最小绝对差）
 * @Date 2022/7/4 9:55 AM
 * @Version 1.0
 */
public class Problem1200MinimumAbsoluteDifference {

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            diff = Math.min(diff, arr[i + 1] - arr[i]);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            if (arr[i + 1] - arr[i] == diff) {
                List<Integer> list = new ArrayList<>();
                list.add(arr[i]);
                list.add(arr[i + 1]);
                ans.add(list);
            }
        }
        return ans;
    }
}
