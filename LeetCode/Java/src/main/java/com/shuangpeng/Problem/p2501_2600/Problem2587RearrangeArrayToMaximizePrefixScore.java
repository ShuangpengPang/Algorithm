package com.shuangpeng.Problem.p2501_2600;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2587RearrangeArrayToMaximizePrefixScore（重排数组以得到最大前缀分数）
 * @date 2023/12/4 11:26 PM
 */
public class Problem2587RearrangeArrayToMaximizePrefixScore {

    public int maxScore0(int[] nums) {
        long sum = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> (b - a));
        int count = 0;
        for (int num : nums) {
            if (num > 0) {
                sum += num;
                count++;
            } else {
                q.offer(num);
            }
        }
        while (!q.isEmpty() && sum + q.peek() > 0) {
            sum += q.poll();
            count++;
        }
        return count;
    }

    public int maxScore(int[] nums) {
        Arrays.sort(nums);
        long sum = 0;
        int n = nums.length, i = n - 1;
        while (i >= 0 && sum + nums[i] > 0) {
            sum += nums[i];
            i--;
        }
        return n - i - 1;
    }
}

class Problem2587RearrangeArrayToMaximizePrefixScore0 {

    int len = 0;
    int[] heap;

    public int maxScore(int[] nums) {
        int n = nums.length, count = 0;
        heap = new int[n + 1];
        len = 0;
        long sum = 0;
        for (int num : nums) {
            if (num > 0) {
                sum += num;
                count++;
            } else {
                add(num);
            }
        }
        while (len > 0 && sum + heap[1] > 0) {
            sum += poll();
            count++;
        }
        return count;
    }

    private void add(int num) {
        int idx = ++len, parent = idx >> 1;
        while (idx > 1 && num > heap[parent]) {
            heap[idx] = heap[parent];
            idx = parent;
            parent = idx >> 1;
        }
        heap[idx] = num;
    }

    private int poll() {
        int ans = heap[1], idx = 1;
        int num = heap[len--];
        while (idx << 1 <= len) {
            int left = idx << 1, right = left + 1;
            int child = right <= len && heap[right] > heap[left] ? right : left;
            if (num >= heap[child]) {
                break;
            }
            heap[idx] = heap[child];
            idx = child;
        }
        heap[idx] = num;
        return ans;
    }
}
