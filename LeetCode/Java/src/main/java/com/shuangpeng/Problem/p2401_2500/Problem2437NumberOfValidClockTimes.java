package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2437NumberOfValidClockTimes（有效时间的数目）
 * @date 2023/5/9 10:38 AM
 */
public class Problem2437NumberOfValidClockTimes {

    public int countTime(String time) {
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
}
