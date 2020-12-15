package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Problem0437PathSumIII {

    // 递归
    public int pathSum0(TreeNode root, int sum) {
        return pathRecurse(root, sum);
    }

    public int pathRecurse(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return pathSequence(root, sum) + pathRecurse(root.left, sum) + pathRecurse(root.right, sum);
    }

    public int pathSequence(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        if (root.val == sum) {
            return 1 + pathSequence(root.left, 0) + pathSequence(root.right, 0);
        }
        return pathSequence(root.left, sum - root.val) + pathSequence(root.right, sum - root.val);
    }

    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int[] answer = new int[]{0};
        dfs(root, sum, 0, map, answer);
        return answer[0];
    }

    public void dfs(TreeNode root, int sum, int currentSum, Map<Integer, Integer> map, int[] answer) {
        if (root == null) {
            return;
        }
        currentSum += root.val;
        answer[0] += map.getOrDefault(currentSum - sum, 0);
        map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);
        dfs(root.left, sum, currentSum, map, answer);
        dfs(root.right, sum, currentSum, map, answer);
        map.put(currentSum, map.get(currentSum) - 1);
    }
}
