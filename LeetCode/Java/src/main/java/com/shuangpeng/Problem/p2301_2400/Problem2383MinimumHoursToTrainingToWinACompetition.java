package com.shuangpeng.Problem.p2301_2400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2383MinimumHoursToTrainingToWinACompetition（赢得比赛需要的最少训练时长）
 * @date 2023/3/13 11:41 AM
 */
public class Problem2383MinimumHoursToTrainingToWinACompetition {

    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int minExperience = initialExperience, n = energy.length;
        for (int i = 0; i < n; i++) {
            initialEnergy -= energy[i];
            minExperience = Math.min(minExperience, initialExperience - experience[i]);
            initialExperience += experience[i];
        }
        return Math.max(0, -(initialEnergy - 1)) + Math.max(0, -(minExperience - 1));
    }
}
