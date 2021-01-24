package com.shuangpeng.interview;

import com.shuangpeng.common.ListNode;
import com.shuangpeng.common.TreeNode;

import java.util.*;

public class Question0403LinkedListOfSpecificDepth {

    public ListNode[] listOfDepth0(TreeNode tree) {
        if (tree == null) {
            return new ListNode[0];
        }
        List<ListNode> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(tree);
        ListNode head = new ListNode();
        while (!queue.isEmpty()) {
            int size = queue.size();
            ListNode current = head;
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = queue.poll();
                ListNode listNode = new ListNode(treeNode.val);
                current.next = listNode;
                current = listNode;
                if (treeNode.left != null) {
                    queue.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.offer(treeNode.right);
                }
            }
            list.add(head.next);
        }
        int size = list.size();
        ListNode[] answer = new ListNode[size];
        for (int i = 0; i < size; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    public ListNode[] listOfDepth(TreeNode tree) {
        List<ListNode> answer = new ArrayList<>();
        List<ListNode> tail = new ArrayList<>();
        dfs(tree, answer, tail, 0);
        return answer.toArray(new ListNode[0]);
    }

    private void dfs(TreeNode tree, List<ListNode> list, List<ListNode> tail, int depth) {
        if (tree == null) {
            return;
        }
        ListNode listNode = new ListNode(tree.val);
        if (list.size() <= depth) {
            list.add(listNode);
            tail.add(listNode);
        } else {
            tail.get(depth).next = listNode;
            tail.set(depth, listNode);
        }
        dfs(tree.left, list, tail, depth + 1);
        dfs(tree.right, list, tail, depth + 1);
    }
}
