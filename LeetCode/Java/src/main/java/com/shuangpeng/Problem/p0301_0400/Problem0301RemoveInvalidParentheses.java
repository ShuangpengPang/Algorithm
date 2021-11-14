package com.shuangpeng.Problem.p0301_0400;

import java.util.*;

public class Problem0301RemoveInvalidParentheses {

//    Set<String> resultSet = new HashSet<>();
    int minOperation = Integer.MAX_VALUE;
    public List<String> removeInvalidParentheses0(String s) {
        resultSet.clear();
        recurse(s, 0, 0, 0, 0, new StringBuilder());
        return new ArrayList<>(resultSet);
    }

    // 回溯
    public void recurse(String s, int index, int removeCount, int leftCount, int rightCount, StringBuilder result) {
        if (index == s.length()) {
            if (leftCount == rightCount && removeCount <= minOperation) {
                if (removeCount < minOperation) {
                    resultSet.clear();
                    minOperation = removeCount;
                }
                resultSet.add(result.toString());
            }
        } else {
            char ch = s.charAt(index);
            int length = result.length();
            if (ch != '(' && ch != ')') {
                result.append(ch);
                recurse(s, index + 1, removeCount, leftCount, rightCount, result);
                result.deleteCharAt(length);
            } else if (ch == '(') {
                recurse(s, index + 1, removeCount + 1, leftCount, rightCount, result);

                result.append(ch);
                recurse(s, index + 1, removeCount, leftCount + 1, rightCount, result);
                result.deleteCharAt(length);
            } else {
                recurse(s, index + 1, removeCount + 1, leftCount, rightCount, result);
                if (leftCount > rightCount) {
                    result.append(ch);
                    recurse(s, index + 1, removeCount, leftCount, rightCount + 1, result);
                    result.deleteCharAt(length);
                }
            }
        }
    }


