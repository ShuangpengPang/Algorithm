package com.shuangpeng.competition;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Solution a = new Solution();
        System.err.println(a.checkPowersOfThree(21));
    }

    public int nearestValidPoint(int x, int y, int[][] points) {
        int answer = -1;
        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < points.length; i++) {
            int[] coord = points[i];
            if (coord[0] == x || coord[1] == y) {
                int d = Math.abs(x - coord[0]) + Math.abs(y - coord[1]);
                if (d < distance) {
                    distance = d;
                    answer = i;
                }
            }
        }
        return answer;
    }

    public boolean checkPowersOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n != 1) {
            if (n % 3 == 0) {
                n /= 3;
            } else if ((n - 1) % 3 == 0) {
                n = (n - 1) / 3;
            } else {
                return false;
            }
        }
        return true;
    }

    public int beautySum(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int[] array = new int[26];
        int n = s.length();
        int answer = 0;
        for (int i = 0; i < n; i++) {
            array = new int[26];
            int min = 1, max = 1;
            int mChar = s.charAt(i) - 'a';
            array[mChar] = 1;
            for (int j = i + 1; j < n; j++) {
                int c = s.charAt(j) - 'a';
                array[c]++;
                max = Math.max(max, array[c]);
                if (c == mChar) {
                    min++;
                    for (int k = 0; k < array.length; k++) {
                        if (array[k] > 0 && array[k] < min) {
                            mChar = k;
                            min = array[k];
                        }
                    }
                } else if (array[c] < min) {
                    mChar = c;
                    min = array[c];
                }
                answer += (max - min);
            }
        }
        return answer;
    }

    public int[] countPairs(int n, int[][] edges, int[] queries) {
        int length = queries.length;
        int[] answer = new int[length];
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0] - 1;
            int v = edge[1] - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }
        List<Integer> list = new ArrayList<>(n * (n - 1) / 2);
        for (int u = 0; u < n; u++) {
            int e = graph.get(u).size();
            for (int v = u + 1; v < n; v++) {
                int b = e;
                List<Integer> l = graph.get(v);
                for (int a : l) {
                    if (a != u) {
                        b++;
                    }
                }
                list.add(b);
            }
        }
        Collections.sort(list);
        int size = list.size();
        for (int i = 0; i < queries.length; i++) {
            answer[i] = size - binarySearch(list, queries[i]);
        }
        return answer;
    }

    private int binarySearch(List<Integer> list, int target) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = (left + right) >> 1;
            int num = list.get(mid);
            if (num <= target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public boolean checkOnesSegment(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        int n = s.length();
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            if (flag && s.charAt(i) == '1') {
                return false;
            } else if (s.charAt(i) == '0') {
                flag = true;
            }
        }
        return true;
    }

    public int minElements(int[] nums, int limit, int goal) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        long diff = Math.abs(goal - sum);
        if (diff == 0) {
            return 0;
        }
        if (diff % limit == 0) {
            return (int) (diff / limit);
        }
        return (int) (diff / limit + 1);
    }

    public int minChanges(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            int j = i + k - 1;
            int result = 0;
            for (int m = i; m <= j; m++) {
                result = result ^ nums[m];
            }
            dp[i][j] = result == 0 ? 0 : 1;
        }

    }

    public int countRestrictedPaths(int n, int[][] edges) {
        if (n == 1) {
            return 1;
        }
        List<List<int[]>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int u = edge[0] - 1;
            int v = edge[1] - 1;
            graph.get(u).add(new int[]{v, edge[2]});
            graph.get(v).add(new int[]{u, edge[2]});
        }
        int[] minWeight = new int[n];
        Arrays.fill(minWeight, Integer.MAX_VALUE);
        minWeight[n - 1] = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(n - 1);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            List<int[]> neighbor = graph.get(u);
            for (int[] info : neighbor) {
                int v = info[0];
                int w = info[1];
                if (w < minWeight[v]) {
                    minWeight[v] = w;
                    queue.offer(v);
                }
            }
        }
        for () {
        }
    }
}
