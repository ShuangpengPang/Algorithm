package com.shuangpeng.Problem.p0301_0400;

import java.util.*;

/**
 * @Description: Problem0388LongestAbsoluteFilePath
 * @Date 2022/4/20 10:13 AM
 * @Version 1.0
 */
public class Problem0388LongestAbsoluteFilePath {

    class FileNode {
        String name;
        boolean isFile;
        List<FileNode> nodes;
        int pathLength;

        FileNode(String name, int previousPathLength) {
            this.name = name;
            isFile = name.indexOf(".") != -1;
            if (!isFile) {
                nodes = new ArrayList<>();
            }
            pathLength = previousPathLength + name.length() + 1;
        }
    }

    public int lengthLongestPath0(String input) {
        int ans = 0;
        int n = input.length();
        FileNode root = new FileNode("", -2);
        int i = 0;
        while (i < n) {
            FileNode node = root;
            while (input.charAt(i) == '\t') {
                node = node.nodes.get(node.nodes.size() - 1);
                ++i;
            }
            StringBuilder sb = new StringBuilder();
            while (i < n && input.charAt(i) != '\n') {
                sb.append(input.charAt(i));
                ++i;
            }
            ++i;
            FileNode fileNode = new FileNode(sb.toString(), node.pathLength);
            node.nodes.add(fileNode);
            if (fileNode.isFile) {
                ans = Math.max(ans, fileNode.pathLength);
            }
        }
        return ans;
    }

    public int lengthLongestPath1(String input) {
        int n = input.length();
        Deque<Integer> stack = new ArrayDeque<>();
        int pos = 0;
        int ans = 0;
        while (pos < n) {
            int depth = 0;
            while (input.charAt(pos) == '\t') {
                ++pos;
                ++depth;
            }
            boolean isFile = false;
            int len = 0;
            while (pos < n && input.charAt(pos) != '\n') {
                if (input.charAt(pos) == '.') {
                    isFile = true;
                }
                ++len;
                ++pos;
            }
            ++pos;
            while (stack.size() > depth) {
                stack.pollLast();
            }
            if (!stack.isEmpty()) {
                len += stack.peekLast() + 1;
            }
            if (isFile) {
                ans = Math.max(ans, len);
            } else {
                stack.addLast(len);
            }
        }
        return ans;
    }

    public int lengthLongestPath2(String input) {
        int n = input.length();
        int pos = 0;
        int ans = 0;
        int[] level = new int[n + 1];

        while (pos < n) {
            /* 检测当前文件的深度 */
            int depth = 1;
            while (pos < n && input.charAt(pos) == '\t') {
                pos++;
                depth++;
            }
            /* 统计当前文件名的长度 */
            int len = 0;
            boolean isFile = false;
            while (pos < n && input.charAt(pos) != '\n') {
                if (input.charAt(pos) == '.') {
                    isFile = true;
                }
                len++;
                pos++;
            }
            /* 跳过换行符 */
            pos++;

            if (depth > 1) {
                len += level[depth - 1] + 1;
            }
            if (isFile) {
                ans = Math.max(ans, len);
            } else {
                level[depth] = len;
            }
        }
        return ans;
    }

    public int lengthLongestPath(String s) {
        Map<Integer, String> map = new HashMap<>();
        int n = s.length();
        String ans = null;
        for (int i = 0; i < n; ) {
            int level = 0;
            while (i < n && s.charAt(i) == '\t' && ++level >= 0) i++;
            int j = i;
            boolean isDir = true;
            while (j < n && s.charAt(j) != '\n') {
                if (s.charAt(j++) == '.') isDir = false;
            }
            String cur = s.substring(i, j);
            String prev = map.getOrDefault(level - 1, null);
            String path = prev == null ? cur : prev + "/" + cur;
            if (isDir) map.put(level, path);
            else if (ans == null || path.length() > ans.length()) ans = path;
            i = j + 1;
        }
        return ans == null ? 0 : ans.length();
    }
}
