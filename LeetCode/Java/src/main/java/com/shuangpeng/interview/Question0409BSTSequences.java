package com.shuangpeng.interview;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Question0409BSTSequences {

    public List<List<Integer>> BSTSequences0(TreeNode root) {
        return dfs(root);
    }

    private List<List<Integer>> dfs(TreeNode node) {
        List<List<Integer>> answer = new ArrayList<>();
        if (node == null) {
            answer.add(new ArrayList<>());
            return answer;
        }
        List<List<Integer>> leftList = dfs(node.left);
        List<List<Integer>> rightList = dfs(node.right);
        for (List<Integer> left : leftList) {
            for (List<Integer> right : rightList) {
                List<Integer> list = new ArrayList<>();
                list.add(node.val);
                backtrack(left, right, 0, 0, list, answer);
            }
        }
        return answer;
    }

    private void backtrack(List<Integer> left, List<Integer> right,
                           int pLeft, int pRight,
                           List<Integer> list, List<List<Integer>> answer) {
        int nLeft = left.size();
        int nRight = right.size();
        if (pLeft == nLeft && pRight == nRight) {
            answer.add(new ArrayList<>(list));
            return;
        }
        if (pLeft >= nLeft) {
            list.add(right.get(pRight));
            backtrack(left, right, pLeft, pRight + 1, list, answer);
            list.remove(list.size() - 1);
            return;
        }
        if (pRight >= nRight) {
            list.add(left.get(pLeft));
            backtrack(left, right, pLeft + 1, pRight, list, answer);
            list.remove(list.size() - 1);
            return;
        }
        list.add(left.get(pLeft));
        backtrack(left, right, pLeft + 1, pRight, list, answer);
        list.remove(list.size() - 1);
        list.add(right.get(pRight));
        backtrack(left, right, pLeft, pRight + 1, list, answer);
        list.remove(list.size() - 1);
    }

    public List<List<Integer>> BSTSequences(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        backtrack(queue, new ArrayList<>(), answer);
        return answer;
    }

    private void backtrack(Queue<TreeNode> queue, List<Integer> list, List<List<Integer>> answer) {
        if (queue.isEmpty()) {
            answer.add(new ArrayList<>(list));
            return;
        }
        for (int i = queue.size(); i > 0; i--) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
            backtrack(queue, list, answer);
            if (node.right != null) {
                queue.remove(node.right);
            }
            if (node.left != null) {
                queue.remove(node.left);
            }
            list.remove(list.size() - 1);
            queue.offer(node);
        }
    }
}
