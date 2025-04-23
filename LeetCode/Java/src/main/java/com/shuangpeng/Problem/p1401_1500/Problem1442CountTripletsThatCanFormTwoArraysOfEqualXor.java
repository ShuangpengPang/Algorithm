package com.shuangpeng.Problem.p1401_1500;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1442CountTripletsThatCanFormTwoArraysOfEqualXor（形成两个异或相等数组的三元组）
 * @date 2025/4/22 18:26
 */
public class Problem1442CountTripletsThatCanFormTwoArraysOfEqualXor {

    public int countTriplets0(int[] arr) {
        int n = arr.length, ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 1, x = 0; i < n; i++) {
            x ^= arr[i - 1];
            for (int s = 0, j = i; j < n; j++) {
                s ^= arr[j];
                ans += map.getOrDefault(x ^ s, 0);
            }
            map.merge(x, 1, Integer::sum);
        }
        return ans;
    }

    public int countTriplets(int[] arr) {
        Map<Integer, Integer> cnt = new HashMap<>(), total = new HashMap<>();
        cnt.put(0, 1);
        total.put(0, 0);
        int n = arr.length, s = 0, ans = 0;
        for (int i = 1; i < n; i++) {
            s ^= arr[i - 1];
            int sum = s ^ arr[i];
            if (cnt.containsKey(sum)) {
                ans += cnt.get(sum) * i - total.get(sum);
            }
            cnt.merge(s, 1, Integer::sum);
            total.merge(s, i, Integer::sum);
        }
        return ans;
    }
}

class Problem1442CountTripletsThatCanFormTwoArraysOfEqualXor0 {
    public int countTriplets(int[] arr) {
        int n = arr.length;
        int capacity = Integer.highestOneBit(n << 1);
        Node[] table = new Node[capacity];
        compute(table, 0, -1);
        int answer = 0;
        int xor = 0;
        for (int i = 0; i < n; i++) {
            xor ^= arr[i];
            answer += compute(table, xor, i);
        }
        return answer;
    }

    // 将哈希表 table 中 key 对应的 cnt + 1， sum + i
    private int compute(Node[] table, int key, int i) {
        int hash = key & table.length - 1;
        Node node = table[hash];
        Node parent = null;
        while (node != null) {
            if (node.key == key) {
                int cnt = node.cnt;
                int sum = node.sum;
                node.cnt++;
                node.sum += i;
                return cnt * i - sum - cnt;
            }
            parent = node;
            if (node.key > key) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        if (parent == null) {
            table[hash] = new Node(key, 1, i);
        } else if (parent.key > key) {
            parent.left = new Node(key, 1, i);
        } else {
            parent.right = new Node(key, 1, i);
        }
        return 0;
    }

    private static class Node {
        private final int key;
        private int cnt;
        private int sum;
        private Node left;
        private Node right;

        private Node(int key, int cnt, int sum) {
            this.key = key;
            this.cnt = cnt;
            this.sum = sum;
        }
    }
}
