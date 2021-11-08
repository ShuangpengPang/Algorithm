package com.shuangpeng.Problem.p0801_0900;

import com.shuangpeng.common.TreeNode;

public class Problem0889ConstructBinaryTreeFromPreorderAndPostorderTraversal {

    public TreeNode constructFromPrePost0(int[] pre, int[] post) {
        if (pre == null || post == null
                || pre.length == 0 || pre.length != post.length) {
            return null;
        }
        int length = pre.length;
        int[] preMap = new int[length + 1];
        int[] postMap = new int[length + 1];
        for (int i = 0; i < length; i++) {
            preMap[pre[i]] = i;
            postMap[post[i]] = i;
        }
        return recurse(pre, 0, length - 1, post, 0, length - 1, preMap, postMap);
    }

    private TreeNode recurse(int[] pre, int s1, int e1,
                             int[] post, int s2, int e2,
                             int[] preMap, int[] postMap) {
        if (s1 > e1) {
            return null;
        }
        if (s1 == e1) {
            return new TreeNode(pre[s1]);
        }
        TreeNode node = new TreeNode(pre[s1]);
        int leftValue = pre[s1 + 1];
        int postIndex = postMap[leftValue];
        if (postIndex == e2 - 1) {
            node.left = recurse(pre, s1 + 1, e1, post, s2, e2 - 1, preMap, postMap);
        } else {
            int rightValue = post[e2 - 1];
            int preIndex = preMap[rightValue];
            node.left = recurse(pre, s1 + 1, preIndex - 1, post, s2, postIndex, preMap, postMap);
            node.right = recurse(pre, preIndex, e1, post, postIndex + 1, e2 - 1, preMap, postMap);
        }
        return node;
    }

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if (pre == null || post == null
                || pre.length == 0 || pre.length != post.length) {
            return null;
        }
        int length = pre.length;
        int[] map = new int[length + 1];
        for (int i = 0; i < length; i++) {
            map[post[i]] = i;
        }
        return build(pre, map, 0, 0, length);
    }

    private TreeNode build(int[] pre, int[] map, int s1, int s2, int n) {
        if (n <= 0) {
            return null;
        }
        TreeNode node = new TreeNode(pre[s1]);
        if (n == 1) {
            return node;
        }
        int index = map[pre[s1 + 1]];
        int L = index - s2 + 1;
        node.left = build(pre, map, s1 + 1, s2, L);
        node.right = build(pre, map, s1 + L + 1, s2 + L, n - L - 1);
        return node;
    }
}
