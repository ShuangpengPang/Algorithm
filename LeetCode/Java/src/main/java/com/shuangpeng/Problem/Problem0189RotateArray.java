package com.shuangpeng.Problem;

public class Problem0189RotateArray {

    // 1 2 3 4 5 6 7
    // 6 7 3 4 5 1 2
    // 4 5 3 6 7 1 2

    public void rotate0(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int length = nums.length;
        k %= length;
        if (k == 0) {
            return;
        }
        int count = length;
        int i = length - 1;
        while (k > 0) {
            while (i > 0) {
                int j = i - k;
                if (j >= 0) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                } else {
                    break;
                }
                i--;
            }
            if (count % k == 0) {
                break;
            }
            i = k - 1;
            k = k - count % k;
            count = i + 1;
        }
    }

    public void rotate1(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
        }
        k %= nums.length;
        if (k == 0) {
            return;
        }
        for (int i = 0; i < k; i++) {
            int last = nums[nums.length - 1];
            for (int j = nums.length - 1; j > 0; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = last;
        }
    }

    public void rotate2(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int length = nums.length;
        k %= length;
        if (k == 0) {
            return;
        }
        int[] answer = new int[length];
        for (int i = 0; i < length; i++) {
            answer[i] = nums[(i + length - k) % length];
        }
        for (int i = 0; i < length; i++) {
            nums[i] = answer[i];
        }
    }

    public void rotate3(int[] nums, int k) {
        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            a[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = a[i];
        }
    }

    public void rotate4(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }

    public void rotate5(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int length = nums.length;
        k %= length;
        if (k == 0) {
            return;
        }
        int count = 0;
        for (int start = 0; count < length; start++) {
            int current = start;
            int previous = nums[current];
            do {
                int next = (current + k) % length;
                int temp = nums[next];
                nums[next] = previous;
                previous = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }

    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return;
        }
        int length = nums.length;
        k %= length;
        reverse(nums, 0, length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, length - 1);
    }

    public void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
