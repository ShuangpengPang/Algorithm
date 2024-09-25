package com.shuangpeng.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0807Permutation（面试题 08.07. 无重复字符串的排列组合）
 * @date 2024/9/25 3:11 PM
 */
public class Question0807Permutation {

    public String[] permutation(String S) {
        char[] cs = S.toCharArray();
        int[] cnt = new int[64];
        for (char c : cs) {
            cnt[c & 63]++;
        }
        List<String> ans = new ArrayList<>();
        dfs(cs, cnt, 0, ans);
        return ans.toArray(new String[ans.size()]);
    }

    private void dfs(char[] cs, int[] cnt, int index, List<String> ans) {
        if (index == cs.length) {
            ans.add(new String(cs));
            return;
        }
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] > 0) {
                cs[index] = (char) (i + 64);
                cnt[i]--;
                dfs(cs, cnt, index + 1, ans);
                cnt[i]++;
            }
        }
    }
}

class Question0807Permutation0 {

    public String[] permutation(String S) {
        char[] cs = S.toCharArray();
        List<String> ans = new ArrayList<>();
        dfs(cs, new char[cs.length], 0, new boolean[cs.length], ans);
        return ans.toArray(new String[ans.size()]);
    }

    private void dfs(char[] origin, char[] cs, int index, boolean[] visited, List<String> ans) {
        int n = cs.length;
        if (index == n) {
            ans.add(new String(cs));
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                cs[index] = origin[i];
                dfs(origin, cs, index + 1, visited, ans);
                visited[i] = false;
            }
        }
    }
}

class Question0807Permutation1 {

    public String[] permutation(String S) {
        List<String> ans = new ArrayList<>();
        dfs(S.toCharArray(), 0, ans);
        return ans.toArray(new String[ans.size()]);
    }

    private void dfs(char[] cs, int index, List<String> ans) {
        int n = cs.length;
        if (index == n) {
            ans.add(new String(cs));
            return;
        }
        for (int i = index; i < n; i++) {
            swap(cs, index, i);
            dfs(cs, index + 1, ans);
            swap(cs, index, i);
        }
    }

    private void swap(char[] cs, int i, int j) {
        if (i != j) {
            cs[i] = (char) (cs[i] ^ cs[j]);
            cs[j] = (char) (cs[i] ^ cs[j]);
            cs[i] = (char) (cs[i] ^ cs[j]);
        }
    }
}
