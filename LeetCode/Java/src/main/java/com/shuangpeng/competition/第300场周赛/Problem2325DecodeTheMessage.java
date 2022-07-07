package com.shuangpeng.competition.第300场周赛;

import java.util.Arrays;

/**
 * @Description: Problem2325DecodeTheMessage（解密消息）
 * @Date 2022/7/7 6:04 PM
 * @Version 1.0
 */
public class Problem2325DecodeTheMessage {

    public String decodeMessage(String key, String message) {
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
}
