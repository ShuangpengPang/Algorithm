package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3280ConvertDateToBinary（将日期转换为二进制表示）
 * @date 2024/11/14 6:43 PM
 */
public class Problem3280ConvertDateToBinary {

    public String convertDateToBinary(String date) {
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8));
        return toBinaryString(year) + "-" + toBinaryString(month) + "-" + toBinaryString(day);
    }

    private String toBinaryString(int i) {
        if (i == 0) {
            return "0";
        }
        char[] cs = new char[Integer.SIZE];
        int index = cs.length;
        while (i > 0) {
            cs[--index] = (char) ('0' + (i & 1));
            i >>= 1;
        }
        return new String(cs, index, cs.length - index);
    }
}
