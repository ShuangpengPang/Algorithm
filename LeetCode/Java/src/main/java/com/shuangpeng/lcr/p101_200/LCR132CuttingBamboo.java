package com.shuangpeng.lcr.p101_200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR132CuttingBamboo（LCR 132. 砍竹子 II）
 * @date 2024/7/25 7:04 PM
 */
public class LCR132CuttingBamboo {

    public int cuttingBamboo(int bamboo_len) {
        if (bamboo_len <= 3) {
            return bamboo_len - 1;
        }
        int p = (bamboo_len - 2) / 3;
        long ans = 1, N = (long) 1e9 + 7, x = 3;
        while (p != 0) {
            if ((p & 1) == 1) {
                ans = (ans * x) % N;
            }
            x = (x * x) % N;
            p >>= 1;
        }
        return (int) (ans * ((bamboo_len - 2) % 3 + 2) % N);
    }
}
