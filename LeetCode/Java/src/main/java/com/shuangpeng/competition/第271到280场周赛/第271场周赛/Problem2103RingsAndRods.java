package com.shuangpeng.competition.第271到280场周赛.第271场周赛;

import java.util.HashSet;
import java.util.Set;

public class Problem2103RingsAndRods {

    // 比赛时写法
    public int countPoints0(String rings) {
        int n = rings.length();
        Set<Character>[] sets = new Set[10];
        for (int i = 0; i < 10; ++i) {
            sets[i] = new HashSet<>();
        }
        for (int i = 0; i < n; i += 2) {
            char color = rings.charAt(i);
            int num = rings.charAt(i + 1) - '0';
            sets[num].add(color);
        }
        int count = 0;
        for (int i = 0; i < 10; ++i) {
            if (sets[i].size() == 3) {
                ++count;
            }
        }
        return count;
    }

    public int countPoints1(String rings) {
        int n = rings.length();
        final int N = 10;
        Set<Character>[] rods = new Set[N];
        for (int i = 0; i < N; ++i) {
            rods[i] = new HashSet<>();
        }
        for (int i = 0; i < n; i += 2) {
            rods[rings.charAt(i + 1) - '0'].add(rings.charAt(i));
        }
        int ans = 0;
        for (int i = 0; i < N; ++i) {
            if (rods[i].size() == 3) {
                ++ans;
            }
        }
        return ans;
    }

    public int countPoints(String rings) {
        int n = rings.length();
        final int N = 10;
        int[] rods = new int[N];
        for (int i = 0; i < n; i += 2) {
            char c = rings.charAt(i);
            int r = rings.charAt(i + 1) - '0';
            if (c == 'R') {
                rods[r] |= 1;
            } else if (c == 'G') {
                rods[r] |= 2;
            } else {
                rods[r] |= 4;
            }
        }
        int ans = 0;
        for (int i = 0; i < N; ++i) {
            if (rods[i] == 7) {
                ++ans;
            }
        }
        return ans;
    }
}
