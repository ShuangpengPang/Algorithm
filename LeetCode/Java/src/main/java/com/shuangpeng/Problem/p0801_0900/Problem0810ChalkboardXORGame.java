package com.shuangpeng.Problem.p0801_0900;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem0810ChalkboardXORGame {

    public boolean xorGame0(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        Set<Integer> keys = new HashSet<>(map.keySet());
        for (int key : map.keySet()) {
            if ((map.get(key) & 1) == 0) {
                keys.remove(key);
            }
        }
        int xor = 0;
        for (int num : keys) {
            xor ^= num;
        }
        return xor == 0 || (keys.size() & 1) == 0;
    }

    public boolean xorGame(int[] nums) {
        if ((nums.length & 1) == 0) {
            return true;
        }
        int xor = 0;
        for (int num : nums) {
            xor ^= num;
        }
        return xor == 0;
    }
}
