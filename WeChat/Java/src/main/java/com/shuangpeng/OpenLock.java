package com.shuangpeng;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

// 题目链接：https://mp.weixin.qq.com/s/tng4c3hgw8eaGxQY23XOqw
// LeetCode链接：https://leetcode.com/problems/open-the-lock/submissions/
public class OpenLock {

    public static void main(String[] args) {
//        String[] deads = {"8887","8889","8878","8898","8788","8988","7888","9888"};
        String[] deads = {"0000"};
        System.out.println(new OpenLock().openLock(deads, "8888"));
    }

    class Node {
        byte[] bytes;

        public Node(byte[] bytes) {
            this.bytes = bytes;
        }

        byte[] getValueArray(int index, byte number) {
            byte[] bytes = new byte[4];
            for (int i = 0; i < 4; i++) {
                if (i == index) {
                    bytes[i] = (byte) ((this.bytes[i] + number + 10) % 10);
                } else {
                    bytes[i] = this.bytes[i];
                }
            }
            return bytes;
        }

        short getValue() {
            return (short) (bytes[0] * 1000 + bytes[1] * 100 + bytes[2] * 10 + bytes[3]);
        }
    }

    public int openLock(String[] deadends, String target) {
        if (target == null || deadends == null || deadends.length == 0) {
            return -1;
        }
        Set<Short> deads = new HashSet<>();
        for (String dead : deadends) {
            deads.add(Short.parseShort(dead));
        }
        if (deads.contains((short) 0)) {
            return -1;
        }
        int depth = 0;
        Queue<Node> topQueue = new LinkedList<>();
        Node first = new Node(new byte[4]);
        topQueue.offer(first);
        Set<Short> topSet = new HashSet<>();
        Set<Short> downSet = new HashSet<>();
        topSet.add(first.getValue());
        Queue<Node> downQueue = new LinkedList<>();
        byte[] targetBytes = new byte[4];
        char[] targetChars = target.toCharArray();
        for (int i = 0; i < 4; i++) {
            targetBytes[i] = (byte) (targetChars[i] - '0');
        }
        Node targetNode = new Node(targetBytes);
        downQueue.offer(targetNode);
        downSet.add(targetNode.getValue());
        boolean isTop = true;
        Queue<Node> queue = topQueue;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                short value = node.getValue();
                if (topSet.contains(value) && downSet.contains(value)) {
                    return depth;
                }
                for (int j = 0; j < 4; j++) {
                    byte[] numbers = {1, -1};
                    for (int k = 0; k < numbers.length; k++) {
                        byte[] bytes = node.getValueArray(j, numbers[k]);
                        Node childNode = new Node(bytes);
                        short childValue = childNode.getValue();
                        if (deads.contains(childValue)) {
                            continue;
                        }
                        if (isTop && !topSet.contains(childValue)) {
                            queue.offer(childNode);
                            topSet.add(childValue);
                        }
                        if (!isTop && !downSet.contains(childValue)) {
                            queue.offer(childNode);
                            downSet.add(childValue);
                        }
                    }
                }
            }
            depth++;
            isTop = !isTop;
            if (isTop) {
                queue = topQueue;
            } else {
                queue = downQueue;
            }
        }

        return -1;
    }

//    public int openLock0(String[] deadends, String target) {
//        if (target == null || deadends == null || deadends.length == 0) {
//            return -1;
//        }
//        short[] deads = new short[deadends.length];
//        for (int i = 0; i < deadends.length; i++) {
//            deads[i] = Short.parseShort(deadends[i]);
//        }
//        short t = Short.parseShort(target);
//        boolean[] visited = new boolean[10000];
//        int depth = 0;
//        LinkedList<Node> queue = new LinkedList<>();
//        byte[] bytes = new byte[4];
//        Node first = new Node(bytes);
//        visited[first.getValue()] = true;
//        queue.offer(first);
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            for (int i = 0; i < size; i++) {
//                Node node = queue.poll();
//                short value = node.getValue();
//                if (!isDead(deads, value)) {
//                    if (value == t) {
//                        return depth;
//                    }
//                    for (int j = 0; j < 4; j++) {
//                        byte[] numbers = {1, -1};
//                        for (byte number: numbers) {
//                            byte[] values = node.getValueArray(j, number);
//                            Node child = new Node(values);
//                            short childValue = child.getValue();
//                            if (!visited[childValue]) {
//                                queue.offer(child);
//                                visited[childValue] = true;
//                            }
//                        }
//                    }
//                }
//            }
//            if (!queue.isEmpty()) {
//                depth++;
//            } else {
//                return -1;
//            }
//        }
//        return -1;
//    }

    public boolean isDead(short[] deads, short value) {
        for (short dead : deads) {
            if (dead == value) {
                return true;
            }
        }
        return false;
    }
}
