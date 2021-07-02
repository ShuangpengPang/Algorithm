package com.shuangpeng.Problem;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

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

    public int integerReplacement(int n) {
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
}
