package com.shuangpeng.Problem.p0701_0800;

import java.util.*;

public class Problem0768MaxChunksToMakeSortedII {

    public int maxChunksToSorted0(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n + 1];
        left[0] = arr[0];
        for (int i = 1; i < n; ++i) {
            left[i] = Math.max(left[i - 1], arr[i]);
        }
        right[n] = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; --i) {
            right[i] = Math.min(right[i + 1], arr[i]);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (left[i] <= right[i + 1]) {
                ++ans;
            }
        }
        return ans;
    }

    public int maxChunksToSorted1(int[] arr) {
        int[] expect = arr.clone();
        Arrays.sort(expect);
        Map<Integer, Integer> count = new HashMap<>();
        int n = arr.length;
        int ans = 0;
        int c = 0;
        for (int i = 0; i < n; ++i) {
            int x = arr[i], y = expect[i];
            int c1 = count.getOrDefault(x, 0);
            if (c1 == 0) {
                ++c;
            }
            count.put(x, c1 + 1);
            int c2 = count.getOrDefault(y, 0);
            if (c2 == 1) {
                --c;
            }
            count.put(y, c2 - 1);
            if (c == 0) {
                ++ans;
            }
        }
        return ans;
    }

    public int maxChunksToSorted2(int[] arr) {
        int[] expect = arr.clone();
        Arrays.sort(expect);
        Map<Integer, Integer> count = new HashMap<>();
        int n = arr.length;
        int ans = 0, nonZero = 0;
        for (int i = 0; i < n; ++i) {
            int x = arr[i], y = expect[i];
            int c1 = count.getOrDefault(x, 0);
            count.put(x, c1 + 1);
            if (c1 == 0) {
                ++nonZero;
            } else if (c1 == -1) {
                --nonZero;
            }
            int c2 = count.getOrDefault(y, 0);
            count.put(y, c2 - 1);
            if (c2 == 0) {
                ++nonZero;
            } else if (c2 == 1) {
                --nonZero;
            }
            if (nonZero == 0) {
                ++ans;
            }
        }
        return ans;
    }

    public int maxChunksToSorted3(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> count = new HashMap<>();
        List<Pair> list = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            int index = count.getOrDefault(arr[i], 0) + 1;
            count.put(arr[i], index);
            list.add(new Pair(arr[i], index));
        }
        List<Pair> expect = new ArrayList<>(list);
        expect.sort(Pair::compare);
        Pair maxValue = list.get(0);
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (list.get(i).compare(maxValue) > 0) {
                maxValue = list.get(i);
            }
            if (maxValue.compare(expect.get(i)) == 0) {
                ++ans;
            }
        }
        return ans;
    }

    class Pair {
        int val;
        int index;

        Pair(int val, int index) {
            this.val = val;
            this.index = index;
        }

        int compare(Pair other) {
            if (this.val != other.val) {
                return this.val - other.val;
            }
            return this.index - other.index;
        }
    }

    public int maxChunksToSorted(int[] arr) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            if (!stack.isEmpty() && stack.peekLast() > arr[i]) {
                while (!stack.isEmpty() && stack.peekLast() >= arr[i]) {
                    stack.pollLast();
                }
            }
        }
        return stack.size();
    }
}
