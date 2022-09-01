package com.shuangpeng.competition.双周赛.第080场双周赛;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Description: Problem2301MatchSubstringAfterReplacement（替换字符后匹配）
 * @Date 2022/6/25 5:05 PM
 * @Version 1.0
 */
public class Problem2301MatchSubstringAfterReplacement {

    // 比赛时写法
    public boolean matchReplacement0(String s, String sub, char[][] mappings) {
        int n = s.length(), m = sub.length();
        Map<Character, Set<Character>> map = new HashMap<>();
        for (char[] mp : mappings) {
            map.putIfAbsent(mp[0], new HashSet<>());
            map.get(mp[0]).add(mp[1]);
        }
        for (int i = 0; i <= n - m; ++i) {
            boolean valid = true;
            for (int j = 0; j < m; ++j) {
                char ci = s.charAt(i + j);
                char cj = sub.charAt(j);
                if (s.charAt(i + j) != sub.charAt(j) && (!map.containsKey(sub.charAt(j)) || !map.get(cj).contains(ci))) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                return true;
            }
        }
        return false;
    }

    public boolean matchReplacement(String s, String sub, char[][] mappings) {
        int n1 = s.length(), n2 = sub.length();
        if (n1 < n2) {
            return false;
        }
        Map<Character, Set<Character>> map = new HashMap<>();
        for (char[] m : mappings) {
            map.putIfAbsent(m[0], new HashSet<>());
            map.get(m[0]).add(m[1]);
        }
        for (int i = 0; i <= n1 - n2; i++) {
            boolean valid = true;
            for (int j = 0; j < n2; j++) {
                char c1 = s.charAt(i + j), c2 = sub.charAt(j);
                if (c1 != c2 && (!map.containsKey(c2) || !map.get(c2).contains(c1))) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                return true;
            }
        }
        return false;
    }

//    public boolean matchReplacement(String s, String sub, char[][] mappings) {
//        boolean[][] map = new boolean[257][257];
//        for (char[] m : mappings) map[m[0]][m[1]] = true;
//        for (int i = 0; i < 257; ++i) map[i][i] = true;
//        int len1 = s.length(), len2 = sub.length();
//        char[] cs1 = s.toCharArray(), cs2 = sub.toCharArray();
//        int[] next = getNext(cs2, map);
//        int j = 0;
//        for (int i = 0; i < len1 && j < len2; ++i) {
//            while (j != -1 && !map[cs2[j]][cs1[i]]) j = next[j];
//            j++;
//        }
//        return j == len2;
//    }
//
//    int[] getNext(char[] cs, boolean[][] map) {
//        int len = cs.length;
//        int[] next = new int[len];
//        int i = 0, j = -1;
//        next[0] = -1;
//        while (i < len - 1) {
//            if (j == -1 || cs[j] == cs[i] || (map[cs[i]][cs[j]] && map[cs[j]][cs[i]])) {
//                if (cs[++j] == cs[++i] || (map[cs[i]][cs[j]] && map[cs[j]][cs[i]])) next[i] = next[j];
//                else next[i] = j;
//            }
//            else j = next[j];
//        }
//        return next;
//    }
}
