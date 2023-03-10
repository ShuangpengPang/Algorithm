package com.shuangpeng.Problem.p0901_1000;

import java.util.Random;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0912SortAnArray（排序数组）
 * @date 2023/3/10 11:07 AM
 */
public class Problem0912SortAnArray {

    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int s, int e) {
        if (s >= e) {
            return;
        }
        int j = new Random().nextInt(e - s + 1) + s;
        swap(nums, e, j);
        int p = s, num = nums[e];
        for (int i = s; i < e; i++) {
            if (nums[i] <= num) {
                swap(nums, p, i);
                p++;
            }
        }
        swap(nums, p, e);
        quickSort(nums, s, p - 1);
        quickSort(nums, p + 1, e);
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[i] ^ nums[j];
            nums[i] = nums[i] ^ nums[j];
        }
    }
}

class Problem0912SortAnArray0 {

    public int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void mergeSort(int[] nums, int s, int e) {
        if (s >= e) {
            return;
        }
        int m = s + e >> 1;
        mergeSort(nums, s, m);
        mergeSort(nums, m + 1, e);
        int n = e - s + 1;
        int[] tmp = new int[n];
        int i = 0, i1 = s, i2 = m + 1;
        while (i1 <= m && i2 <= e) {
            if (nums[i1] <= nums[i2]) {
                tmp[i++] = nums[i1++];
            } else {
                tmp[i++] = nums[i2++];
            }
        }
        while (i1 <= m) {
            tmp[i++] = nums[i1++];
        }
        while (i2 <= e) {
            tmp[i++] = nums[i2++];
        }
        System.arraycopy(tmp, 0, nums, s, n);
    }
}

class Problem0912SortAnArray1 {

    public int[] sortArray(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, n = nums.length;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        int m = max - min + 1;
        int[] cnt = new int[m];
        for (int num : nums) {
            cnt[num - min]++;
        }
        for (int i = 0, j = 0; i < m; i++) {
            while (cnt[i]-- > 0) {
                nums[j++] = i + min;
            }
        }
        return nums;
    }
}

class Problem0912SortAnArray2 {

    public int[] sortArray(int[] nums) {
        int n = nums.length;
        int[] heap = new int[n + 1];
        for (int i = 0; i < n; i++) {
            heap[i + 1] = nums[i];
            ascent(heap, i + 1);
        }
        for (int i = 0; i < n; i++) {
            nums[i] = heap[1];
            swap(heap, 1, n - i);
            sink(heap, n - i - 1);
        }
        return nums;
    }

    private void ascent(int[] heap, int i) {
        while (i > 1 && heap[i] < heap[i >> 1]) {
            swap(heap, i, i >> 1);
            i >>= 1;
        }
    }

    private void sink(int[] heap, int n) {
        int i = 1;
        while (i << 1 <= n) {
            int j = i << 1;
            j = j + 1 > n || heap[j] <= heap[j + 1] ? j : j + 1;
            if (heap[i] <= heap[j]) {
                break;
            }
            swap(heap, i, j);
            i = j;
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[i] ^ nums[j];
            nums[i] = nums[i] ^ nums[j];
        }
    }
}

class Problem0912SortAnArray3 {

    public int[] sortArray(int[] nums) {
        heapSort(nums);
        return nums;
    }

    private void heapSort(int[] nums) {
        buildMaxHeap(nums);
        for (int i = nums.length - 1; i > 0; i--) {
            swap(nums, 0, i);
            sink(nums, 0, i - 1);
        }
    }

    private void buildMaxHeap(int[] nums) {
        int n = nums.length - 1;
        for (int i = n >> 1; i >= 0; i--) {
            sink(nums, i, n);
        }
    }

    private void sink(int[] nums, int i, int n) {
        while ((i << 1) + 1 <= n) {
            int j = (i << 1) + 1;
            j = j + 1 > n || nums[j] >= nums[j + 1] ? j : j + 1;
            if (nums[i] >= nums[j]) {
                break;
            }
            swap(nums, i, j);
            i = j;
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            nums[i] = nums[i] ^ nums[j];
            nums[j] = nums[i] ^ nums[j];
            nums[i] = nums[i] ^ nums[j];
        }
    }
}
