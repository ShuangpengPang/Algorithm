package com.shuangpeng.Problem.p0101_0200;

import java.util.*;

public class Problem0126WordLadderII {

    public List<List<String>> findLadders0(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> map = new HashMap<>();
        boolean hasEnd = false;
        for (String word : wordList) {
            if (word.equals(endWord)) {
                hasEnd = true;
            }
            List<String> masks = getNextMask(word);
            for (String w : masks) {
                map.putIfAbsent(w, new ArrayList<>());
                map.get(w).add(word);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        if (!hasEnd) {
            return ans;
        }
        Queue<List<String>> queue = new LinkedList<>();
        List<String> first = new ArrayList<>();
        first.add(beginWord);
        queue.offer(first);
        boolean find = false;
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        while (!queue.isEmpty() && !find) {
            List<String> temp = new ArrayList<>();
            for (int i = queue.size() - 1; i >= 0; --i) {
                List<String> list = queue.poll();
                String word = list.get(list.size() - 1);
                if (word.equals(endWord)) {
                    ans.add(list);
                    find = true;
                }
                if (find) {
                    continue;
                }
                List<String> masks = getNextMask(word);
                for (String mask : masks) {
                    for (String w : map.getOrDefault(mask, new ArrayList<>())) {
                        if (!visited.contains(w)) {
                            List<String> next = new ArrayList<>(list);
                            next.add(w);
                            queue.offer(next);
                            temp.add(w);
                        }
                    }
                }
            }
            for (String w : temp) {
                visited.add(w);
            }
        }
        return ans;
    }

    private List<String> getNextMask(String word) {
        int n = word.length();
        List<String> ans = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            char[] chars = new char[n];
            for (int j = 0; j < n; ++j) {
                if (i == j) {
                    chars[j] = '_';
                } else {
                    chars[j] = word.charAt(j);
                }
            }
            ans.add(new String(chars));
        }
        return ans;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, List<String>> maskMap = new HashMap<>();
        boolean hasEnd = false;
        for (String word : wordList) {
            if (word.equals(endWord)) {
                hasEnd = true;
            }
            for (String mask : getMask(word)) {
                maskMap.putIfAbsent(mask, new ArrayList<>());
                maskMap.get(mask).add(word);
            }
        }
        if (!hasEnd) {
            return ans;
        }
        Map<String, List<String>> fromMap = new HashMap<>();
        Map<String, Integer> stepMap =  new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        stepMap.put(beginWord, 0);
        int step = 0;
        boolean find = false;
        while (!queue.isEmpty()) {
            ++step;
            for (int i = queue.size() - 1; i >= 0; --i) {
                String word = queue.poll();
                if (word.equals(endWord)) {
                    find = true;
                }
                if (find) {
                    continue;
                }
                for (String mask : getMask(word)) {
                    for (String next : maskMap.getOrDefault(mask, new ArrayList<>())) {
                        int nextStep = stepMap.getOrDefault(next, Integer.MAX_VALUE);
                        if (nextStep == step) {
                            fromMap.get(next).add(word);
                            continue;
                        }
                        if (step < nextStep) {
                            stepMap.put(next, step);
                            fromMap.putIfAbsent(next, new ArrayList<>());
                            fromMap.get(next).add(word);
                            queue.offer(next);
                        }
                    }
                }
            }
        }
        if (!find) {
            return ans;
        }
        Deque<String> deque = new LinkedList<>();
        deque.addFirst(endWord);
        dfs(fromMap, endWord, beginWord, deque, ans);
        return ans;
    }

    private void dfs(Map<String, List<String>> fromMap, String path, String beginWord, Deque<String> deque, List<List<String>> ans) {
        if (path.equals(beginWord)) {
            ans.add(new ArrayList<>(deque));
            return;
        }
        for (String previous : fromMap.get(path)) {
            deque.addFirst(previous);
            dfs(fromMap, previous, beginWord, deque, ans);
            deque.removeFirst();
        }
    }

    private List<String> getMask(String word) {
        int n = word.length();
        char[] chars = word.toCharArray();
        List<String> ans = new ArrayList<>(n);
        for (int i = 0; i < n; ++i) {
            char c = chars[i];
            chars[i] = '_';
            ans.add(new String(chars));
            chars[i] = c;
        }
        return ans;
    }
}
