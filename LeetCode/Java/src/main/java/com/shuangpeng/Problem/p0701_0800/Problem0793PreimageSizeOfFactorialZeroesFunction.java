package com.shuangpeng.Problem.p0701_0800;

public class Problem0793PreimageSizeOfFactorialZeroesFunction {

    public int preimageSizeFZF0(int k) {
        long left = 0, right = (long) k * 5;
        while (left <= right) {
            long mid = left + ((right - left) >> 1);
            long count = 0;
            long n = 5;
            while (mid >= n) {
                count += mid / n;
                n *= 5;
            }
            if (count > k) {
                right = mid - 1;
            } else if (count < k) {
                left = mid + 1;
            } else {
                return 5;
            }
        }
        return 0;
    }

    public int preimageSizeFZF(int k) {
        long left = 0, right = k * 5L;
        while (left <= right) {
            long mid = left + (right - left >> 1);
            long cnt = getCount(mid);
            if (k > cnt) {
                left = mid + 1;
            } else if (k < cnt) {
                right = mid - 1;
            } else {
                return 5;
            }
        }
        return 0;
    }

    private long getCount(long n) {
        long base = 5, cnt = 0;
        while (base <= n) {
            cnt += n / base;
            base *= 5;
        }
        return cnt;
    }

//    public static void main(String[] args) {
//        Problem0793PreimageSizeOfFactorialZeroesFunction a = new Problem0793PreimageSizeOfFactorialZeroesFunction();
//        a.preimageSizeFZF(92);
//    }
}
