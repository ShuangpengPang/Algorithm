package com.shuangpeng.Problem;

import java.util.*;

public class Problem0210CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Set<Integer> zeroSet = new HashSet<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            zeroSet.add(i);
        }
        Map<Integer, List<Integer>> map = new HashMap<>(numCourses);
        int[] indegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            if (!map.containsKey(prerequisites[i][1])) {
                map.put(prerequisites[i][1], new ArrayList<>());
            }
            map.get(prerequisites[i][1]).add(prerequisites[i][0]);
            indegree[prerequisites[i][0]]++;
            zeroSet.remove(prerequisites[i][0]);
        }
        List<Integer> list = new ArrayList<>(numCourses);
        while (!zeroSet.isEmpty()) {
            int course = zeroSet.iterator().next();
            zeroSet.remove(course);
            if (map.containsKey(course)) {
                List<Integer> next = map.get(course);
                map.remove(course);
                int size = next.size();
                for (int i = 0; i < size; i++) {
                    int nextCourse = next.get(i);
                    indegree[nextCourse]--;
                    if (indegree[nextCourse] == 0) {
                        zeroSet.add(nextCourse);
                    }
                }
            }
            list.add(course);
        }
        if (list.size() == numCourses) {
            int[] result = new int[numCourses];
            for (int i = 0; i < numCourses; i++) {
                result[i] = list.get(i);
            }
            return result;
        } else {
            return new int[0];
        }
    }
}
