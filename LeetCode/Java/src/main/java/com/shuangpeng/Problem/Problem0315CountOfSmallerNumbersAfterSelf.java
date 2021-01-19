package com.shuangpeng.Problem;

import java.util.*;

public class Problem0315CountOfSmallerNumbersAfterSelf {

//    public static void main(String[] args) {
//        Problem0315CountOfSmallerNumbersAfterSelf a = new Problem0315CountOfSmallerNumbersAfterSelf();
////        a.countSmaller(new int[]{3, 9, 5, 2, 6, 1, 3});
//        a.countSmaller(new int[]{5,2,6,1});
//    }

    public List<Integer> countSmaller0(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        int n = nums.length;
        Integer[] result = new Integer[n];
        for (int i = n - 1; i >= 0; i--) {
            int min = 0;
            int max = n - i - 1;
            int minCount = 0;
            int maxCount = 0;
            int j = i + 1;
            while (min < max && j < n) {
                if (nums[i] > nums[j]) {
                    minCount++;
                    min = minCount + result[j];
                } else if (nums[i] < nums[j]) {
                    maxCount++;
                    max = Math.min(minCount + result[j], n - i - maxCount - 1);
                } else if (nums[i] == nums[j]) {
                    min = minCount + result[j];
                    break;
                }
                j++;
            }
            if (j == n) {
                result[i] = minCount;
            } else {
                result[i] = min;
            }
        }
        return Arrays.asList(result);
    }


    int[] sorted;
    int[] treeArray;

    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        decretization(nums);
        List<Integer> answer = new ArrayList<>(sorted.length);
        for (int i = nums.length - 1; i >= 0; i--) {
            int index = getIndex(nums[i]);
            queryCount(index - 1, answer);
            updateTreeArray(index);
        }
        Collections.reverse(answer);
        return answer;
    }

    private int getIndex(int num) {
        return Arrays.binarySearch(sorted, num) + 1;
    }

    private void decretization(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        sorted = new int[set.size()];
        int i = 0;
        for (int num : set) {
            sorted[i++] = num;
        }
        Arrays.sort(sorted);
        treeArray = new int[set.size()];
    }

    private int getLowBit(int x) {
        return x & (-x);
    }

    private int toArrayIndex(int x) {
        return x - 1;
    }

    private void queryCount(int index, List<Integer> answer) {
        int sum = 0;
        while (index > 0) {
            sum += (treeArray[toArrayIndex(index)]);
            index -= getLowBit(index);
        }
        answer.add(sum);
    }

    private void updateTreeArray(int index) {
        while (index <= treeArray.length) {
            treeArray[toArrayIndex(index)]++;
            index += getLowBit(index);
        }
    }




    private int[] c;
    private int[] a;

    public List<Integer> countSmaller1(int[] nums) {
        List<Integer> resultList = new ArrayList<Integer>();
        discretization(nums);
        init(nums.length + 5);
        for (int i = nums.length - 1; i >= 0; --i) {
            int id = getId(nums[i]);
            resultList.add(query(id - 1));
            update(id);
        }
        Collections.reverse(resultList);
        return resultList;
    }

    private void init(int length) {
        c = new int[length];
        Arrays.fill(c, 0);
    }

    private int lowBit(int x) {
        return x & (-x);
    }

    private void update(int pos) {
        while (pos < c.length) {
            c[pos] += 1;
            pos += lowBit(pos);
        }
    }

    private int query(int pos) {
        int ret = 0;
        while (pos > 0) {
            ret += c[pos];
            pos -= lowBit(pos);
        }

        return ret;
    }

    private void discretization(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        int size = set.size();
        a = new int[size];
        int index = 0;
        for (int num : set) {
            a[index++] = num;
        }
        Arrays.sort(a);
    }

    private int getId(int x) {
        return Arrays.binarySearch(a, x) + 1;
    }
}
