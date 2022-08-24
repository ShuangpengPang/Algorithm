package com.shuangpeng.Problem.p1501_1600;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: Problem1505MinimumPossibleIntegerAfterAtMostKAdjacentSwapsOnDigits（最多K次交换相邻数位后得到的最小整数）
 * @Date 2022/8/24 11:37 AM
 * @Version 1.0
 */
public class Problem1505MinimumPossibleIntegerAfterAtMostKAdjacentSwapsOnDigits {

    public String minInteger0(String num, int k) {
        List<Integer>[] pos = new List[10];
        Arrays.setAll(pos, e -> new LinkedList<>());
        char[] arr = num.toCharArray();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            pos[arr[i] - '0'].add(i + 1);
        }
        int[] tree = new int[n + 1];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n && k > 0; i++) {
            int d = 0, dis = getDistance(pos, tree, i + 1, d);
            while (dis > k) {
                d++;
                dis = getDistance(pos, tree, i + 1, d);
            }
            k -= dis;
            arr[i] = (char) ('0' + d);
            sb.append((char) ('0' + d));
            add(tree, pos[d].remove(0));
        }
        for (int d = 0; d < 10; d++) {
            for (int p : pos[d]) {
                arr[p + getCount(tree, p) - 1] = (char) ('0' + d);
            }
        }
        return new String(arr);
    }

    private int getDistance(List<Integer>[] pos, int[] tree, int p, int d) {
        if (pos[d].isEmpty()) {
            return Integer.MAX_VALUE;
        }
        return pos[d].get(0) - p + getCount(tree, pos[d].get(0));
    }

    private int getCount(int[] tree, int p) {
        return getSum(tree, tree.length - 1) - getSum(tree, p);
    }

    private int getSum(int[] tree, int x) {
        int ans = 0;
        while (x > 0) {
            ans += tree[x];
            x -= x & -x;
        }
        return ans;
    }

    private void add(int[] tree, int x) {
        while (x < tree.length) {
            tree[x]++;
            x += x & -x;
        }
    }

    public String minInteger(String num, int k) {
        List<Integer>[] pos = new List[10];
        Arrays.setAll(pos, e -> new LinkedList<>());
        char[] cs = num.toCharArray();
        int n = cs.length;
        for (int i = 0; i < n; i++) {
            pos[cs[i] - '0'].add(i + 1);
        }
        int[] tree = new int[n + 1];
        for (int i = 0; i < n; i++) {
            for (int d = 0; d < 10; d++) {
                int dis = distance(pos, tree, i + 1, d);
                if (dis <= k) {
                    k -= dis;
                    update(tree, pos[d].remove(0));
                    cs[i] = (char) (d + '0');
                    break;
                }
            }
        }
        return new String(cs);
    }

    private int distance(List<Integer>[] pos, int[] tree, int idx, int d) {
        if (pos[d].isEmpty()) {
            return Integer.MAX_VALUE;
        }
        int p = pos[d].get(0);
        return p + query(tree, tree.length - 1) - query(tree, p) - idx;
    }

    private int query(int[] tree, int x) {
        int ans = 0;
        while (x > 0) {
            ans += tree[x];
            x -= x & -x;
        }
        return ans;
    }

    private void update(int[] tree, int x) {
        while (x < tree.length) {
            tree[x]++;
            x += x & -x;
        }
    }
}