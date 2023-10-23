package com.shuangpeng.Problem.p2601_2700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2678NumberOfSeniorCitizens（老人的数目）
 * @date 2023/8/17 4:38 PM
 */
public class Problem2678NumberOfSeniorCitizens {

    public int countSeniors0(String[] details) {
        int cnt = 0;
        for (String d : details) {
            if (Integer.parseInt(d.substring(11, 13)) > 60) {
                cnt++;
            }
        }
        return cnt;
    }

    public int countSeniors(String[] details) {
        int ans = 0;
        for (String detail : details) {
            if ((detail.charAt(11) - '0') * 10 + detail.charAt(12) - '0' > 60) {
                ans++;
            }
        }
        return ans;
    }
}
