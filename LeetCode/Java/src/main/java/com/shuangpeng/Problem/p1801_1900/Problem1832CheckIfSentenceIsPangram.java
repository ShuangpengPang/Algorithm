package com.shuangpeng.Problem.p1801_1900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1832CheckIfSentenceIsPangram（判断句子是否为全字母句）
 * @date 2022/12/13 10:19 AM
 */
public class Problem1832CheckIfSentenceIsPangram {

    public boolean checkIfPangram0(String sentence) {
        int n = sentence.length(), cnt = 0;
        boolean[] has = new boolean[26];
        for (int i = 0; i < n; i++) {
            int c = sentence.charAt(i) - 'a';
            if (!has[c]) {
                cnt++;
            }
            has[c] = true;
        }
        return cnt == 26;
    }

    public boolean checkIfPangram(String sentence) {
        int n = sentence.length(), h = 0, N = (1 << 26) - 1;
        for (int i = 0; i < n && h != N; i++) {
            h |= 1 << sentence.charAt(i) - 'a';
        }
        return h == N;
    }
}
