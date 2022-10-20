package com.shuangpeng.Problem.p0701_0800;

/**
 * @Description: Problem0779KthSymbolInGrammar（第K个语法符号）
 * @Date 2022/10/20 10:04 AM
 * @Version 1.0
 */
public class Problem0779KthSymbolInGrammar {

    public int kthGrammar0(int n, int k) {
        return dfs(n, k, 0);
    }

    private int dfs(int n, int k, int num) {
        if (n == 1 && k == 1) {
            return num;
        }
        int h = 1 << (n - 2);
        if (k <= h) {
            return dfs(n - 1, k, num);
        } else {
            return dfs(n - 1, k - h, 1 - num);
        }
    }

    public int kthGrammar1(int n, int k) {
        int ans = 0, h = 1 << (n - 2);
        while (n > 1) {
            if (k > h) {
                k -= h;
                ans = 1 - ans;
            }
            h >>= 1;
            n--;
        }
        return ans;
    }

    public int kthGrammar(int n, int k) {
        k--;
        int ans = 0;
        while (k > 0) {
            k &= k - 1;
            ans ^= 1;
        }
        return ans;
    }
}

class Problem0779KthSymbolInGrammar0 {
    public int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0;
        }
        return (k & 1) ^ 1 ^ kthGrammar(n - 1, (k + 1) / 2);
    }
}

class Problem0779KthSymbolInGrammar1 {
    public int kthGrammar(int n, int k) {
        if (k == 1) {
            return 0;
        }
        if (k > (1 << (n - 2))) {
            return 1 ^ kthGrammar(n - 1, k - (1 << (n - 2)));
        }
        return kthGrammar(n - 1, k);
    }
}

class Problem0779KthSymbolInGrammar2 {
    public int kthGrammar(int n, int k) {
        // return Integer.bitCount(k - 1) & 1;
        k--;
        int res = 0;
        while (k > 0) {
            k &= k - 1;
            res ^= 1;
        }
        return res;
    }
}
