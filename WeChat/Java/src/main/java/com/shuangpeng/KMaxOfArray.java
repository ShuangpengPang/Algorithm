package com.shuangpeng;

// 题目链接：https://mp.weixin.qq.com/s/CWQ8UsePGwDQXUz80lb2Mg
public class KMaxOfArray {
    public static void main(String[] args) {
        int[] data1 = {3,2,1,5,6,4};
        int[] data2 = {3,2,3,1,2,4,5,5,6};
        System.out.println("data1 is: " + getKMaxElement(data1, 4));
        System.out.println("data2 is: " + getKMaxElement(data2, 4));
    }

    static class Node {
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public static int getKMaxElement(int[] data, int k) {
        if (data == null || data.length == 0 || k <= 0 || k > data.length) {
            return -1;
        }
        int first = data[0];
        Node head = new Node(first, null);
        Node tail = head;
        int c = 1;
        for (int i = 1; i < data.length; i++) {
            int d = data[i];
            if (d >= tail.data) {
                Node node = new Node(d, null);
                tail.next = node;
                tail = node;
            } else if (head.data < d) {
                Node current = head;
                while (current.next.data < d) {
                    current = current.next;
                }
                Node node = new Node(d, current.next);
                current.next = node;
            } else {
                Node node = new Node(d, head);
                head = node;
            }
            if (c < k) {
                c++;
            } else {
                head = head.next;
            }
        }
        return head.data;
    }

//    public static int getKthLargest(int[] data, int k) {
//
//    }

    public static int partition(int[] data, int left, int right) {
        if (data == null || data.length == 0 || left > right) {
            return -1;
        }
        int pivot = data[right];
        int p = left - 1;
        for (int i = left; i < right; i++) {
            if (data[i] <= pivot) {
                p++;
                swap(data, p, i);
            }
        }
        swap(data, p + 1, right);
        return p + 1;
    }

    public static void swap(int[] data, int i, int j) {
        if (i != j) {
            data[i] = data[j] - data[i];
            data[j] = data[j] - data[i];
            data[i] = data[j] + data[i];
        }
    }
}
