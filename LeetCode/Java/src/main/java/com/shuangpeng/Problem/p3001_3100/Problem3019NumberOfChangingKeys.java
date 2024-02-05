package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3019NumberOfChangingKeys（按键变更的次数）
 * @date 2024/2/5 10:47 AM
 */
public class Problem3019NumberOfChangingKeys {

    public int countKeyChanges0(String s) {
        int cnt = 0, n = s.length();
        for (int i = 1; i < n; i++) {
            int k = s.charAt(i) - s.charAt(i - 1);
            if (k != 32 && k != -32 && k != 0) {
                cnt++;
            }
        }
        return cnt;
    }

    public int countKeyChanges(String s) {
        int ans = 0;
        for (int i = 1; i < s.length(); i++) {
            if ((s.charAt(i - 1) & 31) != (s.charAt(i) & 31)) {
                ans++;
            }
        }
        return ans;
    }
}
