package com.shuangpeng.Problem.p0701_0800;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Problem0784LetterCasePermutation（字母大小写全排列）
 * @Date 2022/10/21 3:36 PM
 * @Version 1.0
 */
public class Problem0784LetterCasePermutation {

    public List<String> letterCasePermutation0(String s) {
        List<String> ans = new ArrayList<>();
        dfs(s.toCharArray(), 0, ans);
        return ans;
    }

    private void dfs(char[] cs, int i, List<String> ans) {
        if (i == cs.length) {
            ans.add(new String(cs));
            return;
        }
        dfs(cs, i + 1, ans);
        char c = cs[i];
        if (c >= 'a' && c <= 'z') {
            cs[i] = (char) (c - 'a' + 'A');
            dfs(cs, i + 1, ans);
            cs[i] = c;
        } else if (c >= 'A' && c <= 'Z') {
            cs[i] = (char) (c - 'A' + 'a');
            dfs(cs, i + 1, ans);
            cs[i] = c;
        }
    }

    public List<String> letterCasePermutation1(String s) {
        List<StringBuilder> list = new ArrayList<>();
        list.add(new StringBuilder());
        for (char c : s.toCharArray()) {
            int n = list.size();
            if (Character.isLetter(c)) {
                for (int i = 0; i < n; i++) {
                    list.add(new StringBuilder(list.get(i)));
                    list.get(i).append(Character.toLowerCase(c));
                    list.get(n + i).append(Character.toUpperCase(c));
                }
            } else {
                for (int i = 0; i < n; i++) {
                    list.get(i).append(c);
                }
            }
        }
        List<String> ans = new ArrayList<>(list.size());
        for (StringBuilder sb : list) {
            ans.add(sb.toString());
        }
        return ans;
    }

    public List<String> letterCasePermutation(String s) {
        char[] cs = s.toCharArray();
        int N = 0, n = cs.length;
        for (char c : cs) {
            if (Character.isLetter(c)) {
                N++;
            }
        }
        int[] h = new int[N];
        N = 0;
        for (int i = 0; i < n; i++) {
            if (Character.isLetter(cs[i])) {
                h[N++] = i;
            }
        }
        int M = 1 << N;
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            char[] copy = cs.clone();
            for (int j = 0; j < N; j++) {
                int idx = h[j];
                if (((i >> j) & 1) == 1) {
                    copy[idx] = Character.toUpperCase(copy[idx]);
                } else {
                    copy[idx] = Character.toLowerCase(copy[idx]);
                }
            }
            ans.add(new String(copy));
        }
        return ans;
    }
}

class Problem0784LetterCasePermutation0 {

    public List<String> letterCasePermutation(String s) {
        List<String> ans = new ArrayList<>();
        dfs(s.toCharArray(), 0, ans);
        return ans;
    }

    private void dfs(char[] cs, int idx, List<String> ans) {
        if (cs.length == idx) {
            ans.add(new String(cs));
            return;
        }
        if (!Character.isLetter(cs[idx])) {
            dfs(cs, idx + 1, ans);
            return;
        }
        dfs(cs, idx + 1, ans);
        cs[idx] = (char) (cs[idx] ^ 32);
        dfs(cs, idx + 1, ans);
        cs[idx] = (char) (cs[idx] ^ 32);
    }
}