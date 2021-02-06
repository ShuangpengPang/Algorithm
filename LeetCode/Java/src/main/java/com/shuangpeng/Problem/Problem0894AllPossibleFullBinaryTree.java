package com.shuangpeng.Problem;

import com.shuangpeng.common.TreeNode;

import java.util.*;

public class Problem0894AllPossibleFullBinaryTree {

//    public static void main(String[] args) {
//        Problem0894AllPossibleFullBinaryTree a = new Problem0894AllPossibleFullBinaryTree();
//        a.allPossibleFBT(7);
//    }

    public List<TreeNode> allPossibleFBT0(int N) {
        return recurse(N);
    }

    private List<TreeNode> recurse(int N) {
        List<TreeNode> answer = new ArrayList<>();
        if ((N & 1) == 0) {
            return answer;
        }
        if (N == 1) {
            TreeNode node = new TreeNode(0);
            answer.add(node);
            return answer;
        }
        int half = (N - 1) >> 1;
        for (int i = 1; i <= half; i += 2) {
            answer.addAll(combine(recurse(i), recurse(N - i - 1), i == half));
        }
        return answer;
    }

    private List<TreeNode> combine(List<TreeNode> list1, List<TreeNode> list2, boolean equal) {
        List<TreeNode> result = new ArrayList<>();
        if (equal) {
            for (TreeNode node1 : list1) {
                for (TreeNode node2 : list2) {
                    TreeNode node = new TreeNode(0);
                    node.left = node1;
                    node.right = node2;
                    result.add(node);
                }
            }
        } else {
            for (TreeNode node1 : list1) {
                for (TreeNode node2 : list2) {
                    TreeNode node = new TreeNode(0);
                    node.left = node1;
                    node.right = node2;
                    result.add(node);
                    node = new TreeNode(0);
                    node.left = node2;
                    node.right = node1;
                    result.add(node);
                }
            }
        }
        return result;
    }

    public List<TreeNode> allPossibleFBT1(int N) {
        List<TreeNode> answer = new ArrayList<>();
        if (N < 1 || (N & 1) == 0) {
            return answer;
        }
        TreeNode node = new TreeNode(0);
        List<TreeNode> list = new ArrayList<>();
        list.add(node);
        Map<Integer, List<TreeNode>> map = new HashMap<>();
        map.put(1, list);
        int i = 3;
        while (i <= N) {
            List<TreeNode> result = new ArrayList<>();
            int half = (i - 1) >> 1;
            for (int j = 1; j <= half; j += 2) {
                result.addAll(getResult(map.get(j), map.get(i - j - 1), j == half));
            }
            map.put(i, result);
            i += 2;
        }
        return map.get(N);
    }

    private List<TreeNode> getResult(List<TreeNode> list1, List<TreeNode> list2, boolean equal) {
        List<TreeNode> result = new ArrayList<>();
        if (equal) {
            for (TreeNode node1 : list1) {
                for (TreeNode node2 : list2) {
                    TreeNode node = new TreeNode(0);
                    node.left = node1;
                    node.right = node2;
                    result.add(node);
                }
            }
        } else {
            for (TreeNode node1 : list1) {
                for (TreeNode node2 : list2) {
                    TreeNode node = new TreeNode(0);
                    node.left = node1;
                    node.right = node2;
                    result.add(node);
                    node = new TreeNode(0);
                    node.left = node2;
                    node.right = node1;
                    result.add(node);
                }
            }
        }
        return result;
    }

    Map<Integer, List<TreeNode>> memo = new HashMap();

    public List<TreeNode> allPossibleFBT(int N) {
        if (!memo.containsKey(N)) {
            List<TreeNode> ans = new LinkedList();
            if (N == 1) {
                ans.add(new TreeNode(0));
            } else if (N % 2 == 1) {
                for (int x = 0; x < N; ++x) {
                    int y = N - 1 - x;
                    for (TreeNode left: allPossibleFBT(x))
                        for (TreeNode right: allPossibleFBT(y)) {
                            TreeNode bns = new TreeNode(0);
                            bns.left = left;
                            bns.right = right;
                            ans.add(bns);
                        }
                }
            }
            memo.put(N, ans);
        }

        return memo.get(N);
    }
}
