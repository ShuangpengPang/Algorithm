package com.shuangpeng.competition.第230场周赛;

import java.util.List;

public class Problem1773 {

    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
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
}
