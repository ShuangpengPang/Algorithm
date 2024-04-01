package com.shuangpeng.Problem.p3001_3100;

import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3081ReplaceQuestionMarksInStringToMinimizeItsValue（替换字符串中的问号使分数最小）
 * @date 2024/4/1 6:59 PM
 */
public class Problem3081ReplaceQuestionMarksInStringToMinimizeItsValue {

    public String minimizeStringValue(String s) {
        char[] cs = s.toCharArray();
        int[] cnt = new int[26];
        int count = 0;
        for (char c : cs) {
            if (c == '?') {
                count++;
            } else {
                cnt[c - 'a']++;
            }
        }
        int[] marks = new int[26];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]);
        for (int i = 0; i < 26; i++) {
            pq.offer(new int[]{i, cnt[i]});
        }
        for (int i = 0; i < count; i++) {
            int[] p = pq.poll();
            marks[p[0]]++;
            p[1]++;
            pq.offer(p);
        }
        for (int i = 0, j = 0; i < cs.length; i++) {
            if (cs[i] == '?') {
                while (marks[j] == 0) {
                    j++;
                }
                cs[i] = (char) ('a' + j);
                marks[j]--;
            }
        }
        return new String(cs);
    }
}
