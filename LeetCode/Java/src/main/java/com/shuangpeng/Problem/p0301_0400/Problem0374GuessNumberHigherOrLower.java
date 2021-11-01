package com.shuangpeng.Problem.p0301_0400;

public class Problem0374GuessNumberHigherOrLower {

    /**
     * Forward declaration of guess API.
     * @param  num   your guess
     * @return 	     -1 if num is higher than the guess number
     *			      1 if num is lower than the guess number
     *               otherwise return 0
     * int guess(int num);
     */
    int guess(int num) {
        return 0;
    }

    public int guessNumber(int n) {
        int left = 1, right = n;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (guess(mid) == -1) {
                right = mid - 1;
            } else if (guess(mid) == 1) {
                left = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
