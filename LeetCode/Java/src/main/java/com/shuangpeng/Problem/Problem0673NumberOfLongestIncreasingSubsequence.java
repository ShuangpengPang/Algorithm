package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.List;

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

    public int findNumberOfLIS1(int[] nums) {
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

    public int findNumberOfLIS2(int[] nums) {
        int n = nums.length;
        int maxLength = 0;
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; ++i) {
            int length = 0;
            for (int j = i - 1; j >= 0; --j) {
                if (nums[i] > nums[j]) {
                    if (dp[j][0] > length) {
                        length = dp[j][0];
                        dp[i][1] = dp[j][1];
                    } else if (dp[j][0] == length) {
                        dp[i][1] += dp[j][1];
                    }
                }
            }
            dp[i][0] = length + 1;
            dp[i][1] = dp[i][1] == 0 ? 1 : dp[i][1];
            maxLength = Math.max(maxLength, dp[i][0]);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (maxLength == dp[i][0]) {
                ans += dp[i][1];
            }
        }
        return ans;
    }

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        List<List<Integer>> dp = new ArrayList<>();
        List<List<Integer>> count = new ArrayList<>();
        dp.add(new ArrayList<>(1));
        count.add(new ArrayList<>(1));
        dp.get(0).add(Integer.MIN_VALUE);
        count.get(0).add(1);
        for (int i = 0; i < n; ++i) {
            int idx = binarySearch(dp, nums[i]);
            if (idx == dp.size()) {
                dp.add(new ArrayList<>());
                count.add(new ArrayList<>());
            }
            dp.get(idx).add(nums[i]);
            List<Integer> countList = count.get(idx);
            int c = countList.size() > 0 ? countList.get(countList.size() - 1) : 0;
            int j = binarySearch2(dp.get(idx - 1), nums[i]);
            List<Integer> list = count.get(idx - 1);
            c += list.get(list.size() - 1) - (j >= 0 ? list.get(j) : 0);
            countList.add(c);
        }
        List<Integer> list = count.get(count.size() - 1);
        return list.get(list.size() - 1);
    }

    private int binarySearch(List<List<Integer>> lists, int num) {
        int left = 0, right = lists.size() - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            List<Integer> list = lists.get(mid);
            int data = list.get(list.size() - 1);
            if (num > data) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private int binarySearch2(List<Integer> list, int num) {
        int left = 0, right = list.size() - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            int data = list.get(mid);
            if (num > data) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

//    public static void main(String[] args) {
//        Problem0673NumberOfLongestIncreasingSubsequence a = new Problem0673NumberOfLongestIncreasingSubsequence();
//        a.findNumberOfLIS(new int[]{1, 3, 5, 4, 7});
//    }
}
