package com.shuangpeng;

import java.util.HashMap;
import java.util.Map;

public class Problem0010RegularExpressionMatching {

    public static void main(String[] args) {
        Problem0010RegularExpressionMatching a = new Problem0010RegularExpressionMatching();
        String s = "mississippi", p = "mis*is*p*.";
        System.err.println("result is: " + a.isMatch(s, p));
    }

    Map<String, Boolean> map;

    public boolean isMatch(String s, String p) {
        if (s == null || p == null) {
            return false;
        }
        map = new HashMap<>();
        return isMatch(s, p, 0, 0);
    }

    public boolean isMatch(String s, String p, int sStart, int pStart) {
        int sLength = s.length();
        int pLength = p.length();
        String key = "" + sStart + "," + pStart;
        if (map.containsKey(key)) {
            return map.get(key);
        }
        if (sStart == sLength && pStart == pLength) {
            map.put(key, true);
            return true;
        }
        if (sStart == sLength && pLength - pStart >= 2 && p.charAt(pStart + 1) == '*') {
            boolean result = isMatch(s, p, sStart, pStart + 2);
            map.put(key, result);
            return result;
        }
        if (sStart >= sLength || pStart >= pLength) {
            map.put(key, false);
            return false;
        }
        if (p.substring(pStart, pLength).equals(".*")) {
            map.put(key, true);
            return true;
        }
        if (pLength - pStart == 2) {
            if (p.charAt(pLength - 1) == '*') {
                char c = p.charAt(pStart);
                for (int i = sStart; i < sLength; i++) {
                    if (s.charAt(i) != c) {
                        map.put(key, false);
                        return false;
                    }
                }
                map.put(key, true);
                return true;
            }
        }
        char sc = s.charAt(sStart);
        char pc = p.charAt(pStart);
        if (pStart == pLength - 1) {
            if (sStart < sLength - 1) {
                map.put(key, false);
                return false;
            }
            if (pc != '.' && sc != pc) {
                map.put(key, false);
                return false;
            }
            map.put(key, true);
            return true;
        }
        char pn = p.charAt(pStart + 1);
        if (pn != '*') {
            if (pc == '.' || sc == pc) {
                boolean result = isMatch(s, p, sStart + 1, pStart + 1);
                map.put(key, result);
                return result;
            }
            map.put(key, false);
            return false;
        } else {
            // pn = '*'
            if (pc == '.' || pc == sc) {
                boolean result = isMatch(s, p, sStart + 1, pStart) || isMatch(s, p, sStart + 1, pStart + 2)
                        || isMatch(s, p, sStart, pStart + 2);
                map.put(key, result);
                return result;
            } else {
                boolean result = isMatch(s, p, sStart, pStart + 2);
                map.put(key, result);
                return result;
            }
        }
    }
}
