package com.shuangpeng.Problem.p3001_3100;

import java.util.Arrays;
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

class Problem3081ReplaceQuestionMarksInStringToMinimizeItsValue0 {

    public String minimizeStringValue(String S) {
        char[] s = S.toCharArray();
        int[] freq = new int[27];
        freq[26] = Integer.MAX_VALUE / 26; // 哨兵
        int q = 0;
        for (char c : s) {
            if (c != '?') {
                freq[c - 'a']++;
            } else {
                q++;
            }
        }

        int[] f = freq.clone();
        Arrays.sort(f);

        int limit, extra;
        for (int i = 1; ; i++) {
            int sum = i * (f[i] - f[i - 1]);
            if (q <= sum) {
                limit = f[i - 1] + q / i;
                extra = q % i;
                break;
            }
            q -= sum;
        }

        int[] target = freq.clone();
        for (int j = 0; j < 26; j++) {
            if (freq[j] > limit) {
                continue;
            }
            target[j] = limit;
            if (extra > 0) { // 还可以多分配一个
                extra--;
                target[j]++;
            }
        }

        int j = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] != '?') {
                continue;
            }
            while (freq[j] == target[j]) {
                j++;
            }
            freq[j]++;
            s[i] = (char) ('a' + j);
        }
        return new String(s);
    }
}

class Problem3081ReplaceQuestionMarksInStringToMinimizeItsValue1 {

    public String minimizeStringValue(String s) {

        char[] cs = s.toCharArray();
        int n = cs.length, m = 0;

        int[] count1 = new int[26];
        int[] que = new int[n];

        for (int i = 0; i < n; ++i) {
            if (cs[i] != '?')
                count1[cs[i] - 'a']++;
            else
                que[m++] = i;
        }

        if (m == 0)
            return new String(cs);

        int[] count2 = count1.clone();
        int average = 0;
        while (m > 0) {
            average += m < 26 ? 1 : m / 26;
            for (int i = 0; i < 26 && m > 0; ++i) {
                if (count2[i] >= average)
                    continue;

                m -= average - count2[i];
                count2[i] = average;
            }
        }

        int[] count = new int[26];
        for (int i = 0; i < 26; ++i)
            count[i] = count2[i] - count1[i];

        int index = 0;
        for (int i = 0; i < 26; ++i) {
            int c = count[i];
            while (c-- > 0)
                cs[que[index++]] = (char) (i + 'a');
        }

        return new String(cs);
    }
}
