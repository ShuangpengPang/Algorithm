package com.shuangpeng.Problem;

public class Problem0673NumberOfLongestIncreasingSubsequence {

    public int findNumberOfLIS0(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        int maxLength = 1, maxCount = 1;
        for (int i = 1; i < n; i++) {
            int length = 0;
            int count = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    if (dp[j][1] > length) {
                        length = dp[j][1];
                        count = dp[j][0];
                    } else if (dp[j][1] == length) {
                        count += dp[j][0];
                    }
                }
            }
            dp[i][0] = count > 0 ? count : 1;
            dp[i][1] = length + 1;
            if (dp[i][1] > maxLength) {
                maxLength = dp[i][1];
                maxCount = dp[i][0];
            } else if (dp[i][1] == maxLength) {
                maxCount += dp[i][0];
            }
        }
        return maxCount;
    }

    public int findNumberOfLIS(int[] nums) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        Node root = new Node(min, max);
        for (int num : nums) {
            Value value = query(root, num - 1);
            insert(root, num, new Value(value.length + 1, value.count));
        }
        return root.value.count;
    }

    private void insert(Node root, int key, Value value) {
        if (root.start == root.end) {
            root.value = merge(root.value, value);
            return;
        }
        if (key <= root.getMiddle()) {
            insert(root.getLeft(), key, value);
        } else {
            insert(root.getRight(), key, value);
        }
        root.value = merge(root.getLeft().value, root.getRight().value);
    }

    private Value query(Node root, int key) {
        if (root.end <= key) {
            return root.value;
        }
        if (key < root.start) {
            return new Value(0, 1);
        }
        return merge(query(root.getLeft(), key), query(root.getRight(), key));
    }

    private Value merge(Value value1, Value value2) {
        if (value1.length == value2.length) {
            if (value1.length == 0) {
                return value1;
            }
            return new Value(value1.length, value1.count + value2.count);
        }
        return value1.length > value2.length ? value1 : value2;
    }

    class Node {
        int start;
        int end;
        Node left;
        Node right;
        Value value;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
            this.value = new Value(0, 1);
        }

        int getMiddle() {
            return start + ((end - start) >> 1);
        }

        Node getLeft() {
            return left = left == null ? new Node(start, getMiddle()) : left;
        }

        Node getRight() {
            return right = right == null ? new Node(getMiddle() + 1, end) : right;
        }
    }

    class Value {
        int length;
        int count;

        Value(int length, int count) {
            this.length = length;
            this.count = count;
        }
    }

//    public static void main(String[] args) {
//        Problem0673NumberOfLongestIncreasingSubsequence a = new Problem0673NumberOfLongestIncreasingSubsequence();
//        a.findNumberOfLIS(new int[]{1, 3, 5, 4, 7});
//    }
}
