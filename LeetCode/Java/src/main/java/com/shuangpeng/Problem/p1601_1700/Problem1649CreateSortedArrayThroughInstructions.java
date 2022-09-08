package com.shuangpeng.Problem.p1601_1700;

/**
 * @Description: Problem1649CreateSortedArrayThroughInstructions（通过指令创建有序数组）
 * @Date 2022/9/8 3:10 PM
 * @Version 1.0
 */
public class Problem1649CreateSortedArrayThroughInstructions {

    public int createSortedArray(int[] instructions) {
        int n = instructions.length, max = 0, M = (int) 1e9 + 7;
         for (int i : instructions) {
             max = Math.max(max, i);
         }
         int[] cnt = new int[max + 1];
         int ans = 0;
         for (int i : instructions) {
             ans = (ans + Math.min(getCount(cnt, i - 1), getCount(cnt, max) - getCount(cnt, i))) % M;
             while (i <= max) {
                 cnt[i]++;
                 i += i & (-i);
             }
         }
         return ans;
    }

    private int getCount(int[] cnt, int x) {
        int ans = 0;
        while (x > 0) {
            ans += cnt[x];
            x -= x & (-x);
        }
        return ans;
    }
}

class Problem1649CreateSortedArrayThroughInstructions0 {

    class Node {
        int start, end, cnt;
        Node left, right;

        Node(int s, int e) {
            start = s;
            end = e;
            cnt = 0;
        }

        int getMid() {
            return start + (end - start >> 1);
        }

        void update(int num) {
            if (start + 1 > end) {
                return;
            }
            cnt++;
            if (start + 1 == end) {
                return;
            }
            int mid = getMid();
            if (num < mid) {
                if (left == null) {
                    left = new Node(start, mid);
                }
                left.update(num);
            } else if (num >= mid) {
                if (right == null) {
                    right = new Node(mid, end);
                }
                right.update(num);
            }
        }

        int query(int num) {
            if (end <= num) {
                return cnt;
            }
            int mid = getMid();
            if (num <= mid) {
                return left == null ? 0 : left.query(num);
            } else {
                return (left == null ? 0 : left.query(num)) + (right == null ? 0 : right.query(num));
            }
        }
    }

    Node root;

    public int createSortedArray(int[] instructions) {
        int n = instructions.length, max = 0, M = (int) 1e9 + 7;
        for (int i : instructions) {
            max = Math.max(max, i);
        }
        root = new Node(1, max + 1);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int num = instructions[i];
            ans = (ans + Math.min(root.query(num), i - root.query(num + 1))) % M;
            root.update(num);
        }
        return ans;
    }
}

