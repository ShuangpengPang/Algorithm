package com.shuangpeng;

import java.util.LinkedList;
import java.util.Queue;

public class OpenLock {

    public static void main(String[] args) {
        String[] deads = {"8887","8889","8878","8898","8788","8988","7888","9888"};
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

    public int openLock0(String[] deadends, String target) {
        if (target == null || deadends == null || deadends.length == 0) {
            return -1;
        }
        short[] deads = new short[deadends.length];
        for (int i = 0; i < deadends.length; i++) {
            deads[i] = Short.parseShort(deadends[i]);
        }
        short t = Short.parseShort(target);
        boolean[] visited = new boolean[10000];
        int depth = 0;
        LinkedList<Node> queue = new LinkedList<>();
        byte[] bytes = new byte[4];
        Node first = new Node(bytes);
        visited[first.getValue()] = true;
        queue.offer(first);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                short value = node.getValue();
                if (!isDead(deads, value)) {
                    if (value == t) {
                        return depth;
                    }
                    for (int j = 0; j < 4; j++) {
                        byte[] numbers = {1, -1};
                        for (byte number: numbers) {
                            byte[] values = node.getValueArray(j, number);
                            Node child = new Node(values);
                            short childValue = child.getValue();
                            if (!visited[childValue]) {
                                queue.offer(child);
                                visited[childValue] = true;
                            }
                        }
                    }
                }
            }
            if (!queue.isEmpty()) {
                depth++;
            } else {
                return -1;
            }
        }
        return -1;
    }

    public int openLock(String[] deadends, String target) {
        if (target == null || deadends == null || deadends.length == 0) {
            return -1;
        }
        short[] deads = new short[deadends.length];
        for (int i = 0; i < deadends.length; i++) {
            deads[i] = Short.parseShort(deadends[i]);
        }
        short t = Short.parseShort(target);
        boolean[] visited = new boolean[10000];
        int depth = 0;
        LinkedList<Node> queue = new LinkedList<>();
        byte[] bytes = new byte[4];
        Node first = new Node(bytes);
        visited[first.getValue()] = true;
        queue.offer(first);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                short value = node.getValue();
                if (!isDead(deads, value)) {
                    if (value == t) {
                        return depth;
                    }
                    for (int j = 0; j < 4; j++) {
                        byte[] numbers = {1, -1};
                        for (byte number: numbers) {
                            byte[] values = node.getValueArray(j, number);
                            Node child = new Node(values);
                            short childValue = child.getValue();
                            if (!visited[childValue]) {
                                queue.offer(child);
                                visited[childValue] = true;
                            }
                        }
                    }
                }
            }
            if (!queue.isEmpty()) {
                depth++;
            } else {
                return -1;
            }
        }
        return -1;
    }

    public boolean isDead(short[] deads, short value) {
        for (short dead : deads) {
            if (dead == value) {
                return true;
            }
        }
        return false;
    }
}
