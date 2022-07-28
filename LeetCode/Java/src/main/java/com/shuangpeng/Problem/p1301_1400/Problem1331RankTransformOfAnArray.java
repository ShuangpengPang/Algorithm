package com.shuangpeng.Problem.p1301_1400;

import java.util.TreeMap;

/**
 * @Description: Problem1331RankTransformOfAnArray（数组序号转换）
 * @Date 2022/7/28 10:01 AM
 * @Version 1.0
 */
public class Problem1331RankTransformOfAnArray {

    public int[] arrayRankTransform(int[] arr) {
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
}
