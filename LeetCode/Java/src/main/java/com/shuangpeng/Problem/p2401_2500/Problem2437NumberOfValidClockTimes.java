package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2437NumberOfValidClockTimes（有效时间的数目）
 * @date 2023/5/9 10:38 AM
 */
public class Problem2437NumberOfValidClockTimes {

    public int countTime0(String time) {
        int ans = 0;
        char c1 = time.charAt(0), c2 = time.charAt(1);
        char c3 = time.charAt(3), c4 = time.charAt(4);
        for (char h1 = '0'; h1 <= '2'; h1++) {
            if (c1 != '?' && c1 != h1) {
                continue;
            }
            for (char h2 = '0'; h2 <= '9'; h2++) {
                if (h1 == '2' && h2 > '3') {
                    break;
                }
                if (c2 != '?' && c2 != h2) {
                    continue;
                }
                for (char m1 = '0'; m1 <= '5'; m1++) {
                    if (c3 != '?' && c3 != m1) {
                        continue;
                    }
                    for (char m2 = '0'; m2 <= '9'; m2++) {
                        if (c4 != '?' && c4 != m2) {
                            continue;
                        }
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    public int countTime(String time) {
        int[] r1 = getRange(time.charAt(0)), r2 = getRange(time.charAt(1));
        int[] r3 = getRange(time.charAt(3)), r4 = getRange(time.charAt(4));
        int ans = 0;
        for (int h1 = r1[0]; h1 <= r1[1]; h1++) {
            if (h1 > 2) {
                break;
            }
            for (int h2 = r2[0]; h2 <= r2[1]; h2++) {
                if (h1 == 2 && h2 > 3) {
                    break;
                }
                for (int m1 = r3[0]; m1 <= r3[1]; m1++) {
                    if (m1 > 5) {
                        break;
                    }
                    for (int m2 = r4[0]; m2 <= r4[1]; m2++) {
                        ans++;
                    }
                }
            }
        }
        return ans;
    }

    private int[] getRange(char c) {
        if (c == '?') {
            return new int[]{0, 9};
        }
        return new int[]{c - '0', c - '0'};
    }
}
