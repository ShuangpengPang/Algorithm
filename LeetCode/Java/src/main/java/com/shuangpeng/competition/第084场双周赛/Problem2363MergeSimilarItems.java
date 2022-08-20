package com.shuangpeng.competition.第084场双周赛;

import java.util.*;

/**
 * @Description: Problem2363MergeSimilarItems（合并相似的物品）
 * @Date 2022/8/20 4:53 PM
 * @Version 1.0
 */
public class Problem2363MergeSimilarItems {

    // 比赛时写法
    public List<List<Integer>> mergeSimilarItems0(int[][] items1, int[][] items2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] arr : items1) {
            int v = arr[0], w = arr[1];
            map.put(v, w);
        }
        for (int[] arr : items2) {
            int v = arr[0], w = arr[1];
            map.put(v, map.getOrDefault(v, 0) + w);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int v : map.keySet()) {
            List<Integer> list = new ArrayList<>();
            list.add(v);
            list.add(map.get(v));
            ans.add(list);
        }
        ans.sort((a, b) -> a.get(0) - b.get(0));
        return ans;
    }

    public List<List<Integer>> mergeSimilarItems(int[][] items1, int[][] items2) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] item : items1) {
            map.put(item[0], map.getOrDefault(item[0], 0) + item[1]);
        }
        for (int[] item : items2) {
            map.put(item[0], map.getOrDefault(item[0], 0) + item[1]);
        }
        List<List<Integer>> ans = new ArrayList<>(map.size());
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            ans.add(Arrays.asList(entry.getKey(), entry.getValue()));
        }
        return ans;
    }
}
