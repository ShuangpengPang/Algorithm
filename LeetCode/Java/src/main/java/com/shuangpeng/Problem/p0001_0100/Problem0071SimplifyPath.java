package com.shuangpeng.Problem.p0001_0100;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Problem0071SimplifyPath {

    public String simplifyPath0(String path) {
        int n = path.length();
        Deque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            int j = i + 1;
            while (j < n && path.charAt(j) == '/') {
                ++j;
            }
            i = j;
            if ((j + 2 < n && path.startsWith("../", j))
                    || (j + 1 == n - 1 && path.startsWith("..", j))) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                i = j + 1;
            } else if ((j + 1 < n && path.startsWith("./", j))
                    || (j == n - 1 && path.charAt(j) == '.')) {
                i = j;
            } else if (j < n) {
                while (j < n && path.charAt(j) != '/') {
                    ++j;
                }
                stack.push(path.substring(i, j));
                i = j - 1;
            }
        }
        if (stack.isEmpty()) {
            return new String("/");
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append('/').append(stack.pollLast());
        }
        return sb.toString();
    }

    public String simplifyPath1(String path) {
        Stack<String> stack = new Stack<>();
        StringBuilder ret = new StringBuilder();
        for (String p : path.split("/")) {
            if (!stack.empty() && p.equals("..")) {
                stack.pop();
            } else if (!" ..".contains(p)) {
                stack.push(p);
            }
        }
        for (String i : stack) {
            ret.append("/" + i);
        }
        return ret.length() == 0 ? "/" : ret.toString();
    }

    public String simplifyPath(String path) {
        int n = path.length();
        StringBuilder sb = new StringBuilder();
        Deque<String> stack = new LinkedList<>();
        int i = 0;
        while (i < n) {
            if (path.charAt(i) == '/') {
                ++i;
                continue;
            }
            while (i < n && path.charAt(i) != '/') {
                sb.append(path.charAt(i));
                ++i;
            }
            String s = sb.toString();
            sb.delete(0, sb.length());
            if (s.equals(".")) {
                continue;
            }
            if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else {
                stack.offerLast(s);
            }
        }
        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) {
            ans.append('/').append(stack.pollFirst());
        }
        return ans.length() == 0 ? "/" : ans.toString();
    }

//    public static void main(String[] args) {
//        Problem0071SimplifyPath a = new Problem0071SimplifyPath();
//        a.simplifyPath("/a//b////c/d//././/..");
//    }
}
