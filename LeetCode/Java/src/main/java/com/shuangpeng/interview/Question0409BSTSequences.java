package com.shuangpeng.interview;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Question0409BSTSequences {

    public List<List<Integer>> BSTSequences(TreeNode root) {
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
}
