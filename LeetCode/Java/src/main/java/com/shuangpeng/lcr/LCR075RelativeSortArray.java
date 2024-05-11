package com.shuangpeng.lcr;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR075RelativeSortArray（数组的相对排序）
 * @date 2024/5/11 12:13 PM
 */
public class LCR075RelativeSortArray {

    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int n1 = arr1.length, n2 = arr2.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n2; i++) {
            map.put(arr2[i], i);
        }
        Integer[] arr = new Integer[n1];
        Arrays.setAll(arr, i -> arr1[i]);
        Arrays.sort(arr, Comparator.comparingInt(a -> map.getOrDefault(a, n2 + a)));
        int[] ans = new int[n1];
        for (int i = 0; i < n1; i++) {
            ans[i] = arr[i];
        }
        return ans;
    }
}
