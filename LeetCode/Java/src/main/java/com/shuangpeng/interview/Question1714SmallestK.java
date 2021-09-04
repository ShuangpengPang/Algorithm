package com.shuangpeng.interview;

import java.util.PriorityQueue;
import java.util.Random;

public class Question1714SmallestK {

    public int[] smallestK0(int[] arr, int k) {
        int n = arr.length;
        partition(arr, 0, n - 1, k - 1);
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = arr[i];
        }
        return ans;
    }

    private void partition(int[] arr, int start, int end, int k) {
        if (start >= end) {
            return;
        }
        int pivot = arr[end];
        int p = start;
        for (int i = start; i < end; ++i) {
            if (arr[i] <= pivot) {
                swap(arr, p, i);
                p++;
            }
        }
        swap(arr, p, end);
        if (p < k) {
            partition(arr, p + 1, end, k);
        } else if (p > k) {
            partition(arr, start, p - 1, k);
        }
    }

    public int[] smallestK1(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < k; ++i) {
            queue.offer(arr[i]);
        }
        for (int i = k; i < arr.length; ++i) {
            if (arr[i] < queue.peek()) {
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = queue.poll();
        }
        return ans;
    }

    private void swap(int[] arr, int i, int j) {
        if (i != j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    public int[] smallestK(int[] arr, int k) {
        randomizedSelected(arr, 0, arr.length - 1, k);
        int[] ans = new int[k];
        for (int i = 0; i < k; ++i) {
            ans[i] = arr[i];
        }
        return ans;
    }

    private void randomizedSelected(int[] arr, int s, int e, int k) {
        if (s >= e) {
            return;
        }
        int pos = randomizedPartition(arr, s, e);
        int target = s + k - 1;
        if (pos < target) {
            randomizedSelected(arr, pos + 1, e, target - pos);
        } else if (pos > target) {
            randomizedSelected(arr, s, pos - 1, k);
        }
    }

    private int randomizedPartition(int[] arr, int s, int e) {
        int r = s + new Random().nextInt(e - s + 1);
        int pivot = arr[r];
        swap(arr, r, e);
        int p = s;
        for (int i = s; i < e; ++i) {
            if (arr[i] <= pivot) {
                swap(arr, p, i);
                p++;
            }
        }
        swap(arr, p, e);
        return p;
    }
}
