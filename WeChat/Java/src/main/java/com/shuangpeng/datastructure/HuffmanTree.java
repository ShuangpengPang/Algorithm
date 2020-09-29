package com.shuangpeng.datastructure;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class HuffmanTree {
    Node root;

    class Node implements Comparable<Node> {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Node o) {
            return new Integer(this.data).compareTo(new Integer(o.data));
        }
    }

    public void printNode(Node node, int offset) {
        Queue<Integer> space = new LinkedList<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        space.offer(offset);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int start = 0;
            Queue<Integer> tempOffset = new LinkedList<>();
            Queue<Character> charQueue = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                Node n = queue.poll();
                int s = space.poll();
                for (int j = 0; j < s - start; j++) {
                    System.err.print(' ');
                }
                System.err.print(n.data);
                int digit = 1;
                if (n.data >= 100) {
                    digit = 3;
                } else if (n.data >= 10) {
                    digit = 2;
                }
                start = s - start + digit;
                if (n.left != null) {
                    int leftSpace = s - 2;
                    queue.offer(n.left);
                    space.offer(leftSpace);

                    charQueue.offer('/');
                    tempOffset.offer(leftSpace + 1);
                }
                if (n.right != null) {
                    int rightSpace = s + 2;
                    queue.offer(n.right);
                    space.offer(rightSpace);

                    charQueue.offer('\\');
                    tempOffset.offer(rightSpace - 1);
                }
            }
            System.err.println();
            start = 0;
            while (!charQueue.isEmpty()) {
                char c = charQueue.poll();
                int off = tempOffset.poll();
                for (int k = 0; k < off - start; k++) {
                    System.err.print(' ');
                }
                System.err.print(c);
                start = off + 1;
            }
            System.err.println();
        }
//        for (int i = 0; i < offset; i++) {
//            System.err.print(' ');
//        }
//        System.err.println(node.data);
//        if (node.left != null) {
//            for (int i = 0; i < offset - 1; i++) {
//                System.err.print(' ');
//            }
//            System.err.print('/');
//        }
//        if (node.right != null) {
//            if (node.left == null) {
//                for (int i = 0; i < offset + 1; i++) {
//                    System.err.print(' ');
//                }
//                System.err.print('\\');
//            } else {
//                System.err.print(" \\");
//            }
//        }
//        System.err.println();
//        if (node.left != null) {
//            printNode(node.left, offset - 2);
//        }
//        if (node.right != null) {
//            printNode(node.right, offset + 2);
//        }
    }

    public void createHuffmanTree(int[] weight) {
        if (weight == null) {
            return;
        }
        if (weight.length == 1) {
            return;
        }
        Queue<Node> queue = new PriorityQueue<>();
        for (int i = 0; i < weight.length; i++) {
            queue.offer(new Node(weight[i]));
        }
        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();
            int data = left.data + right.data;
            Node parent = new Node(data, left, right);
            queue.offer(parent);
        }
        this.root = queue.poll();
    }

    public static void main(String[] args) {
        int[] weight = {
                2
                ,
                3
                ,
                7
                ,
                9
                ,
                18
                ,
                25
        };
        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.createHuffmanTree(weight);
        huffmanTree.printNode(huffmanTree.root, 10);
    }
}
