package com.shuangpeng.Problem.p0301_0400;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Problem0341FlattenNestedListInterator {

    // This is the interface that allows for creating nested lists.
    // You should not implement it, or speculate about its implementation
    public interface NestedInteger {

        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        public List<NestedInteger> getList();
    }

    public class NestedIterator implements Iterator<Integer> {

        private List<Integer> integerList;
        private int index;

        public NestedIterator(List<NestedInteger> nestedList) {
            integerList = getList(nestedList);
        }

        private List<Integer> getList(List<NestedInteger> nestedList) {
            int size = nestedList.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                NestedInteger nestedInteger = nestedList.get(i);
                if (nestedInteger.isInteger()) {
                    list.add(nestedInteger.getInteger());
                } else {
                    list.addAll(getList(nestedInteger.getList()));
                }
            }
            return list;
        }

        @Override
        public Integer next() {
            return integerList.get(index++);
        }

        @Override
        public boolean hasNext() {
            return index < integerList.size();
        }
    }

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
}
