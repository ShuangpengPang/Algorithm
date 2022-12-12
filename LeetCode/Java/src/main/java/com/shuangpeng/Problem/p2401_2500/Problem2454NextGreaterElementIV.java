package com.shuangpeng.Problem.p2401_2500;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2454NextGreaterElementIV（下一个更大元素IV）
 * @date 2022/12/12 2:46 PM
 */
public class Problem2454NextGreaterElementIV {

    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length;
        PriorityQueue<Integer>[] pqs = new PriorityQueue[2];
        Arrays.setAll(pqs, i -> new PriorityQueue<Integer>(Comparator.comparingInt(a -> nums[a])));
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (!pqs[1].isEmpty() && nums[pqs[1].peek()] < x) {
                ans[pqs[1].poll()] = x;
            }
            while (!pqs[0].isEmpty() && nums[pqs[0].peek()] < x) {
                pqs[1].offer(pqs[0].poll());
            }
            pqs[0].offer(i);
        }
        return ans;
    }
}

class Problem2454NextGreaterElementIV0 {

    static final int N = (int) 1e5;
    static final int[] first = new int[N], second = new int[N];

    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length, p = 0, q = 0;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (q > 0 && nums[second[q - 1]] < x) {
                ans[second[--q]] = x;
            }
            int tp = p;
            while (p > 0 && nums[first[p - 1]] < x) {
                p--;
            }
            if (p < tp) {
                System.arraycopy(first, p, second, q, tp - p);
                q += tp - p;
            }
            first[p++] = i;
        }
        return ans;
    }
}
