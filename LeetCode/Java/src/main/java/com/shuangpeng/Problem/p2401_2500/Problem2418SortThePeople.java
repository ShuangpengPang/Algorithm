package com.shuangpeng.Problem.p2401_2500;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2418SortThePeople（按身高排序）
 * @date 2023/4/25 10:12 AM
 */
public class Problem2418SortThePeople {

    public String[] sortPeople0(String[] names, int[] heights) {
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

    public String[] sortPeople1(String[] names, int[] heights) {
        quickSort(names, heights, 0, names.length - 1);
        return names;
    }

    public void quickSort(String[] names, int[] heights, int start, int end) {
        if (start >= end) {
            return;
        }
        int idx = start + (int) (Math.random() * (end - start + 1));
        int pivot = heights[idx];
        swap(names, heights, idx, end);
        int p = start;
        for (int i = start; i < end; i++) {
            if (heights[i] > pivot) {
                swap(names, heights, p, i);
                p++;
            }
        }
        swap(names, heights, p, end);
        quickSort(names, heights, start, p - 1);
        quickSort(names, heights, p + 1, end);
    }

    private void swap(String[] names, int[] heights, int i, int j) {
        if (i != j) {
            int t1 = heights[i];
            heights[i] = heights[j];
            heights[j] = t1;
            String t2 = names[i];
            names[i] = names[j];
            names[j] = t2;
        }
    }

    public String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;
        mergeSort(heights, names, new String[n], new int[n], 0, n - 1);
        return names;
    }

    private void mergeSort(int[] heights, String[] names, String[] tmpNames, int[] tmpHeights, int start, int end) {
        if (start == end) {
            return;
        }
        int mid = start + (end - start >> 1);
        mergeSort(heights, names, tmpNames, tmpHeights, start, mid);
        mergeSort(heights, names, tmpNames, tmpHeights, mid + 1, end);
        for (int i = start, j = start, k = mid + 1; i <= end; i++) {
            if (j > mid) {
                tmpHeights[i] = heights[k];
                tmpNames[i] = names[k];
                k++;
            } else if (k > end) {
                tmpHeights[i] = heights[j];
                tmpNames[i] = names[j];
                j++;
            } else if (heights[j] > heights[k]) {
                tmpHeights[i] = heights[j];
                tmpNames[i] = names[j];
                j++;
            } else {
                tmpHeights[i] = heights[k];
                tmpNames[i] = names[k];
                k++;
            }
        }
        int count = end - start + 1;
        System.arraycopy(tmpHeights, start, heights, start, count);
        System.arraycopy(tmpNames, start, names, start, count);
    }
}
