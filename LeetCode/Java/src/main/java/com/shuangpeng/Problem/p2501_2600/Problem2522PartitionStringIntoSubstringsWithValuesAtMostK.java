package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2522PartitionStringIntoSubstringsWithValuesAtMostK（将字符串分割成值不超过K的子字符串）
 * @date 2023/11/30 12:10 AM
 */
public class Problem2522PartitionStringIntoSubstringsWithValuesAtMostK {

    public int minimumPartition0(String s, int k) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < n;) {
            long num = 0;
            while (i < n && num * 10 + s.charAt(i) - '0' <= k) {
                num = num * 10 + s.charAt(i++) - '0';
            }
            if (num == 0) {
                return -1;
            }
            ans++;
        }
        return ans;
    }

    public int minimumPartition(String s, int k) {
        int n = s.length(), ans = 0;
        long num = 0;
        for (int i = 0; i < n; i++) {
            int j = s.charAt(i) - '0';
            if (j > k) {
                return -1;
            }
            num = num * 10 + j;
            if (num > k) {
                ans++;
                num %= 10;
            }
        }
        return ans + 1;
    }
}
