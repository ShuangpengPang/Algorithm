package com.shuangpeng;

public class KMP {
    int[] next;
    char[] modelArray;

    public KMP(String model) {
        this.modelArray = model.toCharArray();
        next = new int[model.length() + 1];
    }

    public void buildNext() {
        int length = modelArray.length;
        int j = 0;
        for (int i = 2; i <= length; i++) {
            int lastMatchIndex = i - 1;
            char lastChar = modelArray[lastMatchIndex];
            if (lastChar == modelArray[j]) {
                next[i] = next[i - 1] + 1;
                j++;
            } else {
                while (j > 0 && lastChar != modelArray[j]) {
                    j = next[j];
                }
                if (j > 0) {
                    next[i] = next[j] + 1;
                    j++;
                }
            }
        }
    }
}
