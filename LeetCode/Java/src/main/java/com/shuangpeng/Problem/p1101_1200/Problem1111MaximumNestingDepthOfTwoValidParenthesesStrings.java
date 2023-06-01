package com.shuangpeng.Problem.p1101_1200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1111MaximumNestingDepthOfTwoValidParenthesesStrings（有效括号的嵌套深度）
 * @date 2023/6/1 2:47 PM
 */
public class Problem1111MaximumNestingDepthOfTwoValidParenthesesStrings {

    public int[] maxDepthAfterSplit0(String seq) {
        int n = seq.length(), cnt1 = 0, cnt2 = 0;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int num = seq.charAt(i) == '(' ? 1 : -1;
            if (num == 1 && cnt1 < cnt2 || num == -1 && cnt1 > cnt2) {
                cnt1 += num;
            } else {
                cnt2 += num;
                ans[i] = 1;
            }
        }
        return ans;
    }

    public int[] maxDepthAfterSplit1(String seq) {
        int n = seq.length(), cnt = 0;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = seq.charAt(i) == '(' ? (cnt++) & 1 : (--cnt) & 1;
        }
        return ans;
    }

    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = i & 1 ^ (seq.charAt(i) - '(');
        }
        return ans;
    }
}
