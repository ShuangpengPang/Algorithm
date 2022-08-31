package com.shuangpeng.Problem.p0901_1000;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Problem0946ValidateStackSequences（验证栈序列）
 * @Date 2022/8/31 10:08 AM
 * @Version 1.0
 */
public class Problem0946ValidateStackSequences {

    public boolean validateStackSequences0(int[] pushed, int[] popped) {
        List<Integer> stack = new ArrayList<>();
        int j = 0;
        for (int i = 0; i < pushed.length; i++) {
            while (!stack.isEmpty() && stack.get(stack.size() - 1) == popped[j]) {
                stack.remove(stack.size() - 1);
                j++;
            }
            stack.add(pushed[i]);
        }
        while (!stack.isEmpty() && stack.get(stack.size() - 1) == popped[j]) {
            stack.remove(stack.size() - 1);
            j++;
        }
        return stack.isEmpty();
    }

    public boolean validateStackSequences1(int[] pushed, int[] popped) {
        List<Integer> stack = new ArrayList<>();
        for (int i = 0, j = 0; i < pushed.length; i++) {
            stack.add(pushed[i]);
            while (!stack.isEmpty() && stack.get(stack.size() - 1) == popped[j]) {
                stack.remove(stack.size() - 1);
                j++;
            }
        }
        return stack.isEmpty();
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length, top = 0;
        int[] stack = new int[n];
        for (int i = 0, j = 0; i < n; i++) {
            stack[top++] = pushed[i];
            while (top > 0 && stack[top - 1] == popped[j]) {
                top--;
                j++;
            }
        }
        return top == 0;
    }
}

