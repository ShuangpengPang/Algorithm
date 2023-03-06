package com.shuangpeng.Problem.p1601_1700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1653MinimumDeletionsToMakeStringBalanced（使字符串平衡的最少删除次数）
 * @date 2023/3/6 4:09 PM
 */
public class Problem1653MinimumDeletionsToMakeStringBalanced {

    public int minimumDeletions0(String s) {
        int n = s.length();
        int[] cnt = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            cnt[i] = cnt[i + 1] + (s.charAt(i) == 'a' ? 1 : 0);
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0, count = 0; i <= n; i++) {
            ans = Math.min(ans, count + cnt[i]);
            if (i < n && s.charAt(i) == 'b') {
                count++;
            }
        }
        return ans;
    }

    public int minimumDeletions1(String s) {
        int n = s.length(), rightA = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') {
                rightA++;
            }
        }
        int ans = rightA;
        for (int i = 0, leftB = 0; i < n; i++) {
            if (s.charAt(i) == 'a') {
                rightA--;
            } else {
                leftB++;
            }
            ans = Math.min(ans, leftB + rightA);
        }
        return ans;
    }

    public int minimumDeletions2(String s) {
        char[] cs = s.toCharArray();
        int del = 0;
        for (char c : cs) {
            del += 'b' - c;
        }
        int ans = del;
        for (char c : cs) {
            del += ((c - 'a') << 1) - 1;
            ans = Math.min(ans, del);
        }
        return ans;
    }

    public int minimumDeletions(String s) {
        int n = s.length(), ans = 0, bCnt = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') {
                ans = Math.min(ans + 1, bCnt);
            } else {
                bCnt++;
            }
        }
        return ans;
    }
}
