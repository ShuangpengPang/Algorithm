package com.shuangpeng.Problem.p2601_2700;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2682FindTheLosersOfTheCircularGame（找出转圈游戏输家）
 * @date 2023/8/16 12:35 AM
 */
public class Problem2682FindTheLosersOfTheCircularGame {

    public int[] circularGameLosers(int n, int k) {
        boolean[] visited = new boolean[n];
        for (int i = 0, j = 0; !visited[i]; j++, i = (i + j * k) % n) {
            visited[i] = true;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                list.add(i + 1);
            }
        }
        return list.stream().mapToInt(a -> a).toArray();
    }
}
