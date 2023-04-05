package com.shuangpeng.Problem.p0901_1000;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0966VowelSpellchecker（元音拼写检查器）
 * @date 2023/4/5 7:42 PM
 */
public class Problem0966VowelSpellchecker {

    public String[] spellchecker(String[] wordlist, String[] queries) {
        int m = wordlist.length, n = queries.length;
        Set<String> set = new HashSet<>();
        Map<String, Integer> m1 = new HashMap<>(), m2 = new HashMap<>();
        for (int i = 0; i < m; i++) {
            String w = wordlist[i];
            set.add(w);
            w = w.toLowerCase();
            if (!m1.containsKey(w)) {
                m1.put(w, i);
            }
            w = w.replaceAll("[a,e,i,o,u]", "a");
            if (!m2.containsKey(w)) {
                m2.put(w, i);
            }
        }
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            String w = queries[i];
            if (set.contains(w)) {
                ans[i] = w;
                continue;
            }
            w = w.toLowerCase();
            int index = m1.getOrDefault(w, -1);
            if (index != -1) {
                ans[i] = wordlist[index];
                continue;
            }
            w = w.replaceAll("[a,e,i,o,u]", "a");
            index = m2.getOrDefault(w, -1);
            ans[i] = index == -1 ? "" : wordlist[index];
        }
        return ans;
    }
}

class Problem0966VowelSpellchecker0 {

    public String[] spellchecker(String[] wordlist, String[] queries) {
        Set<String> set = new HashSet<>();
        Map<String, String> m1 = new HashMap<>(), m2 = new HashMap<>();
        for (String w : wordlist) {
            set.add(w);
            String lowerCase = w.toLowerCase();
            m1.putIfAbsent(lowerCase, w);
            m2.putIfAbsent(convert(lowerCase), w);
        }
        int n = queries.length;
        String[] ans = new String[n];
        for (int i = 0; i < n; i++) {
            String w = queries[i];
            if (set.contains(w)) {
                ans[i] = w;
                continue;
            }
            String lowerCase = w.toLowerCase();
            if (m1.containsKey(lowerCase)) {
                ans[i] = m1.get(lowerCase);
                continue;
            }
            String s = convert(lowerCase);
            if (m2.containsKey(s)) {
                ans[i] = m2.get(s);
            } else {
                ans[i] = "";
            }
        }
        return ans;
    }

    private String convert(String w) {
        char[] cs = w.toCharArray();
        for (int i = cs.length - 1; i >= 0; i--) {
            if ("aeiou".indexOf(cs[i]) >= 0) {
                cs[i] = '*';
            }
        }
        return new String(cs);
    }
}
