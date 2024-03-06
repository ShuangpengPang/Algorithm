package com.shuangpeng.Problem.p1401_1500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1491AverageSalaryExcludingTheMinimumAndMaximumSalary（去掉最低工资和最高工资后的工资平均值）
 * @date 2024/3/6 11:55 AM
 */
public class Problem1491AverageSalaryExcludingTheMinimumAndMaximumSalary {

    public double average(int[] salary) {
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        double sum = 0;
        for (int s : salary) {
            min = Math.min(min, s);
            max = Math.max(max, s);
            sum += s;
        }
        return (sum - min - max) / (salary.length - 2);
    }
}
