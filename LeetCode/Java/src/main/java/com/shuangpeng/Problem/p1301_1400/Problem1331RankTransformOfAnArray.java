package com.shuangpeng.Problem.p1301_1400;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description: Problem1331RankTransformOfAnArray（数组序号转换）
 * @Date 2022/7/28 10:01 AM
 * @Version 1.0
 */
public class Problem1331RankTransformOfAnArray {

    public int[] arrayRankTransform0(int[] arr) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : arr) {
            map.put(num, 1);
        }
        int rank = 1;
        for (int num : map.keySet()) {
            map.put(num, rank++);
        }
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            arr[i] = map.get(arr[i]);
        }
        return arr;
    }

    public int[] arrayRankTransform(int[] arr) {
        int[] copy = arr.clone();
        Arrays.sort(copy);
        Map<Integer, Integer> map = new HashMap<>();
        int rank = 1;
        int n = copy.length;
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(copy[i])) {
                map.put(copy[i], rank++);
            }
        }
        for (int i = 0; i < n; i++) {
            copy[i] = map.get(arr[i]);
        }
        return copy;
    }
}


