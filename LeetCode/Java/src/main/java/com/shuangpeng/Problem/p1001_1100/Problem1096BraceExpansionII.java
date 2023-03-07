package com.shuangpeng.Problem.p1001_1100;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
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

class Problem1096BraceExpansionII0 {

    int index = 0;

    public List<String> braceExpansionII(String expression) {
        index = 0;
        List<String> ans = dfs(expression.toCharArray());
        ans.sort(String::compareTo);
        return ans;
    }

    private List<String> dfs(char[] cs) {
        int n = cs.length;
        List<String> ans = new ArrayList<>();
        ans.add("");
        while (index < n && cs[index] != ',' && cs[index] != '}') {
            List<String> list = new ArrayList<>();
            if (cs[index] != '{') {
                StringBuilder sb = new StringBuilder();
                while (index < n && cs[index] >= 'a' && cs[index] <= 'z') {
                    sb.append(cs[index++]);
                }
                list.add(sb.toString());
            } else {
                Set<String> set = new HashSet<>();
                while (cs[index] != '}') {
                    index++;
                    set.addAll(dfs(cs));
                }
                index++;
                list.addAll(set);
            }
            List<String> tmp = new ArrayList<>(ans.size() * list.size());
            for (String s1 : ans) {
                for (String s2 : list) {
                    tmp.add(s1 + s2);
                }
            }
            ans = tmp;
        }
        return ans;
    }
}

class Problem1096BraceExpansionII1 {

    public List<String> braceExpansionII(String expression) {
        Deque<Character> op = new ArrayDeque<>();
        List<List<String>> stack = new ArrayList<>();
        char[] cs = expression.toCharArray();
        int n = cs.length;
        for (int i = 0; i < n; i++) {
            char c = expression.charAt(i);
            if (c == '{') {
                if (i > 0 && cs[i - 1] != ',' && cs[i - 1] != '{') {
                    op.push('*');
                }
                op.push('{');
            } else if (c == '}') {
                while (op.peek() != '{') {
                    calculate(stack, op);
                }
                op.pop();
            } else if (c == ',') {
                while (op.peek() == '*') {
                    calculate(stack, op);
                }
                op.push('+');
            } else {
                if (i > 0 && cs[i - 1] != '{' && cs[i - 1] != ',') {
                    op.push('*');
                }
                List<String> list = new ArrayList<>();
                list.add(String.valueOf(cs[i]));
                stack.add(list);
            }
        }
        while (!op.isEmpty()) {
            calculate(stack, op);
        }
        stack.get(0).sort(String::compareTo);
        return stack.get(0);
    }

    private void calculate(List<List<String>> stack, Deque<Character> op) {
        int n = stack.size();
        List<String> l1 = stack.get(n - 2), l2 = stack.get(n - 1);
        char o = op.pop();
        if (o == '+') {
            Set<String> set = new HashSet<>(l1);
            set.addAll(l2);
            stack.set(n - 2, new ArrayList<>(set));
        } else {
            List<String> list = new ArrayList<>(l1.size() * l2.size());
            for (String s1 : l1) {
                for (String s2 : l2) {
                    list.add(s1 + s2);
                }
            }
            stack.set(n - 2, list);
        }
        stack.remove(n - 1);
    }
}

class Problem1096BraceExpansionII2 {

    Set<String> set;

    public List<String> braceExpansionII(String expression) {
        set = new HashSet<>();
        dfs(expression);
        List<String> ans = new ArrayList<>(set);
        ans.sort(String::compareTo);
        return ans;
    }

    private void dfs(String s) {
        int i = s.indexOf('}');
        if (i == -1) {
            set.add(s);
            return;
        }
        int j = i;
        while (s.charAt(i) != '{') {
            i--;
        }
        String a = s.substring(0, i), c = s.substring(j + 1);
        for (String b : s.substring(i + 1, j).split(",")) {
            dfs(a + b + c);
        }
    }
}
