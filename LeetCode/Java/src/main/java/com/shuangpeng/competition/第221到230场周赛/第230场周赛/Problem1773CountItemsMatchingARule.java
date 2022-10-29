package com.shuangpeng.competition.第221到230场周赛.第230场周赛;

import java.util.List;

/**
 * @Description: 统计匹配检索规则的物品数量
 * @Date 2022/10/29 10:23 AM
 **/
public class Problem1773CountItemsMatchingARule {

    public int countMatches0(List<List<String>> items, String ruleKey, String ruleValue) {
        int answer = 0;
        int size = items.size();
        for (int i = 0; i < size; i++) {
            List<String> item = items.get(i);
            if (ruleKey.equals("type") && item.get(0).equals(ruleValue)) {
                answer++;
            } else if (ruleKey.equals("color") && item.get(1).equals(ruleValue)) {
                answer++;
            } else if (ruleKey.equals("name") && item.get(2).equals(ruleValue)) {
                answer++;
            }
        }
        return answer;
    }

    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int ans = 0;
        int idx = ruleKey.equals("type") ? 0 : (ruleKey.equals("color") ? 1 : 2);
        for (List<String> item : items) {
            if (item.get(idx).equals(ruleValue)) {
                ans++;
            }
        }
        return ans;
    }
}
