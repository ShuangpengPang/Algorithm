package com.shuangpeng.Problem.p1101_1200;

import java.util.*;

/**
 * @Description: Problem1192CriticalConnectionsInANetwork（查找集群内的关键连接）
 * @Date 2022/7/21 2:40 PM
 * @Version 1.0
 */
public class Problem1192CriticalConnectionsInANetwork {

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] graph = new List[n];
        Arrays.setAll(graph, e -> new ArrayList<>());
        for (List<Integer> conn : connections) {
            int x = conn.get(0), y = conn.get(1);
            graph[x].add(y);
            graph[y].add(x);
        }
        int[] ids = new int[n];
        Arrays.fill(ids, -1);
        List<List<Integer>> ans = new ArrayList<>();
        dfs(graph, 0, -1, 0, ids, ans);
        return ans;
    }

    private int dfs(List<Integer>[] graph, int x, int p, int id, int[] ids, List<List<Integer>> ans) {
        ids[x] = id;
        for (int y : graph[x]) {
            if (y == p) {
                continue;
            } else if (ids[y] == -1) {
                ids[x] = Math.min(ids[x], dfs(graph, y, x, id + 1, ids, ans));
            } else {
                ids[x] = Math.min(ids[x], ids[y]);
            }
        }
        if (ids[x] == id && x != 0) {
            ans.add(Arrays.asList(p, x));
        }
        return ids[x];
    }
}

class Problem1192CriticalConnectionsInANetwork0 {
    static class T{
        int N,M;
        int[] e,ne,h;
        int[] dfn,low;
        boolean[] bridge;
        int[] father;
        int idx = 1,cnt = 0;
        public T(int n,int m){
            N = n;M = m;
            e = new int[m];ne = new int[m];
            h = new int[N];
            dfn = new int[N];low = new int[N];father = new int[N];
            bridge = new boolean[N];
        }

        public void add(int a,int b){
            e[idx] = b;ne[idx] = h[a];h[a] = idx++;
        }

        //割边
        public void tarjan(int v,int fa){
            father[v] = fa;
            dfn[v] = low[v] = ++cnt;
            for(int i = h[v];i != 0;i = ne[i]){
                int j = e[i];
                if(dfn[j] == 0){
                    tarjan(j,v);
                    low[v] = Math.min(low[v],low[j]);
                    if(low[j] > dfn[v]) bridge[j] = true;
                }else if(j != fa) low[v] = Math.min(low[v],dfn[j]);
            }
        }
    }
    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        int m = 0;
        for(List<Integer> c : connections){
            m += c.size();
        }
        T tj = new T(n + 10,m * 2 + 10);
        for(List<Integer> c : connections){
            tj.add(c.get(0),c.get(1));
            tj.add(c.get(1),c.get(0));
        }
        tj.tarjan(0,0);
        for(int i = 0;i < n;i++){
            if(tj.bridge[i]){
                res.add(Arrays.asList(tj.father[i],i));
            }
        }
        return res;
    }
}
