package com.shuangpeng.competition.第261场周赛;

public class Problem2029 {

    public boolean stoneGameIX0(int[] stones) {
        if (stones.length == 1) {
            return false;
        }
        int a = 0, b = 0, c = 0;
        for (int s : stones) {
            int r = s % 3;
            if (r == 0) {
                ++a;
            } else if (r == 1) {
                ++b;
            } else {
                ++c;
            }
        }
        return (b >= c + 3 && a % 2 == 1) || (b > 0 && b <= c && a % 2 == 0)
                || (c > 0 && b >= c && a % 2 == 0) || (c >= b + 3 && a % 2 == 1);
    }

    public boolean stoneGameIX(int[] stones) {
        int[] r = new int[3];
        for (int s : stones) {
            ++r[s % 3];
        }
        return dfs(true, 0, r[0], r[1], r[2]);
    }

    /**
     * @Description:
     * @Param isAlice : 当前玩家
     * @Param sum : 当前移除的石子总和（对3取模的结果）
     * @Param r0 : 对3取模为0的个数
     * @Param r1 : 对3取模为1的个数
     * @Param r2 : 对3取模为2的个数
     * @return : boolean。如果返回true表示Alice赢，否则Alice失败
     * @Date 2021/10/15 10:17 上午
     **/
    private boolean dfs(boolean isAlice, int sum, int r0, int r1, int r2) {
        if (r0 == 0 && r1 == 0 && r2 == 0) {
            return false;
        }
        boolean result = !isAlice;
        if (sum == 0) {
            if ((r1 > 0 && dfs(!isAlice, 1, r0, r1 - 1, r2) == isAlice)
                || (r2 > 0 && dfs(!isAlice, 2, r0, r1, r2 - 1) == isAlice)) {
                return isAlice;
            }
        } else {
            if (r0 > 0) {
                return dfs(!isAlice, sum, r0 - 1, r1, r2);
            }
            if (sum == 1 && r1 > 0 && dfs(!isAlice, 2, r0, r1 - 1, r2) == isAlice) {
                return isAlice;
            } else if (sum == 2 && r2 > 0 && dfs(!isAlice, 1, r0, r1, r2 - 1) == isAlice) {
                return isAlice;
            }
        }
        return result;
    }

    // 11212121 0 1
    // 1121212  2
    // 2 212121 1
    // 2 21212 0 2

    // 1 2 2 2 2 2 0 0 0

    // 1 0,

//    public static void main(String[] args) {
//        Problem2029 a = new Problem2029();
//        a.stoneGameIX(new int[]{2, 1});
//    }


    public boolean stoneGameIX1(int[] stones) {
        int[] count = new int[3];
        for (int stone : stones) {
            ++count[stone % 3];
        }
        int m = Math.min(count[1] >> 1, count[2] >> 1);
//        count[1] -= m << 1;
//        count[2] -= m << 1;
//        count[0] %= 2;
        return dfs(count[0], count[1], count[2], 0, 0);
    }

    private boolean dfs(int a, int b, int c, int turn, int num) {
        final int ALICE = 0, BOB = 1;
        if (a == 0 && b == 0 && c == 0) {
            return false;
        }
        int next = 1 - turn;
        if (num == 0) {
            if (b == 0 && c == 0) {
                return false;
            }
            if (b > 0 && dfs(a, b - 1, c, next, 1)) {
                return true;
            }
            return c > 0 && dfs(a, b, c - 1, next, 2);
        }
        if (num == 1) {
            if (b == 0) {
                if (a == 0) {
                    return turn == BOB;
                }
                return dfs(a - 1, b, c, next, num);
            }
            return dfs(a, b - 1, c, next, 2);
        }
        if (c == 0) {
            if (a == 0) {
                return turn == BOB;
            }
            return dfs(a - 1, b, c, next, num);
        }
        return dfs(a, b, c - 1, next, 1);
    }

    public static void main(String[] args) {
        Problem2029 a = new Problem2029();
        int[] stones = {19,2,17,20,7,17};
//        a.stoneGameIX1()
    }
}
