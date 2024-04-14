package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2960CountTestedDevicesAfterTestOperations（统计已测试设备）
 * @date 2024/4/14 4:51 PM
 */
public class Problem2960CountTestedDevicesAfterTestOperations {

    public int countTestedDevices(int[] batteryPercentages) {
        int cnt = 0;
        for (int num : batteryPercentages) {
            if (num > cnt) {
                cnt++;
            }
        }
        return cnt;
    }
}
