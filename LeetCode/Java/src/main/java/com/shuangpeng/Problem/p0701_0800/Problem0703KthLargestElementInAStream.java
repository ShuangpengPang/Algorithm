package com.shuangpeng.Problem.p0701_0800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0703KthLargestElementInAStream（数据流中的第K大元素）
 * @date 2023/12/17 12:40 PM
 */
public class Problem0703KthLargestElementInAStream {

    class KthLargest {

        int k, cnt;
        int[] heap;

        public KthLargest(int k, int[] nums) {
            cnt = 0;
            this.k = k;
            heap = new int[k + 2];
            for (int num : nums) {
                addNum(num);
                if (cnt > k) {
                    popNum();
                }
            }
        }

        public int add(int val) {
            addNum(val);
            if (cnt > k) {
                popNum();
            }
            return heap[1];
        }

        private void addNum(int num) {
            heap[++cnt] = num;
            int idx = cnt, p = idx >> 1;
            while (p > 0 && heap[p] > num) {
                heap[idx] = heap[p];
                idx = p;
                p = idx >> 1;
            }
            heap[idx] = num;
        }

        private int popNum() {
            if (cnt == 0) {
                return -1;
            }
            int ans = heap[1], num = heap[cnt--];
            int idx = 1;
            while (idx << 1 <= cnt) {
                int left = idx << 1, right = left + 1;
                int child = right > cnt || heap[left] <= heap[right] ? left : right;
                if (num <= heap[child]) {
                    break;
                }
                heap[idx] = heap[child];
                idx = child;
            }
            heap[idx] = num;
            return ans;
        }
    }

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
}
