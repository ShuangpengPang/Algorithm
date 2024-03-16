package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1556ThousandSeparator（千位分隔数）
 * @date 2024/3/16 4:57 PM
 */
public class Problem1556ThousandSeparator {

    public String thousandSeparator0(int n) {
        if (n == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; n > 0; i++, n /= 10) {
            if (i > 0 && i % 3 == 0) {
                sb.append('.');
            }
            sb.append(n % 10);
        }
        return sb.reverse().toString();
    }

    public String thousandSeparator(int n) {
        StringBuilder sb = new StringBuilder();
        int cnt = 0;
        do {
            sb.append(n % 10);
            cnt++;;
            n /= 10;
            if (n != 0 && cnt % 3 == 0) {
                sb.append('.');
            }
        } while (n != 0);
        return sb.reverse().toString();
    }
}
