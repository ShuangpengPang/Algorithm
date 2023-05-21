package com.shuangpeng.lcp;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCP33StoreWater（蓄水）
 * @date 2023/5/21 7:04 PM
 */
public class LCP33StoreWater {

    public int storeWater(int[] bucket, int[] vat) {
        int n = bucket.length, max = 0;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, bucket[i] == 0 ? vat[i] : (vat[i] + bucket[i] - 1) / bucket[i]);
        }
        if (max == 0) {
            return 0;
        }
        int ans = Integer.MAX_VALUE;
        for (int m = max; m >= 1; m--) {
            int cnt = m;
            for (int i = 0; i < n && cnt < ans; i++) {
                cnt += Math.max(0, (vat[i] + m - 1) / m - bucket[i]);
            }
            ans = Math.min(ans, cnt);
        }
        return ans;
    }
}
