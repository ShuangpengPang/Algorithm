package com.shuangpeng.Problem.P2201_2300;

import com.shuangpeng.common.TreeNode;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2236RootEqualsSumOfChildren（判断根结点是否等于子结点之和）
 * @date 2023/8/20 12:06 AM
 */
public class Problem2236RootEqualsSumOfChildren {

    public boolean checkTree(TreeNode root) {
        return root.val == root.left.val + root.right.val;
    }
}
