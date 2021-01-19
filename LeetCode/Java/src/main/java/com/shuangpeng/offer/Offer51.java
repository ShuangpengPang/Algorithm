package com.shuangpeng.offer;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Offer51 {

    private int[] tree;
    private int[] map;

    public int reversePairs0(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        init(nums);
        int sum = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            int id = getId(nums[i]);
            sum += query(id - 1);
            update(id);
        }
        return sum;
    }

    private void update(int id) {
        while (id < tree.length) {
            tree[id]++;
            id += lowBit(id);
        }
    }

    private int query(int id) {
        int count = 0;
        while (id > 0) {
            count += tree[id];
            id -= lowBit(id);
        }
        return count;
    }

    private int lowBit(int x) {
        return x & (-x);
    }

    private int getId(int num) {
        return Arrays.binarySearch(map, num) + 1;
    }

    private void init(int[] nums) {
        map = new int[nums.length];
        tree = new int[nums.length + 1];
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int i = 0;
        for (int num : set) {
            map[i++] = num;
        }
        Arrays.sort(map);
    }


    private int answer = 0;

    public int reversePairs1(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        merge(nums, 0, nums.length - 1);
        return answer;
    }

    private int[] merge(int[] nums, int start, int end) {
        if (start >= end) {
            return new int[]{nums[start]};
        }
        int mid = (start + end) >>> 1;
        int[] left = merge(nums, start, mid);
        int[] right = merge(nums, mid + 1, end);
        int[] array = new int[end - start + 1];
        int pLeft = 0;
        int pRight = 0;
        while (pLeft < left.length && pRight < right.length) {
            if (left[pLeft] <= right[pRight]) {
                array[pLeft + pRight] = left[pLeft];
                pLeft++;
                answer += pRight;
            } else {
                array[pLeft + pRight] = right[pRight];
                pRight++;
            }
        }
        while (pLeft < left.length) {
            array[pLeft + pRight] = left[pLeft];
            pLeft++;
            answer += pRight;
        }
        while (pRight < right.length) {
            array[pLeft + pRight] = right[pRight];
            pRight++;
        }
        return array;
    }

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        int[] copy = new int[length];
        for (int i = 0; i < length; i++) {
            copy[i] = nums[i];
        }
        int[] temp = new int[length];
        return mergeAndCount(nums, 0, length - 1, temp);
    }

    private int mergeAndCount(int[] nums, int start, int end, int[] temp) {
        if (start >= end) {
            return 0;
        }
        int mid = start + (end - start) / 2;
        int leftCount = mergeAndCount(nums, start, mid, temp);
        int rightCount = mergeAndCount(nums, mid + 1, end, temp);
        if (nums[mid] <= nums[mid + 1]) {
            return leftCount + rightCount;
        }
        for (int i = start; i <= end; i++) {
            temp[i] = nums[i];
        }
        int j = start;
        int k = mid + 1;
        int count = 0;
        for (int s = start; s <= end; s++) {
            if (j == mid + 1) {
                nums[s] = temp[k++];
            } else if (k == end + 1) {
                nums[s] = temp[j++];
            } else if (temp[j] <= temp[k]) {
                nums[s] = temp[j++];
            } else {
                nums[s] = temp[k++];
                count += (mid - j + 1);
            }
        }
        return leftCount + rightCount + count;
    }




//    public int reversePairs(int[] nums) {
//        int len = nums.length;
//
//        if (len < 2) {
//            return 0;
//        }
//
//        int[] copy = new int[len];
//        for (int i = 0; i < len; i++) {
//            copy[i] = nums[i];
//        }
//
//        int[] temp = new int[len];
//        return reversePairs(copy, 0, len - 1, temp);
//    }
//
//    /**
//     * nums[left..right] 计算逆序对个数并且排序
//     *
//     * @param nums
//     * @param left
//     * @param right
//     * @param temp
//     * @return
//     */
//    private int reversePairs(int[] nums, int left, int right, int[] temp) {
//        if (left == right) {
//            return 0;
//        }
//
//        int mid = left + (right - left) / 2;
//        int leftPairs = reversePairs(nums, left, mid, temp);
//        int rightPairs = reversePairs(nums, mid + 1, right, temp);
//
//        if (nums[mid] <= nums[mid + 1]) {
//            return leftPairs + rightPairs;
//        }
//
//        int crossPairs = mergeAndCount(nums, left, mid, right, temp);
//        return leftPairs + rightPairs + crossPairs;
//    }
//
//    /**
//     * nums[left..mid] 有序，nums[mid + 1..right] 有序
//     *
//     * @param nums
//     * @param left
//     * @param mid
//     * @param right
//     * @param temp
//     * @return
//     */
//    private int mergeAndCount(int[] nums, int left, int mid, int right, int[] temp) {
//        for (int i = left; i <= right; i++) {
//            temp[i] = nums[i];
//        }
//
//        int i = left;
//        int j = mid + 1;
//
//        int count = 0;
//        for (int k = left; k <= right; k++) {
//
//            if (i == mid + 1) {
//                nums[k] = temp[j];
//                j++;
//            } else if (j == right + 1) {
//                nums[k] = temp[i];
//                i++;
//            } else if (temp[i] <= temp[j]) {
//                nums[k] = temp[i];
//                i++;
//            } else {
//                nums[k] = temp[j];
//                j++;
//                count += (mid - i + 1);
//            }
//        }
//        return count;
//    }
}
