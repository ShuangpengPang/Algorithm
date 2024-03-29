package com.shuangpeng.Problem.p0601_0700;

import java.util.*;

/**
 * @Description: Problem0658FindKClosestElements（找到K个最接近的元素）
 * @Date 2022/8/25 10:13 AM
 * @Version 1.0
 */
public class Problem0658FindKClosestElements {

    public List<Integer> findClosestElements0(int[] arr, int k, int x) {
        int idx = binarySearch(arr, x);
        int n = arr.length;
        if (idx < 0) {
            List<Integer> ans = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                ans.add(arr[i]);
            }
            return ans;
        } else if (idx == n - 1) {
            List<Integer> ans = new ArrayList<>();
            for (int i = n - k; i < n; i++) {
                ans.add(arr[i]);
            }
            return ans;
        }
        Deque<Integer> q = new ArrayDeque<>(k);
        int l = idx, r = idx + 1;
        while (l >= 0 && r < n && q.size() < k) {
            if (x - arr[l] <= arr[r] - x) {
                q.addFirst(arr[l--]);
            } else {
                q.addLast(arr[r++]);
            }
        }
        while (q.size() < k && l >= 0) {
            q.addFirst(arr[l--]);
        }
        while (q.size() < k && r < n) {
            q.addLast(arr[r++]);
        }
        return new ArrayList<>(q);
    }

    private int binarySearch(int[] arr, int x) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (x >= arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }

    public List<Integer> findClosestElements1(int[] arr, int k, int x) {
        int n = arr.length;
        int left = 0, right = k;
        for (int l = 0, r = k; r < n; l++, r++) {
            int d1 = Math.abs(x - arr[l]), d2 = Math.abs(x - arr[r]);
            if (d1 > d2) {
                left = l + 1;
                right = r + 1;
            } else if (d1 < d2) {
                break;
            }
        }
        List<Integer> ans = new ArrayList<>(k);
        for (int i = left; i < right; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> copy = new ArrayList<>(arr.length);
        for (int num : arr) {
            copy.add(num);
        }
        copy.sort((a, b) -> {
            if (Math.abs(x - a) != Math.abs(x - b)) {
                return Math.abs(x - a) - Math.abs(x - b);
            }
            return a - b;
        });
        List<Integer> ans = copy.subList(0, k);
        ans.sort(Comparator.comparingInt(a -> a));
        return ans;
    }
}

class Problem0658FindKClosestElements0 {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = binarySearch(arr, x), right = left, n = arr.length;
        while (k-- > 0) {
            if (right == n) {
                left--;
            } else if (left == 0) {
                right++;
            } else if (x - arr[left - 1] <= arr[right] - x) {
                left--;
            } else {
                right++;
            }
        }
        List<Integer> ans = new ArrayList<>(right - left);
        for (int i = left; i < right; i++) {
            ans.add(arr[i]);
        }
        return ans;
    }

    private int binarySearch(int[] arr, int x) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (x >= arr[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}

