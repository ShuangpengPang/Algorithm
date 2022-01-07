package com.shuangpeng.Problem.p0801_0900;

import java.util.*;

public class Problem0843GuessTheWord {

    class Master {
        public int guess(String word) {
            return 0;
        }
    }

    public void findSecretWord(String[] wordlist, Master master) {
        int n = wordlist.length;
        Set<String> set = new HashSet<>(n);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.maxCount));
        List<Node> list = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            list.add(new Node(wordlist[i]));
            set.add(wordlist[i]);
        }
        for (int i = 0; i < n; ++i) {
            int maxCount = 0;
            Node node = list.get(i);
            for (int j = i + 1; j < n; ++j) {
                int c = sameCount(wordlist[i], wordlist[j]);
                node.sets[c].add(wordlist[j]);
                maxCount = Math.max(maxCount, node.sets[c].size());
                list.get(j).sets[c].add(wordlist[i]);
            }
            node.maxCount = maxCount;
            pq.offer(node);
        }
        final int N = 6;
        while (!pq.isEmpty()) {
            while (!pq.isEmpty() && !set.contains(pq.peek().s)) {
                pq.poll();
            }
            if (!pq.isEmpty()) {
                Node node = pq.poll();
                int count = master.guess(node.s);
                if (count == N) {
                    break;
                }
                Set<String> temp = new HashSet<>();
                for (String s : node.sets[count]) {
                    if (set.contains(s)) {
                        temp.add(s);
                    }
                }
                set = temp;
            }
        }
    }

    private int sameCount(String s1, String s2) {
        int n = s1.length();
        int count = 0;
        for (int i = 0; i < n; ++i) {
            if (s1.charAt(i) == s2.charAt(i)) {
                ++count;
            }
        }
        return count;
    }

    class Node {
        static final int N = 7;

        String s;
        int maxCount;
        Set<String>[] sets;

        Node(String s) {
            this.s = s;
            this.maxCount = 0;
            sets = new Set[N];
            for (int i = 0; i < N; ++i) {
                sets[i] = new HashSet<>();
            }
        }
    }

    int[][] H;
    public void findSecretWord0(String[] wordlist, Master master) {
        int N = wordlist.length;
        H = new int[N][N];
        for (int i = 0; i < N; ++i)
            for (int j = i; j < N; ++j) {
                int match = 0;
                for (int k = 0; k < 6; ++k)
                    if (wordlist[i].charAt(k) == wordlist[j].charAt(k))
                        match++;
                H[i][j] = H[j][i] = match;
            }

        List<Integer> possible = new ArrayList();
        List<Integer> path = new ArrayList();
        for (int i = 0; i < N; ++i) possible.add(i);

        while (!possible.isEmpty()) {
            int guess = solve(possible, path);
            int matches = master.guess(wordlist[guess]);
            if (matches == wordlist[0].length()) return;
            List<Integer> possible2 = new ArrayList();
            for (Integer j: possible) if (H[guess][j] == matches) possible2.add(j);
            possible = possible2;
            path.add(guess);
        }

    }

    public int solve(List<Integer> possible, List<Integer> path) {
        if (possible.size() <= 2) return possible.get(0);
        List<Integer> ansgrp = possible;
        int ansguess = -1;

        for (int guess = 0; guess < H.length; ++guess) {
            if (!path.contains(guess)) {
                ArrayList<Integer>[] groups = new ArrayList[7];
                for (int i = 0; i < 7; ++i) groups[i] = new ArrayList<Integer>();
                for (Integer j: possible) if (j != guess) {
                    groups[H[guess][j]].add(j);
                }

                ArrayList<Integer> maxgroup = groups[0];
                for (int i = 0; i < 7; ++i)
                    if (groups[i].size() > maxgroup.size())
                        maxgroup = groups[i];

                if (maxgroup.size() < ansgrp.size()) {
                    ansgrp = maxgroup;
                    ansguess = guess;
                }
            }
        }

        return ansguess;
    }
}
