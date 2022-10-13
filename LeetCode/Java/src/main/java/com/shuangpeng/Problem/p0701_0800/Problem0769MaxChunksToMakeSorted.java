package com.shuangpeng.Problem.p0701_0800;

/**
 * @Description: Problem0769MaxChunksToMakeSorted（最多能完成排序的块）
 * @Date 2022/10/13 10:14 AM
 * @Version 1.0
 */
public class Problem0769MaxChunksToMakeSorted {

    public int maxChunksToSorted(int[] arr) {
        int max = 0, ans = 0, n = arr.length;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, arr[i]);
            if (max == i) {
                ans++;
            }
        }
        return ans;
    }
}
