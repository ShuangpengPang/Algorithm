package com.shuangpeng.Problem;

import java.util.*;

public class Problem0015ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new ArrayList<>();
        }

        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length - 2; i++) {
            int data = nums[i];
            if (!set.contains(data)) {
                List<List<Integer>> lists = twoSum(nums, i + 1, -data);
                for (int j = 0; j < lists.size(); j++) {
                    List<Integer> item = lists.get(j);
                    if (!set.contains(item.get(0)) && !set.contains(item.get(1))) {
                        item.add(data);
                        result.add(item);
                    }
                }
                set.add(data);
            }
        }
        return result;
    }

    public List<List<Integer>> twoSum(int[] nums, int start, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int length = nums.length;
        Map<Integer, Boolean> map = new HashMap<>();
        for (int i = start; i < length; i++) {
            int data = nums[i];
            int other = target - data;
            if (map.containsKey(other)) {
                if (!map.get(other)) {
                    List<Integer> list = new ArrayList<>();
                    list.add(other);
                    list.add(data);
                    result.add(list);
                    map.put(data, true);
                    map.put(other, true);
                }
            } else if (!map.containsKey(data)) {
                map.put(data, false);
            }
        }
        return result;
    }

    public void quickSort(int[] array, int start, int end) {
        if (array == null || array.length <= 1 || start >= end) {
            return;
        }

        int p = partition(array, start, end);
        quickSort(array, start,  p- 1);
        quickSort(array, p + 1, end);
    }

    public int partition0(int[] array, int start, int end) {
        int pivot = array[end];

        // location为当前扫描过的数当中
        int location = -1;
        for (int i = start; i < end; i++) {
            if (array[i] <= pivot) {
                location++;
                if (location < i) {
                    swap(array, location, i);
                }
            }
        }
        int p = location + 1;
        swap(array, p, array.length - 1);
        return p;
    }

    public int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int i = start;
        int j = end;


        while (i < j) {
            while (array[i] <= pivot) {
                i++;
            }
            // 此处array[i] > pivot
            while (array[j] >= pivot) {
                j--;
            }
            // 此处array[j] < pivot
            if (i < j) {
                swap(array, i, j);
                // 此处 array[i] < pivot, array[j] > pivot
            }
        }
        // 此处array[i] > pivot
        // 此处array[j] < pivot
        // j <= i;
        swap(array, i, end);
        return i;
    }

    public void swap(int[] array, int i, int j) {
        if (i != j) {
            array[i] = array[j] - array[i];
            array[j] = array[j] - array[i];
            array[i] = array[j] + array[i];
        }
    }
}
