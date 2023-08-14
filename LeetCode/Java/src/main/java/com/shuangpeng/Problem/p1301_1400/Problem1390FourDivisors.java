package com.shuangpeng.Problem.p1301_1400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1390FourDivisors（四因数）
 * @date 2023/8/14 12:14 PM
 */
public class Problem1390FourDivisors {

    static int N = (int) 1e5;
    static boolean[] prime = new boolean[N + 1];
    static int[] sum = new int[N + 1];
    static {
        Arrays.fill(prime, true);
        prime[0] = prime[1] = false;
        // 埃氏筛
//        for (int i = 2; i <= N; i++) {
//            if (prime[i]) {
//                for (int j = i << 1; j <= N; j += i) {
//                    prime[j] = false;
//                }
//            }
//        }
        // 欧拉筛
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (prime[i]) {
                list.add(i);
            }
            for (int j : list) {
                if (i * j > N) {
                    break;
                }
                prime[i * j] = false;
                if (i % j == 0) {
                    break;
                }
            }
        }
        for (int i = 2; i * i < N; i++) {
            if (prime[i]) {
                long cube = i * i * i;
                if (cube <= N) {
                    sum[(int) cube] = (int) (i + i * i + cube + 1);
                }
                for (int j = i + 1, p = i * j; p <= N; j++, p = i * j) {
                    if (prime[j]) {
                        sum[p] = i + j + p + 1;
                    }
                }
            }
        }
    }

    public int sumFourDivisors(int[] nums) {
        int ans = 0;
        for (int num : nums) {
            ans += sum[num];
        }
        return ans;
    }
}
