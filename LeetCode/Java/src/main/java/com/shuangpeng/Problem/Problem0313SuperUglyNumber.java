package com.shuangpeng.Problem;

import java.util.*;

public class Problem0313SuperUglyNumber {

    public int nthSuperUglyNumber0(int n, int[] primes) {
        if (n == 1) {
            return 1;
        }
        int length = primes.length;
        List<Info> list = new LinkedList<>();
        list.add(new Info(1, 0));
        int count = 1;
        int current = 1;
        while (count < n) {
            int size = list.size();
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < size; i++) {
                Info info = list.get(i);
                int j = info.index;
                while (j < length && info.data * primes[j] <= current) {
                    j++;
                }
                if (j < length) {
                    min = (int) Math.min(min, info.data * primes[j]);
                }
                info.index = j;
            }
            current = min;
            count++;
            list.add(new Info(current, 0));
            while (!list.isEmpty()) {
                if (list.get(0).index == length) {
                    list.remove(0);
                } else {
                    break;
                }
            }
        }
        return current;
    }

    class Info {
        long data;
        int index;
        long next;

        public Info(int data, int index) {
            this.data = data;
            this.index = index;
        }

        public Info(long data, int index, long next) {
            this.data = data;
            this.index = index;
            this.next = next;
        }
    }

    public int nthSuperUglyNumber3(int n, int[] primes) {
        if (n == 1) {
            return 1;
        }
        long answer = 1;
        int count = 1;
        PriorityQueue<Info> queue = new PriorityQueue<>(Comparator.comparingLong(a -> a.next));
        queue.offer(new Info(1, 0, primes[0]));
        while (count < n) {
            Info info = queue.poll();
            long next = info.next;
            if (next > answer) {
                count++;
                answer = next;
                queue.offer(new Info(next, 0, next * primes[0]));
            }
            if (info.index < primes.length - 1) {
                info.index++;
                info.next = info.data * primes[info.index];
                queue.offer(info);
            }
        }
        return (int) answer;
    }

    public int nthSuperUglyNumber1(int n, int[] primes) {
        int len = primes.length;
        int dp[] = new int[n];
        dp[0] = 1;
        /*梳理一下思路，dp[i]保存的是第i个超级丑数*/
        /*index[i]表示的是primes里面的第i个数下一个将要相乘的数在dp中的位置，
        反过来想，对于每个primes来说，我们都需要和dp中已经算出来的结果相乘算，
        然后取最小的那个作为新的dp元素
        索引index实际上表示是这个素数已经和dp中的哪个位置结合了，下一个位置的坐标是多少 */
        int index[] = new int[len];
        /*可能存在重复的丑数，所以呢，不要在for循环里面加break，把所有的情况都+1*/
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            /*遍历对比一下值，找出最小的，*/
            for (int j = 0; j < len; j++) {
                if (min > primes[j] * dp[index[j]]) {
                    min = primes[j] * dp[index[j]];//这个地方就是当前质因数和他要结合的dp
                }
            }
            dp[i] = min;
            /*那个素数要乘以dp的坐标index要加1，向后推一个位
            * 如果存在重复的值，也就是说不同的质因数相乘，得出来相同的结果了，
            * 我们就把这几个位置都+1，这样就可以避免出现重复的值了。
            * 你想想，假如你找到了对应的素数的index，把它加1之后就break掉，那么后面的数也可以算出这个结果，
            * 下次循环的时候，势必会把这个重复的数当成下一个dp，因为这个数肯定要比下一个丑数小
            * 所以我们在for循环中不要加break；*/
            for (int j = 0; j < len; j++) {
                if (min == primes[j] * dp[index[j]]) {
                    index[j]++;
                }
            }

        }
        return dp[n - 1];
    }



















    public int nthSuperUglyNumber2(int n, int[] primes) {
        Set<Long> seen = new HashSet<>();
        PriorityQueue<Long> queue = new PriorityQueue<>();
        seen.add(1L);
        queue.offer(1L);
        int ugly = 1;
        for (int i = 0; i < n; ++i) {
            long curr = queue.poll();
            ugly = (int) curr;
            for (int p : primes) {
                long next = curr * p;
                if (seen.add(next)) {
                    queue.offer(next);
                }
            }
        }
        return ugly;
    }

    public int nthSuperUglyNumber(int n, int[] primes) {
        int m = primes.length;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int[] pointers = new int[m];
        Arrays.fill(pointers, 1);
        for (int i = 2; i <= n; ++i) {
            int minValue = Integer.MAX_VALUE;
            for (int j = 0; j < m; ++j) {
                if (primes[j] * dp[pointers[j]] < minValue) {
                    minValue = primes[j] * dp[pointers[j]];
                }
            }
            for (int j = 0; j < m; ++j) {
                if (primes[j] * dp[pointers[j]] == minValue) {
                    pointers[j]++;
                }
            }
            dp[i] = minValue;
        }
        return dp[n];
    }

    // [1,2,4,7,8,13,14,16,19,26,28,32]

//    public static void main(String[] args) {
//        Problem0313SuperUglyNumber a = new Problem0313SuperUglyNumber();
//        a.nthSuperUglyNumber(12, new int[]{2, 7, 13, 19});
//    }
}
