package com.shuangpeng.Problem.p0101_0200;

public class Problem0134GasStation {

    public int canCompleteCircuit0(int[] gas, int[] cost) {
        int length = gas.length;
        for (int i = 0; i < length; i++) {
            int current = 0;
            int j = 0;
            while (j < length) {
                int k = (i + j) % length;
                if (current + gas[k] >= cost[k]) {
                    current = current + gas[k] - cost[k];
                } else {
                    break;
                }
                j++;
            }
            if (j == length) {
                return i;
            }
        }
        return -1;
    }

//    public static void main(String[] args) {
//        Problem0134GasStation a = new Problem0134GasStation();
//        int[] gas = {1,2,3,4,5};
//        int[] cost = {3,4,5,1,2};
//        a.canCompleteCircuit(gas, cost);
//    }

    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int length = gas.length;
        int i = 0;
        while (i < length) {
            int current = 0;
            int j = 0;
            while (j < length) {
                int k = (i + j) % length;
                if (current + gas[k] >= cost[k]) {
                    current = current + gas[k] - cost[k];
                } else if (k >= i) {
                    i = k + 1;
                    break;
                } else {
                    return -1;
                }
                j++;
            }
            if (j == length) {
                return i;
            }
        }
        return -1;
    }

    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0;
        while (i < n) {
            int sumOfGas = 0, sumOfCost = 0;
            int cnt = 0;
            while (cnt < n) {
                int j = (i + cnt) % n;
                sumOfGas += gas[j];
                sumOfCost += cost[j];
                if (sumOfCost > sumOfGas) {
                    break;
                }
                cnt++;
            }
            if (cnt == n) {
                return i;
            } else {
                i = i + cnt + 1;
            }
        }
        return -1;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int length = gas.length;
        int i = 0;
        while (i < length) {
            int current = 0;
            int j = 0;
            int k = 0;
            while (j < length) {
                k = (i + j) % length;
                if (current + gas[k] >= cost[k]) {
                    current = current + gas[k] - cost[k];
                } else {
                    break;
                }
                j++;
            }
            if (j == length) {
                return i;
            } else if (k >= i) {
                i = k + 1;
            } else {
                return -1;
            }
        }
        return -1;
    }
}
