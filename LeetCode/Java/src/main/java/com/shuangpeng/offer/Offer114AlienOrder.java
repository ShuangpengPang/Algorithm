package com.shuangpeng.offer;

import com.shuangpeng.common.ListNode;

import java.util.*;

/**
 * @Description: Offer114AlienOrder（外星文字典）
 * @Date 2022/5/31 10:11 AM
 * @Version 1.0
 */
public class Offer114AlienOrder {

    public String alienOrder(String[] words) {
        Set<Character> set = new HashSet<>();
        for (String w : words) {
            int n = w.length();
            for (int i = 0; i < n; ++i) {
                set.add(w.charAt(i));
            }
        }
        int size = set.size();
        Map<Character, List<Character>> orderMap = new HashMap<>(size);
        Map<Character, Integer> inDegreeMap = new HashMap<>(size);
        for (char c : set) {
            orderMap.put(c, new ArrayList<>());
            inDegreeMap.put(c, 0);
        }
        int n = words.length;
        for (int i = 0; i < n - 1; ++i) {
            String w1 = words[i], w2 = words[i + 1];
            int n1 = w1.length(), n2 = w2.length();
            int i1 = 0, i2 = 0;
            while (i1 < n1 && i2 < n2) {
                char c1 = w1.charAt(i1), c2 = w2.charAt(i2);
                if (c1 != c2) {
                    orderMap.get(c1).add(c2);
                    inDegreeMap.put(c2, inDegreeMap.get(c2) + 1);
                    break;
                }
                ++i1;
                ++i2;
            }
            if (i1 < n1 && i2 == n2) {
                return "";
            }
        }
        Set<Character> visited = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        for (char c : set) {
            if (inDegreeMap.get(c) == 0) {
                visited.add(c);
                sb.append(c);
                queue.offer(c);
            }
        }
        while (!queue.isEmpty()) {
            char c1 = queue.poll();
            for (char c2 : orderMap.get(c1)) {
                int degree = inDegreeMap.get(c2) - 1;
                inDegreeMap.put(c2, degree);
                if (degree == 0) {
                    if (visited.contains(c2)) {
                        return "";
                    }
                    visited.add(c2);
                    sb.append(c2);
                    queue.offer(c2);
                }
            }
        }
        return sb.length() == size ? sb.toString() : "";
    }
}

class Offer114AlienOrder0 {

    static final int N = 26;
    Map<Character, List<Character>> edges = new HashMap<>(N);
    Map<Character, Integer> state = new HashMap<>(N);
    boolean valid = true;
    static final int VISITING = 1, VISITED = 2;
    char[] chars;
    int index;

    public String alienOrder(String[] words) {
        for (String w : words) {
            int m = w.length();
            for (int i = 0; i < m; ++i) {
                edges.putIfAbsent(w.charAt(i), new ArrayList<>());
            }
        }
        int n = words.length;
        for (int i = 0; i < n - 1; ++i) {
            String w1 = words[i], w2 = words[i + 1];
            int n1 = w1.length(), n2 = w2.length();
            int i1 = 0, i2 = 0;
            while (i1 < n1 && i2 < n2) {
                char c1 = w1.charAt(i1), c2 = w2.charAt(i2);
                if (c1 != c2) {
                    edges.get(c1).add(c2);
                    break;
                }
                ++i1;
                ++i2;
            }
            if (i1 < n1 && i2 == n2) {
                return "";
            }
        }
        valid = true;
        chars = new char[edges.size()];
        index = edges.size() - 1;
        for (char c : edges.keySet()) {
            if (!state.containsKey(c) && valid) {
                dfs(c);
            }
        }
        return valid ? new String(chars) : "";
    }

    private void dfs(char u) {
        state.put(u, VISITING);
        for (char v : edges.get(u)) {
            if (!state.containsKey(v)) {
                dfs(v);
            } else if (state.get(v) == VISITING) {
                valid = false;
                return;
            }
        }
        state.put(u, VISITED);
        chars[index--] = u;
    }
}

class Offer114AlienOrder1 {
    private final char[] order = new char[26];
    private int begin = 26;

    public String alienOrder(String[] words) {
        char[][] newWords = new char[words.length][];
        // visited 二进制中的第 i 位记录字符 i + 'a' 是否在 words 中出现过。
        int visited = 0;
        for (int i = 0; i < words.length; i++) {
            newWords[i] = words[i].toCharArray();
            for (char ch : newWords[i]) {
                visited |= 1 << ch - 'a';
            }
        }
        ListNode[] adj = new ListNode[26];
        for (int i = 1; i < words.length; i++) {
            int[] edge = createEdge(newWords[i - 1], newWords[i]);
            if (edge == null) {
                return "";
            }
            adj[edge[0]] = new ListNode(edge[1], adj[edge[0]]);
        }
        // 记录节点的访问状态，0：未访问，1：访问中，2：已访问。
        int[] status = new int[26];
        for (int i = 0; i < 26; i++) {
            if ((visited >> i & 1) == 1 && status[i] == 0) {
                if (hasCycle(adj, i, status)) {
                    return "";
                }
            }
        }
        return new String(order, begin, 26 - begin);
    }

    // 从图 adj 的节点 i 开始进行深度优先搜索，判断图中是否有环。
    private boolean hasCycle(ListNode[] adj, int i, int[] status) {
        if (status[i] == 2) {
            return false;
        }
        if (status[i] == 1) {
            return true;
        }
        status[i] = 1; // 将节点标记为访问中。
        for (ListNode nei = adj[i]; nei != null; nei = nei.next) {
            if (hasCycle(adj, nei.val, status)) {
                return true;
            }
        }
        status[i] = 2; // 将节点标记为已访问。
        order[--begin] = (char) (i + 'a');
        return false;
    }

    // 根据单词 word1 与 word2 之间的先后顺序计算两个字符之间的大小。
    private int[] createEdge(char[] word1, char[] word2) {
        int minLen = Math.min(word1.length, word2.length);
        int i = 0;
        while (i < minLen && word1[i] == word2[i]) {
            i++;
        }
        if (i == word1.length) {
            return new int[2];
        }
        if (i == word2.length) {
            return null;
        }
        return new int[]{word1[i] - 'a', word2[i] - 'a'};
    }
}

