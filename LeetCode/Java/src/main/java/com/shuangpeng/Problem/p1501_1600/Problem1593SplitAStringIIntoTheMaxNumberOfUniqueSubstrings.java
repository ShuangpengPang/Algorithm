package com.shuangpeng.Problem.p1501_1600;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1593SplitAStringIIntoTheMaxNumberOfUniqueSubstrings（拆分字符串使唯一子字符串的数目最大）
 * @date 2023/9/8 4:49 PM
 */
public class Problem1593SplitAStringIIntoTheMaxNumberOfUniqueSubstrings {

    int ans = 0;

    public int maxUniqueSplit(String s) {
        ans = 0;
        dfs(s.toCharArray(), 0, new HashSet<>());
        return ans;
    }

    private void dfs(char[] cs, int pos, Set<String> set) {
        int n = cs.length;
        if (pos == n || set.size() + n - pos <= ans) {
            ans = Math.max(ans, set.size());
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = pos; i < n; i++) {
            sb.append(cs[i]);
            String s = sb.toString();
            if (set.add(s)) {
                dfs(cs, i + 1, set);
                set.remove(s);
            }
        }
    }
}
