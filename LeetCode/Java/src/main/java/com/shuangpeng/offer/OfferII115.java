package com.shuangpeng.offer;

import java.util.*;

/**
 * @Description: OfferII115（重建序列）
 * @Date 2022/7/23 10:04 AM
 * @Version 1.0
 */
public class OfferII115 {

    public boolean sequenceReconstruction0(int[] nums, int[][] sequences) {
        int n = nums.length;
        List<Integer>[] graph = new List[n];
        Arrays.setAll(graph, e -> new ArrayList<>());
        int[] inDegree = new int[n];
        for (int[] arr : sequences) {
            int m = arr.length;
            for (int i = 0; i < m - 1; i++) {
                int n1 = arr[i] - 1, n2 = arr[i + 1] - 1;
                graph[n1].add(n2);
                inDegree[n2]++;
            }
        }
        int num = -1;
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                if (num != -1) {
                    return false;
                }
                num = i;
            }
        }
        for (int i = 0; i < n; i++) {
            if (num + 1 != nums[i]) {
                return false;
            }
            List<Integer> list = graph[num];
            num = -1;
            for (int j : list) {
                inDegree[j]--;
                if (inDegree[j] == 0) {
                    if (num != -1) {
                        return false;
                    }
                    num = j;
                }
            }
        }
        return true;
    }

    public boolean sequenceReconstruction(int[] org, int[][] sequences) {
        int n = org.length;
        int[] index = new int[n + 1];
        for(int i = 0; i < n; i++) index[org[i]] = i; // index[num]表示org中的num的下标
        boolean[] adj = new boolean[n + 1]; // 用于判断3的数组
        for(int[] seq : sequences){
            int preIdx = -1, seqLen = seq.length;
            for(int i = 0; i < seqLen; i++){
                if(seq[i] > n || seq[i] < 1) return false; // 检查1
                int cur = seq[i], curIdx = index[cur]; // 当前数字cur和当前数字在org中的下标
                if(preIdx + 1 == curIdx) adj[cur] = true; // 检查3前准备
                if(curIdx <= preIdx) return false; // 检查2
                else preIdx = curIdx;
            }
        }
        for(int i = 1; i < adj.length; i++) if(!adj[i]) return false; // 检查3
        return true;
    }
}
