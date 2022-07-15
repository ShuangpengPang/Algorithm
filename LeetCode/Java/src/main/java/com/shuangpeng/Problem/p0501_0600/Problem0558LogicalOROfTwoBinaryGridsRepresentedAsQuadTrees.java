package com.shuangpeng.Problem.p0501_0600;

/**
 * @Description: Problem0558LogicalOROfTwoBinaryGridsRepresentedAsQuadTrees（四叉树交集）
 * @Date 2022/7/15 9:58 AM
 * @Version 1.0
 */
public class Problem0558LogicalOROfTwoBinaryGridsRepresentedAsQuadTrees {

    public Node intersect(Node quadTree1, Node quadTree2) {
        if (quadTree1 == null) {
            return quadTree2;
        }
        if (quadTree2 == null) {
            return quadTree1;
        }
        if (quadTree1.isLeaf) {
            return quadTree1.val ? quadTree1 : quadTree2;
        }
        if (quadTree2.isLeaf) {
            return quadTree2.val ? quadTree2 : quadTree1;
        }
        Node node = new Node();
        node.topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
        node.topRight = intersect(quadTree1.topRight, quadTree2.topRight);
        node.bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
        node.bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);
        if (node.topLeft.isLeaf && node.topRight.isLeaf && node.bottomLeft.isLeaf && node.bottomRight.isLeaf
                && node.topLeft.val == node.topRight.val && node.bottomLeft.val == node.bottomRight.val && node.topLeft.val == node.bottomLeft.val) {
            node.isLeaf = true;
            node.val = node.topLeft.val;
            node.topLeft = null;
            node.topRight = null;
            node.bottomLeft = null;
            node.bottomRight = null;
        }
        return node;
    }
}

class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {
    }

    public Node(boolean _val, boolean _isLeaf, Node _topLeft, Node _topRight, Node _bottomLeft, Node _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};
