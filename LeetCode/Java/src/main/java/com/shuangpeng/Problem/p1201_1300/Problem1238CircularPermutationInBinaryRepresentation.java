package com.shuangpeng.Problem.p1201_1300;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1238CircularPermutationInBinaryRepresentation（循环码排列）
 * @date 2023/2/28 3:01 PM
 */
public class Problem1238CircularPermutationInBinaryRepresentation {

    public List<Integer> circularPermutation0(int n, int start) {
        List<Integer> list = new LinkedList<>();
        list.add(0);
        for (int i = 0; i < n; i++) {
            for (int j = list.size() - 1; j >= 0; j--) {
                list.add(list.get(j) | 1 << i);
            }
        }
        while (list.get(0) != start) {
            list.add(list.remove(0));
        }
        return list;
    }

    public List<Integer> circularPermutation(int n, int start) {
        int N = 1 << n;
        List<Integer> list = new ArrayList<>(N);
        list.add(0);
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = list.size() - 1; j >= 0; j--) {
                int num = list.get(j) | 1 << i;
                if (num == start) {
                    index = list.size();
                }
                list.add(num);
            }
        }
        List<Integer> ans = new ArrayList<>(N);
        for (int i = index; i < index + N; i++) {
            ans.add(list.get(i % N));
        }
        return ans;
    }
}
