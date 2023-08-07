package com.shuangpeng.Problem.p1301_1400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1352ProductOfTheLastKNumbers（最后K个数的乘积）
 * @date 2023/8/7 11:36 AM
 */
public class Problem1352ProductOfTheLastKNumbers {

    static class ProductOfNumbers {

        static int N = 4 * (int) 1e4 + 1;
        static int[] arr = new int[N];
        int pos = 0, zero = -1;

        public ProductOfNumbers() {
            arr[0] = 1;
            pos = 0;
            zero = -1;
        }

        public void add(int num) {
            if (num == 0) {
                arr[pos + 1] = 1;
                zero = pos + 1;
            } else {
                arr[pos + 1] = arr[pos] * num;
            }
            pos++;
        }

        public int getProduct(int k) {
            return pos - k < zero ? 0 : arr[pos] / arr[Math.max(pos - k, 0)];
        }
    }

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */
}
