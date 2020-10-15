package com.shuangpeng;

public class KMP {
    int[] next;
    char[] modelArray;
    String string;

    public KMP(String model, String string) {
        if (model != null) {
            this.modelArray = model.toCharArray();
            next = new int[model.length() + 1];
            this.string = string;
        }
    }

    public static void main(String[] args) {
        String string = "GTGTGAGCTGGTGTGTGCFAA";
        String model = "GTGTGCF";
        KMP kmp = new KMP(model, string);
        System.err.println("index is: " + kmp.getIndex());
    }

    public int getIndex() {
        if (modelArray == null || modelArray.length == 0
                || string == null || string.length() == 0) {
            return -1;
        }
        buildNext();
        int length = string.length();
        char[] chars = string.toCharArray();
        int j = 0;
        for (int i = 0; i < length; i++) {
            if (modelArray[j] == chars[i]) {
                j++;
            } else {
                while (j > 0 && modelArray[j] != chars[i]) {
                    j = next[j];
                }
                if (j > 0 || modelArray[j] == chars[i]) {
                    j++;
                }
            }
            if (j == modelArray.length) {
                return i - modelArray.length + 1;
            }
        }
        return -1;
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
                } else if (lastChar == modelArray[j]) {
                    next[i] = next[j] + 1;
                    j++;
                } else {
                    next[i] = 0;
                    j = 0;
                }
            }
        }
    }
}
