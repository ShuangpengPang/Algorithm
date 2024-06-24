package com.shuangpeng.lcr.p001_100;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR055BSTIterator（二叉搜索树迭代器）
 * @date 2024/6/24 6:42 PM
 */
public class LCR055BSTIterator {
}

class BSTIterator {

    private Deque<TreeNode> s;

    public BSTIterator(TreeNode root) {
        s = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null) {
            s.offerLast(node);
            node = node.left;
        }
    }

    public int next() {
        TreeNode node = s.pollLast();
        int ans = node.val;
        node = node.right;
        while (node != null) {
            s.offerLast(node);
            node = node.left;
        }
        return ans;
    }

    public boolean hasNext() {
        return !s.isEmpty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
