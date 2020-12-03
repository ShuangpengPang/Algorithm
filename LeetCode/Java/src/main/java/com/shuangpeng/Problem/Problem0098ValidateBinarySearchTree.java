package com.shuangpeng.Problem;

public class Problem0098ValidateBinarySearchTree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 递归
    public boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        if (root.val <= min || root.val >= max) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }

    public boolean isValidBST0(TreeNode root) {
        return inorderSearch(root);
    }

    // 中序遍历
    public boolean inorderSearch(TreeNode root) {
        if (root == null) {
            return true;
        }
        long val = Long.MIN_VALUE;
        while (root != null) {
            if (root.left == null) {
                if (root.val <= val) {
                    return false;
                }
                val = root.val;
                root = root.right;
            } else {
                TreeNode node = root.left;
                while (node.right != null && node.right != root) {
                    node = node.right;
                }
                if (node.right == null) {
                    node.right = root;
                    root = root.left;
                } else {
                    // 完毕
                    if (root.val <= val) {
                        return false;
                    }
                    val = root.val;
                    node.right = null;
                    root = root.right;
                }
            }
        }
        return true;
    }

    public boolean isValidBST1(TreeNode root) {
        return inorderSearch(root, Long.MIN_VALUE).isValid;
    }

    class Item {
        boolean isValid;
        long minValue;

        public Item(boolean isValid, long minValue) {
            this.isValid = isValid;
            this.minValue = minValue;
        }
    }

    // 中序遍历（递归）
    public Item inorderSearch(TreeNode root, long minValue) {
        if (root == null) {
            return new Item(true, minValue);
        }
        if (root.val <= minValue) {
            return new Item(false, minValue);
        }
        if (root.left == null && root.right == null) {
            return new Item(true, root.val);
        }
        Item left = inorderSearch(root.left, minValue);
        if (!left.isValid || root.val <= left.minValue) {
            return new Item(false, left.minValue);
        }
        return inorderSearch(root.right, root.val);
    }

    // 中序遍历（非递归）
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        TreeNode[] stack = new TreeNode[2000];
        int count = 0;
        long value = Long.MIN_VALUE;
        while (root != null || count > 0) {
            if (root == null) {
                root = stack[--count];
                if (root.val <= value) {
                    return false;
                }
                value = root.val;
                root = root.right;
                if (root == null) {
                    continue;
                }
            }
            while (root.left != null) {
                stack[count++] = root;
                root = root.left;
            }
            if (root.val <= value) {
                return false;
            }
            value = root.val;
            if (root.right != null) {
                root = root.right;
            } else {
                if (count > 0) {
                    root = stack[--count];
                    if (root.val <= value) {
                        return false;
                    }
                    value = root.val;
                    root = root.right;
                } else {
                    break;
                }
            }
        }
        return true;
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        TreeNode[] stack = new TreeNode[2000];
        int count = 0;
        stack[count++] = root;
        long value = Long.MIN_VALUE;
        while (root != null || count > 0) {
            if (root == null) {
                root = stack[--count];
                if (root.val <= value) {
                    return false;
                }
                value = root.val;
                root = root.right;
                if (root != null) {
                    stack[count++] = root;
                } else {
                    continue;
                }
            }
            while (root.left != null) {
                stack[count++] = root.left;
                root = root.left;
            }
            root = stack[--count];
            if (root.val <= value) {
                return false;
            }
            value = root.val;
            if (root.right != null) {
                stack[count++] = root.right;
            }
            root = root.right;
        }
        return true;
    }
}
