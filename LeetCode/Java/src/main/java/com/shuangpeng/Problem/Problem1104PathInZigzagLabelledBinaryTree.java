package com.shuangpeng.Problem;

import java.util.ArrayList;
import java.util.List;

public class Problem1104PathInZigzagLabelledBinaryTree {

    public List<Integer> pathInZigZagTree(int label) {
        if (label < 1) {
            return new ArrayList<>();
        }
        List<Integer> answer = new ArrayList<>();
        recurse(label, getLevel(label), answer);
        return answer;
    }

    private void recurse(int n, int level, List<Integer> answer) {
        answer.add(0, n);
        if (n == 1) {
            return;
        }
        recurse(getParent(n, level), level - 1, answer);
    }

    private int getParent(int n, int level) {
        int max = (1 << level) - 1;
        int min = 1 << (level - 1);
        return (min + max - n) >> 1;
    }

    private int getLevel(int n) {
        int level = 0;
        while (n > 0) {
            level++;
            n >>= 1;
        }
        return level;
    }
}
