package com.shuangpeng.Problem.p2401_2500;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2418SortThePeople（按身高排序）
 * @date 2023/4/25 10:12 AM
 */
public class Problem2418SortThePeople {

    public String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, (a, b) -> heights[b] - heights[a]);
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            String name = names[i];
            int j = i;
            while (i != ids[j]) {
                int id = ids[j];
                names[j] = names[id];
                visited[j] = true;
                j = id;
            }
            names[j] = name;
            visited[j] = true;
        }
        return names;
    }
}
