package com.shuangpeng.Problem.p1201_1300;

import java.util.*;

/**
 * @Description: Problem1298MaximumCandiesYouCanGetFromBoxes（你能从盒子里获得的最大糖果数）
 * @Date 2022/7/29 11:08 PM
 * @Version 1.0
 */
public class Problem1298MaximumCandiesYouCanGetFromBoxes {

    public int maxCandies0(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        Set<Integer> boxSet = new HashSet<>(), keySet = new HashSet<>();
        for (int b : initialBoxes) {
            boxSet.add(b);
        }
        int ans = 0;
        boolean flag = true;
        while (flag) {
            flag = false;
            List<Integer> list = new ArrayList<>(boxSet);
            for (int b : list) {
                if (status[b] == 1 || status[b] == 0 && keySet.contains(b)) {
                    flag = true;
                    status[b] = -1;
                    ans += candies[b];
                    candies[b] = 0;
                    for (int box : containedBoxes[b]) {
                        boxSet.add(box);
                    }
                    boxSet.remove(b);
                    for (int key : keys[b]) {
                        keySet.add(key);
                    }
                    if (keySet.contains(b)) {
                        keySet.remove(b);
                    }
                }
            }
        }
        return ans;
    }

    public int maxCandies1(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int n = status.length;
        boolean[] hasOwn = new boolean[n], hasOpen = new boolean[n], hasKey = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        for (int b : initialBoxes) {
            if (status[b] == 1 && !hasOpen[b]) {
                q.offer(b);
                hasOpen[b] = true;
            }
            hasOwn[b] = true;
        }
        int ans = 0;
        while (!q.isEmpty()) {
            for (int i = q.size() - 1; i >= 0; i--) {
                int b = q.poll();
                ans += candies[b];
                for (int c : containedBoxes[b]) {
                    hasOwn[c] = true;
                }
                for (int k : keys[b]) {
                    hasKey[k] = true;
                }
            }
            for (int i = 0; i < n; i++) {
                if (!hasOpen[i] && hasOwn[i] && (hasKey[i] || status[i] == 1)) {
                    hasOpen[i] = true;
                    q.offer(i);
                }
            }
        }
        return ans;
    }

    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        int n = status.length;
        boolean[] hasOwn = new boolean[n], canOpen = new boolean[n], hasOpen = new boolean[n];
        for (int i = 0; i < n; i++) {
            canOpen[i] = status[i] == 1;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i : initialBoxes) {
            hasOwn[i] = true;
            if (!hasOpen[i] && canOpen[i]) {
                hasOpen[i] = true;
                q.offer(i);
            }
        }
        int ans = 0;
        while (!q.isEmpty()) {
            int b = q.poll();
            ans += candies[b];
            for (int k : keys[b]) {
                canOpen[k] = true;
                if (!hasOpen[k] && hasOwn[k]) {
                    hasOpen[k] = true;
                    q.offer(k);
                }
            }
            for (int c : containedBoxes[b]) {
                hasOwn[c] = true;
                if (!hasOpen[c] && canOpen[c]) {
                    hasOpen[c] = true;
                    q.offer(c);
                }
            }
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem1298MaximumCandiesYouCanGetFromBoxes a = new Problem1298MaximumCandiesYouCanGetFromBoxes();
//        int[] status = {1, 0, 1, 0};
//        int[] candies = {7, 5, 4, 100};
//        int[][] keys = {{}, {}, {1}, {}};
//        int[][] containedBoxes = {{1, 2}, {3}, {}, {}};
//        int[] init = {0};
//        a.maxCandies(status, candies, keys, containedBoxes, init);
//    }
}
