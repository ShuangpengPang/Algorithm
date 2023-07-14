package com.shuangpeng.Problem.p0901_1000;

import com.shuangpeng.common.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * @description:（在二叉树中分配硬币）
 * @date 2023/7/14 11:40 AM
 **/
public class Problem0979DistributeCoinsInBinaryTree {

    private int moveSteps = 0;

    class NodeInfo {
        int remain = 0;
        List<Integer> moveList;

        public NodeInfo(int remain, List<Integer> moveList) {
            this.remain = remain;
            this.moveList = moveList;
        }
    }

    public int distributeCoins0(TreeNode root) {
        dfs(root);
        return moveSteps;
    }

    private NodeInfo dfs(TreeNode node) {
        if (node == null) {
            return new NodeInfo(0, new LinkedList<>());
        }
        NodeInfo left = dfs(node.left);
        NodeInfo right = dfs(node.right);
        int value = node.val;
        if (left.remain > 0) {
            moveSteps += left.remain;
            value += left.remain;
        }
        if (right.remain > 0) {
            moveSteps += right.remain;
            value += right.remain;
        }
        List<Integer> list = mergeList(left.moveList, right.moveList);
        while (value > 0 && !list.isEmpty()) {
            moveSteps += list.get(0);
            list.remove(0);
            value--;
        }
        int remain = left.remain + right.remain + node.val - 1;
        if (remain < 0) {
            list.add(0);
        }
        return new NodeInfo(remain, list);
    }

    private List<Integer> mergeList(List<Integer> leftList, List<Integer> rightList) {
        int leftSize = leftList.size();
        int rightSize = rightList.size();
        for (int i = 0; i < leftSize; i++) {
            leftList.set(i, leftList.get(i) + 1);
        }
        for (int i = 0; i < rightSize; i++) {
            rightList.set(i, rightList.get(i) + 1);
        }
        List<Integer> list = new LinkedList<>();
        int i = 0;
        int j = 0;
        while (i < leftSize || j < rightSize) {
            if (i < leftSize && j < rightSize) {
                if (leftList.get(i) >= rightList.get(j)) {
                    list.add(leftList.get(i));
                    i++;
                } else {
                    list.add(rightList.get(j));
                    j++;
                }
            } else if (i >= leftSize) {
                list.add(rightList.get(j));
                j++;
            } else if (j >= rightSize) {
                list.add(leftList.get(i));
                i++;
            }
        }
        return list;
    }

    private int answer = 0;

    public int distributeCoins(TreeNode root) {
        recurse(root);
        return answer;
    }

    private int recurse(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = recurse(node.left);
        int right = recurse(node.right);
        answer += Math.abs(left) + Math.abs(right);
        return left + right + node.val - 1;
    }
}

class Problem0979DistributeCoinsInBinaryTree0 {

    int ans;

    public int distributeCoins(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int d = dfs(root.left) + dfs(root.right) + root.val - 1;
        ans += Math.abs(d);
        return d;
    }
}
