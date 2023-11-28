package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2526FindConsecutiveIntegersFromADataStream（找到数据流中的连续整数）
 * @date 2023/11/28 11:20 PM
 */
public class Problem2526FindConsecutiveIntegersFromADataStream {
}

class DataStream {

    int value, k, count;

    public DataStream(int value, int k) {
        this.value = value;
        this.k = k;
        count = 0;
    }

    public boolean consec(int num) {
        if (num == value) {
            count++;
        } else {
            count = 0;
        }
        return count >= k;
    }
}

/**
 * Your DataStream object will be instantiated and called as such:
 * DataStream obj = new DataStream(value, k);
 * boolean param_1 = obj.consec(num);
 */
