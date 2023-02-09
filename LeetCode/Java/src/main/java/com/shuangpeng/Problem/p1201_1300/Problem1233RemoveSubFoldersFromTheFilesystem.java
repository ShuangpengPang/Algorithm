package com.shuangpeng.Problem.p1201_1300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1233RemoveSubFoldersFromTheFilesystem（删除子文件夹）
 * @date 2023/2/8 4:58 PM
 */
public class Problem1233RemoveSubFoldersFromTheFilesystem {

    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder, Comparator.comparingInt(a -> a.length()));
        Set<String> set = new HashSet<>();
        List<String> ans = new ArrayList<>();
        for (String f : folder) {
            StringBuilder sb = new StringBuilder("/");
            boolean isSubFolder = false;
            int n = f.length(), i = 1;
            while (i < n) {
                while (i < n && f.charAt(i) != '/') {
                    sb.append(f.charAt(i));
                    i++;
                }
                if (set.contains(sb.toString())) {
                    isSubFolder = true;
                    break;
                }
                if (i < n) {
                    sb.append('/');
                }
                i++;
            }
            if (!isSubFolder) {
                String s = sb.toString();
                set.add(s);
                ans.add(s);
            }
        }
        return ans;
    }
}

class Problem1233RemoveSubFoldersFromTheFilesystem0 {

    class Trie {
        Map<String, Trie> trieMap = new HashMap<>();
        boolean isFolder;
    }

    public List<String> removeSubfolders(String[] folder) {
        Trie root = new Trie();
        for (String f : folder) {
            int n = f.length(), i = 1;
            Trie trie = root;
            while (i < n && !trie.isFolder) {
                StringBuilder sb = new StringBuilder();
                while (i < n && f.charAt(i) != '/') {
                    sb.append(f.charAt(i));
                    i++;
                }
                String s = sb.toString();
                if (!trie.trieMap.containsKey(s)) {
                    trie.trieMap.put(s, new Trie());
                }
                trie = trie.trieMap.get(s);
                i++;
            }
            trie.isFolder = true;
        }
        List<String> ans = new ArrayList<>();
        dfs(root, new StringBuilder(), ans);
        return ans;
    }

    private void dfs(Trie root, StringBuilder sb, List<String> ans) {
        if (root.isFolder) {
            ans.add(sb.toString());
            return;
        }
        int n = sb.length();
        sb.append('/');
        for (Map.Entry<String, Trie> entry : root.trieMap.entrySet()) {
            String s = entry.getKey();
            sb.append(s);
            dfs(entry.getValue(), sb, ans);
            sb.setLength(n + 1);
        }
        sb.setLength(n);
    }
}

class Problem1233RemoveSubFoldersFromTheFilesystem1 {

    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> ans = new ArrayList<>();
        ans.add(folder[0]);
        for (int i = 1; i < folder.length; i++) {
            String p = ans.get(ans.size() - 1), s = folder[i];
            int n1 = p.length(), n2 = s.length();
            if (n2 < n1 || !p.equals(s.substring(0, n1)) || (n1 < n2 && s.charAt(n1) != '/')) {
                ans.add(s);
            }
        }
        return ans;
    }
}

class Problem1233RemoveSubFoldersFromTheFilesystem2 {

    class Trie {
        int index = -1;
        Map<String, Trie> tries = new HashMap<>();
    }

    public List<String> removeSubfolders(String[] folder) {
        Trie root = new Trie();
        for (int i = 0; i < folder.length; i++) {
            String f = folder[i];
            StringBuilder sb = new StringBuilder();
            Trie cur = root;
            for (int j = 1; j <= f.length() && cur.index == -1; j++) {
                if (j == f.length() || f.charAt(j) == '/') {
                    String s = sb.toString();
                    cur.tries.putIfAbsent(s, new Trie());
                    cur = cur.tries.get(s);
                    sb.setLength(0);
                } else {
                    sb.append(f.charAt(j));
                }
            }
            if (cur.index == -1) {
                cur.index = i;
            }
        }
        List<String> ans = new ArrayList<>();
        dfs(root, folder, ans);
        return ans;
    }

    private void dfs(Trie root, String[] folder, List<String> ans) {
        if (root.index != -1) {
            ans.add(folder[root.index]);
            return;
        }
        for (Trie trie : root.tries.values()) {
            dfs(trie, folder, ans);
        }
    }
}
