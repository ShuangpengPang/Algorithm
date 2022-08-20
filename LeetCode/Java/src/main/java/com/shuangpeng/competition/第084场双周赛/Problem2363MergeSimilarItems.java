package com.shuangpeng.competition.第084场双周赛;

import java.util.*;

/**
 * @Description: Problem2363MergeSimilarItems（合并相似的物品）
 * @Date 2022/8/20 4:53 PM
 * @Version 1.0
 */
public class Problem2363MergeSimilarItems {

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
