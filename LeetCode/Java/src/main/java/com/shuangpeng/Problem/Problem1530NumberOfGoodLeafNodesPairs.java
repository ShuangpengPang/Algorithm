package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.*;

public class Problem1530NumberOfGoodLeafNodesPairs {

    private int answer = 0;

    public int countPairs0(TreeNode root, int distance) {
        if (root == null) {
            return 0;
        }
        dfs0(root, distance);
        return answer;
    }

    private Map<Integer, Integer> dfs0(TreeNode root, int distance) {
        if (root == null) {
            return new HashMap<>();
        }
        Map<Integer, Integer> leftMap = dfs0(root.left, distance);
        Map<Integer, Integer> rightMap = dfs0(root.right, distance);
        if (leftMap.isEmpty() && rightMap.isEmpty()) {
            return new HashMap<Integer, Integer>() {{
                put(1, 1);
            }};
        }
//        if (leftMap.isEmpty() || rightMap.isEmpty()) {
//            Map<Integer, Integer> map = leftMap.isEmpty() ? rightMap : leftMap;
//            List<Integer> list = new ArrayList<>(map.keySet());
//            list.sort((a, b) -> b - a);
//            for (int key : list) {
//                int count = map.get(key);
//                map.remove(key);
//                if (key < distance) {
//                    map.put(key + 1, count);
//                }
//            }
//            if (map.isEmpty()) {
//                map.put(distance + 2, 0);
//            }
//            return map;
//        }
        Set<Integer> leftSet = leftMap.keySet();
        Set<Integer> rightSet = rightMap.keySet();
        for (int leftKey : leftSet) {
            for (int rightKey : rightSet) {
                if (leftKey + rightKey <= distance) {
                    answer += leftMap.get(leftKey) * rightMap.get(rightKey);
                }
            }
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int key : leftSet) {
            if (key < distance) {
                map.put(key + 1, leftMap.get(key));
            }
        }
        for (int key : rightSet) {
            if (key < distance) {
                map.put(key + 1, map.getOrDefault(key + 1, 0) + rightMap.get(key));
            }
        }
        if (map.isEmpty()) {
            map.put(distance + 2, 0);
        }
        return map;
    }


    public int countPairs1(TreeNode root, int distance) {
        Pair pair = dfs(root, distance);
        return pair.count;
    }

    // 对于 dfs(root,distance)，同时返回：
    // 1）每个叶子节点与 root 之间的距离
    // 2) 以 root 为根节点的子树中好叶子节点对的数量
    public Pair dfs(TreeNode root, int distance) {
        int[] depths = new int[distance + 1];
        boolean isLeaf = root.left == null && root.right == null;
        if (isLeaf) {
            depths[0] = 1;
            return new Pair(depths, 0);
        }

        int[] leftDepths = new int[distance + 1];
        int[] rightDepths = new int[distance + 1];
        int leftCount = 0, rightCount = 0;
        if (root.left != null) {
            Pair leftPair = dfs(root.left, distance);
            leftDepths = leftPair.depths;
            leftCount = leftPair.count;
        }
        if (root.right != null) {
            Pair rightPair = dfs(root.right, distance);
            rightDepths = rightPair.depths;
            rightCount = rightPair.count;
        }

        for (int i = 0; i < distance; i++) {
            depths[i + 1] += leftDepths[i];
            depths[i + 1] += rightDepths[i];
        }

        int cnt = 0;
        for (int i = 0; i <= distance; i++) {
            for (int j = 0; j + i + 2 <= distance; j++) {
                cnt += leftDepths[i] * rightDepths[j];
            }
        }
        return new Pair(depths, cnt + leftCount + rightCount);
    }

    class Pair {
        int[] depths;
        int count;

        public Pair(int[] depths, int count) {
            this.depths = depths;
            this.count = count;
        }
    }


    private int result = 0;

    public int countPairs(TreeNode root, int distance) {
        if (root == null) {
            return 0;
        }
        recurse(root, distance);
        return result;
    }

    private int[] recurse(TreeNode root, int distance) {
        int[] depth = new int[distance];
        if (root.left == null && root.right == null) {
            depth[0] = 1;
            return depth;
        }
        int[] leftDepth = new int[distance];
        int[] rightDepth = new int[distance];
        if (root.left != null) {
            leftDepth = recurse(root.left, distance);
        }
        if (root.right != null) {
            rightDepth = recurse(root.right, distance);
        }
        for (int i = 0; i < distance; i++) {
            for (int j = 0; i + j + 2 <= distance; j++) {
                result += leftDepth[i] * rightDepth[j];
            }
        }
        for (int i = 0; i < distance - 1; i++) {
            depth[i + 1] += leftDepth[i];
            depth[i + 1] += rightDepth[i];
        }
        return depth;
    }
}
