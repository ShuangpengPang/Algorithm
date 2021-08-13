package com.shuangpeng.Problem;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Problem0805SplitArrayWithSameAverage {

    // MTE
    public boolean splitArraySameAverage0(int[] nums) {
        int n = nums.length;
        final int M = 1 << n;
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += nums[i];
        }
        int[] dp = new int[M];
        for (int status = 0; status < M - 1; ++status) {
            int count = Integer.bitCount(status);
            for (int i = 0; i < n; ++i) {
                int j = 1 << i;
                if ((status & j) == 0) {
                    int k = status | j;
                    if (dp[k] == 0) {
                        dp[k] = dp[status] + nums[n - i - 1];
                        if (dp[k] != sum && dp[k] * (n - count - 1) == (sum - dp[k]) * (count + 1)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean splitArraySameAverage1(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; ++i) {
            sum += nums[i];
        }
        for (int i = 0; i < n; ++i) {
            nums[i] = nums[i] * n - sum;
        }
        int half = n >> 1;
        Map<Integer, Set<Integer>> right = new HashMap<>();
        calculate(nums, right, half, 0);
        Map<Integer, Set<Integer>> left = new HashMap<>();
        calculate(nums, left, n - half, half);
        if (check(right, n) || check(left, n)) {
            return true;
        }
        for (int key : left.keySet()) {
            Set<Integer> set1 = left.get(key);
            Set<Integer> set2 = right.get(-key);
            if (set2 != null) {
                for (int c1 : set1) {
                    for (int c2 : set2) {
                        if (c1 + c2 < n) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean check(Map<Integer, Set<Integer>> map, int n) {
        for (int key : map.keySet()) {
            if (key == 0) {
                for (int c : map.get(key)) {
                    if (c < n) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void calculate(int[] nums, Map<Integer, Set<Integer>> sumMap, int n, int offset) {
        final int M = 1 << n;
        int[] dp = new int[M];
        for (int status = 0; status < M; ++status) {
            for (int i = 0; i < n; ++i) {
                if ((status & (1 << i)) == 0) {
                    int j = status | (1 << i);
                    dp[j] = dp[status] + nums[nums.length - i - offset - 1];
                    Set<Integer> set = sumMap.getOrDefault(dp[j], new HashSet<>());
                    set.add(Integer.bitCount(j));
                    sumMap.put(dp[j], set);
                }
            }
        }
    }

    public boolean splitArraySameAverage2(int[] A) {
        int N = A.length;
        int S = 0;
        for (int x: A) S += x;
        if (N == 1) return false;

        int g = gcd(S, N);
        Point mu = new Point(-(S/g), N/g);
        // A[i] -> fracAdd(A[i], mu)
        List<Point> A2 = new ArrayList();
        for (int x: A)
            A2.add(fracAdd(new Point(x, 1), mu));

        Set<Point> left = new HashSet();
        left.add(A2.get(0));
        for (int i = 1; i < N/2; ++i) {
            Set<Point> left2 = new HashSet();
            Point z = A2.get(i);
            left2.add(z);
            for (Point p: left) {
                left2.add(p);
                left2.add(fracAdd(p, z));
            }
            left = left2;
        }

        if (left.contains(new Point(0, 1))) return true;

        Set<Point> right = new HashSet();
        right.add(A2.get(N-1));
        for (int i = N/2; i < N-1; ++i) {
            Set<Point> right2 = new HashSet();
            Point z = A2.get(i);
            right2.add(z);
            for (Point p: right) {
                right2.add(p);
                right2.add(fracAdd(p, z));
            }
            right = right2;
        }

        if (right.contains(new Point(0, 1))) return true;

        Point sleft = new Point(0, 1);
        for (int i = 0; i < N/2; ++i)
            sleft = fracAdd(sleft, A2.get(i));

        Point sright = new Point(0, 1);
        for (int i = N/2; i < N; ++i)
            sright = fracAdd(sright, A2.get(i));

        for (Point ha: left) {
            Point ha2 = new Point(-ha.x, ha.y);
            if (right.contains(ha2) && (!ha.equals(sleft) || !ha2.equals(sright)))
                return true;
        }
        return false;
    }

    public Point fracAdd(Point A, Point B) {
        int numer = A.x * B.y + B.x * A.y;
        int denom = A.y * B.y;
        int g = gcd(numer, denom);
        numer /= g;
        denom /= g;

        if (denom < 0) {
            numer *= -1;
            denom *= -1;
        }

        return new Point(numer, denom);
    }

    public int gcd(int a, int b) {
        if (b==0) return a;
        return gcd(b, a%b);
    }

    // 参考：https://leetcode-cn.com/problems/split-array-with-same-average/solution/0-1bei-bao-zhuang-tai-ya-suo-by-guo-ke-f-pgvn/
    public boolean splitArraySameAverage(int[] nums) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        int half = sum >> 1;
        int[] dp = new int[half + 1];
        dp[0] = 1;
        if (sum == 0) {
            return n > 1;
        }
        for (int i = 0; i < n; ++i) {
            for (int j = half; j >= nums[i]; --j) {
                dp[j] = dp[j] | (dp[j - nums[i]] << 1);
                if (j > 0 && j * n % sum == 0 && ((1 << j * n / sum) & dp[j]) != 0) {
                    return true;
                }
            }
        }
        return false;
    }

//    public static void main(String[] args) {
//        Problem0805SplitArrayWithSameAverage a = new Problem0805SplitArrayWithSameAverage();
//        int[] nums = {5, 3, 11, 19, 2};
//        a.splitArraySameAverage(nums);
//    }
}
