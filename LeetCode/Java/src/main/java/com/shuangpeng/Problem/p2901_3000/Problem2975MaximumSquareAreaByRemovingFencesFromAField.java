package com.shuangpeng.Problem.p2901_3000;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2975MaximumSquareAreaByRemovingFencesFromAField（移除栅栏得到的正方形田地的最大面积）
 * @date 2024/1/14 8:49 PM
 */
public class Problem2975MaximumSquareAreaByRemovingFencesFromAField {

    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        Arrays.sort(hFences);
        Arrays.sort(vFences);
        Set<Integer> set = new HashSet<>();
        int hn = hFences.length, vn = vFences.length;
        for (int i = 0; i <= hn; i++) {
            int h = i == 0 ? 1 : hFences[i - 1];
            for (int j = i + 1; j <= hn + 1; j++) {
                set.add((j <= hn ? hFences[j - 1] : m) - h);
            }
        }
        int side = -1, N = (int) 1e9 + 7;
        for (int i = 0; i <= vn; i++) {
            int v = i == 0 ? 1 : vFences[i - 1];
            for (int j = vn + 1; j > i; j--) {
                int s = (j == vn + 1 ? n : vFences[j - 1]) - v;
                if (s <= side) {
                    break;
                }
                if (set.contains(s)) {
                    side = s;
                }
            }
        }
        return side == -1 ? -1 : (int) ((long) side * side % N);
    }
}
