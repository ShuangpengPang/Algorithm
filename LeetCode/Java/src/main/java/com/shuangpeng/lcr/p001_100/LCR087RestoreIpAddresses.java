package com.shuangpeng.lcr.p001_100;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR087RestoreIpAddresses（复原 IP 地址）
 * @date 2024/7/4 7:14 PM
 */
public class LCR087RestoreIpAddresses {

    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        dfs(s.toCharArray(), 0, new int[4], 0, ans);
        return ans;
    }

    private void dfs(char[] cs, int start, int[] ip, int pos, List<String> ans) {
        int n = cs.length;
        if (start == n || pos == 4) {
            if (start == n && pos == 4) {
                StringBuilder sb = new StringBuilder();
                sb.append(ip[0]);
                for (int i = 1; i < 4; i++) {
                    sb.append('.').append(ip[i]);
                }
                ans.add(sb.toString());
            }
            return;
        }
        if (cs[start] == '0') {
            ip[pos] = 0;
            dfs(cs, start + 1, ip, pos + 1, ans);
        } else {
            int num = 0;
            for (int i = start; i < n && num <= 255; i++) {
                num = num * 10 + cs[i] - '0';
                if (num <= 255) {
                    ip[pos] = num;
                    dfs(cs, i + 1, ip, pos + 1, ans);
                }
            }
        }
    }
}
