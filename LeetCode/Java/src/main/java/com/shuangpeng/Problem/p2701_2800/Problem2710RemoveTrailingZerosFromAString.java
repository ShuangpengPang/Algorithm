package com.shuangpeng.Problem.p2701_2800;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2710RemoveTrailingZerosFromAString（移除字符串中的尾随零）
 * @date 2023/8/6 2:29 PM
 */
public class Problem2710RemoveTrailingZerosFromAString {

    public String removeTrailingZeros(String num) {
        int i = num.length() - 1;
        while (i >= 0 && num.charAt(i) == '0') {
            i--;
        }
        return i < 0 ? "0" : num.substring(0, i + 1);
    }
}
