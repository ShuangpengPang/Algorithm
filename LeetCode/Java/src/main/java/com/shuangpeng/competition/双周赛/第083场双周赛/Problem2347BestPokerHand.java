package com.shuangpeng.competition.双周赛.第083场双周赛;

/**
 * @Description: Problem2347BestPokerHand（最好的扑克手牌）
 * @Date 2022/8/16 7:26 PM
 * @Version 1.0
 */
public class Problem2347BestPokerHand {

    // 比赛时写法
    public String bestHand0(int[] ranks, char[] suits) {
        boolean one = true;;
        for (int i = 1; i < 5; i++) {
            if (suits[i] != suits[i - 1]) {
                one = false;
                break;
            }
        }
        if (one) {
            return "Flush";
        }
        int[] cnt = new int[14];
        boolean three = false, two = false;
        for (int r : ranks) {
            cnt[r]++;
            if (cnt[r] == 2) {
                two = true;
            } else if (cnt[r] == 3) {
                three = true;
            }
        }
        if (three) {
            return "Three of a Kind";
        }
        if (two) {
            return "Pair";
        }
        return "High Card";
    }

    public String bestHand1(int[] ranks, char[] suits) {
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

    public String bestHand(int[] ranks, char[] suits) {
        boolean flag = true;
        for (int i = 0; i < suits.length; i++) {
            if (suits[i] != suits[0]) {
                flag = false;
                break;
            }
        }
        if (flag) {
            return "Flush";
        }
        int[] cnt = new int[14];
        boolean pair = false;
        for (int r : ranks) {
            cnt[r]++;
            if (cnt[r] >= 3) {
                return "Three of a Kind";
            } else if (cnt[r] >= 2) {
                pair = true;
            }
        }
        return pair ? "Pair" : "High Card";
    }
}
