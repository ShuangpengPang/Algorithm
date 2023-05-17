package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2446DetermineIfTwoEventsHaveConflict（判断两个事件是否存在冲突）
 * @date 2023/5/17 10:14 AM
 */
public class Problem2446DetermineIfTwoEventsHaveConflict {

    public boolean haveConflict(String[] event1, String[] event2) {
        int s1 = hash(event1[0]), e1 = hash(event1[1]);
        int s2 = hash(event2[0]), e2 = hash(event2[1]);
        return s2 <= e1 && s1 <= e2;
    }

    private int hash(String s) {
        return Integer.parseInt(s.substring(0, 2)) * 60 + Integer.parseInt(s.substring(3));
    }
}
