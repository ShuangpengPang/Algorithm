package com.shuangpeng.offer;

import java.util.*;

/**
 * @Description: OfferII115（重建序列）
 * @Date 2022/7/23 10:04 AM
 * @Version 1.0
 */
public class OfferII115 {

    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        int n = nums.length;
        List<Integer>[] graph = new List[n];
        Arrays.setAll(graph, e -> new ArrayList<>());
        int[] inDegree = new int[n];
        for (int[] arr : sequences) {
            int m = arr.length;
            for (int i = 0; i < m - 1; i++) {
                int n1 = arr[i] - 1, n2 = arr[i + 1] - 1;
                graph[n1].add(n2);
                inDegree[n2]++;
            }
        }
        int num = -1;
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                if (num != -1) {
                    return false;
                }
                num = i;
            }
        }
        for (int i = 0; i < n; i++) {
            if (num + 1 != nums[i]) {
                return false;
            }
            List<Integer> list = graph[num];
            num = -1;
            for (int j : list) {
                inDegree[j]--;
                if (inDegree[j] == 0) {
                    if (num != -1) {
                        return false;
                    }
                    num = j;
                }
            }
        }
        return true;
    }
}
