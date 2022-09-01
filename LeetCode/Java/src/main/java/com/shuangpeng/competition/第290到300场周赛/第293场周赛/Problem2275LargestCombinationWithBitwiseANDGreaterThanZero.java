package com.shuangpeng.competition.第290到300场周赛.第293场周赛;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: Problem2275LargestCombinationWithBitwiseANDGreaterThanZero（按位与结果大于零的最长组合）
 * @Date 2022/6/25 2:46 PM
 * @Version 1.0
 */
public class Problem2275LargestCombinationWithBitwiseANDGreaterThanZero {

    // 比赛时写法
    public int largestCombination0(int[] candidates) {
        List<Integer>[] lists = new List[31];
        for (int i = 0; i < 31; ++i) {
            lists[i] = new ArrayList<>();
        }
        int n = candidates.length;
        for (int i = 0; i < n; ++i) {
            int num = candidates[i];
            for (int j = 0; j < 31; ++j) {
                if ((num & (1 << j)) != 0) {
                    lists[j].add(i);
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < 31; ++i) {
            ans = Math.max(ans, lists[i].size());
        }
        return ans;
    }

    public int largestCombination1(int[] candidates) {
        int N = 24;
        int[] cnt = new int[N];
        for (int c : candidates) {
            for (int i = 0; i < N; i++) {
                if ((c & (1 << i)) != 0) {
                    cnt[i]++;
                }
            }
        }
        return Arrays.stream(cnt).max().getAsInt();
    }

    public int largestCombination(int[] candidates) {
        int n = candidates.length;
        int length= 0;
        int target = 1;
        int max = 0;
        for (int cur:candidates) {
            target = Math.max(cur,target);
        }
        while (target > 0) {
            for (int i = 0 ; i < n ; i++) {
                length += candidates[i] & 1;
                candidates[i] >>= 1;
            }
            max = Math.max(length, max);
            length = 0;
            target >>= 1;
        }
        return max;
    }
}
