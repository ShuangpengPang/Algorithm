package com.shuangpeng;

import java.util.ArrayList;
import java.util.List;

public class Permute {
    List<List<Integer>> result;

    public void permute(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        int nums = 1;
        for (int i = 0; i < array.length; i++) {
            nums = nums * (array.length - i);
        }
        result = new ArrayList<>(nums);

//        List<List<Integer>> orderList = new ArrayList<>();
//        List<Integer> order = new ArrayList<>();
//
//        while (order.size() != array.length) {
//            for (int i = 0; i < array.length; i++) {
//                int item = array[i];
//                if (!order.contains(item)) {
//                    order.add(item);
//                }
//            }
//        }

        backtrack(array, new ArrayList<>());
    }

//    public static void main(String[] args) {
//        Permute permute = new Permute();
//        int[] array = {1, 2};
//        permute.permute(array);
//        System.err.print('[');
//        for (int i = 0; i < permute.result.size(); i++) {
//            List<Integer> set = permute.result.get(i);
//            if (i != 0) {
//                System.err.print(", ");
//            }
//            System.err.print('[');
//            for (int j = 0; j < set.size(); j++) {
//                if (j != 0) {
//                    System.err.print(", ");
//                }
//                System.err.print(set.get(j));
//            }
//            System.err.print(']');
//        }
//        System.err.print(']');
//        System.err.println();
//    }

    public void backtrack(int[] array, List<Integer> list) {
        if (list.size() == array.length) {
            List<Integer> copy = new ArrayList<>(array.length);
            for (int i = 0; i < array.length; i++) {
                copy.add(list.get(i));
            }
            result.add(copy);
            return;
        }
        for (int i = 0; i < array.length; i++) {
            int item = array[i];
            if (!list.contains(item)) {
                list.add(item);
                backtrack(array, list);
                list.remove((Object) item);
            }
        }
    }
}
