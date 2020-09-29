package com.shuangpeng;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Intersect {

    public static void quickSort(int[] array, int start, int end) {
        if (array == null || array.length == 0 || start >= end) {
            return;
        }
        int location = partition(array, start, end);
        quickSort(array, start, location - 1);
        quickSort(array, location + 1, end);
    }

    public static int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int index = start - 1;
        for (int i = start; i < end; i++) {
            if (array[i] <= pivot) {
                index++;
                swap(array, index, i);
            }
        }
        swap(array, index + 1, end);
        return index + 1;
    }

    public static void swap(int[] array, int i, int j) {
        if (i != j) {
            array[i] = array[j] - array[i];
            array[j] = array[j] - array[i];
            array[i] = array[j] + array[i];
        }
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null
                || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        quickSort(nums1, 0, nums1.length - 1);
        quickSort(nums2, 0, nums2.length - 1);
        int p1 = 0;
        int p2 = 0;
        int minLength = Math.min(nums1.length, nums2.length);
        int[] temp = new int[minLength];
        int count = 0;
        while (p1 < nums1.length && p2 < nums2.length) {
            if (nums1[p1] == nums2[p2]) {
                temp[count++] = nums1[p1];
                p1++;
                p2++;
            } else if (nums1[p1] < nums2[p2]) {
                p1++;
            } else {
                p2++;
            }
        }
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = temp[i];
        }
        return result;
    }

    public int[] intersect0(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null
                || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            map1.compute(nums1[i], (k, v) -> v == null ? 1 : v + 1);
        }
        for (int i = 0; i < nums2.length; i++) {
            map2.compute(nums2[i], (k, v) -> v == null ? 1 : v + 1);
        }
        Iterator<Map.Entry<Integer, Integer>> iterator = map1.entrySet().iterator();
        int minLength = Math.min(nums1.length, nums2.length);
        int[] temp = new int[minLength];
        int count = 0;
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            int key = entry.getKey();
            if (map2.containsKey(key)) {
                int minCount = Math.min(map2.get(key), entry.getValue());
                for (int i = 0; i < minCount; i++) {
                    temp[count++] = key;
                }
                map2.remove(key);
            }
        }
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = temp[i];
        }
        return result;
    }
}
