package com.shuangpeng.Problem.p0901_1000;

/**
 * @Description: Problem0953VerifyingAnAlienDictionary（验证外星语词典）
 * @Date 2022/5/17 10:18 AM
 * @Version 1.0
 */
public class Problem0953VerifyingAnAlienDictionary {

    public boolean isAlienSorted(String[] words, String order) {
        int N = 26;
        int[] map = new int[N];
        for (int i = 0; i < N; ++i) {
            map[order.charAt(i) - 'a'] = i;
        }
        int n = words.length;
        for (int i = 1; i < n; ++i) {
            String w1 = words[i - 1], w2 = words[i];
            int n1 = w1.length(), n2 = w2.length();
            boolean valid = false;
            for (int j = 0; j < n1 && j < n2; ++j) {
                int o1 = map[w1.charAt(j) - 'a'], o2 = map[w2.charAt(j) - 'a'];
                if (o1 < o2) {
                    valid = true;
                    break;
                } else if (o1 > o2) {
                    return false;
                }
            }
            if (!valid && n1 > n2) {
                return false;
            }
        }
        return true;
    }
}
