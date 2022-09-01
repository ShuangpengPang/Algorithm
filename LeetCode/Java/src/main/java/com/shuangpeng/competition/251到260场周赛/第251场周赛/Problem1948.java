package com.shuangpeng.competition.第251场周赛;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem1948 {

    class Trie {
        String serial;
        Map<String, Trie> trieMap;

        Trie() {
            serial = "";
            trieMap = new HashMap<>();
        }
    }

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        Trie root = new Trie();
        for (List<String> path : paths) {
            Trie trie = root;
            int n = path.size();
            for (int i = 0; i < n; i++) {
                String folder = path.get(i);
                Trie next = trie.trieMap.getOrDefault(folder, new Trie());
                trie.trieMap.put(folder, next);
                trie = next;
            }
        }
        Map<String, Integer> map = new HashMap<>();
        postorder(root, "/", map);
        List<String> list = new ArrayList<>();
        List<List<String>> answer = new ArrayList<>();
        for (String key : root.trieMap.keySet()) {
            dfs(root.trieMap.get(key), key, map, list, answer);
        }
        return answer;
    }

    private void dfs(Trie root, String folder, Map<String, Integer> map,
                     List<String> list, List<List<String>> answer) {
        Map<String, Trie> trieMap = root.trieMap;
        if (!root.serial.isEmpty() && map.get(root.serial) > 1) {
            return;
        }
        list.add(folder);
        answer.add(new ArrayList<>(list));
        for (String key : trieMap.keySet()) {
            dfs(trieMap.get(key), key, map, list, answer);
        }
        list.remove(list.size() - 1);
    }

    private String postorder(Trie root, String folder, Map<String, Integer> map) {
        Map<String, Trie> trieMap = root.trieMap;
        if (trieMap.isEmpty()) {
            return folder;
        }
        List<String> list = new ArrayList<>(trieMap.size());
        for (String key : trieMap.keySet()) {
            list.add(postorder(trieMap.get(key), key, map));
        }
        list.sort(String::compareTo);
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append('(').append(s).append(')');
        }
        String string = sb.toString();
        root.serial = string;
        map.put(string, map.getOrDefault(string, 0) + 1);
        return string + folder;
    }

//    public static void main(String[] args) {
//        Problem1948 a = new Problem1948();
//        String[][] strings = {{"a"},{"c"},{"d"},{"a","b"},{"c","b"},{"d","a"}};
//        a.deleteDuplicateFolder(Arrays.stream(strings).map(Arrays::asList).collect(Collectors.toList()));
//    }
}
