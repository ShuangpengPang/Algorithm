package com.shuangpeng.interview;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0809GenerateParenthesis（面试题 08.09. 括号）
 * @date 2024/9/23 7:17 PM
 */
public class Question0809GenerateParenthesis {

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        dfs(new char[n << 1], 0, n, 0, ans);
        return ans;
    }

    private void dfs(char[] cs, int index, int left, int count, List<String> ans) {
        if (index == cs.length) {
            ans.add(new String(cs));
            return;
        }
        if (left > 0) {
            cs[index] = '(';
            dfs(cs, index + 1, left - 1, count + 1, ans);
        }
        if (count > 0) {
            cs[index] = ')';
            dfs(cs, index + 1, left, count - 1, ans);
        }
    }
}
