package com.shuangpeng.Problem.p1601_1700;

/**
 * @Description: Problem1694ReformatPhoneNumber（重新格式化电话号码）
 * @Date 2022/10/4 2:28 PM
 * @Version 1.0
 */
public class Problem1694ReformatPhoneNumber {

    public String reformatNumber(String number) {
        int n = number.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char c = number.charAt(i);
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }
        int m = sb.length();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < m; i += 3) {
            if (i > 0) {
                ans.append('-');
            }
            if (i + 4 == m) {
                ans.append(sb.substring(i, i + 2));
                ans.append('-');
                ans.append(sb.substring(i + 2, i + 4));
                break;
            }
            ans.append(sb.substring(i, Math.min(m, i + 3)));
        }
        return ans.toString();
    }
}
