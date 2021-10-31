package com.shuangpeng.Problem.p0201_0300;

import java.util.ArrayList;
import java.util.List;

public class Problem0273IntegerToEnglishWords {

    public String numberToWords0(int num) {
        if (num == 0) {
            return "Zero";
        }
        String[] ones = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
                "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] tens = {"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        String[] units = {"Thousand", "Million", "Billion"};
        int u = 0;
        List<String> list = new ArrayList<>();
        while (num > 0) {
            StringBuilder sb = new StringBuilder();
            int mod = num % 1000;
            if (mod >= 100) {
                sb.append(ones[mod / 100 - 1] + " Hundred");
                mod %= 100;
                if (mod > 0) {
                    sb.append(" ");
                }
            }
            if (mod >= 20) {
                sb.append(tens[mod / 10 - 2]);
                if (mod % 10 > 0) {
                    sb.append(" " + ones[mod % 10 - 1]);
                }
            } else if (mod > 0) {
                sb.append(ones[mod - 1]);
            }
            if (u > 0 && sb.length() > 0) {
                sb.append(" " + units[u - 1]);
            }
            if (sb.length() > 0) {
                list.add(sb.toString());
            }
            ++u;
            num /= 1000;
        }
        StringBuilder sb = new StringBuilder(list.get(list.size() - 1));
        for (int i = list.size() - 2; i >= 0; --i) {
            sb.append(" " + list.get(i));
        }
        return sb.toString();
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }
        final String[] units = {"", "Thousand", "Million", "Billion"};
        StringBuilder sb = new StringBuilder();
        for (int i = 3, u = 1_000_000_000; u > 0; u /= 1000, --i) {
            int currNum = num / u;
            if (currNum != 0) {
                num -= currNum * u;
                StringBuilder curr = new StringBuilder();
                recurse(curr, currNum);
                curr.append(" ").append(units[i]);
                sb.append(curr.toString());
            }
        }
        return sb.toString().trim();
    }

    private void recurse(StringBuilder sb, int num) {
        if (num == 0) {
            return;
        }
        final String[] withinTwenties = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
                "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        final String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        sb.append(" ");
        if (num < 20) {
            sb.append(withinTwenties[num]);
        } else if (num < 100) {
            sb.append(tens[num / 10]);
            recurse(sb, num % 10);
        } else {
            sb.append(withinTwenties[num / 100]).append(" Hundred");
            recurse(sb, num % 100);
        }
    }
}
