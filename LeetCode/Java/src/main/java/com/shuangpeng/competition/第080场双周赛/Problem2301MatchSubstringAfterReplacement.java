package com.shuangpeng.competition.第080场双周赛;

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
}
