package com.shuangpeng.Problem.p1401_1500;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1424DiagonalTraverseII（对角线遍历II）
 * @date 2023/8/24 5:36 PM
 */
public class Problem1424DiagonalTraverseII {

    public int[] findDiagonalOrder0(List<List<Integer>> nums) {
        int cnt = 0, m = nums.size();
        for (List<Integer> list : nums) {
            cnt += list.size();
        }
        int[] ans = new int[cnt];
        int idx = 0;
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        q.offer(new int[]{idx++, 0, 0, nums.get(0).get(0)});
        while (!q.isEmpty()) {
            int[] p = q.poll();
            ans[p[0]] = p[3];
            if (p[2] == 0 && p[1] + 1 < m) {
                q.offer(new int[]{idx++, p[1] + 1, 0, nums.get(p[1] + 1).get(0)});
            }
            List<Integer> list = nums.get(p[1]);
            if (p[2] + 1 < list.size()) {
                q.offer(new int[]{idx++, p[1], p[2] + 1, list.get(p[2] + 1)});
            }
        }
        return ans;
    }

    public int[] findDiagonalOrder1(List<List<Integer>> nums) {
        List<int[]> arr = new ArrayList<>();
        int m = nums.size();
        for (int i = 0; i < m; i++) {
            List<Integer> list = nums.get(i);
            int n = list.size();
            for (int j = 0; j < n; j++) {
                arr.add(new int[]{i + j, j, list.get(j)});
            }
        }
        arr.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        int size = arr.size();
        int[] ans = new int[size];
        for (int i = 0; i < size; i++) {
            ans[i] = arr.get(i)[2];
        }
        return ans;
    }

    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        int m = nums.size(), N = 0, cnt = 0;
        for (int i = 0; i < m; i++) {
            int c = nums.get(i).size();
            N = Math.max(N, i + c);
            cnt += c;
        }
        List<Integer>[] arr = new List[N];
        Arrays.setAll(arr, i -> new ArrayList<>());
        for (int i = m - 1; i >= 0; i--) {
            List<Integer> list = nums.get(i);
            int n = list.size();
            for (int j = 0; j < n; j++) {
                arr[i + j].add(list.get(j));
            }
        }
        int[] ans = new int[cnt];
        int idx = 0;
        for (List<Integer> list : arr) {
            for (int num : list) {
                ans[idx++] = num;
            }
        }
        return ans;
    }
}
