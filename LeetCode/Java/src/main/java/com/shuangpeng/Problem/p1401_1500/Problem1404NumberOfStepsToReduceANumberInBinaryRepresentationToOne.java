package com.shuangpeng.Problem.p1401_1500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1404NumberOfStepsToReduceANumberInBinaryRepresentationToOne（将二进制表示减到1的步骤数）
 * @date 2023/8/22 3:14 PM
 */
public class Problem1404NumberOfStepsToReduceANumberInBinaryRepresentationToOne {

    public int numSteps(String s) {
        int n = s.length(), ans = 0, carry = 0;
        for (int i = n - 1; i > 0; i--) {
            carry += s.charAt(i) - '0';
            ans += 1 + (carry & 1);
            carry = (carry + (carry & 1)) >> 1;
        }
        return ans + (carry & 1);
    }
}
