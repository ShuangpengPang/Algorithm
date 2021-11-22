package com.shuangpeng.Problem.p0401_0500;

public class Problem0476NumberComplement {

    public int findComplement(int num) {
        if (num == 0) {
            return 1;
        }
        int i = 31;
        while (i >= 0 && ((1 << i) & num) == 0) {
            --i;
        }
        return (1 << (i + 1)) - num - 1;
    }
}