    Set<String> resultSet = new HashSet<>();
    public List<String> removeInvalidParentheses1(String s) {
        resultSet.clear();
        int leftRem = 0;
        int rightRem = 0;
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                leftRem++;
            } else if (ch == ')') {
                rightRem += leftRem > 0 ? 0 : 1;
                leftRem -= leftRem > 0 ? 1 : 0;
            }
        }
        backtrack(s, 0, 0, 0, leftRem, rightRem, new StringBuilder());
        return new ArrayList<>(resultSet);
    }

    public void backtrack(String s, int index, int leftCount, int rightCount, int leftRem, int rightRem, StringBuilder result) {
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0) {
                resultSet.add(result.toString());
            }
        } else {
            char ch = s.charAt(index);
            // 删除
            if (ch == '(' && leftRem > 0) {
                backtrack(s, index + 1, leftCount, rightCount, leftRem - 1, rightRem, result);
            } else if (ch == ')' && rightRem > 0) {
                backtrack(s, index + 1, leftCount, rightCount, leftRem, rightRem - 1, result);
            }

            int length = result.length();
            result.append(ch);
            if (ch != '(' && ch != ')') {
                backtrack(s, index + 1, leftCount, rightCount, leftRem, rightRem, result);
            } else if (ch == '(') {
                backtrack(s, index + 1, leftCount + 1, rightCount, leftRem, rightRem, result);
            } else if (ch == ')' && leftCount > rightCount) {
                backtrack(s, index + 1, leftCount, rightCount + 1, leftRem, rightRem, result);
            }
            result.deleteCharAt(length);
        }
    }

    public List<String> removeInvalidParentheses2(String s) {
        int n = s.length();
        int lRemove = 0, rRemove = 0;
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                ++lRemove;
            } else if (c == ')') {
                if (lRemove > 0) {
                    --lRemove;
                } else {
                    ++rRemove;
                }
            }
        }
        List<String> ans = new ArrayList<>();
        dfs(s, 0, 0, 0, lRemove, rRemove, ans);
        return ans;
    }

    private void dfs(String s, int start, int lCount, int rCount, int lRemove, int rRemove, List<String> ans) {
        if (lRemove == 0 && rRemove == 0) {
            if (check(s)) {
                ans.add(s);
            }
            return;
        }
        int n = s.length();
        for (int i = start; i < n; ++i) {
            if (lRemove + rRemove > n - i || lCount < rCount) {
                return;
            }
            char c = s.charAt(i);
            if (i == start || c != s.charAt(i - 1)) {
                if (c == '(' && lRemove > 0) {
                    dfs(s.substring(0, i) + s.substring(i + 1, n), i, lCount, rCount, lRemove - 1, rRemove, ans);
                } else if (c == ')' && rRemove > 0) {
                    dfs(s.substring(0, i) + s.substring(i + 1, n), i, lCount, rCount, lRemove, rRemove - 1, ans);
                }
            }
            if (c == '(') {
                ++lCount;
            } else if (c == ')') {
                ++rCount;
            }
        }
    }

    private boolean check(String s) {
        int left = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                ++left;
            } else if (c == ')') {
                if (left > 0) {
                    --left;
                } else {
                    return false;
                }
            }
        }
        return left == 0;
    }

    public List<String> removeInvalidParentheses3(String s) {
        Queue<String> queue = new LinkedList<>();
        boolean find = false;
        queue.offer(s);
        List<String> ans = new ArrayList<>();
        Set<String> set = new HashSet<>();
        while (!queue.isEmpty() && !find) {
            for (int i = queue.size() - 1; i >= 0; --i) {
                String str = queue.poll();
                if (check(str)) {
                    find = true;
                    ans.add(str);
                } else {
                    int n = str.length();
                    for (int j = 0; j < n; ++j) {
                        char c = str.charAt(j);
                        if (c == '(' || c == ')') {
                            String string = str.substring(0, j) + str.substring(j + 1, n);
                            if (!set.contains(string)) {
                                set.add(string);
                                queue.offer(string);
                            }
                        }
                    }
                }
            }
        }
        return ans;
    }

    public List<String> removeInvalidParentheses(String s) {
        int length = s.length();
        int m = 0, n = 0;
        int leftCount = 0, rightCount = 0;
        for (int i = 0; i < length; ++i) {
            char c = s.charAt(i);
            if (c ==  '(') {
                ++leftCount;
                ++m;
            } else if (c == ')') {
                ++rightCount;
                if (m > 0) {
                    --m;
                } else {
                    ++n;
                }
            }
        }
        List<Integer> leftMask = new ArrayList<>();
        for (int i = 0; i < (1 << leftCount); ++i) {
            if (Integer.bitCount(i) + m == leftCount) {
                leftMask.add(i);
            }
        }
        List<Integer> rightMask = new ArrayList<>();
        for (int i = 0; i < (1 << rightCount); ++i) {
            if (Integer.bitCount(i) + n == rightCount) {
                rightMask.add(i);
            }
        }
        Set<String> set = new HashSet<>();
        for (int lMask : leftMask) {
            for (int rMask : rightMask) {
                String str = getString(s, lMask, rMask);
                if (isValid(str)) {
                    set.add(str);
                }
            }
        }
        return new ArrayList<>(set);
    }

    private String getString(String s, int lMask, int rMask) {
        int length = s.length();
        int pos1 = 0, pos2 = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                if ((lMask & (1 << pos1)) != 0) {
                    sb.append(c);
                }
                ++pos1;
            } else if (c == ')') {
                if ((rMask & (1 << pos2)) != 0) {
                    sb.append(c);
                }
                ++pos2;
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private boolean isValid(String s) {
        int count = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char c = s.charAt(i);
            if (c == '(') {
                ++count;
            } else if (c == ')') {
                if (count > 0) {
                    --count;
                } else {
                    return false;
                }
            }
        }
        return count == 0;
    }

//    public static void main(String[] args) {
//        Problem0301RemoveInvalidParentheses a = new Problem0301RemoveInvalidParentheses();
//        a.removeInvalidParentheses(")(");
//    }
}
