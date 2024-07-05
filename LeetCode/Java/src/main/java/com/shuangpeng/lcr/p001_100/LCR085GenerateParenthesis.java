package com.shuangpeng.lcr.p001_100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR085GenerateParenthesis（括号生成）
 * @date 2024/7/4 11:44 PM
 */
public class LCR085GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs(new char[n << 1], 0, n, 0, ans);
        return ans;
    }

    private void dfs(char[] cs, int pos, int left, int cnt, List<String> ans) {
        if (pos == cs.length) {
            ans.add(new String(cs));
            return;
        }
        if (left > 0) {
            cs[pos] = '(';
            dfs(cs, pos + 1, left - 1, cnt + 1, ans);
        }
        if (cnt > 0) {
            cs[pos] = ')';
            dfs(cs, pos + 1, left, cnt - 1, ans);
        }
    }
}

class LCR085GenerateParenthesis0 {

    public List<String> generateParenthesis(int n) {
        return dfs(0, n << 1);
    }

    List<String> dfs(int i, int j) {
        if (i == j) {
            return new ArrayList<String>() {{
                add("");
            }};
        }
        List<String> ans = new ArrayList<>();
        int n = j - i;
        for (int k = i + 1; k < j; k += 2) {
            for (String left : dfs(i + 1, k)) {
                for (String right : dfs(k + 1, j)) {
                    StringBuilder sb = new StringBuilder(n);
                    sb.append("(").append(left).append(")").append(right);
                    ans.add(sb.toString());
                }
            }
        }
        return ans;
    }
}
