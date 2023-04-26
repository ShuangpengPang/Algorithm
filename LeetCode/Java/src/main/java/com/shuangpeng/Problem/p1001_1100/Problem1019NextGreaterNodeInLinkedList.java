package com.shuangpeng.Problem.p1001_1100;

import com.shuangpeng.common.ListNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1019NextGreaterNodeInLinkedList（链表中的下一个更大节点）
 * @date 2023/4/7 4:50 PM
 */
public class Problem1019NextGreaterNodeInLinkedList {

    public int[] nextLargerNodes0(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int n = list.size();
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0);
        for (int i = n - 1; i >= 0; i--) {
            int num = list.get(i);
            while (stack.peek() != 0 && stack.peek() <= num) {
                stack.pop();
            }
            ans[i] = stack.peek();
            stack.push(num);
        }
        return ans;
    }

    public int[] nextLargerNodes1(ListNode head) {
        int n = 0;
        ListNode prev = null;
        while (head != null) {
            ListNode node = head.next;
            head.next = prev;
            prev = head;
            head = node;
            n++;
        }
        int[] ans = new int[n];
        Deque<Integer> q = new ArrayDeque<>();
        q.push(0);
        for (int i = n - 1; i >= 0; i--) {
            int num = prev.val;
            while (q.peek() != 0 && q.peek() <= num) {
                q.pop();
            }
            ans[i] = q.peek();
            q.push(num);
            prev = prev.next;
        }
        return ans;
    }

    public int[] nextLargerNodes2(ListNode head) {
        return recurse(head, 0, new ArrayDeque<>());
    }

    private int[] recurse(ListNode node, int n, Deque<Integer> stack) {
        if (node == null) {
            return new int[n];
        }
        int[] ans = recurse(node.next, n + 1, stack);
        while (!stack.isEmpty() && stack.peek() <= node.val) {
            stack.pop();
        }
        ans[n] = stack.isEmpty() ? 0 : stack.peek();
        stack.push(node.val);
        return ans;
    }

    public int[] nextLargerNodes(ListNode head) {
        ListNode prev = null;
        int n = 0;
        for (ListNode node = head, next; node != null; node = next) {
            next = node.next;
            node.next = prev;
            prev = node;
            n++;
        }
        int[] ans = new int[n], stack = new int[n];
        int top = 0;
        ListNode node = prev;
        for (int i = n - 1; i >= 0; i--) {
            while (top > 0 && stack[top - 1] <= node.val) {
                top--;
            }
            ans[i] = top > 0 ? stack[top - 1] : 0;
            stack[top++] = node.val;
            node = node.next;
        }
        return ans;
    }
}
