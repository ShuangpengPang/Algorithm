package com.shuangpeng.Problem.p0801_0900;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.TreeSet;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0855ExamRoom（考场就座）
 * @date 2022/11/18 5:20 PM
 */
public class Problem0855ExamRoom {

    class ExamRoom {

        int n;
        TreeSet<Integer> set;
        PriorityQueue<int[]> q;

        public ExamRoom(int n) {
            this.n = n;
            this.set = new TreeSet<>(Arrays.asList(-1, n));
            this.q = new PriorityQueue<>((a, b) -> {
                int m1 = a[0] == -1 || a[1] == n ? a[1] - a[0] - 1 : a[1] - a[0] >> 1;
                int m2 = b[0] == -1 || b[1] == n ? b[1] - b[0] - 1 : b[1] - b[0] >> 1;
                if (m1 != m2) {
                    return m2 - m1;
                }
                return a[0] - b[0];
            });
            q.offer(new int[]{-1, n});
        }

        public int seat() {
            while (!set.contains(q.peek()[0]) || !set.contains(q.peek()[1]) || q.peek()[1] != set.higher(q.peek()[0])) {
                q.poll();
            }
            int[] p = q.poll();
            int ans = p[0] == -1 ? 0 : p[1] == n ? n - 1 : p[0] + (p[1] - p[0] >> 1);
            set.add(ans);
            q.offer(new int[]{p[0], ans});
            p[0] = ans;
            q.offer(p);
            return ans;
        }

        public void leave(int p) {
            int s = set.lower(p), e = set.higher(p);
            set.remove(p);
            q.offer(new int[]{s, e});
        }
    }

/**
 * Your ExamRoom object will be instantiated and called as such:
 * ExamRoom obj = new ExamRoom(n);
 * int param_1 = obj.seat();
 * obj.leave(p);
 */
}

// 超时
class Problem0855ExamRoom0 {

    class ExamRoom {

        int n;
        TreeSet<Integer> set;

        public ExamRoom(int n) {
            this.n = n;
            set = new TreeSet<>();
            set.add(n);
        }

        public int seat() {
            int ans = 0, maxLen = 0;
            for (int num : set) {
                Integer s = set.lower(num);
                int len = s == null ? num : num == n ? n - s - 1 : num - s >> 1;
                if (len > maxLen) {
                    maxLen = len;
                    ans = s == null ? 0 : s + len;
                }
            }
            set.add(ans);
            return ans;
        }

        public void leave(int p) {
            set.remove(p);
        }
    }
}