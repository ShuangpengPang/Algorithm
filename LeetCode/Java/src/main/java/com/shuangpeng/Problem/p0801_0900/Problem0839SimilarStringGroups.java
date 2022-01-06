package com.shuangpeng.Problem.p0801_0900;

import java.util.HashMap;
import java.util.Map;

public class Problem0839SimilarStringGroups {

    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        Map<String, String> parent = new HashMap<>();
        for (String s : strs) {
            parent.put(s, s);
        }
        int ans = parent.size();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                String p1 = find(parent, strs[i]);
                String p2 = find(parent, strs[j]);
                if (p1.equals(p2)) {
                    continue;
                }
                if (check(strs[i], strs[j])) {
                    parent.put(p1, p2);
                    --ans;
                }
            }
        }
        return ans;
    }

    private String find(Map<String, String> parent, String s) {
        String p = parent.get(s);
        if (s.equals(p)) {
            return s;
        }
        p = find(parent, p);
        parent.put(s, p);
        return p;
    }

    private boolean check(String s1, String s2) {
        int count = 0;
        int n = s1.length();
        for (int i = 0; i < n; ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                ++count;
            }
            if (count > 2) {
                return false;
            }
        }
        return count == 0 || count == 2;
    }
}
