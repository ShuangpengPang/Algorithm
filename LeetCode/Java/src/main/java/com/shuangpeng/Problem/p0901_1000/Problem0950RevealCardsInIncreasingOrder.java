package com.shuangpeng.Problem.p0901_1000;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0950RevealCardsInIncreasingOrder（按递增顺序显示卡牌）
 * @date 2023/3/17 11:05 AM
 */
public class Problem0950RevealCardsInIncreasingOrder {

    // java语音特点：单面分解垃圾，跨高网多动健安
    // 简单性、面向对象、分布式、解释型、垃圾回收、跨平台、高性能、支持网络编程、多线程、动态性、健壮性、安全性
    // 开接一单，里迪合
    // 管道变线，启动加入中断，销毁传单（happens before原则）

    public int[] deckRevealedIncreasing0(int[] deck) {
        Arrays.sort(deck);
        int n = deck.length;
        int[] ans = new int[n];
        boolean[] visited = new boolean[n];
        for (int i = 0, j = 0; i < n; i++) {
            ans[j] = deck[i];
            if (i == n - 1) {
                break;
            }
            visited[j] = true;
            while (visited[j]) {
                j = (j + 1) % n;
            }
            j = (j + 1) % n;
            while (visited[j]) {
                j = (j + 1) % n;
            }
        }
        return ans;
    }

    public int[] deckRevealedIncreasing1(int[] deck) {
        Arrays.sort(deck);
        int n = deck.length;
        Deque<Integer> q = new ArrayDeque<>(n);
        for (int i = 0; i < n; i++) {
            q.addLast(i);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[q.pollFirst()] = deck[i];
            if (i < n - 1) {
                q.addLast(q.pollFirst());
            }
        }
        return ans;
    }

    public int[] deckRevealedIncreasing2(int[] deck) {
        Arrays.sort(deck);
        int n = deck.length;
        int[] ans = new int[n];
        boolean skip = false;
        for (int i = 0, j = 0; i < n; i++) {
            while (ans[j] != 0 || skip) {
                if (ans[j] == 0) {
                    skip = false;
                }
                j = (j + 1) % n;
            }
            ans[j] = deck[i];
            skip = true;
        }
        return ans;
    }

    public int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);
        int n = deck.length;
        int[] ans = new int[n];
        int index = 0;
        boolean skip = false;
        while (index < n) {
            for (int i = 0; i < n; i++) {
                if (ans[i] == 0) {
                    if (!skip) {
                        ans[i] = deck[index++];
                    }
                    skip = !skip;
                }
            }
        }
        return ans;
    }
}
