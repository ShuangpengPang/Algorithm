package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2432TheEmployeeThatWorkedOnTheLongestTask（处理用时最长的那个任务的员工）
 * @date 2023/5/5 10:38 AM
 */
public class Problem2432TheEmployeeThatWorkedOnTheLongestTask {

    public int hardestWorker(int n, int[][] logs) {
        int ans = 0, maxTime = 0, startTime = 0;
        for (int[] log : logs) {
            int id = log[0], time = log[1] - startTime;
            if (time > maxTime || (time == maxTime && id < ans)) {
                maxTime = time;
                ans = id;
            }
            startTime = log[1];
        }
        return ans;
    }
}
