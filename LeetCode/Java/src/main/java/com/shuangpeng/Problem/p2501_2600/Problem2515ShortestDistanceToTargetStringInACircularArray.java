package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2515ShortestDistanceToTargetStringInACircularArray（到目标字符串的最短距离）
 * @date 2024/3/31 12:32 AM
 */
public class Problem2515ShortestDistanceToTargetStringInACircularArray {

    public int closetTarget0(String[] words, String target, int startIndex) {
        int n = words.length, ans = n;
        for (int i = startIndex, j = 0; j < n; i++, j++) {
            if (i == n) {
                i = 0;
            }
            if (words[i].equals(target)) {
                ans = Math.min(ans, j);
                break;
            }
        }
        for (int i = startIndex, j = 0; j < n; i--, j++) {
            if (i == -1) {
                i = n - 1;
            }
            if (words[i].equals(target)) {
                ans = Math.min(ans, j);
                break;
            }
        }
        return ans == n ? -1 : ans;
    }

    public int closetTarget(String[] words, String target, int startIndex) {
        int n = words.length, ans = n;
        for (int i = 0; i < n; i++) {
            if (words[i].equals(target)) {
                int diff = Math.abs(i - startIndex);
                ans = Math.min(ans, Math.min(diff, n - diff));
            }
        }
        return ans == n ? -1 : ans;
    }
}
