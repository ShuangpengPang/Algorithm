package com.shuangpeng.Problem;

import java.util.*;

public class Problem0127WordLadder {

    public int ladderLength0(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null) {
            return 0;
        }
        if (beginWord.length() != endWord.length()) {
            return 0;
        }
        if (beginWord.equals(endWord)) {
            return 1;
        }
        boolean contain = false;
        for (String str : wordList) {
            if (str.equals(endWord)) {
                contain = true;
                break;
            }
        }
        if (!contain) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        wordList.remove(beginWord);
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String string = queue.poll();
                for (int j = 0; j < wordList.size(); j++) {
                    String str = wordList.get(j);
                    if (string.length() != str.length()) {
                        wordList.remove(str);
                        j = -1;
                    } else if (isNext(string, str)) {
                        if (str.equals(endWord)) {
                            return step + 1;
                        }
                        queue.offer(str);
                        wordList.remove(str);
                        j = -1;
                    }
                }
            }
            step++;
        }
        return 0;
    }

    public boolean isNext(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        int count = 0;
        int length = str1.length();
        for (int i = 0; i < length; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }
        return count == 1;
    }


    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }
        int[] dis = new int[nodeNum];
        Arrays.fill(dis, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord), endId = wordId.get(endWord);
        dis[beginId] = 0;

        Queue<Integer> que = new LinkedList<Integer>();
        que.offer(beginId);
        while (!que.isEmpty()) {
            int x = que.poll();
            if (x == endId) {
                return dis[endId] / 2 + 1;
            }
            for (int it : edge.get(x)) {
                if (dis[it] == Integer.MAX_VALUE) {
                    dis[it] = dis[x] + 1;
                    que.offer(it);
                }
            }
        }
        return 0;
    }

    Map<String, Integer> wordId = new HashMap<String, Integer>();
    List<List<Integer>> edge = new ArrayList<List<Integer>>();
    int nodeNum = 0;

    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }

        int[] disBegin = new int[nodeNum];
        Arrays.fill(disBegin, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord);
        disBegin[beginId] = 0;
        Queue<Integer> queBegin = new LinkedList<Integer>();
        queBegin.offer(beginId);

        int[] disEnd = new int[nodeNum];
        Arrays.fill(disEnd, Integer.MAX_VALUE);
        int endId = wordId.get(endWord);
        disEnd[endId] = 0;
        Queue<Integer> queEnd = new LinkedList<Integer>();
        queEnd.offer(endId);

        while (!queBegin.isEmpty() && !queEnd.isEmpty()) {
            int queBeginSize = queBegin.size();
            for (int i = 0; i < queBeginSize; ++i) {
                int nodeBegin = queBegin.poll();
                if (disEnd[nodeBegin] != Integer.MAX_VALUE) {
                    return (disBegin[nodeBegin] + disEnd[nodeBegin]) / 2 + 1;
                }
                for (int it : edge.get(nodeBegin)) {
                    if (disBegin[it] == Integer.MAX_VALUE) {
                        disBegin[it] = disBegin[nodeBegin] + 1;
                        queBegin.offer(it);
                    }
                }
            }

            int queEndSize = queEnd.size();
            for (int i = 0; i < queEndSize; ++i) {
                int nodeEnd = queEnd.poll();
                if (disBegin[nodeEnd] != Integer.MAX_VALUE) {
                    return (disBegin[nodeEnd] + disEnd[nodeEnd]) / 2 + 1;
                }
                for (int it : edge.get(nodeEnd)) {
                    if (disEnd[it] == Integer.MAX_VALUE) {
                        disEnd[it] = disEnd[nodeEnd] + 1;
                        queEnd.offer(it);
                    }
                }
            }
        }
        return 0;
    }

    public void addEdge(String word) {
        addWord(word);
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for (int i = 0; i < length; ++i) {
            char tmp = array[i];
            array[i] = '*';
            String newWord = new String(array);
            addWord(newWord);
            int id2 = wordId.get(newWord);
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            array[i] = tmp;
        }
    }

    public void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edge.add(new ArrayList<Integer>());
        }
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null || wordList.size() == 0) {
            return 0;
        }
        int size = wordList.size();
        Map<String, Integer> map = new HashMap<>(size + 1);
        List<List<Integer>> edges = new ArrayList<>(size + 1);
        addWord(beginWord, map, edges);
        for (int i = 0; i < size; i++) {
            addWord(wordList.get(i), map, edges);
        }
        if (!map.containsKey(endWord)) {
            return 0;
        }
        int targetId = map.get(endWord);
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(map.get(beginWord));
        int count = 1;
        boolean[] visited = new boolean[map.size()];
        while (!queue.isEmpty()) {
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                int id = queue.poll();
                if (id == targetId) {
                    return count / 2 + 1;
                }
                List<Integer> list = edges.get(id);
                for (int edge : list) {
                    if (!visited[edge]) {
                        queue.offer(edge);
                        visited[edge] = true;
                    }
                }
            }
            count++;
        }
        return 0;
    }

    public void addWord(String word, Map<String, Integer> map, List<List<Integer>> edges) {
        int wordCount = edges.size();
        if (!map.containsKey(word)) {
            map.put(word, wordCount++);
            edges.add(new ArrayList<>());
            char[] chars = word.toCharArray();
            int wordId = map.get(word);
            for (int i = 0; i < chars.length; i++) {
                char temp = chars[i];
                chars[i] = '*';
                String newWord = new String(chars);
                if (!map.containsKey(newWord)) {
                    map.put(newWord, wordCount++);
                    edges.add(new ArrayList<>());
                }
                int newWordId = map.get(newWord);
                edges.get(wordId).add(newWordId);
                edges.get(newWordId).add(wordId);
                chars[i] = temp;
            }
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null
                || wordList.isEmpty() || beginWord.length() != endWord.length()) {
            return 0;
        }
        if (beginWord.equals(endWord)) {
            return 1;
        }
        int size = wordList.size();
        Map<String, Integer> map = new HashMap<>(size + 1);
        List<List<Integer>> edges = new ArrayList<>(size + 1);
        addWord(beginWord, map, edges);
        for (int i = 0; i < size; i++) {
            addWord(wordList.get(i), map, edges);
        }
        if (!map.containsKey(endWord)) {
            return 0;
        }
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();
        queue1.offer(map.get(beginWord));
        queue2.offer(map.get(endWord));
        set1.add(map.get(beginWord));
        set2.add(map.get(endWord));
        int count = 2;
        boolean flag = false;
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            Queue<Integer> queue = queue1;
            Set<Integer> set = set1;
            Set<Integer> compareSet = set2;
            if (flag) {
                queue = queue2;
                set = set2;
                compareSet = set1;
            }
            int length = queue.size();
            for (int i = 0; i < length; i++) {
                int id = queue.poll();
                if (compareSet.contains(id)) {
                    return (count - 1) / 2 + 1;
                }
                List<Integer> list = edges.get(id);
                for (int newId : list) {
                    if (!set.contains(newId)) {
                        set.add(newId);
                        queue.offer(newId);
                    }
                }
            }
            count++;
            flag = !flag;
        }
        return 0;
    }
}
