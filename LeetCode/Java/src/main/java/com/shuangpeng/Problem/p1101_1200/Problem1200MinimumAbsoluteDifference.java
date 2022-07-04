package com.shuangpeng.Problem.p1101_1200;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: Problem1200MinimumAbsoluteDifference（最小绝对差）
 * @Date 2022/7/4 9:55 AM
 * @Version 1.0
 */
public class Problem1200MinimumAbsoluteDifference {

    public List<List<Integer>> minimumAbsDifference0(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int diff = Integer.MAX_VALUE;
        for (int i = 0; i < n - 1; i++) {
            diff = Math.min(diff, arr[i + 1] - arr[i]);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            if (arr[i + 1] - arr[i] == diff) {
                List<Integer> list = new ArrayList<>();
                list.add(arr[i]);
                list.add(arr[i + 1]);
                ans.add(list);
            }
        }
        return ans;
    }

    public List<List<Integer>> minimumAbsDifference1(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int diff = Integer.MAX_VALUE;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            int d = arr[i + 1] - arr[i];
            if (d < diff || d == diff) {
                if (d < diff) {
                    diff = d;
                    ans.clear();
                }
                List<Integer> list = new ArrayList<>(2);
                list.add(arr[i]);
                list.add(arr[i + 1]);
                ans.add(list);
            }
        }
        return ans;
    }

    public List<List<Integer>> minimumAbsDifference2(int[] arr) {
        return new java.util.AbstractList<List<Integer>>() {
            private int[] buffer;
            private int size;
            private boolean initialed;
            private int min = Integer.MAX_VALUE;
            private void init() {
                if (initialed) {
                    return;
                }
                buffer = new int[arr.length];
                Arrays.sort(arr);
                for (int i = 1; i < arr.length; i++) {
                    int diff = arr[i] - arr[i - 1];
                    if (diff > min) {
                        continue;
                    } else if (diff < min) {
                        size = 0;
                        min = diff;
                    }
                    buffer[size++] = arr[i - 1];
                }
                initialed = true;
            }
            @Override
            public List<Integer> get(int index) {
                init();
                return Arrays.asList(buffer[index], buffer[index] + min);
            }

            @Override
            public int size() {
                init();
                return size;
            }
        };
    }

    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int diff = Integer.MAX_VALUE;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            int d = arr[i + 1] - arr[i];
            if (d < diff) {
                diff = d;
                ans.clear();
            }
            if (d == diff) {
                List<Integer> list = new ArrayList<>(2);
                list.add(arr[i]);
                list.add(arr[i + 1]);
                ans.add(list);
            }
        }
        return ans;
    }
}
