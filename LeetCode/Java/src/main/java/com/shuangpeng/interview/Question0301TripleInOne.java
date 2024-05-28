package com.shuangpeng.interview;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Question0301TripleInOne（面试题 03.01 三合一）
 * @date 2024/5/29 12:42 AM
 */
public class Question0301TripleInOne {

    class TripleInOne {

        private int[] arr, cnt;
        private int stackSize;

        public TripleInOne(int stackSize) {
            this.stackSize = stackSize;
            arr = new int[stackSize * 3];
            cnt = new int[3];
        }

        public void push(int stackNum, int value) {
            if (cnt[stackNum] == stackSize) {
                return;
            }
            arr[stackNum * stackSize + (cnt[stackNum]++)] = value;
        }

        public int pop(int stackNum) {
            if (cnt[stackNum] == 0) {
                return -1;
            }
            return arr[stackNum * stackSize + (--cnt[stackNum])];
        }

        public int peek(int stackNum) {
            if (cnt[stackNum] == 0) {
                return -1;
            }
            return arr[stackNum * stackSize + cnt[stackNum] - 1];
        }

        public boolean isEmpty(int stackNum) {
            return cnt[stackNum] == 0;
        }
    }

/**
 * Your TripleInOne object will be instantiated and called as such:
 * TripleInOne obj = new TripleInOne(stackSize);
 * obj.push(stackNum,value);
 * int param_2 = obj.pop(stackNum);
 * int param_3 = obj.peek(stackNum);
 * boolean param_4 = obj.isEmpty(stackNum);
 */
}
