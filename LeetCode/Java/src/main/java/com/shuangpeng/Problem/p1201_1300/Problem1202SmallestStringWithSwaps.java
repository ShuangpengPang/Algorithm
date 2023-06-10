package com.shuangpeng.Problem.p1201_1300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1202SmallestStringWithSwaps（交换字符串中的元素）
 * @date 2023/6/10 8:57 PM
 */
public class Problem1202SmallestStringWithSwaps {

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        char[] cs = s.toCharArray();
        int n = cs.length;
        int[] parent = new int[n];
        Arrays.setAll(parent, i -> i);
        for (List<Integer> pair : pairs) {
            union(parent, pair.get(0), pair.get(1));
        }
        List<Character>[] list = new List[n];
        Arrays.setAll(list, i -> new ArrayList<>());
        for (int i = 0; i < n; i++) {
            int p = find(parent, i);
            list[p].add(cs[i]);
        }
        for (int i = 0; i < n; i++) {
            list[i].sort((a, b) -> b - a);
        }
        char[] ans = new char[n];
        for (int i = 0; i < n; i++) {
            List<Character> arr = list[find(parent, i)];
            ans[i] = arr.remove(arr.size() - 1);
        }
        return new String(ans);
    }

    private int find(int[] parent, int x) {
        return parent[x] = parent[x] == x ? x : find(parent, parent[x]);
    }

    private void union(int[] parent, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px != py) {
            parent[py] = px;
        }
    }
}
