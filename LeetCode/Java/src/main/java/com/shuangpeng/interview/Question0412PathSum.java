package com.shuangpeng.interview;

import com.shuangpeng.common.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Question0412PathSum {

    public int pathSum0(TreeNode root, int sum) {
        return dfs(root, sum, new HashMap<>());
    }

    private int dfs(TreeNode root, int sum, Map<TreeNode, Integer> map) {
        if (root == null) {
            return 0;
        }
        int count = map.getOrDefault(root, -1);
        if (count != -1) {
            return count;
        }
        count = recurse(root, sum) + dfs(root.left, sum, map) + dfs(root.right, sum, map);
        map.put(root, count);
        return count;
    }

    private int recurse(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int left = recurse(root.left, sum - root.val);
        int right = recurse(root.right, sum - root.val);
        return root.val == sum ? 1 + left + right : left + right;
    }

    private int answer = 0;

    public int pathSum1(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        dfs(root, sum, 0, map);
        return answer;
    }

    private void dfs(TreeNode node, int sum, int current, Map<Integer, Integer> map) {
        if (node == null) {
            return;
        }
        int s = current + node.val;
        answer += map.getOrDefault(s - sum, 0);
        map.put(s, map.getOrDefault(s, 0) + 1);
        dfs(node.left, sum, s, map);
        dfs(node.right, sum, s, map);
        map.put(s, map.get(s) - 1);
    }

    public int pathSum(TreeNode root, int sum) {
        // key是前缀和, value是大小为key的前缀和出现的次数
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        // 前缀和为0的一条路径
        prefixSumCount.put(0, 1);
        // 前缀和的递归回溯思路
        return recursionPathSum(root, prefixSumCount, sum, 0);
    }

    /**
     * 前缀和的递归回溯思路
     * 从当前节点反推到根节点(反推比较好理解，正向其实也只有一条)，有且仅有一条路径，因为这是一棵树
     * 如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
     * 所以前缀和对于当前路径来说是唯一的，当前记录的前缀和，在回溯结束，回到本层时去除，保证其不影响其他分支的结果
     * @param node 树节点
     * @param prefixSumCount 前缀和Map
     * @param target 目标值
     * @param currSum 当前路径和
     * @return 满足题意的解
     */
    private int recursionPathSum(TreeNode node, Map<Integer, Integer> prefixSumCount, int target, int currSum) {
        // 1.递归终止条件
        if (node == null) {
            return 0;
        }
        // 2.本层要做的事情
        int res = 0;
        // 当前路径上的和
        currSum += node.val;

        //---核心代码
        // 看看root到当前节点这条路上是否存在节点前缀和加target为currSum的路径
        // 当前节点->root节点反推，有且仅有一条路径，如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
        // currSum-target相当于找路径的起点，起点的sum+target=currSum，当前点到起点的距离就是target
        res += prefixSumCount.getOrDefault(currSum - target, 0);
        // 更新路径上当前节点前缀和的个数
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);
        //---核心代码

        // 3.进入下一层
        res += recursionPathSum(node.left, prefixSumCount, target, currSum);
        res += recursionPathSum(node.right, prefixSumCount, target, currSum);

        // 4.回到本层，恢复状态，去除当前节点的前缀和数量
        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
        return res;
    }
}
