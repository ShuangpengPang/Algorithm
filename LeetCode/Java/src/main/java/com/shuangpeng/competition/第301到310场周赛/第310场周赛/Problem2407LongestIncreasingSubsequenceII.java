package com.shuangpeng.competition.第301到310场周赛.第310场周赛;

/**
 * @Description: Problem2407LongestIncreasingSubsequenceII（最长递增子序列II）
 * @Date 2022/11/4 5:11 PM
 * @Version 1.0
 */
public class Problem2407LongestIncreasingSubsequenceII {

    class Node {
        int start, end, max, lazyNum, lazyValue;
        Node left, right;

        Node(int s, int e) {
            start = s;
            end = e;
        }

        int query(int s, int e) {
            if (s <= start && end <= e) {
                return max;
            }
            down();
            int mid = start + (end - start >> 1);
            int ans = 0;
            if (s < mid) {
                ans = Math.max(ans, left.query(s, Math.min(e, mid)));
            }
            if (e > mid) {
                ans = Math.max(ans, right.query(Math.max(s, mid), e));
            }
            return ans;
        }

        void down() {
            int mid = start + (end - start >> 1);
            if (left == null) {
                left = new Node(start, mid);
            }
            if (right == null) {
                right = new Node(mid, end);
            }
            if (lazyNum > 0) {
                if (lazyNum < mid) {
                    left.insert(lazyNum, lazyValue);
                } else {
                    right.insert(lazyNum, lazyValue);
                }
                lazyNum = 0;
                lazyValue = 0;
            }
        }

        void insert(int num, int val) {
            if (start + 1 == end) {
                max = Math.max(max, val);
                return;
            }
            if (lazyNum > 0) {
                down();
            }
            lazyNum = num;
            lazyValue = val;
            max = Math.max(max, val);
        }
    }

    public int lengthOfLIS(int[] nums, int k) {
        int n = nums.length;
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        Node root = new Node(0, max + 1);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int cnt = root.query(Math.max(nums[i] - k, 0), nums[i]) + 1;
            root.insert(nums[i], cnt);
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem2407LongestIncreasingSubsequenceII a = new Problem2407LongestIncreasingSubsequenceII();
//        int[] nums = {4, 2, 1, 4, 3, 4, 5, 8, 15};
//        a.lengthOfLIS(nums, 3);
//        int i = 0;
//    }
}