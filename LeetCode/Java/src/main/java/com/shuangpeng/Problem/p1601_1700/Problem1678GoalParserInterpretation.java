package com.shuangpeng.Problem.p1601_1700;

/**
 * @author ShuangPengPang
 * @Description: Problem1678GoalParserInterpretation（设计Goal解析器）
 * @Date 2022/11/6 8:57 PM
 * @Version 1.0
 */
public class Problem1678GoalParserInterpretation {

    public String interpret(String command) {
        int n = command.length();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < n) {
            if (command.charAt(i) == 'G') {
                sb.append('G');
                i++;
            } else if (command.charAt(i + 1) == ')') {
                sb.append('o');
                i += 2;
            } else {
                sb.append("al");
                i += 4;
            }
        }
        return sb.toString();
    }
}
