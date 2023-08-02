package com.shuangpeng.Problem.p1201_1300;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1209RemoveAllAdjacentDuplicatesInStringII（删除字符串中的所有相邻重复项）
 * @date 2023/6/13 5:45 PM
 */
public class Problem1209RemoveAllAdjacentDuplicatesInStringII {

    public String removeDuplicates0(String s, int k) {
        Deque<Pair<Character, Integer>> q = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (!q.isEmpty() && q.peekLast().getKey() == c) {
                int cnt = q.pollLast().getValue() + 1;
                if (cnt < k) {
                    q.offerLast(new Pair<>(c, cnt));
                }
            } else {
                q.offerLast(new Pair<>(c, 1));
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            Pair<Character, Integer> p = q.pollFirst();
            char c = p.getKey();
            int cnt = p.getValue();
            for (int i = 0; i < cnt; i++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public String removeDuplicates1(String s, int k) {
        int n = s.length();
        Deque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (!q.isEmpty() && q.peekLast()[0] == c) {
                if (q.peekLast()[1] == k - 1) {
                    q.pollLast();
                } else {
                    q.peekLast()[1]++;
                }
            } else {
                q.offerLast(new int[]{c, 1});
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int[] p = q.pollFirst();
            char c = (char) ('a' + p[0]);
            while (p[1] > 0) {
                sb.append(c);
                p[1]--;
            }
        }
        return sb.toString();
    }

    public String removeDuplicates(String s, int k) {
        char[] cs = s.toCharArray();
        int n = cs.length, j = 0;
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (j == 0 || cs[i] != cs[j - 1]) {
                cs[j++] = cs[i];
                q.offerLast(1);
            } else {
                int cnt = q.pollLast() + 1;
                if (cnt == k) {
                    j = j - k + 1;
                } else {
                    cs[j++] = cs[i];
                    q.offerLast(cnt);
                }
            }
        }
        return new String(cs, 0, j);
    }
}
