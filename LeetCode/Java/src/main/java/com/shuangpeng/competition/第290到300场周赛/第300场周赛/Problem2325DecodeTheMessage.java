package com.shuangpeng.competition.第290到300场周赛.第300场周赛;

import java.util.Arrays;

/**
 * @Description: Problem2325DecodeTheMessage（解密消息）
 * @Date 2022/7/7 6:04 PM
 * @Version 1.0
 */
public class Problem2325DecodeTheMessage {

    // 比赛时写法
    public String decodeMessage0(String key, String message) {
        int[] map = new int[26];
        Arrays.fill(map, -1);
        int idx = 0;
        int n = key.length();
        for (int i = 0; i < n; i++) {
            int j = key.charAt(i) - 'a';
            if (j >= 0 && j < 26) {
                if (map[j] == -1) {
                    map[j] = idx++;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            int j = message.charAt(i) - 'a';
            if (j >= 0 && j < 26) {
                sb.append((char)('a' + map[j]));
            } else {
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    public String decodeMessage1(String key, String message) {
        int N = 26;
        char[] map = new char[N];
        Arrays.fill(map, 'A');
        int m = key.length();
        char c = 'a';
        for (int i = 0; i < m; i++) {
            int j = key.charAt(i) - 'a';
            if (j >= 0 && j < N && map[j] == 'A') {
                map[j] = c;
                c++;
            }
        }
        int n = message.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = message.charAt(i);
            if (ch == ' ') {
                sb.append(ch);
            } else {
                sb.append(map[ch - 'a']);
            }
        }
        return sb.toString();
    }

    public String decodeMessage(String key, String message) {
        int N = 26;
        char[] map = new char[N];
        Arrays.fill(map, '.');
        int m = key.length(), n = message.length();
        char ch = 'a';
        for (int i = 0; i < m; i++) {
            int j = key.charAt(i) - 'a';
            if (j >= 0 && j < N && map[j] == '.') {
                map[j] = ch++;
            }
        }
        char[] chars = message.toCharArray();
        for (int i = 0; i < n; i++) {
            int j = chars[i] - 'a';
            if (j >= 0 && j < N) {
                chars[i] = map[j];
            }
        }
        return new String(chars);
    }
}
