package com.shuangpeng.Problem.p1701_1800;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: Algorithm
 * @description: Problem1733MinimumNumberOfPeopleToTeach（需要教语言的最少人数）
 * @author: ShuangPengPang
 * @create: 2025-05-27 19:14
 */
public class Problem1733MinimumNumberOfPeopleToTeach {

    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        Set<Integer>[] peopleSet = new Set[n];
        Arrays.setAll(peopleSet, i -> new HashSet<>());
        int m = languages.length;
        for (int i = 0; i < m; i++) {
            for (int l : languages[i]) {
                peopleSet[l - 1].add(i);
            }
        }
        boolean[] userToTeach = new boolean[m];
        for (int[] f : friendships) {
            int x = f[0] - 1, y = f[1] - 1;
            boolean hasCommon = false;
            for (int l : languages[y]) {
                if (peopleSet[l - 1].contains(x)) {
                    hasCommon = true;
                    break;
                }
            }
            if (!hasCommon) {
                userToTeach[x] = userToTeach[y] = true;
            }
        }
        int[] peopleCount = new int[n];
        int maxCount = 0, total = 0;
        for (int i = 0; i < m; i++) {
            if (userToTeach[i]) {
                total++;
                for (int j : languages[i]) {
                    maxCount = Math.max(maxCount, ++peopleCount[j - 1]);
                }
            }
        }
        return total - maxCount;
    }
}
