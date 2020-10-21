package com.shuangpeng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem0039CombinationSum {

    public static void main(String[] args) {
        int[] array = {2, 3, 5};
        Problem0039CombinationSum a = new Problem0039CombinationSum();
        a.combinationSum(array, 8);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(candidates, result, new ArrayList<>(), 0, target);
        return result;
    }

    public void backtrack(int[] candidates, List<List<Integer>> result, List<Integer> combinate, int start, int rest) {
        if (rest < 0) {
            return;
        }
        if (rest == 0) {
            List<Integer> copy = new ArrayList<>(combinate);
            result.add(copy);
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            int data = candidates[i];
            int count = 1;
            int r = rest - data * count;
            while (r >= 0) {
                for (int c = 0; c < count; c++) {
                    combinate.add(data);
                }
                // TODO: 2020/10/20 start + 1应改为i + 1;
//                backtrack(candidates, result, combinate, start + 1, r);
                backtrack(candidates, result, combinate, i + 1, r);
                for (int c = 0; c < count; c++) {
                    combinate.remove(combinate.size() - 1);
                }
                count++;
                r = rest - data * count;
            }
        }
    }

    private List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum0(int[] candidates, int target) {
        List<Integer> path = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(path,candidates,target,0,0);
        return res;
    }

    private void backtrack(List<Integer> path,int[] candidates,int target,int sum,int begin) {
        if(sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = begin;i < candidates.length;i++) {
            int rs = candidates[i] + sum;
            if(rs <= target) {
                path.add(candidates[i]);
                backtrack(path,candidates,target,rs,i);
                path.remove(path.size()-1);
            } else {
                break;
            }
        }
    }
}
