package com.shuangpeng.Problem.p1301_1400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1362ClosestDivisors（最接近的因数）
 * @date 2023/8/9 10:42 AM
 */
public class Problem1362ClosestDivisors {

    public int[] closestDivisors0(int num) {
        int[] arr1 = find(num + 1), arr2 = find(num + 2);
        if (arr1[1] - arr1[0] <= arr2[1] - arr2[0]) {
            return arr1;
        }
        return arr2;
    }

    private int[] find(int num) {
        int[] ans = {1, num};
        for (int i = (int) Math.sqrt(num); i > 1; i--) {
            if (num % i == 0) {
                ans[0] = i;
                ans[1] = num / i;
                break;
            }
        }
        return ans;
    }

    // 链接：https://leetcode.cn/problems/closest-divisors/solutions/550830/javahao-shi-ji-bai-100ping-fang-gen-zui-wef78/
    public int[] closestDivisors(int num) {
        int divisor = num == 1 ? num + 1 : num + 2;
        int i = (int) Math.sqrt(divisor);
        while (divisor % i > 1) {
            i--;
        }
        return new int[]{i, divisor / i};
    }
}
