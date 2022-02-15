package com.shuangpeng.Problem.p0801_0900;

public class Problem0878NthMagicalNumber {

    public int nthMagicalNumber(int n, int a, int b) {
        final int M = (int) 1e9 + 7;
        int g = gcd(a, b);
        int c = a * b / g;
        int count = c / a + c / b - 1;
        int k = n / count;
        int m = n % count;
        return (int) (((long) c * k + getNumber(m, a, b)) % M);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private int getNumber(int m, int a, int b) {
        int ans = 0;
        int a1 = 1, b1 = 1;
        for (int i = 0; i < m; ++i) {
            int x = a * a1, y = b * b1;
            if (x < y) {
                ans = x;
                ++a1;
            } else {
                ans = y;
                ++b1;
            }
        }
        return ans;
    }

//    public int nthMagicalNumber(int N, int A, int B) {
//        int MOD = 1_000_000_007;
//        int L = A / gcd(A, B) * B;
//
//        long lo = 0;
//        long hi = (long) 1e15;
//        while (lo < hi) {
//            long mi = lo + (hi - lo) / 2;
//            // If there are not enough magic numbers below mi...
//            if (mi / A + mi / B - mi / L < N)
//                lo = mi + 1;
//            else
//                hi = mi;
//        }
//
//        return (int) (lo % MOD);
//    }
//
//    public int gcd(int x, int y) {
//        if (x == 0) return y;
//        return gcd(y % x, x);
//    }

    public static void main(String[] args) {
        Problem0878NthMagicalNumber a = new Problem0878NthMagicalNumber();

    }

//    public long maxRunTime(int n, int[] batteries) {
//        int m = batteries.length;
//        if (m < n) {
//            return 0;
//        }
//        Long[] copy = new Long[m];
//        for (int i = 0; i < m; ++i) {
//            copy[i] = (long) batteries[i];
//        }
//        Arrays.sort(copy, (a, b) -> b - a);
//        PriorityQueue<Long> pq = new PriorityQueue<>();
//        for (int i = 0; i < n; ++i) {
//            pq.offer(copy[i]);
//        }
//        for (int i = n; i < m; ++i) {
//            long num = pq.poll();
//            pq.offer(num + copy[i]);
//        }
//        return pq.peek();
//    }
}
