package com.shuangpeng.Problem.p0201_0300;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Problem0284PeekingIterator {

    class PeekingIterator implements Iterator<Integer> {
        List<Integer> list;
        int index = 0;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            list = new ArrayList<>();
            while (iterator.hasNext()) {
                list.add(iterator.next());
            }
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            return list.get(index);
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            return list.get(index++);
        }

        @Override
        public boolean hasNext() {
            return index < list.size();
        }
    }
}
