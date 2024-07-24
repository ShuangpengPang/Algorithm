package com.shuangpeng.lcr.p101_200;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR148ValidateBookSequences（LCR 148. 验证图书取出顺序）
 * @date 2024/7/24 2:28 PM
 */
public class LCR148ValidateBookSequences {

    public boolean validateBookSequences(int[] putIn, int[] takeOut) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = putIn.length;
        for (int i = 0, j = 0; j < n; j++) {
            while (stack.isEmpty() || stack.peekLast() != takeOut[j] && i < n) {
                stack.offerLast(putIn[i++]);
            }
            if (stack.isEmpty() || stack.peekLast() != takeOut[j]) {
                return false;
            }
            stack.pollLast();
        }
        return true;
    }
}
