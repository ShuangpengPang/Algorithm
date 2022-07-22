package com.shuangpeng.Problem.p0701_0800;

import java.util.*;

/**
 * @Description: 设置交集大小至少为2（757)
 * @Date 2022/7/22 3:02 PM
 **/
public class Problem0757SetIntersectionSizeAtLeastTwo {

    public int intersectionSizeTwo0(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });
        int n = intervals.length;
        int[] todo = new int[n];
        Arrays.fill(todo, 2);
        int ans = 0;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = intervals[i][0]; j < intervals[i][0] + todo[i]; ++j) {
                for (int k = 0; k < i; ++k) {
                    if (todo[k] > 0 && j <= intervals[k][1]) {
                        --todo[k];
                    }
                }
            }
            ans += todo[i];
        }
        return ans;
    }

    public int intersectionSizeTwo1(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        int first = -2, second = -1;
        int ans = 0;
        for (int[] pair : intervals) {
            int a = pair[0], b = pair[1];
            if (a > second) {
                first = b - 1;
                second = b;
                ans += 2;
            } else if (a > first) {
                if (b > second) {
                    first = second;
                    second = b;
                } else {
                    first = b - 1;
                }
                ++ans;
            }
        }
        return ans;
    }

    public int intersectionSizeTwo2(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        int ans = 0, a = -2, b = -1;
        for (int[] p : intervals) {
            if (b < p[0]) {
                a = p[1] - 1;
                b = p[1];
                ans += 2;
            } else if (a < p[0]) {
                if (b == p[1]) {
                    a = p[1] - 1;
                } else {
                    a = b;
                    b = p[1];
                }
                ans++;
            }
        }
        return ans;
    }

    public int intersectionSizeTwo3(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });
        int ans = 0, a = -2, b = -1;
        for (int[] p : intervals) {
            if (b < p[0]) {
                a = p[1] - 1;
                b = p[1];
                ans += 2;
            } else if (a < p[0]) {
                a = b;
                b = p[1];
                ans++;
            }
        }
        return ans;
    }

    public int intersectionSizeTwo4(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });
        int n = intervals.length;
        List<Integer>[] lists = new List[n];
        Arrays.setAll(lists, e -> new ArrayList<>());
        int m = 2;
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = intervals[i][0], k = lists[i].size(); k < m; j++, k++) {
                ans++;
                for (int s = i - 1; s >= 0 && intervals[s][1] >= j; s--) {
                    lists[s].add(j);
                }
            }
        }
        return ans;
    }

    public int intersectionSizeTwo5(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }
            return b[1] - a[1];
        });
        int m = 2, n = intervals.length;
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = intervals[n - 1][0]; i < intervals[n - 1][0] + m; i++) {
            q.addLast(i);
        }
        int ans = m;
        for (int i = n - 2; i >= 0; i--) {
            while (!q.isEmpty() && q.peekLast() > intervals[i][1]) {
                q.pollLast();
            }
            int count = m - q.size();
            for (int j = intervals[i][0] + count - 1; j >= intervals[i][0]; j--) {
                q.addFirst(j);
            }
            ans += count;
        }
        return ans;
    }

    public int intersectionSizeTwo(int[][] intervals) {

        int idx = 0;
        // 这是用一个数代表一个区间
        // 把左区间的数转成负数 右区间的数左移32位，这样也是 按照右区间从小打到 左区间从大到小排列
        long[] endStartPairs = new long[intervals.length];
        for (int[] interval : intervals) {
            endStartPairs[idx] = -interval[0] & 0xFFFFFFFFL;
            endStartPairs[idx++] |= (long) (interval[1]) << 32;
        }
        Arrays.sort(endStartPairs);
        int min = -2;
        int max = -1;
        int curStart;
        int curEnd;
        int res = 0;
        for (long endStartPair : endStartPairs) {
            curStart = -(int) endStartPair;
            curEnd = (int) (endStartPair >> 32);
            if (curStart <= min) {
                continue;
            }
            if (curStart <= max) {
                res += 1;
                min = max;
            } else {
                res += 2;
                min = curEnd - 1;
            }
            max = curEnd;
        }
        return res;
    }
}


