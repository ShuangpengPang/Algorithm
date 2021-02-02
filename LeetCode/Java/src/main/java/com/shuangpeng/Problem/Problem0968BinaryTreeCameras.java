package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

public class Problem0968BinaryTreeCameras {

    public int minCameraCover(TreeNode root) {
        return dfs(root)[1];
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{Integer.MAX_VALUE / 2, 0, 0};
        }
        int[] array = new int[3];
        int[] leftArray = dfs(node.left);
        int[] rightArray = dfs(node.right);
        array[0] = 1 + leftArray[2] + rightArray[2];
        array[1] = Math.min(array[0], Math.min(leftArray[0] + rightArray[1], leftArray[1] + rightArray[0]));
        array[2] = Math.min(array[1], leftArray[1] + rightArray[1]);
        return array;
    }


//    //NO_CAMERA表示的是子节点没有相机，当前节点也没放相机
//    private final int NO_CAMERA = 0;
//    //HAS_CAMERA表示当前节点有一个相机
//    private final int HAS_CAMERA = 1;
//    //NO_NEEDED表示当前节点没有相机，但他的子节点有一个相机，把它给
//    //覆盖了，所以它不需要了。或者他是一个空的节点也是不需要相机的
//    private final int NO_NEEDED = 2;
//
//    //全局的，统计有多少相机
//    int res = 0;
//
//    public int minCameraCover(TreeNode root) {
//        //边界条件判断
//        if (root == null)
//            return 0;
//        //如果最后返回的是NO_CAMERA，表示root节点的子节点也没有相机，
//        //所以root节点要添加一个相机
//        if (dfs(root) == NO_CAMERA)
//            res++;
//        //返回结果
//        return res;
//    }
//
//    public int dfs(TreeNode root) {
//        //如果是空的，就不需要相机了
//        if (root == null)
//            return NO_NEEDED;
//        int left = dfs(root.left), right = dfs(root.right);
//        //如果左右子节点有一个是NO_CAMERA，表示的是子节点既没相机，也没相机覆盖它，
//        //所以当前节点需要有一个相机
//        if (left == NO_CAMERA || right == NO_CAMERA) {
//            //在当前节点放一个相机，统计相机的个数
//            res++;
//            return HAS_CAMERA;
//        }
//        //如果左右子节点只要有一个有相机，那么当前节点就不需要相机了，否则返回一个没有相机的标记
//        return left == HAS_CAMERA || right == HAS_CAMERA ? NO_NEEDED : NO_CAMERA;
//    }


//    class TreeNodeStatus {
//        TreeNode treeNode;
//        boolean mark;
//        boolean visited;
//
//        public TreeNodeStatus(TreeNode treeNode, boolean mark, boolean visited) {
//            this.treeNode = treeNode;
//            this.mark = mark;
//            this.visited = visited;
//        }
//    }
//
//    public int minCameraCover1(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//        Queue<TreeNodeStatus> queue = new LinkedList<>();
//        queue.offer(new TreeNodeStatus(root, false, false));
//        int answer = 0;
//        while (!queue.isEmpty()) {
//            TreeNodeStatus status = queue.poll();
//            TreeNode node = status.treeNode;
//            if (!status.visited) {
//                if (node.left == null && node.right == null) {
//                    answer++;
//                } else if (node.left == null) {
//                    answer++;
//                    queue.offer(new TreeNodeStatus(node.right, true, true));
//                } else if (node.right == null) {
//                    answer++;
//                    queue.offer(new TreeNodeStatus(node.left, true, true));
//                } else if (node.left.left == null && node.left.right == null
//                        && node.right.left == null && node.right.right == null) {
//                    answer++;
//                } else {
//                    queue.offer(new TreeNodeStatus(node.left, true, true));
//                    queue.offer(new TreeNodeStatus(node.right, true, true));
//                    answer += 2;
//                }
//            } else {
//                boolean mark = status.mark;
//                if (!mark && node.left != null && node.left.left == null && node.left.right == null
//                        && node.right != null && node.right.left == null && node.right.right == null) {
//                    answer++;
//                    continue;
//                }
//                if (node.left != null) {
//                    queue.offer(new TreeNodeStatus(node.left, false, mark));
//                }
//                if (node.right != null) {
//                    queue.offer(new TreeNodeStatus(node.right, false, mark));
//                }
//            }
//        }
//        return answer;
//    }
}
