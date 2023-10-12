package com.shuangpeng.Problem.p1801_1900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1860IncrementalMemoryLeak（增长的内存泄露）
 * @date 2023/10/11 7:20 PM
 */
public class Problem1860IncrementalMemoryLeak {

    public int[] memLeak0(int memory1, int memory2) {
        int time = 1;
        while (time <= memory1 || time <= memory2) {
            if (memory1 >= memory2) {
                memory1 -= time;
            } else {
                memory2 -= time;
            }
            time++;
        }
        return new int[]{time, memory1, memory2};
    }

    public int[] memLeak(int memory1, int memory2) {
        boolean swap = false;
        if (memory1 < memory2) {
            int tmp = memory1;
            memory1 = memory2;
            memory2 = tmp;
            swap = true;
        }
        long d = memory1 - memory2, start = (int) ((-1 + Math.sqrt(1 + (d << 3))) / 2), s = (start + 1) * start >> 1;
        memory1 -= s;
        if (d == s) {
            swap = false;
        }
        int[] arr1 = getResult(memory1, start + 1), arr2 = getResult(memory2, start + 2);
        int time = Math.min(arr1[0], arr2[0]);
        if (!swap) {
            return new int[]{time, arr1[1], arr2[1]};
        }
        return new int[]{time, arr2[1], arr1[1]};
    }

    private int[] getResult(long num, long start) {
        int x = (int) ((-2 + Math.sqrt(4 - 4 * ((2 - start) * start - 4 * num ))) / 2);
        x = (x - start & 1) == 0 ? x : x - 1;
        return new int[]{x + 2, (int) (num - (x + start) * ((x - start) / 2 + 1) / 2)};
    }
}

class Problem1860IncrementalMemoryLeak0 {

    public int[] memLeak(int memory1, int memory2) {

        boolean swapped = false;
        if(memory2 > memory1){
            swapped = true;
            int temp = memory1;
            memory1 = memory2;
            memory2 = temp;
        }

        int start = (int)((Math.sqrt(8L * (memory1 - memory2) + 1) -1)/2L);

        memory1 -= (int)(start * (start + 1L) / 2L);
        if(memory1 == memory2)
            swapped = false;

        int steps = (int) ((- (start + 1L) + (long) Math.sqrt((start + 1L) * (start + 1L) + 4L * memory2))/2L);

        int end1 = start - 1 + 2 * steps;
        int end2 = start + 2 * steps;

        memory1 -= (int)(steps * (start + 1L + end1)/2);
        memory2 -= (int)(steps * (start + 2L + end2)/2);

        if (memory1 >= end2 + 1){
            end1 = end2 + 1;
            memory1 -= end1;
        }
        int time = end1 > end2? end1:end2;

        return swapped? new int[]{ time + 1, memory2, memory1}: new int[]{ time + 1, memory1, memory2};
    }
}
