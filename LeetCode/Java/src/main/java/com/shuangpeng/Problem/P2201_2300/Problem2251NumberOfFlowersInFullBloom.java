package com.shuangpeng.Problem.P2201_2300;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2251NumberOfFlowersInFullBloom（花期内花的数目）
 * @date 2022/12/7 4:05 PM
 */
public class Problem2251NumberOfFlowersInFullBloom {

    class Node {
        int start, end, cnt;
        Node left, right;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        void update(int s, int e) {
            if (s <= start && end <= e) {
                cnt++;
                return;
            }
            int mid = start + (end - start >> 1);
            down(mid);
            if (s <= mid) {
                left.update(s, Math.min(mid, e));
            }
            if (e > mid) {
                right.update(Math.max(mid + 1, s), e);
            }
        }

        int query(int x) {
            if (start == end) {
                return cnt;
            }
            int mid = start + (end - start >> 1);
            down(mid);
            if (x <= mid) {
                return left.query(x);
            }
            return right.query(x);
        }

        void down(int mid) {
            if (left == null) {
                left = new Node(start, mid);
            }
            if (right == null) {
                right = new Node(mid + 1, end);
            }
            left.cnt += this.cnt;
            right.cnt += this.cnt;
            cnt = 0;
        }
    }

    public int[] fullBloomFlowers0(int[][] flowers, int[] persons) {
        Node root = new Node(1, (int) 1e9);
        for (int[] p : flowers) {
            root.update(p[0], p[1]);
        }
        int n = persons.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = root.query(persons[i]);
        }
        return ans;
    }

    public int[] fullBloomFlowers1(int[][] flowers, int[] persons) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] f : flowers) {
            int s = f[0], e = f[1] + 1;
            map.put(s, map.getOrDefault(s, 0) + 1);
            map.put(e, map.getOrDefault(e, 0) - 1);
        }
        int m = map.size();
        int[] time = map.keySet().stream().mapToInt(Integer::intValue).sorted().toArray();
        int n = persons.length;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, Comparator.comparingInt(i -> persons[i]));
        int sum = 0, i = 0;
        int[] ans = new int[n];
        for (int id : ids) {
            while (i < m && time[i] <= persons[id]) {
                sum += map.get(time[i]);
                i++;
            }
            ans[id] = sum;
        }
        return ans;
    }

    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        int m = flowers.length;
        int[] start = new int[m], end = new int[m];
        for (int i = 0; i < m; i++) {
            start[i] = flowers[i][0];
            end[i] = flowers[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int n = persons.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = binarySearch(start, persons[i]) - binarySearch(end, persons[i] - 1);
        }
        return ans;
    }

    private int binarySearch(int[] arr, int num) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (arr[mid] <= num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
