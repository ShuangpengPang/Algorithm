package com.shuangpeng.Problem.p1201_1300;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Description: Problem1298MaximumCandiesYouCanGetFromBoxes（你能从盒子里获得的最大糖果数）
 * @Date 2022/7/29 11:08 PM
 * @Version 1.0
 */
public class Problem1298MaximumCandiesYouCanGetFromBoxes {

    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        Set<Integer> boxSet = new HashSet<>(), keySet = new HashSet<>();
        for (int b : initialBoxes) {
            boxSet.add(b);
        }
        int ans = 0;
        boolean flag = true;
        while (flag) {
            flag = false;
            List<Integer> list = new ArrayList<>(boxSet);
            for (int b : list) {
                if (status[b] == 1 || status[b] == 0 && keySet.contains(b)) {
                    flag = true;
                    status[b] = -1;
                    ans += candies[b];
                    candies[b] = 0;
                    for (int box : containedBoxes[b]) {
                        boxSet.add(box);
                    }
                    boxSet.remove(b);
                    for (int key : keys[b]) {
                        keySet.add(key);
                    }
                    if (keySet.contains(b)) {
                        keySet.remove(b);
                    }
                }
            }
        }
        return ans;
    }
}
