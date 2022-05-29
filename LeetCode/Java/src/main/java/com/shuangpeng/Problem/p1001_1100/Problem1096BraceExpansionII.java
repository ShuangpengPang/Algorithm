package com.shuangpeng.Problem.p1001_1100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: Problem1096BraceExpansionII（花括号展开II）
 * @Date 2022/5/27 12:01 PM
 * @Version 1.0
 */
public class Problem1096BraceExpansionII {

    static final int UNION = 1, CONCAT = 2;

    public List<String> braceExpansionII(String expression) {
        List<String> ans = getExpression(expression.toCharArray(), 0, expression.length());
        ans.sort(String::compareTo);
        return ans;
    }

    public List<String> getExpression(char[] expression, int s, int e) {
        List<List<String>> lists = new ArrayList<>();
        int type = getType(expression, s, e);
        if (type == UNION) {
            int count = 0;
            for (int i = s + 1, start = i; i < e; ++i) {
                if (i == e - 1) {
                    lists.add(getExpression(expression, start, i));
                } else {
                    char c = expression[i];
                    if (c == ',' && count == 0) {
                        lists.add(getExpression(expression, start, i));
                        start = i + 1;
                    } else if (c == '{') {
                        ++count;
                    } else if (c == '}') {
                        --count;
                    }
                }
            }
        } else {
            int count = 0;
            for (int i = s, start = i; i <= e; ++i) {
                if (i == e) {
                    if (start == s) {
                        List<String> list = new ArrayList<>();
                        list.add(new String(expression, s, e - s));
                        return list;
                    }
                    if (start < e) {
                        lists.add(getExpression(expression, start, i));
                    }
                    break;
                }
                char c = expression[i];
                if (c == '{') {
                    if (count == 0) {
                        if (start < i) {
                            lists.add(getExpression(expression, start, i));
                        }
                        start = i;
                    }
                    ++count;
                } else if (c == '}') {
                    --count;
                    if (count == 0) {
                        if (start == s && i == e - 1) {
                            return getExpression(expression, s + 1, e - 1);
                        }
                        lists.add(getExpression(expression, start, i + 1));
                        start = i + 1;
                    }
                }
            }
        }
        if (type == UNION) {
            Set<String> set = new HashSet<>();
            for (List<String> list : lists) {
                for (String str : list) {
                    set.add(str);
                }
            }
            return new ArrayList<>(set);
        }
        List<String> ans = new ArrayList<>();
        dfs(lists, 0, new StringBuilder(), ans);
        return ans;
    }

    private int getType(char[] expression, int s, int e) {
        if (expression[s] != '{' || expression[e - 1] != '}') {
            return CONCAT;
        }
        int count = 0;
        int type = CONCAT;
        for (int i = s + 1; i < e - 1; ++i) {
            char c = expression[i];
            if (count < 0) {
                return CONCAT;
            }
            if (c == '{') {
                ++count;
            } else if (c == '}') {
                --count;
            } else if (c == ',' && count == 0) {
                type = UNION;
            }
        }
        return type;
    }

    private void dfs(List<List<String>> lists, int i, StringBuilder sb, List<String> ans) {
        int n = lists.size();
        if (i == n) {
            ans.add(sb.toString());
            return;
        }
        List<String> list = lists.get(i);
        int length = sb.length();
        for (String s : list) {
            sb.append(s);
            dfs(lists, i + 1, sb, ans);
            sb.setLength(length);
        }
    }
}
