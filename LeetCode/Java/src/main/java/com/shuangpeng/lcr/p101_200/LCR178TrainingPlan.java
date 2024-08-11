package com.shuangpeng.lcr.p101_200;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR178TrainingPlan（LCR 178. 训练计划 VI）
 * @date 2024/8/11 4:32 PM
 */
public class LCR178TrainingPlan {

    public int trainingPlan0(int[] actions) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : actions) {
            map.merge(a, 1, Integer::sum);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return -1;
    }

    public int trainingPlan(int[] actions) {
        int ones = 0, twos = 0;
        for(int action : actions){
            ones = ones ^ action & ~twos;
            twos = twos ^ action & ~ones;
        }
        return ones;
    }
}
