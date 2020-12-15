package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Problem0337HouseRobberIII {

    public int rob0(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Map<TreeNode, Integer> robMap = new HashMap<>();
        Map<TreeNode, Integer> unrobMap = new HashMap<>();
        robRecurse(root, true, robMap, unrobMap);
        robRecurse(root, false, robMap, unrobMap);
        return Math.max(robMap.getOrDefault(root, 0), unrobMap.getOrDefault(root, 0));
    }

    public int robRecurse(TreeNode root, boolean isRob, Map<TreeNode, Integer> robMap, Map<TreeNode, Integer> unrobMap) {
        if (root == null) {
            return 0;
        }
        if (isRob) {
            if (!robMap.containsKey(root)) {
                robMap.put(root, root.val + robRecurse(root.left, false, robMap, unrobMap) + robRecurse(root.right, false, robMap, unrobMap));
            }
            return robMap.get(root);
        }
        if (!unrobMap.containsKey(root)) {
            unrobMap.put(root, Math.max(robRecurse(root.left, true, robMap, unrobMap), robRecurse(root.left, false, robMap, unrobMap))
                    + Math.max(robRecurse(root.right, true, robMap, unrobMap), robRecurse(root.right, false, robMap, unrobMap)));
        }
        return unrobMap.get(root);
    }

    class RobResult {
        int rob;
        int unRob;
    }

    public int rob(TreeNode root) {
        RobResult robResult = dfs(root);
        return Math.max(robResult.rob, robResult.unRob);
    }

    public RobResult dfs(TreeNode root) {
        if (root == null) {
            return new RobResult();
        }
        RobResult leftRobResult =  dfs(root.left);
        RobResult rightRobResult = dfs(root.right);
        RobResult robResult = new RobResult();
        robResult.rob = root.val + leftRobResult.unRob + rightRobResult.unRob;;
        robResult.unRob = Math.max(leftRobResult.unRob, leftRobResult.rob) + Math.max(rightRobResult.unRob, rightRobResult.rob);
        return robResult;
    }
}
