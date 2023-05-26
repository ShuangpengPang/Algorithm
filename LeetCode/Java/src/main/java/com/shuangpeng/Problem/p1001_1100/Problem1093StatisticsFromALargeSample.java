package com.shuangpeng.Problem.p1001_1100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1093StatisticsFromALargeSample（大样本统计）
 * @date 2023/5/26 2:24 PM
 */
public class Problem1093StatisticsFromALargeSample {

    public double[] sampleStats(int[] count) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int cnt = 0, maxFreq = 0, mode = -1;
        long sum = 0;
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                cnt += count[i];
                sum += (long) count[i] * i;
                min = Math.min(min, i);
                max = i;
                if (count[i] > maxFreq) {
                    maxFreq = count[i];
                    mode = i;
                }
            }
        }
        int c1 = (cnt + 1) >> 1, c2 = (cnt + 2) >> 1;
        int m1 = -1, m2 = -1;
        for (int i = 0, c = 0; i < count.length && c < c2; i++) {
            c += count[i];
            if (m1 == -1 && c >= c1) {
                m1 = i;
            }
            if (m2 == -1 && c >= c2) {
                m2 = i;
            }
        }
        return new double[]{min, max, (double) sum / cnt, (m1 + m2) / 2.0, mode};
    }
}
