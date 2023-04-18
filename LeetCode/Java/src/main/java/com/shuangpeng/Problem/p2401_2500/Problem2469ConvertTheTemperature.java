package com.shuangpeng.Problem.p2401_2500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2469ConvertTheTemperature（温度转换）
 * @date 2023/3/21 7:41 PM
 */
public class Problem2469ConvertTheTemperature {

    public double[] convertTemperature(double celsius) {
        return new double[]{celsius + 273.15d, celsius * 1.8d + 32d};
    }
}
