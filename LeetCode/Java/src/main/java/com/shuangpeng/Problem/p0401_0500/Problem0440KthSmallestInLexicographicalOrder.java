package com.shuangpeng.Problem.p0401_0500;

public class Problem0440KthSmallestInLexicographicalOrder {

    public int findKthNumber0(int n, int k) {
        int ans = 0;
        int count = 0;
        while (count < k) {
            int left = ans == 0 ? 1 : 0, right = 10;
            int pre = ans * 10;
            while (left < right - 1) {
                int mid = left + ((right - left) >> 1);
                int c = getCount(n, pre + left, pre + mid);
                if (count + c < k) {
                    left = mid;
                    count += c;
                } else {
                    right = mid;
                }
            }
            ++count;
            ans = pre + left;
        }
        return ans;
    }

    private int getCount(int n, long cur, long next) {
        int ans = 0;
        while (cur <= n) {
            ans += Math.min(next, n + 1) - cur;
            if (next > n) {
                break;
            }
            cur *= 10;
            next *= 10;
        }
        return ans;
    }

    public int findKthNumber(int n, int k) {
        int prefix = 1;
        int count = 1;
        while (count < k) {
            int c = getCount(n, prefix);
            if (count + c > k) {
                prefix *= 10;
                count++;
            } else {
                prefix++;
                count += c;
            }
        }
        return prefix;
    }

    private int getCount(int n, long prefix) {
        int ans = 0;
        for (long cur = prefix, next = prefix + 1; cur <= n; cur *= 10, next *= 10) {
            ans += Math.min(next, n + 1) - cur;
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem0440KthSmallestInLexicographicalOrder a = new Problem0440KthSmallestInLexicographicalOrder();
//        a.findKthNumber(100, 10);
//    }
}
