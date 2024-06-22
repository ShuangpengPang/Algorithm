package com.shuangpeng.lcr;

import com.shuangpeng.common.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR043CBTInserter
 * @date 2024/6/21 7:57 PM
 */
public class LCR043CBTInserter {

    class CBTInserter {

        private List<TreeNode> list;

        public CBTInserter(TreeNode root) {
            list = new ArrayList<>();
            Queue<TreeNode> q = new ArrayDeque<>();
            q.offer(root);
            while (!q.isEmpty()) {
                TreeNode node = q.poll();
                list.add(node);
                if (node.left != null) {
                    q.offer(node.left);
                }
                if (node.right != null) {
                    q.offer(node.right);
                }
            }
        }

        public int insert(int v) {
            TreeNode node = new TreeNode(v), parent = list.get(list.size() - 1 >> 1);
            list.add(node);
            if (parent.left == null) {
                parent.left = node;
            } else {
                parent.right = node;
            }
            return parent.val;
        }

        public TreeNode get_root() {
            return list.get(0);
        }
    }

/**
 * Your CBTInserter object will be instantiated and called as such:
 * CBTInserter obj = new CBTInserter(root);
 * int param_1 = obj.insert(v);
 * TreeNode param_2 = obj.get_root();
 */
}

class LCR043CBTInserter0 {

    class CBTInserter {

        private TreeNode root;
        private int count;

        public CBTInserter(TreeNode root) {
            this.root = root;
            count = getCount(root);
        }

        public int insert(int v) {
            count++;
            TreeNode parent = root, node = new TreeNode(v);
            int mask = 1 << 30 - Integer.numberOfLeadingZeros(count);
            while (mask > 1) {
                parent = (mask & count) == mask ? parent.right : parent.left;
                mask >>= 1;
            }
            if ((count & 1) == 0) {
                parent.left = node;
            } else {
                parent.right = node;
            }
            return parent.val;
        }

        public TreeNode get_root() {
            return root;
        }

        private int getCount(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return getCount(root.left) + getCount(root.right) + 1;
        }
    }
}
