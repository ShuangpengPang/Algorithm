package com.shuangpeng.Problem.p2701_2800;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2798NumberOfEmployeesWhoMetTheTarget（满足目标工作时长的员工数目）
 * @date 2023/8/3 2:38 PM
 */
public class Problem2798NumberOfEmployeesWhoMetTheTarget {

    public int numberOfEmployeesWhoMetTarget0(int[] hours, int target) {
        int cnt = 0;
        for (int h : hours) {
            if (h >= target) {
                cnt++;
            }
        }
        return cnt;
    }

    public int numberOfEmployeesWhoMetTarget(int[] hours, int target) {
        return (int) Arrays.stream(hours).filter(h -> h >= target).count();
    }
}
