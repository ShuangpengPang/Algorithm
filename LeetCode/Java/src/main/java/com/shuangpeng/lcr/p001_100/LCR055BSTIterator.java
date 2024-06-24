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

    class BSTIterator {

        private Deque<TreeNode> s;
        private TreeNode cur;

        public BSTIterator(TreeNode root) {
            s = new ArrayDeque<>();
            cur = root;
        }

        public int next() {
            while (cur != null) {
                s.offerLast(cur);
                cur = cur.left;
            }
            cur = s.pollLast();
            int ans = cur.val;
            cur = cur.right;
            return ans;
        }

        public boolean hasNext() {
            return !s.isEmpty() || cur != null;
        }
    }

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
}

class LCR055BSTIterator0 {

    class BSTIterator {

        private TreeNode cur;

        public BSTIterator(TreeNode root) {
            cur = root;
        }

        public int next() {
            while (cur.left != null)  {
                TreeNode node = cur.left;
                while (node.right != null && node.right != cur) {
                    node = node.right;
                }
                if (node.right == null) {
                    node.right = cur;
                    cur = cur.left;
                } else {
                    node.right = null;
                    break;
                }
            }
            int ans = cur.val;
            cur = cur.right;
            return ans;
        }

        public boolean hasNext() {
            return cur != null;
        }
    }
}
