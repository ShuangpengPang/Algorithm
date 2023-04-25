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
        for (int i = 0; i < n; i++) {
            int id = ids[i];
            if (i != id) {
                String name = names[i];
                names[i] = names[id];
                names[id] = name;
            }
        }
        return names;
    }
}
