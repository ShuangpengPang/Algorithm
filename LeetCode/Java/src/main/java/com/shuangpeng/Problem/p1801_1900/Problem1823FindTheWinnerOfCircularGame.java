package com.shuangpeng.Problem.p1801_1900;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description: Problem1823FindTheWinnerOfCircularGame（找出游戏的获胜者）
 * @Date 2022/5/13 4:07 PM
 * @Version 1.0
 */
public class Problem1823FindTheWinnerOfCircularGame {

    public int findTheWinner(int n, int k) {
        return n == 1 ? 1 : (k + findTheWinner(n - 1, k) - 1) % n + 1;
    }
}

class Problem1823FindTheWinnerOfCircularGame0 {

    public int findTheWinner(int n, int k) {
        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= n; ++i) {
            list.add(i);
        }
        int idx = 0;
        while (list.size() > 1) {
            idx = (idx + k - 1) % list.size();
            list.remove(idx);
        }
        return list.get(0);
    }
}

class Problem1823FindTheWinnerOfCircularGame01 {

    public int findTheWinner(int n, int k) {
        int winner = 1;
        for (int i = 1; i < n; ++i) {
            winner = (k + winner - 1) % (i + 1) + 1;
        }
        return winner;
    }
}
