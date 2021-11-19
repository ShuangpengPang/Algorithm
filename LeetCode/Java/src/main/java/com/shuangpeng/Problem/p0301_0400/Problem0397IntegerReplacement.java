package com.shuangpeng.Problem.p0301_0400;

import java.util.*;

public class Problem0397IntegerReplacement {

    public int integerReplacement0(int n) {
        if (n <= 1) {
            return 0;
        }
        Set<Long> visited = new HashSet<>();
        Queue<Long> queue = new LinkedList<>();
        queue.offer((long) n);
        visited.add((long) n);
        int step = 0;
        while (true) {
            step++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                long m = queue.poll();
                if (m == 2) {
                    return step;
                }
                if ((m & 1) == 0) {
                    m >>= 1;
                    if (!visited.contains(m)) {
                        visited.add(m);
                        queue.offer(m);
                    }
                } else {
                    queue.offer(m - 1);
                    queue.offer(m + 1);
                }
            }
        }
    }

    public int integerReplacement1(int n) {
        if (n <= 1) {
            return 0;
        }
        long m = n;
        int answer = 0;
        while (m != 1) {
            if (m == 3) {
                return answer + 2;
            }
            long mod = m & 3;
            if (mod == 0 || mod == 2) {
                m >>= 1;
            } else if (mod == 1) {
                m -= 1;
            } else {
                m += 1;
            }
            answer++;
        }
        return answer;
    }

    public int integerReplacement2(int n) {
        return dp(n, new HashMap<>());
    }

    private int dp(long n, Map<Long, Integer> memo) {
        if (n == 1) {
            return 0;
        }
        if ((n & 1) == 0) {
            return dp(n >> 1, memo) + 1;
        }
        int count = memo.getOrDefault(n, -1);
        if (count != -1) {
            return count;
        }
        int ans = Math.min(dp(n - 1, memo), dp(n + 1, memo)) + 1;
        memo.put(n, ans);
        return ans;
    }

    public int integerReplacement(int n) {
//        if (n == Integer.MAX_VALUE) {
//            return 32;
//        }
        int ans = 0;
        while (n > 1) {
            if ((n & 1) == 0) {
                ++ans;
                n >>= 1;
            } else if ((n & 3) == 1) {
                ans += 2;
                n >>= 1;
            } else if (n > 3) {
                ans += 2;
                n = (n + 1) >> 1;
            } else {
                ans += 2;
                n >>= 1;
            }
        }
        return ans;
    }
}
