package com.shuangpeng.Problem.p1201_1300;

import java.util.Arrays;
import java.util.Random;

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

class Problem1206DesignSkiplist0 {

    class Skiplist {

        class Node {
            int value;
            Node[] next;
            Node(int value, int size) {
                this.value = value;
                next = new Node[size];
            }
        }

        static final int MAX_LEVEL = 32;
        static final double FACTOR = 0.25;

        int currentLevel = 1;
        Node head;

        public Skiplist() {
            currentLevel = 1;
            head = new Node(0, MAX_LEVEL);
        }

        public boolean search(int target) {
            Node node = head;
            for (int i = currentLevel - 1; i >= 0; i--) {
                node = findNode(node, target, i);
                if (node.next[i] != null && node.next[i].value == target) {
                    return true;
                }
            }
            return false;
        }

        public void add(int num) {
            int level = getLevel();
            Node node = head;
            Node cur = new Node(num, level);
            for (int i = level - 1; i >= 0; i--) {
                node = findNode(node, num, i);
                cur.next[i] = node.next[i];
                node.next[i] = cur;
            }
            currentLevel = Math.max(currentLevel, level);
        }

        public boolean erase(int num) {
            Node node = head;
            boolean find = false;
            for (int i = currentLevel - 1; i >= 0; i--) {
                node = findNode(node, num, i);
                if (node.next[i] != null && node.next[i].value == num) {
                    node.next[i] = node.next[i].next[i];
                    find = true;
                }
            }
            return find;
        }

        private int getLevel() {
            int level = 1;
            while (Math.random() <= FACTOR && level < MAX_LEVEL) {
                level++;
            }
            return level;
        }

        private Node findNode(Node node, int num, int level) {
            while (node.next[level] != null && num > node.next[level].value) {
                node = node.next[level];
            }
            return node;
        }
    }
}

class Skiplist {
    static final int MAX_LEVEL = 32;
    static final double P_FACTOR = 0.25;
    private SkiplistNode head;
    private int level;
    private Random random;

    public Skiplist() {
        this.head = new SkiplistNode(-1, MAX_LEVEL);
        this.level = 0;
        this.random = new Random();
    }

    public boolean search(int target) {
        SkiplistNode curr = this.head;
        for (int i = level - 1; i >= 0; i--) {
            /* 找到第 i 层小于且最接近 target 的元素*/
            while (curr.forward[i] != null && curr.forward[i].val < target) {
                curr = curr.forward[i];
            }
        }
        curr = curr.forward[0];
        /* 检测当前元素的值是否等于 target */
        if (curr != null && curr.val == target) {
            return true;
        }
        return false;
    }

    public void add(int num) {
        SkiplistNode[] update = new SkiplistNode[MAX_LEVEL];
        Arrays.fill(update, head);
        SkiplistNode curr = this.head;
        for (int i = level - 1; i >= 0; i--) {
            /* 找到第 i 层小于且最接近 num 的元素*/
            while (curr.forward[i] != null && curr.forward[i].val < num) {
                curr = curr.forward[i];
            }
            update[i] = curr;
        }
        int lv = randomLevel();
        level = Math.max(level, lv);
        SkiplistNode newNode = new SkiplistNode(num, lv);
        for (int i = 0; i < lv; i++) {
            /* 对第 i 层的状态进行更新，将当前元素的 forward 指向新的节点 */
            newNode.forward[i] = update[i].forward[i];
            update[i].forward[i] = newNode;
        }
    }

    public boolean erase(int num) {
        SkiplistNode[] update = new SkiplistNode[MAX_LEVEL];
        SkiplistNode curr = this.head;
        for (int i = level - 1; i >= 0; i--) {
            /* 找到第 i 层小于且最接近 num 的元素*/
            while (curr.forward[i] != null && curr.forward[i].val < num) {
                curr = curr.forward[i];
            }
            update[i] = curr;
        }
        curr = curr.forward[0];
        /* 如果值不存在则返回 false */
        if (curr == null || curr.val != num) {
            return false;
        }
        for (int i = 0; i < level; i++) {
            if (update[i].forward[i] != curr) {
                break;
            }
            /* 对第 i 层的状态进行更新，将 forward 指向被删除节点的下一跳 */
            update[i].forward[i] = curr.forward[i];
        }
        /* 更新当前的 level */
        while (level > 1 && head.forward[level - 1] == null) {
            level--;
        }
        return true;
    }

    private int randomLevel() {
        int lv = 1;
        /* 随机生成 lv */
        while (random.nextDouble() < P_FACTOR && lv < MAX_LEVEL) {
            lv++;
        }
        return lv;
    }
}

class SkiplistNode {
    int val;
    SkiplistNode[] forward;

    public SkiplistNode(int val, int maxLevel) {
        this.val = val;
        this.forward = new SkiplistNode[maxLevel];
    }
}





