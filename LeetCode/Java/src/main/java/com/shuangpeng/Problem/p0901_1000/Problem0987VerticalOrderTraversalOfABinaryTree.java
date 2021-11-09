package com.shuangpeng.Problem.p0901_1000;

import com.shuangpeng.common.TreeNode;

import java.util.*;

public class Problem0987VerticalOrderTraversalOfABinaryTree {

    public List<List<Integer>> verticalTraversal0(TreeNode root) {
        List<Map<Integer, List<Integer>>> maps = new LinkedList<>();
        dfs(root, maps, 0, 0, true);
        List<List<Integer>> answer = new ArrayList<>(maps.size());
        for (Map<Integer, List<Integer>> map : maps) {
            List<Integer> lists = new ArrayList<>();
            for (int key : map.keySet()) {
                List<Integer> list = map.get(key);
                Collections.sort(list);
                lists.addAll(list);
            }
            answer.add(lists);
        }
        return answer;
    }

    private int dfs(TreeNode root, List<Map<Integer, List<Integer>>> maps, int i, int level, boolean isLeft) {
        if (root == null) {
            return -1;
        }
        int j = 0;
        if (isLeft) {
            if (i == 0) {
                Map<Integer, List<Integer>> map = new TreeMap<>(Comparator.comparingInt(a -> a));
                List<Integer> list = new ArrayList<>();
                list.add(root.val);
                map.put(level, list);
                j = 0;
                maps.add(j, map);
            } else {
                j = i - 1;
                Map<Integer, List<Integer>> map = maps.get(j);
                List<Integer> list = map.getOrDefault(level, new ArrayList<>());
                list.add(root.val);
                map.put(level, list);
            }
        } else {
            j = i + 1;
            if (j >= maps.size()) {
                Map<Integer, List<Integer>> map = new TreeMap<>(Comparator.comparingInt(a -> a));
                List<Integer> list = new ArrayList<>();
                list.add(root.val);
                map.put(level, list);
                maps.add(map);
            } else {
                Map<Integer, List<Integer>> map = maps.get(j);
                List<Integer> list = map.getOrDefault(level, new ArrayList<>());
                list.add(root.val);
                map.put(level, list);
            }
        }
        int leftIndex = dfs(root.left, maps, j, level + 1, true);
        int k = leftIndex == -1 ? j : leftIndex + 1;
        int rightIndex = dfs(root.right, maps, k, level + 1, false);
        return Math.max(k, rightIndex - 1);
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<int[]> nodes = new ArrayList<>();
        dfs(root, 0, 0, nodes);
        Collections.sort(nodes, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[2] - b[2];
        });
        List<List<Integer>> answer = new ArrayList<>();
        int previous = Integer.MIN_VALUE;
        for (int[] node : nodes) {
            int col = node[0];
            if (col != previous) {
                previous = col;
                List<Integer> list = new ArrayList<>();
                answer.add(list);
            }
            answer.get(answer.size() - 1).add(node[2]);
        }
        return answer;
    }

    private void dfs(TreeNode root, int row, int col, List<int[]> nodes) {
        if (root == null) {
            return;
        }
        nodes.add(new int[]{col, row, root.val});
        dfs(root.left, row + 1, col - 1, nodes);
        dfs(root.right, row + 1, col + 1, nodes);
    }
}
