package com.shuangpeng.Problem.p0401_0500;

import java.util.HashMap;
import java.util.Map;

public class Problem0464CanIWin {

    public boolean canIWin0(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }
        int sum = (1 + maxChoosableInteger) * maxChoosableInteger >> 1;
        if (sum < desiredTotal) {
            return false;
        }
        if (sum == desiredTotal) {
            return (maxChoosableInteger & 1) == 1;
        }
        int remain = desiredTotal % (maxChoosableInteger + 1);
        if (remain == 0) {
            return false;
        }
        int k = desiredTotal / (maxChoosableInteger + 1);
        return maxChoosableInteger == ((remain << 1) - 1);
    }

    public boolean canIWin1(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }
        int sum = maxChoosableInteger * (maxChoosableInteger + 1) >> 1;
        if (desiredTotal > sum) {
            return false;
        }
        if (desiredTotal == sum) {
            return (maxChoosableInteger & 1) == 1;
        }
        if ((maxChoosableInteger & 1) == 0 && (desiredTotal % (maxChoosableInteger + 1) == 0)) {
            return false;
        }
        return check(maxChoosableInteger, 0, desiredTotal, new HashMap<>());
    }

    private boolean check(int maxChoosableInteger, int state, int desiredTotal, Map<Integer, Boolean> memo) {
        if (desiredTotal <= 0) {
            return false;
        }
        if (memo.containsKey(state)) {
            return memo.get(state);
        }
        for (int i = 0; i < maxChoosableInteger; i++) {
            int flag = 1 << i;
            if ((state & flag) == 0 && !check(maxChoosableInteger, state | flag, desiredTotal - i - 1, memo)) {
                memo.put(state, true);
                return true;
            }
        }
        memo.put(state, false);
        return false;
    }

    public boolean canIWin12(int maxChoosableInteger, int desiredTotal) {
        if (maxChoosableInteger >= desiredTotal) {
            return true;
        }
        int sum = maxChoosableInteger * (maxChoosableInteger + 1) >> 1;
        if (desiredTotal > sum) {
            return false;
        }
        if (desiredTotal == sum) {
            return (maxChoosableInteger & 1) == 1;
        }
        if ((maxChoosableInteger & 1) == 0 && (desiredTotal % (maxChoosableInteger + 1) == 0)) {
            return false;
        }
        return dfs(0, maxChoosableInteger, desiredTotal, new Boolean[1 << maxChoosableInteger]);
    }

    private boolean dfs(int state, int maxChoosableInteger, int desiredTotal, Boolean[] memo) {
        if (desiredTotal <= 0) {
            return false;
        }
        if (memo[state] != null) {
            return memo[state];
        }
        for (int i = 0; i < maxChoosableInteger; i++) {
            int flag = 1 << i;
            if ((state & flag) == 0 && !dfs(state | flag, maxChoosableInteger, desiredTotal - i - 1, memo)) {
                memo[state] = true;
                return true;
            }
        }
        memo[state] = false;
        return false;
    }

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        int canReachTotal = (1 + maxChoosableInteger) * maxChoosableInteger / 2;
        if (canReachTotal < desiredTotal) { // 达不到
            return false;
        } else if (canReachTotal == desiredTotal) { // 刚好达到，maxChoosableInteger奇数赢
            return (maxChoosableInteger & 1) == 1;
        }

        return canWin(0, desiredTotal, maxChoosableInteger, new int[1 << maxChoosableInteger]);
    }

    private boolean canWin(int bits, int distance, int maxChoosableInteger, int[] dp) {
        if (dp[bits] != 0) { // 已经计算过。0：未计算，1：true，2：false
            return dp[bits] == 1;
        }

        boolean result = false;
        for (int cur = maxChoosableInteger; cur > 0; cur--) {
            int curBit = 1 << (cur - 1);
            if ((bits & curBit) == 0) { // 当前值没有被使用
                if (cur >= distance // 可以一步成功
                        || !canWin(bits | curBit, distance - cur, maxChoosableInteger, dp)) { // 如果能找到一步让对方无法赢
                    result = true;
                    break;
                }
            }
        }

        dp[bits] = result ? 1 : 2;
        return result;
    }

    // 4 6
    // 6 % 5 1
    // (1, 10), (2, 9), (3, 8), (4, 7), (5, 6)

//    public static void main(String[] args) {
//        Problem0464CanIWin a = new Problem0464CanIWin();
//        a.canIWin(7, 16);
//    }
}
