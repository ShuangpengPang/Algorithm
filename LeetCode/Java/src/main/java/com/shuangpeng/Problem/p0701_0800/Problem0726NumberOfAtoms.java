package com.shuangpeng.Problem.p0701_0800;

import java.util.*;

public class Problem0726NumberOfAtoms {

    public String countOfAtoms0(String formula) {
        Map<String, Integer> map = calculate(formula, new int[]{0});
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                builder.append(entry.getKey()).append(entry.getValue());
            } else {
                builder.append(entry.getKey());
            }
        }
        return builder.toString();
    }

    private Map<String, Integer> calculate(String formula, int[] pIndex) {
        int n = formula.length();
        int i = pIndex[0];
        int j = i;
        Map<String, Integer> map = new TreeMap<>((String::compareTo));
        while (j < n && formula.charAt(j) != ')') {
            char c = formula.charAt(j);
            if (Character.isDigit(c)) {
                String atom = formula.substring(i, j);
                int number = 0;
                while (j < n && Character.isDigit(formula.charAt(j))) {
                    number = number * 10 + (formula.charAt(j) - '0');
                    j++;
                }
                map.put(atom, map.getOrDefault(atom, 0) + number);
                i = j;
            } else if (c >= 'A' && c <= 'Z') {
                if (i < j) {
                    String atom = formula.substring(i, j);
                    map.put(atom, map.getOrDefault(atom, 0) + 1);
                    i = j;
                }
                j++;
            } else if (c == '(') {
                if (i < j) {
                    String atom = formula.substring(i, j);
                    map.put(atom, map.getOrDefault(atom, 0) + 1);
                }
                pIndex[0] = j;
                pIndex[0]++;
                Map<String, Integer> tempMap = calculate(formula, pIndex);
                pIndex[0]++;
                i = pIndex[0];
                j = i;
                int number = 0;
                while (j < n && Character.isDigit(formula.charAt(j))) {
                    number = number * 10 + (formula.charAt(j) - '0');
                    j++;
                }
                pIndex[0] = j;
                number = number == 0 ? 1 : number;
                merge(map, tempMap, number);
                i = j;
            } else {
                j++;
            }
        }
        if (i < n && formula.charAt(i) != ')') {
            String atom = formula.substring(i, j);
            map.put(atom, map.getOrDefault(atom, 0) + 1);
        }
        pIndex[0] = j;
        return map;
    }

    private void merge(Map<String, Integer> map, Map<String, Integer> mapToMerge, int count) {
        for (Map.Entry<String, Integer> entry : mapToMerge.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            map.put(key, map.getOrDefault(key, 0) + value * count);
        }
    }

    public String countOfAtoms1(String formula) {
        int n = formula.length();
        int i = 0, j = 0;
        Map<String, Integer> map = new TreeMap<>(String::compareTo);
        Deque<Map<String, Integer>> stack = new LinkedList<>();
        while (j < n) {
            char c = formula.charAt(j);
            if (Character.isDigit(c)) {
                String atom = formula.substring(i, j);
                int number = 0;
                while (j < n && Character.isDigit(formula.charAt(j))) {
                    number = number * 10 + (formula.charAt(j) - '0');
                    j++;
                }
                map.put(atom, map.getOrDefault(atom, 0) + number);
                i = j;
            } else if (c >= 'A' && c <= 'Z') {
                addAtom(formula, i, j, map);
                i = j;
                j++;
            } else if (c == '(') {
                addAtom(formula, i, j, map);
                stack.offerLast(map);
                map = new TreeMap<>(String::compareTo);
                j++;
                i = j;
            } else if (c == ')') {
                addAtom(formula, i, j, map);
                j++;
                int number = 0;
                while (j < n && Character.isDigit(formula.charAt(j))) {
                    number = number * 10 + (formula.charAt(j) - '0');
                    j++;
                }
                number = number == 0 ? 1 : number;
                Map<String, Integer> previous = stack.pollLast();
                merge(previous, map, number);
                map = previous;
                i = j;
            } else {
                j++;
            }
        }
        if (i < n) {
            addAtom(formula, i, j, map);
        }
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String key = entry.getKey();
            int value = entry.getValue();
            builder.append(key);
            if (value > 1) {
                builder.append(value);
            }
        }
        return builder.toString();
    }

    private void addAtom(String formula, int i, int j, Map<String, Integer> map) {
        if (i < j) {
            String atom = formula.substring(i, j);
            map.put(atom, map.getOrDefault(atom, 0) + 1);
        }
    }


    int i, n;
    String formula;

    public String countOfAtoms(String formula) {
        this.i = 0;
        this.n = formula.length();
        this.formula = formula;

        Deque<Map<String, Integer>> stack = new LinkedList<Map<String, Integer>>();
        stack.push(new HashMap<String, Integer>());
        while (i < n) {
            char ch = formula.charAt(i);
            if (ch == '(') {
                i++;
                stack.push(new HashMap<String, Integer>()); // 将一个空的哈希表压入栈中，准备统计括号内的原子数量
            } else if (ch == ')') {
                i++;
                int num = parseNum(); // 括号右侧数字
                Map<String, Integer> popMap = stack.pop(); // 弹出括号内的原子数量
                Map<String, Integer> topMap = stack.peek();
                for (Map.Entry<String, Integer> entry : popMap.entrySet()) {
                    String atom = entry.getKey();
                    int v = entry.getValue();
                    topMap.put(atom, topMap.getOrDefault(atom, 0) + v * num); // 将括号内的原子数量乘上 num，加到上一层的原子数量中
                }
            } else {
                String atom = parseAtom();
                int num = parseNum();
                Map<String, Integer> topMap = stack.peek();
                topMap.put(atom, topMap.getOrDefault(atom, 0) + num); // 统计原子数量
            }
        }

        Map<String, Integer> map = stack.pop();
        TreeMap<String, Integer> treeMap = new TreeMap<String, Integer>(map);

        StringBuffer sb = new StringBuffer();
        for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
            String atom = entry.getKey();
            int count = entry.getValue();
            sb.append(atom);
            if (count > 1) {
                sb.append(count);
            }
        }
        return sb.toString();
    }

    public String parseAtom() {
        StringBuffer sb = new StringBuffer();
        sb.append(formula.charAt(i++)); // 扫描首字母
        while (i < n && Character.isLowerCase(formula.charAt(i))) {
            sb.append(formula.charAt(i++)); // 扫描首字母后的小写字母
        }
        return sb.toString();
    }

    public int parseNum() {
        if (i == n || !Character.isDigit(formula.charAt(i))) {
            return 1; // 不是数字，视作 1
        }
        int num = 0;
        while (i < n && Character.isDigit(formula.charAt(i))) {
            num = num * 10 + formula.charAt(i++) - '0'; // 扫描数字
        }
        return num;
    }

//    public static void main(String[] args) {
//        Problem0726NumberOfAtoms a = new Problem0726NumberOfAtoms();
//        a.countOfAtoms("K4(ON(SO3)2)2");
//    }
}
