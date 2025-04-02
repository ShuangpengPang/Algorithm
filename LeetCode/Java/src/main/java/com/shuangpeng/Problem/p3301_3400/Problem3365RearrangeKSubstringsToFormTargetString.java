package com.shuangpeng.Problem.p3301_3400;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3365RearrangeKSubstringsToFormTargetString（重排子字符串以形成目标字符串）
 * @date 2025/4/2 14:38
 */
public class Problem3365RearrangeKSubstringsToFormTargetString {

    public boolean isPossibleToRearrange(String s, String t, int k) {
        int n = s.length(), m = n / k;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i += m) {
            map.merge(s.substring(i, i + m), 1, Integer::sum);
            map.merge(t.substring(i, i + m), -1, Integer::sum);
        }
        for (int c : map.values()) {
            if (c != 0) {
                return false;
            }
        }
        return true;
    }
}
