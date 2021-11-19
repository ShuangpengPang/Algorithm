package com.shuangpeng.Problem.p0301_0400;

import java.util.HashMap;
import java.util.Map;

public class Problem0318MaximumProductOfWordLengths {

    public int maxProduct(String[] words) {
        Map<Integer, Integer> map = new HashMap<>();
        for (String word : words) {
            int n = word.length();
            int mask = 0;
            for (int i = 0; i < n; ++i) {
                mask |= (1 << (word.charAt(i) - 'a'));
            }
            int length = map.getOrDefault(mask, 0);
            if (n > length) {
                map.put(mask, n);
            }
        }
        int ans = 0;
        for (Map.Entry<Integer, Integer> entry1 : map.entrySet()) {
            for (Map.Entry<Integer, Integer> entry2 : map.entrySet()) {
                if ((entry1.getKey() & entry2.getKey()) == 0) {
                    ans = Math.max(ans, entry1.getValue() * entry2.getValue());
                }
            }
        }
        return ans;
    }
}
