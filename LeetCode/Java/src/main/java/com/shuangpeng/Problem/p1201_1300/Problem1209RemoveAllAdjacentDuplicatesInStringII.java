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

    public String removeDuplicates(String s, int k) {
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

//    public String removeDuplicates(String s, int k) {
//        StringBuilder sb = new StringBuilder();
//        int n = s.length();
//        int[] cnt = new int[n];
//        for (int i = 0, j = 0; j < n; i++, j++) {
//            char c = s.charAt(j);
//            if (i > 0 && c == sb.charAt(i - 1)) {
//                if (cnt[i - 1] == k - 1) {
//                    i -= k - 1;
//                    sb.setLength(i--);
//                } else {
//                    cnt[i] =
//                }
//            } else {
//                cnt[]
//            }
//        }
//    }

//    public String removeDuplicates(String s, int k) {
//        StringBuilder sb = new StringBuilder(s);
//        int count[] = new int[sb.length()];
//        for (int i = 0; i < sb.length(); ++i) {
//            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
//                count[i] = 1;
//            } else {
//                count[i] = count[i - 1] + 1;
//                if (count[i] == k) {
//                    sb.delete(i - k + 1, i + 1);
//                    i = i - k;
//                }
//            }
//        }
//        return sb.toString();
//    }



}
