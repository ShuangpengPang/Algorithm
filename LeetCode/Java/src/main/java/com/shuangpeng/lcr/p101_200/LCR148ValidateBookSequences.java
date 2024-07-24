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

    public boolean validateBookSequences0(int[] putIn, int[] takeOut) {
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

    public boolean validateBookSequences(int[] pushed, int[] popped) {
        // 空间复杂度 O(1)，但是会修改入参
        int len = pushed.length;
        int index = 0;

        // 用 pushed 当作栈
        for (int i = 0, j = 0; i < len; ++i) {
            pushed[index++] = pushed[i];
            while (index > 0 && pushed[index - 1] == popped[j]) {
                j++;
                index--;
            }
        }

        return index == 0;
    }
}
