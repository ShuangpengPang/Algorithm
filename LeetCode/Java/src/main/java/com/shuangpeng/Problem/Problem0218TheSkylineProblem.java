package com.shuangpeng.Problem;

import java.util.*;

public class Problem0218TheSkylineProblem {

//    public static void main(String[] args) {
//        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
//        Problem0218TheSkylineProblem a = new Problem0218TheSkylineProblem();
//        List list = a.getSkyline(buildings);
//    }

    public List<List<Integer>> getSkyline0(int[][] buildings) {
        if (buildings == null || buildings.length == 0) {
            return new ArrayList<>();
        }
        return calculateSkyline(buildings, 0, buildings.length);
    }

    private List<List<Integer>> calculateSkyline(int[][] buildings, int start, int end) {
        if (start >= end) {
            return new ArrayList<>();
        }
        List<List<Integer>> answer = new ArrayList<>();
        if (start + 1 == end) {
            answer.add(new ArrayList<Integer>() {{
                add(buildings[start][0]);
                add(buildings[start][2]);
            }});
            answer.add(new ArrayList<Integer>() {{
                add(buildings[start][1]);
                add(0);
            }});
            return answer;
        }
        int mid = (start + end) >>> 1;
        List<List<Integer>> left = calculateSkyline(buildings, start, mid);
        List<List<Integer>> right = calculateSkyline(buildings, mid, end);
        return mergeSkyline(left, right);
    }

