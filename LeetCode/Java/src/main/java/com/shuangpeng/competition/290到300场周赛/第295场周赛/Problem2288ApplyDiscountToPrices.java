package com.shuangpeng.competition.第295场周赛;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Problem2288ApplyDiscountToPrices（价格减免）
 * @Date 2022/6/15 7:35 PM
 * @Version 1.0
 */
public class Problem2288ApplyDiscountToPrices {

    public String discountPrices0(String sentence, int discount) {
        List<String> list = new ArrayList<>();
        int n = sentence.length();
        for (int i = 0, j = 0; i < n; i = j + 1) {
            boolean isPrice = sentence.charAt(i) == '$';
            double price = 0;
            StringBuilder sb = new StringBuilder();
            sb.append(sentence.charAt(i));
            for (j = i + 1; j < n && sentence.charAt(j) != ' '; ++j) {
                char c = sentence.charAt(j);
                if (!Character.isDigit(c)) {
                    isPrice = false;
                } else {
                    price = price * 10 + c - '0';
                }
                sb.append(c);
            }
            if (isPrice && j - i > 1) {
                price = price * (100 - discount) / 100;
                list.add("$" + String.format("%.2f", price));
            } else {
                list.add(sb.toString());
            }
        }
        int m = list.size();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; ++i) {
            if (i > 0) {
                sb.append(' ');
            }
            sb.append(list.get(i));
        }
        return sb.toString();
    }

    public String discountPrices(String sentence, int discount) {
        int n = sentence.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0, j = 0; i < n; i = j + 1) {
            if (sentence.charAt(i) == '$') {
                sb.append('$');
                boolean isNum = true;
                long num = 0;
                for (j = i + 1; j < n && sentence.charAt(j) != ' '; ++j) {
                    int k = sentence.charAt(j) - '0';
                    if (k >= 0 && k <= 9) {
                        num = num * 10 + k;
                    } else {
                        isNum = false;
                        break;
                    }
                }
                if (isNum && j - i > 1) {
                    num *= 100 - discount;
                    sb.append(num / 100);
                    long m = num % 100;
                    if (m < 10) {
                        sb.append(".0" + m);
                    } else {
                        sb.append("." + m);
                    }
                } else {
                    for (j = i + 1; j < n && sentence.charAt(j) != ' '; ++j) {
                        sb.append(sentence.charAt(j));
                    }
                }
            } else {
                for (j = i; j < n && sentence.charAt(j) != ' '; ++j) {
                    sb.append(sentence.charAt(j));
                }
            }
            sb.append(' ');
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}