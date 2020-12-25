package com.shuangpeng.Problem;

import java.util.HashSet;
import java.util.Set;

public class Problem0202HappyNumber {

    public boolean isHappy0(int n) {
        Set<Integer> set = new HashSet<>();
        while (!set.contains(n)) {
            set.add(n);
            int sum = 0;
            while (n > 0) {
                sum += (n % 10) * (n % 10);
                n /= 10;
            }
            n = sum;
        }
        return set.contains(1);
    }

    public boolean isHappy(int n) {
        int slow = n;
        int fast = getNext(n);
        while (slow != 1 && fast != 1 && slow != fast) {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        }
        return slow == 1 || fast == 1;
    }

    public int getNext(int n) {
        int next = 0;
        while (n > 0) {
            int d = n % 10;
            next += d * d;
            n /= 10;
        }
        return next;
    }
}
