package com.shuangpeng.Problem;

import java.util.HashMap;
import java.util.Map;

public class Problem0964LeastOperatorsToExpressNumber {

    public int leastOpsExpressTarget0(int x, int target) {
        return (int) (leastCount(x, target, 0, new HashMap<>()) - 1);
    }

    private long leastCount(long x, long target, int e, Map<Long, Long> memo) {
        long power = (long) Math.pow(x, e);
        long nextPower = power * x;
        if (target == nextPower) {
            return e + 1;
        }
        long mod = target % nextPower;
        long count = mod / power * (e == 0 ? 2 : e);
        long mod2 = nextPower - mod;
        long count2 = mod2 / power * (e == 0 ? 2 : e);
        if (target < nextPower) {
            return Math.min(count, count2 + e + 1);
        }
        long key = target * 33 + e;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        long ans =  Math.min(count + leastCount(x, target - mod, e + 1, memo), count2 + leastCount(x, target + mod2, e + 1, memo));
        memo.put(key, ans);
        return ans;
    }

    public int leastOpsExpressTarget(int x, int target) {
        return dp(x, target, 0, new HashMap<>()) - 1;
    }

    public int dp(int x, int target, int i, Map<Long, Integer> memo) {
        if (target == 0) {
            return 0;
        }
        if (target < x) {
            return Math.min(target * cost(i), i + 1 + (x - target) * cost(i));
        }
        long key = target * 33 + i;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        int quotient = target / x;
        int mod = target % x;
        if (mod == 0) {
            int ans = dp(x, quotient, i + 1, memo);
            memo.put(key, ans);
            return ans;
        }
        int ans = Math.min(mod * cost(i) + dp(x, quotient, i + 1, memo), (x - mod) * cost(i) + dp(x, quotient + 1, i + 1, memo));
        memo.put(key, ans);
        return ans;
    }

    public int cost(int x) {
        return x == 0 ? 2 : x;
    }

//    public static void main(String[] args) {
//        Problem0964LeastOperatorsToExpressNumber a = new Problem0964LeastOperatorsToExpressNumber();
//        a.leastOpsExpressTarget(3, 19);
//    }
}
