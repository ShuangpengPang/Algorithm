package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2565SubsequenceWithTheMinimumScore（最少得分子序列）
 * @date 2024/1/28 7:29 PM
 */
public class Problem2565SubsequenceWithTheMinimumScore {

    public int minimumScore0(String s, String t) {
        char[] cs = s.toCharArray(), ct = t.toCharArray();
        int n1 = cs.length, n2 = ct.length;
        int[] index1 = new int[n2], index2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            index1[i] = n1;
            index2[i] = -1;
        }
        int left = 0;
        for (int i = 0; left < n2 && i < n1; i++) {
            if (ct[left] == cs[i]) {
                index1[left++] = i;
            }
        }
        if (left == n2) {
            return 0;
        }
        int ans = n2 - left;
        int right = n2 - 1;
        for (int i = n1 - 1; i >= 0; i--) {
            if (ct[right] == cs[i]) {
                index2[right--] = i;
            }
        }
        ans = Math.min(ans, right + 1);
        for (int i = 0, j = right + 1; i < left && j < n2; i++) {
            while (j < n2 && index2[j] <= index1[i]) {
                j++;
            }
            ans = Math.min(ans, j - i - 1);
        }
        return ans;
    }

    public int minimumScore(String s, String t) {
        char[] cs = s.toCharArray(), ct = t.toCharArray();
        int n1 = cs.length, n2 = ct.length;
        int[] suf = new int[n1 + 1];
        suf[n1] = n2;
        for (int i = n1 - 1, j = n2 - 1; i >= 0; i--) {
            suf[i] = j >= 0 && cs[i] == ct[j] ? j-- : j + 1;
        }
        int ans = suf[0];
        if (ans == 0) {
            return 0;
        }
        for (int i = 0, j = 0; i < n1; i++) {
            if (cs[i] == ct[j]) {
                ans = Math.min(ans, suf[i + 1] - ++j);
            }
        }
        return ans;
    }
}
