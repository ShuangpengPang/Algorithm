package com.shuangpeng;

import javafx.util.Pair;

import java.util.*;

public class Problem0399EvaluateDivision {

    class KeyValue {
        String vertex;
        double value;

        public KeyValue(String vertex, double value) {
            this.vertex = vertex;
            this.value = value;
        }
    }

    public double[] calcEquation0(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        buildGraph(graph, equations, values);
        int size = queries.size();
        double[] answers = new double[size];
        for (int i = 0; i < size; i++) {
            List<String> query = queries.get(i);
            String start = query.get(0);
            String end = query.get(1);
            Queue<KeyValue> queue = new LinkedList<>();
            KeyValue keyValue = new KeyValue(start, 1);
            queue.offer(keyValue);
            Set<String> visited = new HashSet<>();
            visited.add(start);
            boolean finish = false;
            double result = -1;
            while (!queue.isEmpty() && !finish) {
                int length = queue.size();
                for (int j = 0; j < length; j++) {
                    KeyValue node = queue.poll();
                    String vertex = node.vertex;
                    if (!graph.containsKey(vertex)) {
                        finish = true;
                        break;
                    }
                    if (vertex.equals(end)) {
                        result = node.value;
                        finish = true;
                        break;
                    }
                    Map<String, Double> edgeMap = graph.get(vertex);
                    Set<String> edges = edgeMap.keySet();
                    for (String edge : edges) {
                        if (!visited.contains(edge)) {
                            visited.add(edge);
                            queue.offer(new KeyValue(edge, node.value * edgeMap.get(edge)));
                        }
                    }
                }
            }
            answers[i] = result;
        }
        return answers;
    }

    public void buildGraph(Map<String, Map<String, Double>> graph, List<List<String>> equations, double[] values) {
        int length = equations.size();
        for (int i = 0; i < length; i++) {
            List<String> edge = equations.get(i);
            String start = edge.get(0);
            String end = edge.get(1);
            if (!graph.containsKey(start)) {
                graph.put(start, new HashMap<>());
            }
            if (!graph.containsKey(end)) {
                graph.put(end, new HashMap<>());
            }
            graph.get(start).put(end, values[i]);
            graph.get(end).put(start, 1 / values[i]);
        }
    }


    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, String> parents  = new HashMap<>();
        Map<String, Integer> sizes = new HashMap<>();
        Map<String, Double> weights = new HashMap<>();
        int length = equations.size();
        for (int i = 0; i < length; i++) {
            String start = equations.get(i).get(0);
            String end = equations.get(i).get(1);
            union(start, end, values[i], parents, sizes, weights);
        }
        int size = queries.size();
        double[] answers = new double[size];
        for (int i = 0; i < size; i++) {
            List<String> edge = queries.get(i);
            String start = edge.get(0);
            String end = edge.get(1);
            if (!parents.containsKey(start) || !parents.containsKey(end)) {
                answers[i] = -1;
                continue;
            }
            Pair<String, Double> startPair = findRoot(parents, sizes, weights, edge.get(0));
            Pair<String, Double> endPair = findRoot(parents, sizes, weights, edge.get(1));
            if (!startPair.getKey().equals(endPair.getKey())) {
                answers[i] = -1;
                continue;
            }
            answers[i] = startPair.getValue() / endPair.getValue();
        }
        return answers;
    }

    public void union(String a, String b, double value, Map<String, String> parents,
                      Map<String, Integer> sizes, Map<String, Double> weights) {
        Pair<String, Double> pairA = findRoot(parents, sizes, weights, a);
        Pair<String, Double> pairB = findRoot(parents, sizes, weights, b);
        String parentA = pairA.getKey();
        String parentB = pairB.getKey();
        if (!parentA.equals(parentB)) {
            double aToRoot = pairA.getValue();
            double bToRoot = pairB.getValue();
            int sizeA = sizes.get(parentA);
            int sizeB = sizes.get(parentB);
            if (sizeA < sizeB) {
                parents.put(parentA, parentB);
                weights.put(parentA, value * bToRoot / aToRoot);
            } else if (sizeA > sizeB) {
                parents.put(parentB, parentA);
                weights.put(parentB, aToRoot / (value * bToRoot));
            } else {
                parents.put(parentB, parentA);
                weights.put(parentB, aToRoot / (value * bToRoot));
                sizes.put(parentA, sizes.get(parentA) + 1);
            }
        }
    }

    public Pair<String, Double> findRoot(Map<String, String> parents, Map<String, Integer> sizes,
                           Map<String, Double> weights, String string) {
        if (!parents.containsKey(string)) {
            parents.put(string, string);
            sizes.put(string, 1);
            weights.put(string, 1.0);
        }
        double weight = 1;
        while (!parents.get(string).equals(string)) {
            String parent = parents.get(string);
            String grandfather = parents.get(parent);
            double newWeight = weights.get(string) * weights.get(parent);
            weights.put(string, newWeight);
            weight *= newWeight;
            parents.put(string, grandfather);
            string = grandfather;
        }
        return new Pair<>(string, weight);
    }
}
