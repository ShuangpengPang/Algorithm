package com.shuangpeng.Problem.p2401_2500;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2434UsingARobotToPrintTheLexicographicallySmallestString（使用机器人打印字典序最小的字符串）
 * @date 2023/11/21 10:53 AM
 */
public class Problem2434UsingARobotToPrintTheLexicographicallySmallestString {

    public String robotWithString0(String s) {
        int n = s.length(), N = 26;
        int[] cnt = new int[N];
        for (int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }
        Deque<Character> stack = new ArrayDeque<>(n);
        int index = 0;
        while (index < N && cnt[index] == 0) {
            index++;
        }
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            stack.offerLast(c);
            cnt[c - 'a']--;
            while (index < N && cnt[index] == 0) {
                index++;
            }
            while (!stack.isEmpty() && stack.peekLast() <= (char) (index + 'a')) {
                sb.append(stack.pollLast());
            }
        }
        return sb.toString();
    }

    public String robotWithString(String s) {
        int n = s.length();
        char[] cs = new char[n + 1];
        cs[n] = 'z';
        for (int i = n - 1; i >= 0; i--) {
            cs[i] = (char) Math.min(cs[i + 1], s.charAt(i));
        }
        Deque<Character> q = new ArrayDeque<>(n);
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            q.offerLast(s.charAt(i));
            while (!q.isEmpty() && q.peekLast() <= cs[i + 1]) {
                sb.append(q.pollLast());
            }
        }
        return sb.toString();
    }
}
