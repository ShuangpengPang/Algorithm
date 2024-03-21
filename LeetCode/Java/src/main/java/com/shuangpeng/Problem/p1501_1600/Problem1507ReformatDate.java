package com.shuangpeng.Problem.p1501_1600;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1507ReformatDate（转变日期格式）
 * @date 2024/3/21 4:23 PM
 */
public class Problem1507ReformatDate {

    private static String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private static Map<String, Integer> map = new HashMap<>();
    static {
        for (int i = 0; i < 12; i++) {
            map.put(months[i], i + 1);
        }
    }

    public String reformatDate0(String date) {
        String[] strs = date.split(" ");
        StringBuilder sb = new StringBuilder(10);
        sb.append(strs[2]).append('-');
        int m = map.get(strs[1]);
        if (m < 10) {
            sb.append(0);
        }
        sb.append(m).append('-');
        char c1 = strs[0].charAt(0), c2 = strs[0].charAt(1);
        if (c2 < '0' || c2 > '9') {
            sb.append(0).append(c1);
        } else {
            sb.append(c1).append(c2);
        }
        return sb.toString();
    }

    public String reformatDate(String date) {
        String[] array = date.split(" ");
        int day = Integer.parseInt(array[0].substring(0, array[0].length() - 2));
        return String.format("%s-%02d-%02d", array[2], map.get(array[1]), day);
    }
}