    private List<List<Integer>> mergeSkyline(List<List<Integer>> left, List<List<Integer>> right) {
        int leftSize = left.size();
        int rightSize = right.size();
        int pLeft = 0;
        int pRight = 0;
        List<List<Integer>> answer = new ArrayList<>();
        int currentHeight = 0;
        int leftHeight = 0;
        int rightHeight = 0;
        int x = 0;
        while (pLeft < leftSize && pRight < rightSize) {
            List<Integer> leftPoint = left.get(pLeft);
            List<Integer> rightPoint = right.get(pRight);
            if (leftPoint.get(0) < rightPoint.get(0)) {
                x = leftPoint.get(0);
                leftHeight = leftPoint.get(1);
                pLeft++;
            } else if (leftPoint.get(0) > rightPoint.get(0)) {
                x = rightPoint.get(0);
                rightHeight = rightPoint.get(1);
                pRight++;
            } else if (leftPoint.get(0) == rightPoint.get(0) && leftPoint.get(1) > rightPoint.get(1)) {
                x = leftPoint.get(0);
                leftHeight = leftPoint.get(1);
                pLeft++;
            } else if (leftPoint.get(0) == rightPoint.get(0) && leftPoint.get(1) < rightPoint.get(1)) {
                x = rightPoint.get(0);
                rightHeight = rightPoint.get(1);
                pRight++;
            } else {
                x = leftPoint.get(0);
                leftHeight = leftPoint.get(1);
                rightHeight = leftHeight;
                pLeft++;
                pRight++;
            }
            int height = Math.max(leftHeight, rightHeight);
            if (currentHeight != height) {
                List<Integer> list = new ArrayList<>();
                list.add(x);
                list.add(height);
                answer.add(list);
                currentHeight = height;
            }
        }
        if (pLeft < leftSize) {
            if (answer.size() > 0 && answer.get(answer.size() - 1).get(0) == left.get(pLeft).get(0)) {
                answer.get(answer.size() - 1).set(1, left.get(pLeft).get(1));
                pLeft++;
            }
            while (pLeft < leftSize) {
                answer.add(left.get(pLeft));
                pLeft++;
            }
        }
        if (pRight < rightSize) {
            if (answer.size() > 0 && answer.get(answer.size() - 1).get(0) == right.get(pRight).get(0)) {
                answer.get(answer.size() - 1).set(1, right.get(pRight).get(1));
                pRight++;
            }
            while (pRight < rightSize) {
                answer.add(right.get(pRight));
                pRight++;
            }
        }
        return answer;
    }


    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings == null || buildings.length == 0) {
            return new ArrayList<>();
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]);
        int length = buildings.length;
        for (int i = 0; i < length; i++) {
            int[] building = buildings[i];
            queue.offer(new int[]{building[0], building[2]});
            queue.offer(new int[]{building[1], -building[2]});
        }
        TreeMap<Integer, Integer> map = new TreeMap<>((a, b) -> b - a);
        List<List<Integer>> answer = new ArrayList<>();
        map.put(0, 1);
        int height = 0;
        while (!queue.isEmpty()) {
            int[] data = queue.poll();
            if (data[1] > 0) {
                map.put(data[1], map.getOrDefault(data[1], 0) + 1);
            } else {
                map.put(-data[1], map.getOrDefault(-data[1], 0) - 1);
            }
            int d = data[1] > 0 ? data[1] : -data[1];
            if (map.get(d) == 0) {
                map.remove(d);
            }
            int maxHeight = map.keySet().iterator().next();
            if (maxHeight != height) {
                height = maxHeight;
                List<Integer> list = new ArrayList<>();
                list.add(data[0]);
                list.add(maxHeight);
                answer.add(list);
            }
        }
        return answer;
    }

    public List<List<Integer>> getSkyline1(int[][] buildings) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        for (int[] building : buildings) {
            pq.offer(new int[]{building[0], -building[2]});
            pq.offer(new int[]{building[1], building[2]});
        }

        List<List<Integer>> res = new ArrayList<>();

        TreeMap<Integer, Integer> heights = new TreeMap<>((a, b) -> b - a);
        heights.put(0, 1);
        int left = 0, height = 0;
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            if (arr[1] < 0) {
                heights.put(-arr[1], heights.getOrDefault(-arr[1], 0) + 1);
            } else {
                heights.put(arr[1], heights.get(arr[1]) - 1);
                if (heights.get(arr[1]) == 0) heights.remove(arr[1]);
            }
            int maxHeight = heights.keySet().iterator().next();
            if (maxHeight != height) {
                left = arr[0];
                height = maxHeight;
                res.add(Arrays.asList(left, height));
            }
        }

        return res;
    }

    class Solution {
        /**
         * Divide-and-conquer algorithm to solve skyline problem,
         * which is similar with the merge sort algorithm.
         */
        public List<List<Integer>> getSkyline(int[][] buildings) {
            int n = buildings.length;
            List<List<Integer>> output = new ArrayList<List<Integer>>();

            // The base cases
            if (n == 0) return output;
            if (n == 1) {
                int xStart = buildings[0][0];
                int xEnd = buildings[0][1];
                int y = buildings[0][2];

                output.add(new ArrayList<Integer>() {{
                    add(xStart);
                    add(y);
                }});
                output.add(new ArrayList<Integer>() {{
                    add(xEnd);
                    add(0);
                }});
                // output.add(new int[]{xStart, y});
                // output.add(new int[]{xEnd, 0});
                return output;
            }

            // If there is more than one building,
            // recursively divide the input into two subproblems.
            List<List<Integer>> leftSkyline, rightSkyline;
            leftSkyline = getSkyline(Arrays.copyOfRange(buildings, 0, n / 2));
            rightSkyline = getSkyline(Arrays.copyOfRange(buildings, n / 2, n));

            // Merge the results of subproblem together.
            return mergeSkylines(leftSkyline, rightSkyline);
        }

        /**
         * Merge two skylines together.
         */
        public List<List<Integer>> mergeSkylines(List<List<Integer>> left, List<List<Integer>> right) {
            int nL = left.size(), nR = right.size();
            int pL = 0, pR = 0;
            int currY = 0, leftY = 0, rightY = 0;
            int x, maxY;
            List<List<Integer>> output = new ArrayList<List<Integer>>();

            // while we're in the region where both skylines are present
            while ((pL < nL) && (pR < nR)) {
                List<Integer> pointL = left.get(pL);
                List<Integer> pointR = right.get(pR);
                // pick up the smallest x
                if (pointL.get(0) < pointR.get(0)) {
                    x = pointL.get(0);
                    leftY = pointL.get(1);
                    pL++;
                } else {
                    x = pointR.get(0);
                    rightY = pointR.get(1);
                    pR++;
                }
                // max height (i.e. y) between both skylines
                maxY = Math.max(leftY, rightY);
                // update output if there is a skyline change
                if (currY != maxY) {
                    updateOutput(output, x, maxY);
                    currY = maxY;
                }
            }

            // there is only left skyline
            appendSkyline(output, left, pL, nL, currY);

            // there is only right skyline
            appendSkyline(output, right, pR, nR, currY);

            return output;
        }

        /**
         * Update the final output with the new element.
         */
        public void updateOutput(List<List<Integer>> output, int x, int y) {
            // if skyline change is not vertical -
            // add the new point
            if (output.isEmpty() || output.get(output.size() - 1).get(0) != x)
                output.add(new ArrayList<Integer>() {{
                    add(x);
                    add(y);
                }});
                // if skyline change is vertical -
                // update the last point
            else {
                output.get(output.size() - 1).set(1, y);
            }
        }

        /**
         * Append the rest of the skyline elements with indice (p, n)
         * to the final output.
         */
        public void appendSkyline(List<List<Integer>> output, List<List<Integer>> skyline,
                                  int p, int n, int currY) {
            while (p < n) {
                List<Integer> point = skyline.get(p);
                int x = point.get(0);
                int y = point.get(1);
                p++;

                // update output
                // if there is a skyline change
                if (currY != y) {
                    updateOutput(output, x, y);
                    currY = y;
                }
            }
        }
    }
}
