package com.shuangpeng.lcr.p101_200;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR167DismantlingAction（LCR 167. 招式拆解 I）
 * @date 2024/8/17 11:49 AM
 */
public class LCR167DismantlingAction {

    public int dismantlingAction(String arr) {
        Map<Character, Integer> map = new HashMap<>();
        char[] cs = arr.toCharArray();
        int n = cs.length, ans = 0, length = 0;
        for (int i = 0; i < n; i++) {
            length = Math.min(length + 1, i - map.getOrDefault(cs[i], -1));
            ans = Math.max(ans, length);
            map.put(cs[i], i);
        }
        return ans;
    }
}
