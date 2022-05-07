package com.shuangpeng.Problem.p0401_0500;

import java.util.*;

/**
 * @Description: Problem0433MinimumGeneticMutation
 * @Date 2022/5/7 10:03 AM
 * @Version 1.0
 */
public class Problem0433MinimumGeneticMutation {

    public int minMutation0(String start, String end, String[] bank) {
        if (start.equals(end)) {
            return 0;
        }
        Set<Integer> set = new HashSet<>();
        for (String b : bank) {
            set.add(toCode(b));
        }
        Queue<String> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(toCode(start));
        queue.add(start);
        char[] genes = {'A', 'C', 'G', 'T'};
        int[] map = new int[26];
        map['A' - 'A'] = 1;
        map['C' - 'A'] = 2;
        map['G' - 'A'] = 3;
        map['T' - 'A'] = 4;
        int target = toCode(end);
        int step = 0;
        while (!queue.isEmpty()) {
            ++step;
            for (int i = queue.size() - 1; i >= 0; --i) {
                String s = queue.poll();
                int code = toCode(s);
                int n = s.length();
                char[] chars = s.toCharArray();
                int unit = 1;
                for (int j = 0; j < n; ++j) {
                    char c = chars[n - j - 1];
                    for (char g : genes) {
                        int value = code + (map[g - 'A'] - map[c - 'A']) * unit;
                        if (!visited.contains(value) && set.contains(value)) {
                            if (value == target) {
                                return step;
                            }
                            visited.add(value);
                            chars[n - j - 1] = g;
                            queue.add(new String(chars));
                            chars[n - j - 1] = c;
                        }
                    }
                    unit *= 10;
                }
            }
        }
        return -1;
    }

    private int toCode(String s) {
        int n = s.length();
        int code = 0;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            int v = 1;
            if (c == 'A') {
                v = 1;
            } else if (c == 'C') {
                v = 2;
            } else if (c == 'G') {
                v = 3;
            } else {
                v = 4;
            }
            code = code * 10 + v;
        }
        return code;
    }

    public int minMutation1(String start, String end, String[] bank) {
        if (start.equals(end)) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        for (String s : bank) {
            set.add(s);
        }
        if (!set.contains(end)) {
            return -1;
        }
        char[] keys = {'A', 'C', 'G', 'T'};
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(start);
        queue.offer(start);
        int step = 0;
        while (!queue.isEmpty()) {
            ++step;
            for (int i = queue.size() - 1; i >= 0; --i) {
                String s = queue.poll();
                int n = s.length();
                for (int j = 0; j < n; ++j) {
                    char c = s.charAt(j);
                    for (char k : keys) {
                        if (c != k) {
                            StringBuilder sb = new StringBuilder(s);
                            sb.setCharAt(j, k);
                            String str = sb.toString();
                            if (!visited.contains(str) && set.contains(str)) {
                                if (str.equals(end)) {
                                    return step;
                                }
                                visited.add(str);
                                queue.offer(str);
                            }
                        }
                    }
                }
            }
        }
        return -1;
    }

    public int minMutation(String start, String end, String[] bank) {
        if (start.equals(end)) {
            return 0;
        }
        int n = bank.length;
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<>();
        }
        int endIndex = -1;
        for (int i = 0; i < n; ++i) {
            String s1 = bank[i];
            if (end.equals(s1)) {
                endIndex = i;
            }
            int m = s1.length();
            for (int j = i + 1; j < n; ++j) {
                int mutation = 0;
                String s2 = bank[j];
                for (int k = 0; k < m; ++k) {
                    if (s1.charAt(k) != s2.charAt(k)) {
                        ++mutation;
                    }
                    if (mutation > 1) {
                        break;
                    }
                }
                if (mutation == 1) {
                    adj[i].add(j);
                    adj[j].add(i);
                }
            }
        }
        if (endIndex == -1) {
            return -1;
        }
        int m = start.length();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; ++i) {
            int mutation = 0;
            String s = bank[i];
            for (int j = 0; j < m; ++j) {
                if (start.charAt(j) != s.charAt(j)) {
                    ++mutation;
                    if (mutation > 1) {
                        break;
                    }
                }
            }
            if (mutation == 1) {
                queue.offer(i);
                visited[i] = true;
            }
        }
        int step = 1;
        while (!queue.isEmpty()) {
            for (int i = queue.size() - 1; i >= 0; --i) {
                int idx = queue.poll();
                if (idx == endIndex) {
                    return step;
                }
                for (int j : adj[idx]) {
                    if (!visited[j]) {
                        visited[j] = true;
                        queue.offer(j);
                    }
                }
            }
            ++step;
        }
        return -1;
    }
}
