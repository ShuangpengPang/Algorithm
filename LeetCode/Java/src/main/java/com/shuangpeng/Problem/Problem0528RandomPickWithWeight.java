package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.List;

public class Problem0528RandomPickWithWeight {

    class Solution {

        int[] preSum;
        int n;

        public Solution(int[] w) {
            n = w.length;
            preSum = new int[n + 1];
            for (int i = 0; i < n; ++i) {
                preSum[i + 1] = preSum[i] + w[i];
            }
        }

        public int pickIndex() {
            int num = 1 + (int) (preSum[n] * Math.random());
            int left = 1, right = n;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (num <= preSum[mid]) {
                    right = mid - 1;
                } else if (num > preSum[mid]) {
                    left = mid + 1;
                }
            }
            return left - 1;
        }
    }

    class Solution0 {
        int bid, iid, tot;
        List<int[]> list = new ArrayList<>();
        public Solution0(int[] w) {
            int n = w.length;
            double sum = 0, min = 1e9;
            for (int i : w) {
                sum += i;
                min = Math.min(min, i);
            }
            double minv = min / sum;
            int k = 1;
            while (minv * k < 1) k *= 10;
            for (int i = 0; i < n; i++) {
                int cnt = (int)(w[i] / sum * k);
                list.add(new int[]{i, cnt});
                tot += cnt;
            }
        }

        public int pickIndex() {
            if (bid >= list.size()) {
                bid = 0; iid = 0;
            }
            int[] info = list.get(bid);
            int id = info[0], cnt = info[1];
            if (iid >= cnt) {
                bid++; iid = 0;
                return pickIndex();
            }
            iid++;
            return id;
        }
    }
}
