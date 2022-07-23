package com.shuangpeng.Problem.p1201_1300;

/**
 * @Description: Problem1206DesignSkiplist（设计跳表）
 * @Date 2022/7/22 4:51 PM
 * @Version 1.0
 */
public class Problem1206DesignSkiplist {

    class Skiplist {

        class Node {
            int value;
            Node[] next;

            Node(int value, int level) {
                this.value = value;
                this.next = new Node[level];
            }
        }

        static final int MAX_LEVEL = 32;
        static final double DEFAULT_FACTOR = 0.25;

        int currentLevel = 1;
        Node head;

        public Skiplist() {
            currentLevel = 1;
            head = new Node(0, MAX_LEVEL);
        }

        public boolean search(int target) {
            Node node = head;
            for (int i = currentLevel - 1; i >= 0; i--) {
                node = findClosest(node, target, i);
                if (node.next[i] != null && node.next[i].value == target) {
                    return true;
                }
            }
            return false;
        }

        public void add(int num) {
            int level = randomLevel();
            currentLevel = Math.max(currentLevel, level);
            Node updateNode = head;
            Node node = new Node(num, level);
            for (int i = level - 1; i >= 0; i--) {
                updateNode = findClosest(updateNode, num, i);
                node.next[i] = updateNode.next[i];
                updateNode.next[i] = node;
            }
        }

        public boolean erase(int num) {
            boolean flag = false;
            Node node = head;
            for (int i = currentLevel - 1; i >= 0; i--) {
                node = findClosest(node, num, i);
                if (node.next[i] != null && node.next[i].value == num) {
                    node.next[i] = node.next[i].next[i];
                    flag = true;
                }
            }
            return flag;
        }

        private int randomLevel() {
            int level = 1;
            while (Math.random() < DEFAULT_FACTOR && level < MAX_LEVEL) {
                level++;
            }
            return level;
        }

        private Node findClosest(Node node, int num, int level) {
            while (node.next[level] != null && num > node.next[level].value) {
                node = node.next[level];
            }
            return node;
        }
    }

/**
 * Your Skiplist object will be instantiated and called as such:
 * Skiplist obj = new Skiplist();
 * boolean param_1 = obj.search(target);
 * obj.add(num);
 * boolean param_3 = obj.erase(num);
 */
}

