package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem1530NumberOfGoodLeafNodesPairs {

    private int answer = 0;

    public int countPairs(TreeNode root, int distance) {
        if (root == null) {
            return 0;
        }
        dfs(root, distance);
        return answer;
    }

    private Map<Integer, Integer> dfs(TreeNode root, int distance) {
        if (root == null) {
            return new HashMap<>();
        }
        Map<Integer, Integer> leftMap = dfs(root.left, distance);
        Map<Integer, Integer> rightMap = dfs(root.right, distance);
        if (leftMap.isEmpty() && rightMap.isEmpty()) {
            return new HashMap<Integer, Integer>() {{
                put(1, 1);
            }};
        }
        if (leftMap.isEmpty() || rightMap.isEmpty()) {
            Map<Integer, Integer> map = leftMap.isEmpty() ? rightMap : leftMap;
            Set<Integer> set = new HashSet<>(map.keySet());
            for (int key : set) {
                int count = map.get(key);
                map.remove(key);
                if (key < distance) {
                    map.put(key + 1, count);
                }
            }
            if (map.isEmpty()) {
                map.put(distance + 2, 0);
            }
            return map;
        }
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
}
