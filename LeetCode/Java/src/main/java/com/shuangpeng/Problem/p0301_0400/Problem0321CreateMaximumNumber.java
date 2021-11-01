package com.shuangpeng.Problem.p0301_0400;

public class Problem0321CreateMaximumNumber {

    public int[] maxNumber0(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] maxSubsequence = new int[k];
        int start = Math.max(0, k - n), end = Math.min(k, m);
        for (int i = start; i <= end; i++) {
            int[] subsequence1 = maxSubsequence(nums1, i);
            int[] subsequence2 = maxSubsequence(nums2, k - i);
            int[] curMaxSubsequence = merge(subsequence1, subsequence2);
            if (compare(curMaxSubsequence, 0, maxSubsequence, 0) > 0) {
                System.arraycopy(curMaxSubsequence, 0, maxSubsequence, 0, k);
            }
        }
        return maxSubsequence;
    }

    public int[] maxSubsequence(int[] nums, int k) {
        int length = nums.length;
        int[] stack = new int[k];
        int top = -1;
        int remain = length - k;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            while (top >= 0 && stack[top] < num && remain > 0) {
                top--;
                remain--;
            }
            if (top < k - 1) {
                stack[++top] = num;
            } else {
                remain--;
            }
        }
        return stack;
    }

    public int[] merge(int[] subsequence1, int[] subsequence2) {
        int x = subsequence1.length, y = subsequence2.length;
        if (x == 0) {
            return subsequence2;
        }
        if (y == 0) {
            return subsequence1;
        }
        int mergeLength = x + y;
        int[] merged = new int[mergeLength];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < mergeLength; i++) {
            if (compare(subsequence1, index1, subsequence2, index2) > 0) {
                merged[i] = subsequence1[index1++];
            } else {
                merged[i] = subsequence2[index2++];
            }
        }
        return merged;
    }

    public int compare(int[] subsequence1, int index1, int[] subsequence2, int index2) {
        int x = subsequence1.length, y = subsequence2.length;
        while (index1 < x && index2 < y) {
            int difference = subsequence1[index1] - subsequence2[index2];
            if (difference != 0) {
                return difference;
            }
            index1++;
            index2++;
        }
        return (x - index1) - (y - index2);
    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int start = k - Math.min(n, k), end = Math.min(m, k);
        int[] answer = new int[k];
        for (int i = start; i <= end; i++) {
            int[] sequence = mergeSequence(subsequence(nums1, i), subsequence(nums2, k - i));
            answer = compareSequence(answer, sequence);
        }
        return answer;
    }

    private int[] subsequence(int[] nums, int length) {
        int[] stack = new int[length];
        int top = -1, remain = nums.length - length;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            while (top >= 0 && stack[top] < num && remain > 0) {
                top--;
                remain--;
            }
            if (top < length - 1) {
                stack[++top] = num;
            } else {
                remain--;
            }
        }
        return stack;
    }

    private int[] mergeSequence(int[] sequence1, int[] sequence2) {
        int n1 = sequence1.length, n2 = sequence2.length;
        if (n1 == 0) {
            return sequence2;
        }
        if (n2 == 0) {
            return sequence1;
        }
        int index1 = 0, index2 = 0;
        int[] sequence = new int[n1 + n2];
        for (int i = 0; i < sequence.length; i++) {
            if (compare(sequence1, sequence2, index1, index2) > 0) {
                sequence[i] = sequence1[index1++];
            } else {
                sequence[i] = sequence2[index2++];
            }
        }
        return sequence;
    }

    private int compare(int[] sequence1, int[] sequence2, int index1, int index2) {
        int n1 = sequence1.length, n2 = sequence2.length;
        if (index1 >= n1) {
            return -1;
        }
        if (index2 >= n2) {
            return 1;
        }
        if (sequence1[index1] > sequence2[index2]) {
            return 1;
        }
        if (sequence1[index1] < sequence2[index2]) {
            return -1;
        }
        return compare(sequence1, sequence2, index1 + 1, index2 + 1);
    }

    private int[] compareSequence(int[] sequence1, int[] sequence2) {
        for (int i = 0; i < sequence1.length; i++) {
            if (sequence1[i] > sequence2[i]) {
                return sequence1;
            } else if (sequence1[i] < sequence2[i]) {
                return sequence2;
            }
        }
        return sequence1;
    }
}
