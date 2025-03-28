package com.shuangpeng.Problem.p3401_3500;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3477FruitsIntoBasketsII（将水果放入篮子II）
 * @date 2025/3/25 15:23
 */
public class Problem3477FruitsIntoBasketsII {

    class ST {
        ST left, right;
        int start, end, value, M = Integer.MAX_VALUE;

        ST(int start, int end, int value) {
            this.start = start;
            this.end = end;
            this.value = value;
        }

        public void update(int x, int v) {
            if (start + 1 == end) {
                this.value = v;
                return;
            }
            down();
            int mid = getMid();
            if (x < mid) {
                left.update(x, v);
            } else {
                right.update(x, v);
            }
            this.value = Math.min(left.value, right.value);
        }

        private int getMid() {
            return start + (end - start >> 1);
        }

        private void down() {
            int mid = getMid();
            if (left == null) {
                left = new ST(start, mid, M);
            }
            if (right == null) {
                right = new ST(mid, end, M);
            }
        }

        private int getMin(int x) {
            if (start + 1 == end) {
                return value;
            }
            down();
            int mid = getMid();
            if (x < mid) {
                return Math.min(left.getMin(x), right.value);
            } else {
                return right.getMin(x);
            }
        }
    }

    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int mx = 0;
        for (int b : baskets) {
            mx = Math.max(mx, b);
        }
        ST st = new ST(0, mx + 1, Integer.MAX_VALUE);
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = baskets.length;
        for (int i = n - 1; i >= 0; i--) {
            map.computeIfAbsent(baskets[i], k -> new ArrayList<>()).add(i);
            st.update(baskets[i], i);
        }
        int ans = 0;
        for (int f : fruits) {
            if (f > mx) {
                ans++;
                continue;
            }
            int x = st.getMin(f);
            if (x >= n) {
                ans++;
                continue;
            }
            int y = baskets[x];
            List<Integer> list = map.get(y);
            list.remove(list.size() - 1);
            if (list.isEmpty()) {
                map.remove(y);
                st.update(y, Integer.MAX_VALUE);
            } else {
                st.update(y, list.get(list.size() - 1));
            }
        }
        return ans;
    }
}

class Problem3477FruitsIntoBasketsII0 {

    class SegmentTree {
        int[] maxValues;

        SegmentTree(int[] a) {
            int m = a.length;
            int n = 2 << (32 - Integer.numberOfLeadingZeros(m - 1));
            maxValues = new int[n];
            build(a, 0, 0, m - 1);
        }

        public void build(int[] a, int i, int s, int e) {
            if (s == e) {
                maxValues[i] = a[s];
                return;
            }
            int m = s + (e - s >> 1);
            int l = (i << 1) + 1, r = l + 1;
            build(a, l, s, m);
            build(a, r, m + 1, e);
            maxValues[i] = Math.max(maxValues[l], maxValues[r]);
        }

        public int findAndUpdate(int i, int s, int e, int x) {
            if (maxValues[i] < x) {
                return -1;
            }
            if (s == e) {
                maxValues[i] = -1;
                return i;
            }
            int m = s + (e - s >> 1);
            int l = (i << 1) + 1, r = l + 1;
            int ans = maxValues[l] >= x ? findAndUpdate(l, s, m, x) : findAndUpdate(r, m + 1, e, x);
            maxValues[i] = Math.max(maxValues[l], maxValues[r]);
            return ans;
        }
    }

    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        SegmentTree st = new SegmentTree(baskets);
        int ans = 0;
        for (int i = 0, n = fruits.length; i < n; i++) {
            if (st.findAndUpdate(0, 0, n - 1, fruits[i]) == -1) {
                ans++;
            }
        }
        return ans;
    }
}
