package com.shuangpeng.Problem.p1001_1100;

import java.util.Arrays;
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

    public int[] rearrangeBarcodes0(int[] barcodes) {
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

    public int[] rearrangeBarcodes1(int[] barcodes) {
        int n = barcodes.length, max = 0;
        Integer[] copy = new Integer[n];
        for (int i = 0; i < n; i++) {
            int b = barcodes[i];
            copy[i] = b;
            max = Math.max(max, b);
        }
        int[] cnt = new int[max + 1];
        for (int b : barcodes) {
            cnt[b]++;
        }
        Arrays.sort(copy, (a, b) -> cnt[b] == cnt[a] ? a - b : cnt[b] - cnt[a]);
        int[] ans = new int[n];
        for (int i = 0, k = 0; k < 2; k++) {
            for (int j = k; j < n; j += 2, i++) {
                ans[j] = copy[i];
            }
        }
        return ans;
    }

    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int code : barcodes) {
            map.merge(code, 1, Integer::sum);
        }
        int n = barcodes.length, h = n >> 1;
        int[] ans = new int[n];
        int evenIndex = 0, oddIndex = 1;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey(), count = entry.getValue();
            if (count <= h) {
                while (oddIndex < n && count > 0) {
                    ans[oddIndex] = num;
                    oddIndex += 2;
                    count--;
                }
            }
            while (count > 0) {
                ans[evenIndex] = num;
                evenIndex += 2;
                count--;
            }
        }
        return ans;
    }
}
