package com.shuangpeng.Problem;

import java.util.Deque;
import java.util.LinkedList;

public class Problem0331VerifyPreorderSerializationOfABinaryTree {

    public boolean isValidSerialization0(String preorder) {
        if (preorder == null || preorder.length() == 0) {
            return true;
        }
        String[] strings = preorder.split(",");
        int[] result = preorder(strings, 0);
        return result[0] == 1 && result[1] == strings.length;
    }

    private int[] preorder(String[] strings, int index) {
        if (index >= strings.length) {
            return new int[]{0, index};
        }
        if (strings[index].equals("#")) {
            return new int[]{1, index + 1};
        }
        int[] left = preorder(strings, index + 1);
        if (left[0] == 0) {
            return new int[]{0, index + 1};
        }
        return preorder(strings, left[1]);
    }

//    public boolean isValidSerialization(String preorder) {
//        if (preorder == null || preorder.length() == 0 || preorder.equals("#")) {
//            return true;
//        }
//        String[] strings = preorder.split(",");
//        int count = 0;
//        int i = 0;
//        while (i < strings.length) {
//            while (!strings[i].equals("#")) {
//                count++;
//                i++;
//            }
//
//        }
//    }
}
