package com.shuangpeng.Problem.p1301_1400;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1333FilterRestaurantsByVeganFriendlyPriceAndDistance（餐厅过滤器）
 * @date 2023/7/26 4:04 PM
 */
public class Problem1333FilterRestaurantsByVeganFriendlyPriceAndDistance {

    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        List<int[]> list = new ArrayList<>();
        for (int[] r : restaurants) {
            if ((veganFriendly == 0 || r[2] == 1) && r[3] <= maxPrice && r[4] <= maxDistance) {
                list.add(r);
            }
        }
        list.sort((a, b) -> a[1] != b[1] ? b[1] - a[1] : b[0] - a[0]);
        List<Integer> ans = new ArrayList<>(list.size());
        for (int[] r : list) {
            ans.add(r[0]);
        }
        return ans;
    }
}
