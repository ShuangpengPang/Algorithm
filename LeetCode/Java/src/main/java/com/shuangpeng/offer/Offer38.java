package com.shuangpeng.offer;

import com.shuangpeng.competition.Solution;

import java.util.*;

public class Offer38 {

    public String[] permutation0(String s) {
        List<Character> origin = new LinkedList<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            origin.add(s.charAt(i));
        }
        Set<String> answer = new HashSet<>();
        backtrack(origin, new ArrayList<>(), answer);
        return answer.toArray(new String[0]);
    }

    private void backtrack(List<Character> origin, List<Character> list, Set<String> result) {
        if (origin.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            for (char c : list) {
                builder.append(c);
            }
            result.add(builder.toString());
            return;
        }
        int n = origin.size();
        for (int i = 0; i < n; i++) {
            char c = origin.remove(i);
            list.add(c);
            backtrack(origin, list, result);
            list.remove(list.size() - 1);
            origin.add(i, c);
        }
    }


    List<String> rec;
    boolean[] vis;

    public String[] permutation1(String s) {
        int n = s.length();
        rec = new ArrayList<String>();
        vis = new boolean[n];
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        StringBuffer perm = new StringBuffer();
        backtrack(arr, 0, n, perm);
        int size = rec.size();
        String[] recArr = new String[size];
        for (int i = 0; i < size; i++) {
            recArr[i] = rec.get(i);
        }
        return recArr;
    }

    public void backtrack(char[] arr, int i, int n, StringBuffer perm) {
        if (i == n) {
            rec.add(perm.toString());
            return;
        }
        for (int j = 0; j < n; j++) {
            if (vis[j] || (j > 0 && !vis[j - 1] && arr[j - 1] == arr[j])) {
                continue;
            }
            vis[j] = true;
            perm.append(arr[j]);
            backtrack(arr, i + 1, n, perm);
            perm.deleteCharAt(perm.length() - 1);
            vis[j] = false;
        }
    }

    public String[] permutation(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        int n = chars.length;
        List<String> list = new ArrayList<>();
        backtrack(chars, new boolean[n], new StringBuilder(), list);
        int length = list.size();
        String[] answer = new String[length];
        for (int i = 0; i < length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    private void backtrack(char[] chars, boolean[] visited, StringBuilder builder, List<String> list) {
        if (chars.length == builder.length()) {
            list.add(builder.toString());
            return;
        }
        int n = chars.length;
        for (int i = 0; i < n; i++) {
            if (visited[i] || (i > 0 && !visited[i - 1] && chars[i - 1] == chars[i])) {
                continue;
            }
            visited[i] = true;
            builder.append(chars[i]);
            backtrack(chars, visited, builder, list);
            builder.deleteCharAt(builder.length() - 1);
            visited[i] = false;
        }
    }
}
