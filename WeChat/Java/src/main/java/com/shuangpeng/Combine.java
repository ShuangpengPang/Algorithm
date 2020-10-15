package com.shuangpeng;

import java.util.ArrayList;
import java.util.List;

// 链接：https://mp.weixin.qq.com/s/2p95pKpX-GgC3NBBN2jP2g
public class Combine {
    List<List<Integer>> result;

    public void combine(int n, int k) {
        if (n <= 0 || k <= 0 || k > n) {
            return;
        }
        int num = 1;
        for (int i = 0; i < k; i++) {
            num = num * (n - i) / (i + 1);
        }
        result = new ArrayList<>(num);
//        backtrack(n, k, 1, new ArrayList<>());
        backtrack1(n, k, new ArrayList<>());
    }

    public static void main(String[] args) {
        Combine combine = new Combine();
        combine.combine(4, 2);
        System.err.print('[');
        for (int i = 0; i < combine.result.size(); i++) {
            List<Integer> set = combine.result.get(i);
            if (i != 0) {
                System.err.print(", ");
            }
            System.err.print('[');
            for (int j = 0; j < set.size(); j++) {
                if (j != 0) {
                    System.err.print(", ");
                }
                System.err.print(set.get(j));
            }
            System.err.print(']');
        }
        System.err.print(']');
        System.err.println();
    }

    public void backtrack0(int n, int k, List<Integer> set) {
        if (k > n) {
            return;
        }
        if (k == 0) {
            List<Integer> copy = new ArrayList<>(set.size());
            for (int i = 0; i < set.size(); i++) {
                copy.add(set.get(i));
            }
            result.add(copy);
            return;
        }
        set.add(n);
        backtrack0(n - 1, k - 1, set);
        set.remove(set.size() - 1);
        backtrack0(n - 1, k, set);
    }

    public void backtrack(int n, int k, int start, List<Integer> set) {
        if (k > n || k < 0) {
            return;
        }
        if (k == 0) {
            List<Integer> copy = new ArrayList<>(set.size());
            for (int i = 0; i < set.size(); i++) {
                copy.add(set.get(i));
            }
            result.add(copy);
            return;
        }
        for (int i = start; i <= n; i++) {
            set.add(i);
            backtrack(n, k - 1, i + 1, set);
            set.remove(set.size() - 1);
        }
    }

    public void backtrack1(int n, int k, List<Integer> set) {
        if (k > n || k < 0) {
            return;
        }
        if (k == 0) {
            List<Integer> copy = new ArrayList<>(set.size());
            for (int i = 0; i < set.size(); i++) {
                copy.add(set.get(i));
            }
            result.add(copy);
            return;
        }
        for (int i = n; i > 0; i--) {
            set.add(i);
            backtrack1(i - 1, k - 1, set);
            set.remove(set.size() - 1);
        }
    }
}
