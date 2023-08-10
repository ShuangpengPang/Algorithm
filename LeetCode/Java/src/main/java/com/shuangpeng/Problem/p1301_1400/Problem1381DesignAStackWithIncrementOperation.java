package com.shuangpeng.Problem.p1301_1400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1381DesignAStackWithIncrementOperation（设计一个支持增量操作的栈）
 * @date 2023/8/10 3:48 PM
 */
public class Problem1381DesignAStackWithIncrementOperation {

    class CustomStack {

        class Segment {
            int start, end, value, add;
            Segment left, right;

            Segment(int start, int end) {
                this.start = start;
                this.end = end;
                value = add = 0;
            }

            void increment(int s, int e, int val) {
                if (s <= start && end <= e) {
                    add += val;
                    return;
                }
                int mid = start + (end - start >> 1);
                split(mid);
                if (s <= mid) {
                    left.increment(s, Math.min(mid, e), val);
                }
                if (e > mid) {
                    right.increment(Math.max(s, mid + 1), e, val);
                }
            }

            int get(int pos) {
                if (start == end) {
                    return value + add;
                }
                int mid = start + (end - start >> 1);
                split(mid);
                if (pos <= mid) {
                    return left.get(pos);
                }
                return right.get(pos);
            }

            void set(int pos, int val) {
                if (start == end) {
                    add = 0;
                    value = val;
                    return;
                }
                int mid = start + (end - start >> 1);
                split(mid);
                if (pos <= mid) {
                    left.set(pos, val);
                } else {
                    right.set(pos, val);
                }
            }

            void split(int mid) {
                if (left == null) {
                    left = new Segment(start, mid);
                }
                if (right == null) {
                    right = new Segment(mid + 1, end);
                }
                left.add += add;
                right.add += add;
                add = 0;
            }
        }

        Segment root;
        int top = 0, maxSize;

        public CustomStack(int maxSize) {
            root = new Segment(0, maxSize);
            this.maxSize = maxSize;
            top = 0;
        }

        public void push(int x) {
            if (top < maxSize) {
                root.set(top++, x);
            }
        }

        public int pop() {
            if (top == 0) {
                return -1;
            }
            return root.get(--top);
        }

        public void increment(int k, int val) {
            if (top > 0) {
                root.increment(0, Math.min(k, top) - 1, val);
            }
        }
    }

/**
 * Your CustomStack object will be instantiated and called as such:
 * CustomStack obj = new CustomStack(maxSize);
 * obj.push(x);
 * int param_2 = obj.pop();
 * obj.increment(k,val);
 */
}
