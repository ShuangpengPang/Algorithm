package com.shuangpeng.competition.第251到260场周赛.第251场周赛;

/**
 * @author shuangpeng
 * @description: 字符串转化后的各位数字之和
 * @date 2022/12/15 10:32 AM
 **/
public class Problem1945SumOfDigitsOfStringAfterConvert {

    public int getLucky0(String s, int k) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append((int) (c - 'a' + 1));
        }
        String string = sb.toString();
        int sum = 0;
        for (char c : string.toCharArray()) {
            sum += (c - '0');
        }
        for (int i = 1; i < k; i++) {
            int temp = sum;
            sum = 0;
            while (temp > 0) {
                sum += temp % 10;
                temp /= 10;
            }
        }
        return sum;
    }

    public int getLucky(String s, int k) {
        int n = s.length(), sum = 0;
        for (int i = 0; i < n; i++) {
            sum += getDigitSum(s.charAt(i) - 'a' + 1);
        }
        for (int i = 1; i < k && sum >= 10; i++) {
            sum = getDigitSum(sum);
        }
        return sum;
    }

    private int getDigitSum(int i) {
        int ans = 0;
        while (i > 0) {
            ans += i % 10;
            i /= 10;
        }
        return ans;
    }
}
