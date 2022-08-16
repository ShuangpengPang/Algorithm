package com.shuangpeng.competition.第083场双周赛;

/**
 * @Description: Problem2347BestPokerHand（最好的扑克手牌）
 * @Date 2022/8/16 7:26 PM
 * @Version 1.0
 */
public class Problem2347BestPokerHand {

    public String bestHand(int[] ranks, char[] suits) {
        int[] rankCount = new int[14], suitCount = new int[4];
        int n = ranks.length;
        boolean hasFive = false, hasThree = false, hasPair = false;
        for (int i = 0; i < n; i++) {
            int r = ranks[i];
            rankCount[r]++;
            if (rankCount[r] == 3) {
                hasThree = true;
            } else if (rankCount[r] == 2) {
                hasPair = true;
            }
            int s = suits[i] - 'a';
            suitCount[s]++;
            if (suitCount[s] == 5) {
                hasFive = true;
            }
        }
        if (hasFive) {
            return "Flush";
        }
        if (hasThree) {
            return "Three of a Kind";
        }
        if (hasPair) {
            return "Pair";
        }
        return "High Card";
    }
}
