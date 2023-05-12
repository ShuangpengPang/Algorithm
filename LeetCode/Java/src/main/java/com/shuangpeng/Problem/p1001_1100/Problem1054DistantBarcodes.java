package com.shuangpeng.Problem.p1001_1100;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1054DistantBarcodes（距离相等的条形码）
 * @date 2023/5/12 2:49 PM
 */
public class Problem1054DistantBarcodes {

    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int code : barcodes) {
            countMap.merge(code, 1, Integer::sum);
        }
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            q.add(new int[]{entry.getKey(), entry.getValue()});
        }
        int n = barcodes.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int[] p = q.poll();
            if (i == 0 || p[0] != ans[i - 1]) {
                ans[i] = p[0];
                p[1]--;
            } else {
                int[] a = q.poll();
                ans[i] = a[0];
                a[1]--;
                q.add(a);
            }
            q.add(p);
        }
        return ans;
    }
}
