package com.shuangpeng.competition.第290到300场周赛.第295场周赛;

import java.util.*;

/**
 * @Description: Problem2289StepsToMakeArrayNonDecreasing（使数组按非递减顺序排序）
 * @Date 2022/6/13 3:09 PM
 * @Version 1.0
 */
public class Problem2289StepsToMakeArrayNonDecreasing {

    public int totalSteps0(int[] nums) {
        int n = nums.length;
        int ans = 0;
        int[] counts = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            int max = 0;
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                max = Math.max(max, counts[stack.pop()]);
            }
            counts[i] = stack.isEmpty() ? 0 : max + 1;
            ans = Math.max(ans, counts[i]);
            stack.push(i);
        }
        return ans;
    }

    public int totalSteps1(int[] nums) {
        int ans = 0;
        Deque<int[]> stack = new ArrayDeque<>();
        for (int num : nums) {
            int count = 1;
            while (!stack.isEmpty() && stack.peek()[0] <= num) {
                count = Math.max(count, stack.pop()[1] + 1);
            }
            count = stack.isEmpty() ? 0 : count;
            stack.push(new int[]{num, count});
            ans = Math.max(ans, count);
        }
        return ans;
    }

    public int totalSteps(int[] nums) {
        List<Integer> list = new LinkedList<>();
        for (int num : nums) {
            list.add(num);
        }
        boolean flag = true;
        int ans = 0;
        while (flag) {
            flag = false;
            int n = list.size();
            for (int i = n - 2; i >= 0; i--) {
                if (list.get(i) > list.get(i + 1)) {
                    list.remove(i + 1);
                    flag = true;
                }
            }
            if (flag) {
                ans++;
            }
        }
        return ans;
    }
}

class Problem2289StepsToMakeArrayNonDecreasing0 {

    class ListNode {
        ListNode prev, next;
        int val;

        ListNode(int val) {
            this.val = val;
        }

        ListNode add(ListNode node) {
            node.prev = this;
            node.next = this.next;
            this.next = node;
            return node;
        }

        void remove() {
            ListNode next = this.next.next;
            this.next = next;
            next.prev = this;
        }
    }

    public int totalSteps(int[] nums) {
        ListNode head = new ListNode(0);
        head.next = head;
        head.prev = head;
        ListNode node = head;
        for (int num : nums) {
            node = node.add(new ListNode(num));
        }
        node = node.prev;
        List<ListNode> list = new ArrayList<>();
        while (node != head) {
            if (node.val > node.next.val) {
                if (!list.isEmpty() && list.get(list.size() - 1) == node.next) {
                    list.remove(list.size() - 1);
                }
                node.remove();
                list.add(node);
            }
            node = node.prev;
        }
        int ans = 0;
        while (!list.isEmpty()) {
            ans++;
            List<ListNode> temp = new ArrayList<>();
            for (ListNode curr : list) {
                if (curr.next == head) {
                    continue;
                }
                if (curr.val > curr.next.val) {
                    if (!temp.isEmpty() && temp.get(temp.size() - 1) == curr.next) {
                        temp.remove(temp.size() - 1);
                    }
                    curr.remove();
                    temp.add(curr);
                }
            }
            list = temp;
        }
        return ans;
    }
}

class Problem2289StepsToMakeArrayNonDecreasing1 {

    class Node {
        int index;
        int count;

        public Node(int index, int count) {
            this.index = index;
            this.count = count;
        }
    }

    public int totalSteps(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0, j = 0; i < n - 1; i = j) {
            j = i + 1;
            if (nums[i] > nums[i + 1]) {
                Node node = getStep(nums, i);
                j = node.index;
                ans = Math.max(ans, node.count);
            }
        }
        return ans;
    }

    private Node getStep(int[] nums, int i) {
        int n = nums.length;
        int j = i + 2;
        int ans = 1;
        while (j < n && nums[j] < nums[i]) {
            if (nums[j - 1] > nums[j]) {
                Node node = getStep(nums, j - 1);
                j = node.index;
                ans = Math.max(ans, node.count);
            } else {
                ans++;
                j++;
            }
        }
        return new Node(j, ans);
    }
}