package com.shuangpeng.Problem.p1401_1500;

import java.util.Arrays;

public class Problem1405LongestHappyString {

    class Node {
        char c;
        int count;

        Node(char c, int count) {
            this.c = c;
            this.count = count;
        }
    }

    public String longestDiverseString0(int a, int b, int c) {
        Node[] nodes = {new Node('a', a), new Node('b', b), new Node('c', c)};
        Arrays.sort(nodes, (n1, n2) -> n2.count - n1.count);
        char ch1 = nodes[0].c, ch2 = nodes[1].c, ch3 = nodes[2].c;
        int c1 = nodes[0].count, c2 = nodes[1].count, c3 = nodes[2].count;

        int c4 = c2 - c3;
        int c5 = c4 == 0 ? 0 : Math.min(c4, c1 - c4);
        int c6 = c4 - c5;
        c1 -= c4 + c5;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c5; ++i) {
            sb.append(ch1).append(ch1).append(ch2);
        }
        for (int i = 0; i < c6; ++i) {
            sb.append(ch1).append(ch2);
        }
        for (int i = 0; i < c3; ++i) {
            for (int j = 0; c1 > 0 && j < 2; ++j, --c1) {
                sb.append(ch1);
            }
            sb.append(ch2);
            for (int j = 0; c1 > 0 && j < 2; ++j, --c1) {
                sb.append(ch1);
            }
            sb.append(ch3);
        }
        for (int j = 0; c1 > 0 && j < 2; ++j, --c1) {
            sb.append(ch1);
        }
        return sb.toString();
    }

    public String longestDiverseString(int a, int b, int c) {
        Pair[] pairs = {new Pair(a, 'a'), new Pair(b, 'b'), new Pair(c, 'c')};
        StringBuilder sb = new StringBuilder();
        boolean hasNext = true;
        while (hasNext) {
            hasNext = false;
            Arrays.sort(pairs, (p1, p2) -> p2.freq - p1.freq);
            for (Pair pair : pairs) {
                if (pair.freq == 0) {
                    break;
                }
                int size = sb.length();
                if (size >= 2 && sb.charAt(size - 1) == pair.ch && sb.charAt(size - 2) == pair.ch) {
                    continue;
                }
                hasNext = true;
                sb.append(pair.ch);
                --pair.freq;
                break;
            }
        }
        return sb.toString();
    }

    class Pair {
        int freq;
        char ch;

        Pair(int freq, char ch) {
            this.freq = freq;
            this.ch = ch;
        }
    }

//    public String longestDiverseString(int a, int b, int c) {
//        PriorityQueue<int[]> q = new PriorityQueue<>((x, y)->y[1]-x[1]);
//        if (a > 0) q.add(new int[]{0, a});
//        if (b > 0) q.add(new int[]{1, b});
//        if (c > 0) q.add(new int[]{2, c});
//        StringBuilder sb = new StringBuilder();
//        while (!q.isEmpty()) {
//            int[] cur = q.poll();
//            int n = sb.length();
//            if (n >= 2 && sb.charAt(n - 1) - 'a' == cur[0] && sb.charAt(n - 2) - 'a' == cur[0]) {
//                if (q.isEmpty()) break;
//                int[] next = q.poll();
//                sb.append((char)(next[0] + 'a'));
//                if (--next[1] != 0) q.add(next);
//                q.add(cur);
//            } else {
//                sb.append((char)(cur[0] + 'a'));
//                if (--cur[1] != 0) q.add(cur);
//            }
//        }
//        return sb.toString();
//    }
}
