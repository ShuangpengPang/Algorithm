package com.shuangpeng.Problem.p3101_3200;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3180MaximumTotalRewardUsingOperationsI（执行操作可获得的最大总奖励）
 * @date 2024/7/30 12:05 PM
 */
public class Problem3180MaximumTotalRewardUsingOperationsI {

    public int maxTotalReward0(int[] rewardValues) {
        Arrays.sort(rewardValues);
        int n = rewardValues.length, N = rewardValues[n - 1];
        boolean[] set = new boolean[N];
        set[0] = true;
        int ans = 0;
        for (int r : rewardValues) {
            for (int i = r - 1; i >= 0; i--) {
                if (set[i]) {
                    int s = i + r;
                    ans = Math.max(ans, s);
                    if (s < N) {
                        set[s] = true;
                    }
                }
            }
        }
        return ans;
    }

    public int maxTotalReward1(int[] rewardValues) {
        int m = 0;
        for (int r : rewardValues) {
            m = Math.max(m, r);
        }
        Set<Integer> set = new HashSet<>();
        set.add(0);
        for (int r : rewardValues) {
            if (m - 1 - r != r && set.contains(m - 1 - r)) {
                return m * 2 - 1;
            }
            set.add(r);
        }
        Arrays.sort(rewardValues);
        BigInteger b = BigInteger.ONE;
        for (int r : Arrays.stream(rewardValues).distinct().sorted().toArray()) {
            BigInteger mask = BigInteger.ONE.shiftLeft(r).subtract(BigInteger.ONE);
            b = b.or(b.and(mask).shiftLeft(r));
        }
        return b.bitLength() - 1;
    }

    public int maxTotalReward(int[] rewardValues) {
        int max = 0;
        for (int r : rewardValues) {
            max = Math.max(max, r);
        }
        Set<Integer> set = new HashSet<>();
        set.add(0);
        for (int r : rewardValues) {
            if (r != max - 1 - r && set.contains(max - 1 - r)) {
                return (max << 1) - 1;
            }
            set.add(r);
        }
        Arrays.sort(rewardValues);
        int m = (2 * max + 63) / 64;
        long[] bits = new long[m + 2];
        bits[0] = 1;
        for (int r : rewardValues) {
            shift(bits, r);
        }
        for (int i = m - 1; i >= 0; i--) {
            if (bits[i] != 0) {
                return (i + 1) * 64 - Long.numberOfLeadingZeros(bits[i]) - 1;
            }
        }
        return 0;
    }

    private void shift(long[] bits, int n) {
        int cnt = n / 64, m = n % 64;
        for (int i = 0; i < cnt; i++) {
            bits[i + cnt] |= bits[i] << m;
            bits[i + cnt + 1] |= bits[i] >>> (63 - m) >>> 1;
        }
        long num = bits[cnt] & ((1L << m) - 1);
        bits[cnt << 1] |= num << m;
        bits[(cnt << 1) + 1] |= num >>> (63 - m) >>> 1;
    }
}
