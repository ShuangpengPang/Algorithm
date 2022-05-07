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

    public int minMutation2(String start, String end, String[] bank) {
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

    public int minMutation3(String start, String end, String[] bank) {
        if (start.equals(end)) {
            return 0;
        }
        Queue<String> q1 = new LinkedList<>(), q2 = new LinkedList<>();
        Map<String, Integer> map1 = new HashMap<>(), map2 = new HashMap<>();
        map1.put(start, 0);
        map2.put(end, 0);
        q1.offer(start);
        q2.offer(end);
        Set<String> set = new HashSet<>();
        char[] keys = {'A', 'C', 'G', 'T'};
        for (String s : bank) {
            set.add(s);
        }
        while (!q1.isEmpty() && !q2.isEmpty()) {
            int result = -1;
            if (q1.size() <= q2.size()) {
                result = find(q1, map1, map2, keys, set);
            } else {
                result = find(q2, map2, map1, keys, set);
            }
            if (result != -1) {
                return result;
            }
        }
        return -1;
    }

    private int find(Queue<String> queue, Map<String, Integer> cur, Map<String, Integer> other, char[] keys, Set<String> set) {
        for (int i = queue.size() - 1; i >= 0; --i) {
            String s = queue.poll();
            char[] chars = s.toCharArray();
            int m = chars.length;
            int step = cur.get(s);
            for (int j = 0; j < m; ++j) {
                char c = chars[j];
                for (char k : keys) {
                    if (c != k) {
                        chars[j] = k;
                        String str = new String(chars);
                        if (!cur.containsKey(str) && set.contains(str)) {
                            if (other.containsKey(str)) {
                                return other.get(str) + step + 1;
                            }
                            cur.put(str, step + 1);
                            queue.offer(str);
                        }
                        chars[j] = c;
                    }
                }
            }
        }
        return -1;
    }

    class Node {
        String s;
        int val;

        Node(String s, String t) {
            this.s = s;
            int n = s.length();
            val = 0;
            for (int i = 0; i < n; ++i) {
                if (s.charAt(i) != t.charAt(i)) {
                    ++val;
                }
            }
        }
    }

    public int minMutation(String start, String end, String[] bank) {
        if (start.equals(end)) {
            return 0;
        }
        Set<String> set = new HashSet<>();
        for (String b : bank) {
            set.add(b);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        Map<String, Integer> map = new HashMap<>();
        pq.offer(new Node(start, end));
        map.put(start, 0);
        char[] keys = {'A', 'C', 'G', 'T'};
        while (!pq.isEmpty()) {
            String str = pq.poll().s;
            char[] chars = str.toCharArray();
            int step = map.get(str);
            int n = chars.length;
            for (int i = 0; i < n; ++i) {
                char c = chars[i];
                for (char k : keys) {
                    if (c != k) {
                        chars[i] = k;
                        String s = String.valueOf(chars);
                        if (!map.containsKey(s) && set.contains(s)) {
                            if (s.equals(end)) {
                                return step + 1;
                            }
                            map.put(s, step + 1);
                            pq.offer(new Node(s, end));
                        }
                        chars[i] = c;
                    }
                }
            }
        }
        return -1;
    }
}
