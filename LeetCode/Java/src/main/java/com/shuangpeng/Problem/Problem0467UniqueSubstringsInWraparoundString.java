package com.shuangpeng.Problem;

public class Problem0467UniqueSubstringsInWraparoundString {

    public int findSubstringInWraproundString(String p) {
        int N = 26;
        int[] array = new int[N];
        array[p.charAt(0) - 'a'] = 1;
        int n = p.length();
        int left = 0, right = 1;
        int answer = 1;
        while (right < n) {
            char previous = p.charAt(right - 1);
            char current = p.charAt(right);
            int i = current - 'a';
            if ((current != previous + 1) && (previous != 'z' || current != 'a')) {
                left = right;
            }
            int length = right - left + 1;
            if (length > array[i]) {
                answer += (length - array[i]);
                array[i] = length;
            }
            right++;
        }
        return answer;
    }
}
