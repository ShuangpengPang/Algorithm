package com.shuangpeng.Problem.p2701_2800;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2709GreatestCommonDivisorTraversal（最大公约数遍历）
 * @date 2024/2/16 7:46 PM
 */
public class Problem2709GreatestCommonDivisorTraversal {

    public boolean canTraverseAllPairs(int[] nums) {
        int cnt = nums.length;
        if (cnt == 1) {
            return true;
        }
        Map<Integer, Integer> parent = new HashMap<>();
        for (int num : nums) {
            if (num == 1) {
                return false;
            }
            cnt -= getCount(parent, num);
        }
        return cnt == 1;
    }

    private int find(Map<Integer, Integer> parent, int x) {
        int p = parent.get(x);
        if (p == x) {
            return x;
        }
        p = find(parent, p);
        parent.put(x, p);
        return p;
    }

    private int union(Map<Integer, Integer> parent, int x, int y) {
        int px = find(parent, x), py = find(parent, y);
        if (px == py) {
            return px;
        }
        parent.put(py, px);
        return px;
    }

    private int getCount(Map<Integer, Integer> parent, int num) {
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                while (num % i == 0) {
                    num /= i;
                }
                if (parent.containsKey(i)) {
                    set.add(find(parent, i));
                }
                list.add(i);
            }
        }
        if (num > 1) {
            if (parent.containsKey(num)) {
                set.add(find(parent, num));
            }
            list.add(num);
        }
        for (int i = 0, p = list.get(0); i < list.size(); i++) {
            int x = list.get(i);
            if (!parent.containsKey(x)) {
                parent.put(x, x);
            }
            p = union(parent, p, x);
        }
        return set.size();
    }
}
