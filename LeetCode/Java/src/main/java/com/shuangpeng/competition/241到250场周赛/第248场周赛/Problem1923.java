package com.shuangpeng.competition.第248场周赛;

import java.util.*;

public class Problem1923 {

    public int longestCommonSubpath0(int n, int[][] paths) {
        int minLength = Integer.MAX_VALUE;
        int m = paths.length;
        for (int i = 0; i < m; i++) {
            minLength = Math.min(minLength, paths[i].length);
        }
        int left = 1, right = minLength;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (check(paths, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }

    private boolean check(int[][] paths, int length) {
        long mod = Long.MAX_VALUE;
        int m = paths.length;
        int base = 135721;
        Set<Long>[] sets = new Set[m];
        for (int i = 0; i < m; i++) {
            sets[i] = new HashSet<>();
            int[] path = paths[i];
            long value = 0;
            for (int j = 0; j < length; j++) {
                value = (value * base + path[j]) % mod;
            }
            if (i == 0) {
                sets[i].add(value);
            } else if (sets[i - 1].contains(value)) {
                sets[i].add(value);
            }
            for (int j = length; j < path.length; j++) {
                value = ((value - (long) path[j - length] * getMod(base, length - 1, mod)) * base + path[j]) % mod;
                value = (value + mod) % mod;
                if (i == 0) {
                    sets[i].add(value);
                } else if (sets[i - 1].contains(value)) {
                    sets[i].add(value);
                }
            }
            if (sets[i].isEmpty()) {
                break;
            }
        }
        return sets[m - 1] != null && !sets[m - 1].isEmpty();
    }

    private long getMod(long n, int length, long mod) {
        long answer = 1;
        while (length > 0) {
            if ((length & 1) == 1) {
                answer = answer * n % mod;
            }
            n = n * n % mod;
            length >>= 1;
        }
        return answer;
    }

    public int longestCommonSubpath(int n, int[][] paths) {
        int left = 1, right = getMin(paths);
        long base = 135721;//100000 + rand.nextInt(1000000-100000+1);
        long mod = Long.MAX_VALUE;
        long muti = 0;
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            muti = pow(base, mid, mod);
            Set<Long> set = new HashSet<>();
            for (int i = 0; i < paths.length; i++) {
                long hash = 0;
                Set<Long> tmp = new HashSet<>();
                for (int j = 0; j < mid; j++) {
                    hash = (hash * base + paths[i][j]) % mod;
                }
                if (i == 0 || set.contains(hash)) {
                    tmp.add(hash);
                }

                for (int j = mid; j < paths[i].length; j++) {
                    hash = (hash * base + paths[i][j] - muti * paths[i][j - mid]) % mod;
                    if (i == 0 || set.contains(hash)) {
                        tmp.add(hash);
                    }
                }
                set = tmp;
                if (set.isEmpty()) {
                    break;
                }
            }
            if (set.isEmpty()) {
                right = mid - 1;
            } else {
                left = mid + 1;
                ans = mid;
            }
        }
        return ans;
    }

    int getMin(int[][] paths) {
        int min = paths[0].length;
        for (int[] path : paths) {
            min = Math.min(path.length, min);
        }
        return min;
    }

    long pow(long base, long n, long mod) {
        long res = 1;
        for (int i = 0; i < n; i++) {
            res = (res * base) % mod;
        }
        return res;
    }

    int N = 100010;
    long[] h = new long[N];
    long[] p = new long[N];
    int[][] path;
    Map<Long, Integer> cnt = new HashMap<>();
    Map<Long, Integer> inner = new HashMap<>();

    public int longestCommonSubpath1(int n, int[][] paths) {
        this.path = paths;
        int l = 0;
        int r = N;
        for (int i = 0; i < paths.length; i++) {
            r = Math.min(r, paths[i].length);
        }
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid))
                l = mid;
            else
                r = mid - 1;
        }
        return l;
    }

    private boolean check(int mid) {
        cnt.clear();
        inner.clear();
        p[0] = 1;
        for (int j = 0; j < path.length; j++) {
            int n = path[j].length;
            for (int i = 1; i <= n; i++) {
                p[i] = p[i - 1] * 133331;
                h[i] = h[i - 1] * 133331 + path[j][i - 1];
            }
            for (int i = mid; i <= n; i++) {
                long val = get(i - mid + 1, i);
                if (!inner.containsKey(val) || inner.get(val) != j) {
                    inner.put(val, j);
                    cnt.put(val, cnt.getOrDefault(val, 0) + 1);
                }
            }
        }
        int max = 0;
        for (long key : cnt.keySet()) {
            max = Math.max(max, cnt.get(key));
        }
        return max == path.length;
    }

    public long get(int l, int r) {
        return h[r] - h[l - 1] * p[r - l + 1];
    }

//    public static void main(String[] args) {
//        Problem1923 a = new Problem1923();
//    }
}
