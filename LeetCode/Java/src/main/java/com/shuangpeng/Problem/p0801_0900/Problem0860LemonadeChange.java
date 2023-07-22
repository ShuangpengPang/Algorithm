package com.shuangpeng.Problem.p0801_0900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0860LemonadeChange（柠檬水找零）
 * @date 2023/7/22 2:52 PM
 */
public class Problem0860LemonadeChange {

    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0, fifteen = 0;
        for (int b : bills) {
            if (b == 5) {
                five++;
            } else if (b == 10) {
                if (five == 0) {
                    return false;
                }
                five--;
                ten++;
            } else if (b == 15) {
                if (ten > 0) {
                    ten--;
                    fifteen++;
                } else if (five >= 2) {
                    five -= 2;
                    fifteen++;
                } else {
                    return false;
                }
            } else if (fifteen > 0) {
                fifteen--;
            } else if (ten > 0 && five > 0) {
                ten--;
                five--;
            } else if (five >= 3) {
                five -= 3;
            } else {
                return false;
            }
        }
        return true;
    }
}
