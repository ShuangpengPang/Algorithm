package com.shuangpeng.competition.第281到290场周赛.第289场周赛;

/**
 * @Description: Problem2243CalculateDigitSumOfAString（计算字符串的数字和）
 * @Date 2022/5/29 6:47 PM
 * @Version 1.0
 */
public class Problem2243CalculateDigitSumOfAString {

    // 比赛时写法
    public String digitSum0(String s, int k) {
        while (s.length() > k) {
            int n = s.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i += k) {
                String str = s.substring(i, Math.min(i + k, n));
                int sum = 0;
                int m = str.length();
                for (int j = 0; j < m; ++j) {
                    sum += str.charAt(j) - '0';
                }
                sb.append(sum);
            }
            s = sb.toString();
        }
        return s;
    }

    public String digitSum1(String s, int k) {
        while (s.length() > k) {
            int n = s.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i += k) {
                int num = 0;
                for (int j = 0; j < k && i + j < n; ++j) {
                    num += s.charAt(i + j) - '0';
                }
                sb.append(num);
            }
            s = sb.toString();
        }
        return s;
    }

    public String digitSum(String s, int k) {
        while (s.length() > k) {
            int n = s.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i += k) {
                int sum = 0;
                for (int j = i; j < i + k && j < n; ++j) {
                    sum += s.charAt(j) - '0';
                }
                sb.append(sum);
            }
            s = sb.toString();
        }
        return s;
    }
}
