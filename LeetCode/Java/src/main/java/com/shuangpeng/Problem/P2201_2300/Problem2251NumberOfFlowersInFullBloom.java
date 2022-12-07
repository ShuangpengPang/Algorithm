package com.shuangpeng.Problem.P2201_2300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2251NumberOfFlowersInFullBloom（花期内花的数目）
 * @date 2022/12/7 4:05 PM
 */
public class Problem2251NumberOfFlowersInFullBloom {

    class Node {
        int start, end, cnt;
        Node left, right;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
        }

        void update(int s, int e) {
            if (s <= start && end <= e) {
                cnt++;
                return;
            }
            int mid = start + (end - start >> 1);
            down(mid);
            if (s <= mid) {
                left.update(s, Math.min(mid, e));
            }
            if (e > mid) {
                right.update(Math.max(mid + 1, s), e);
            }
        }

        int query(int x) {
            if (start == end) {
                return cnt;
            }
            int mid = start + (end - start >> 1);
            down(mid);
            if (x <= mid) {
                return left.query(x);
            }
            return right.query(x);
        }

        void down(int mid) {
            if (left == null) {
                left = new Node(start, mid);
            }
            if (right == null) {
                right = new Node(mid + 1, end);
            }
            left.cnt += this.cnt;
            right.cnt += this.cnt;
            cnt = 0;
        }
    }

    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        Node root = new Node(1, (int) 1e9);
        for (int[] p : flowers) {
            root.update(p[0], p[1]);
        }
        int n = persons.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = root.query(persons[i]);
        }
        return ans;
    }
}
