package com.shuangpeng.Problem.p0601_0700;

import java.util.*;

public class Problem0679Game24 {

    public boolean judgePoint24_0(int[] cards) {
        Arrays.sort(cards);
        int n = cards.length;
        List<List<Integer>> lists = new ArrayList<>();
        arrange(cards, new boolean[n], new ArrayList<>(), lists);
        for (List<Integer> list : lists) {
            List<int[]> results = merge(list, 0, cards.length - 1);
            for (int[] pair : results) {
                if (pair[0] == 24 && pair[1] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    private void arrange(int[] cards, boolean[] visited, List<Integer> list, List<List<Integer>> ans) {
        int n = cards.length;
        int size = list.size();
        if (n == size) {
            ans.add(new ArrayList<>(list));
        }
        for (int i = 0; i < n; ++i) {
            if (!visited[i] && (i == 0 || visited[i - 1] || cards[i] != cards[i - 1])) {
                visited[i] = true;
                list.add(cards[i]);
                arrange(cards, visited, list, ans);
                list.remove(list.size() - 1);
                visited[i] = false;
            }
        }
    }

    private List<int[]> merge(List<Integer> list, int s, int e) {
        List<int[]> ans = new ArrayList<>();
        if (s == e) {
            ans.add(new int[]{list.get(s), 1});
            return ans;
        }
        final char[] chars = {'+', '-', '*', '/'};
        Set<Integer> set = new HashSet<>();
        final int N = 10000;
        for (int i = s; i < e; ++i) {
            List<int[]> left = merge(list, s, i);
            List<int[]> right = merge(list, i + 1, e);
            for (int[] leftPart : left) {
                for (int[] rightPart : right) {
                    for (char c : chars) {
                        int[] pair = getResult(leftPart, rightPart, c);
                        if (pair != null) {
                            set.add(pair[0] * N + pair[1]);
                        }
                    }
                }
            }
        }
        for (int hash : set) {
            ans.add(new int[]{hash / N, hash % N});
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private int[] getResult(int[] leftPart, int[] rightPart, char c) {
        int x1 = leftPart[0], y1 = leftPart[1];
        int x2 = rightPart[0], y2 = rightPart[1];
        int a = 0, b = 0;
        if (c == '+') {
            a = x1 * y2 + x2 * y1;
            b = y1 * y2;
        } else if (c == '-') {
            a = x1 * y2 - x2 * y1;
            b = y1 * y2;
        } else if (c == '*') {
            a = x1 * x2;
            b = y1 * y2;
        } else if (c == '/') {
            if (x2 == 0) {
                return null;
            }
            a = x1 * y2;
            b = y1 * x2;
        }
        if (a == 0) {
            b = 1;
        }
        int g = gcd(a, b);
        return new int[]{a / g, b / g};
    }

    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for (int num : nums) {
            list.add((double) num);
        }
        return solve(list);
    }

    private boolean solve(List<Double> list) {
        final double EPSILON = 1e-6;
        final int ADD = 0, MULTIPLY = 1, SUBTRACT = 2, DIVIDE = 3;
        final int TARGET = 24;
        int n = list.size();
        if (n == 1) {
            return Math.abs(list.get(0) - TARGET) < EPSILON;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != j) {
                    List<Double> list2 = new ArrayList<>();
                    for (int k = 0; k < n; ++k) {
                        if (k != i && k != j) {
                            list2.add(list.get(k));
                        }
                    }
                    for (int k = 0; k < 4; ++k) {
                        if (k < 2 && i > j) {
                            continue;
                        }
                        if (k == ADD) {
                            list2.add(list.get(i) + list.get(j));
                        } else if (k == SUBTRACT) {
                            list2.add(list.get(i) - list.get(j));
                        } else if (k == MULTIPLY) {
                            list2.add(list.get(i) * list.get(j));
                        } else if (k == DIVIDE) {
                            if (Math.abs(list.get(j)) < EPSILON) {
                                continue;
                            }
                            list2.add(list.get(i) / list.get(j));
                        }
                        if (solve(list2)) {
                            return true;
                        }
                        list2.remove(list2.size() - 1);
                    }
                }
            }
        }
        return false;
    }
}
