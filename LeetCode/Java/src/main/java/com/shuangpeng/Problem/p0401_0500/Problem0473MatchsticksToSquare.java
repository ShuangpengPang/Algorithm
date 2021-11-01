package com.shuangpeng.Problem.p0401_0500;

import javafx.util.Pair;

import java.util.*;

public class Problem0473MatchsticksToSquare {

    public boolean makesquare0(int[] matchsticks) {
        int n = matchsticks.length;
        if (n < 4) {
            return false;
        }
        Arrays.sort(matchsticks);
        int sum = 0;
        int maxValue = 0;
        for (int i = 0; i < n; i++) {
            sum += matchsticks[i];
            maxValue = Math.max(maxValue, matchsticks[i]);
        }
        if ((sum & 3) != 0) {
            return false;
        }
        int target = sum >> 2;
        if (maxValue > target) {
            return false;
        }
        return backtrack(matchsticks, new boolean[n], target, 0);
    }

    private boolean backtrack(int[] matchsticks, boolean[] visited, int target, int count) {
        if (count == 3) {
            return true;
        }
        int n = matchsticks.length;
        Deque<Integer> queue = new LinkedList<>();
        int lastIndex = -1;
        int sum = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (!visited[i]) {
                visited[i] = true;
                queue.offerLast(i);
                sum += matchsticks[i];
                break;
            }
        }
        boolean valid = false;
        while (!queue.isEmpty()) {
            if (sum < target) {
                boolean flag = false;
                for (int i = lastIndex + 1; i < n; i++) {
                    if (!visited[i] && sum + matchsticks[i] <= target) {
                        visited[i] = true;
                        queue.offerLast(i);
                        sum += matchsticks[i];
                        flag = true;
                        lastIndex = i;
                        break;
                    } else if (sum + matchsticks[i] > target) {
                        break;
                    }
                }
                if (!flag) {
                    lastIndex = queue.pollLast();
                    visited[lastIndex] = false;
                    sum -= matchsticks[lastIndex];
                }
            } else if (sum == target) {
                if (backtrack(matchsticks, visited, target, count + 1)) {
                    valid = true;
                    break;
                }
                lastIndex = queue.pollLast();
                visited[lastIndex] = false;
                sum -= matchsticks[lastIndex];
            }
        }
        while (!queue.isEmpty()) {
            visited[queue.pollLast()] = false;
        }
        return valid;
    }

    // The memoization cache to be used during recursion.
    public HashMap<Pair<Integer, Integer>, Boolean> memo = new HashMap<>();

    // Array containing our matchsticks.
    public int[] nums;

    // Possible side of our square depending on the total sum of all the matchsticks. 
    public int possibleSquareSide;

    // Main DP function.
    public boolean recurse(Integer mask, Integer sidesDone) {
        int total = 0;
        int L = this.nums.length;

        // The memo key for this recursion
        Pair<Integer, Integer> memoKey = new Pair(mask, sidesDone);

        // Find out the sum of matchsticks used till now.
        for(int i = L - 1; i >= 0; i--) {
            if ((mask&(1 << i)) == 0) {
                total += this.nums[L - 1 - i];
            }
        }

        // If the sum if divisible by our square's side, then we increment our number of complete sides formed variable.
        if (total > 0 && total % this.possibleSquareSide == 0) {
            sidesDone++;
        }

        // Base case.
        if (sidesDone == 3) {
            return true;
        }


        // Return precomputed results
        if (this.memo.containsKey(memoKey)) {
            return this.memo.get(memoKey);
        }

        boolean ans = false;
        int c = total / this.possibleSquareSide;

        // Remaining vlength in the current partially formed side.
        int rem = this.possibleSquareSide * (c + 1) - total;

        // Try out all remaining options (that are valid)
        for(int i = L - 1; i >= 0; i--) {
            if (this.nums[L - 1 - i] <= rem && (mask&(1 << i)) > 0) {
                if (this.recurse(mask ^ (1 << i), sidesDone)) {
                    ans = true;
                    break;
                }
            }
        }

        // Cache the computed results.
        this.memo.put(memoKey, ans);
        return ans;
    }

    public boolean makesquare1(int[] nums) {

        // Empty matchsticks.
        if (nums == null || nums.length == 0) {
            return false;
        }

        // Find the perimeter of the square (if at all possible)
        int L = nums.length;
        int perimeter = 0;
        for(int i = 0; i < L; i++) {
            perimeter += nums[i];
        }

        int possibleSquareSide =  perimeter / 4;
        if (possibleSquareSide * 4 != perimeter) {
            return false;
        }

        this.nums = nums;
        this.possibleSquareSide = possibleSquareSide;
        return this.recurse((1 << L) - 1, 0);
    }

    public boolean makesquare(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
        }
        if ((sum & 3) != 0) {
            return false;
        }
        return dfs(nums, (1 << n) - 1, 0, sum >> 2, new HashMap<>());
    }

    private boolean dfs(int[] nums, int mask, int sum, int sideSum, Map<Pair<Integer, Integer>, Boolean> memo) {
        int sideDone = sum / sideSum;
        if (sideDone == 3) {
            return true;
        }
        int remain = sideSum - sum % sideSum;
        int n = nums.length;
        Pair<Integer, Integer> pair = new Pair<>(mask, sum);
        if (memo.containsKey(pair)) {
            return memo.get(pair);
        }
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) > 0 && nums[n - i - 1] <= remain) {
                if (dfs(nums, mask ^ (1 << i), sum + nums[n - i - 1], sideSum, memo)) {
                    memo.put(pair, true);
                    return true;
                }
            }
        }
        memo.put(pair, false);
        return false;
    }

//    public static void main(String[] args) {
//        Problem0473MatchsticksToSquare a = new Problem0473MatchsticksToSquare();
//        a.makesquare(new int[]{5, 5, 5, 5, 4, 4, 4, 4, 3, 3, 3, 3});
//        int i = 1;
//    }
}
