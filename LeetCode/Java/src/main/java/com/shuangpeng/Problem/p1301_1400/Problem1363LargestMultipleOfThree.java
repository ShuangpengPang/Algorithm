package com.shuangpeng.Problem.p1301_1400;

/**
 * @Description: Problem1363LargestMultipleOfThree（形成三的最大倍数）
 * @Date 2022/8/9 3:02 PM
 * @Version 1.0
 */
public class Problem1363LargestMultipleOfThree {

    public String largestMultipleOfThree(int[] digits) {
        int a = 0, b = 0;
        int[] cnt = new int[10];
        for (int d : digits) {
            cnt[d]++;
            if (d % 3 == 1) {
                a++;
            } else if (d % 3 == 2) {
                b++;
            }
        }
        int m = (a + 2 * b) % 3;
        if (m == 1) {
            if (a > 0) {
                a--;
            } else {
                b -= 2;
            }
        } else if (m == 2) {
            if (b > 0) {
                b--;
            } else {
                a -= 2;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i > 0; i--) {
            int mod = i % 3;
            if (mod == 0) {
                add(sb, i, cnt[i]);
            } else if (mod == 1) {
                int count = Math.min(a, cnt[i]);
                add(sb, i, count);
                a -= count;
            } else if (mod == 2) {
                int count = Math.min(b, cnt[i]);
                add(sb, i, count);
                b -= count;
            }
        }
        if (sb.length() > 0) {
            add(sb, 0, cnt[0]);
        } else if (cnt[0] > 0) {
            sb.append(0);
        }
        return sb.toString();
    }

    private void add(StringBuilder sb, int d, int count) {
        for (int i = 0; i < count; i++) {
            sb.append(d);
        }
    }
}
