package com.shuangpeng.Problem.p1301_1400;

/**
 * @Description: Problem1374GenerateAStringWithCharactersThatHaveOddCounts（生成每种字符都是奇数个的字符串）
 * @Date 2022/8/1 11:12 AM
 * @Version 1.0
 */
public class Problem1374GenerateAStringWithCharactersThatHaveOddCounts {

    public String generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        if ((n & 1) == 0) {
            sb.append('b');
            n--;
        }
        for (int i = 0; i < n; i++) {
            sb.append('a');
        }
        return sb.toString();
    }
}
