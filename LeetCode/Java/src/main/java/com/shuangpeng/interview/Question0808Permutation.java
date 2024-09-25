package com.shuangpeng.interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0808Permutation（面试题 08.08. 有重复字符串的排列组合）
 * @date 2024/9/24 5:08 PM
 */
public class Question0808Permutation {

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

class Question0808Permutation0 {

    public String[] permutation(String S) {
        char[] cs = S.toCharArray();
        Arrays.sort(cs);
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
            if (!visited[i] && (i == 0 || visited[i - 1] || origin[i] != origin[i - 1])) {
                visited[i] = true;
                cs[index] = origin[i];
                dfs(origin, cs, index + 1, visited, ans);
                visited[i] = false;
            }
        }
    }
}
