package com.shuangpeng.Problem.p0901_1000;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Problem0911OnlineElection {

    class TopVotedCandidate {

        Map<Integer, Integer> personMap;
        TreeMap<Integer, Integer> voteMap;
        int[] mostVote;
        int[] times;

        public TopVotedCandidate(int[] persons, int[] times) {
            personMap = new HashMap<>();
            voteMap = new TreeMap<>();
            this.times = times;
            int n = persons.length;
            mostVote = new int[n];
            for (int i = 0; i < n; ++i) {
                int person = persons[i];
                int vote = personMap.getOrDefault(person, 0) + 1;
                personMap.put(person, vote);
                voteMap.put(vote, person);
                mostVote[i] = voteMap.lastEntry().getValue();
            }
        }

        public int q(int t) {
            int left = 0, right = times.length - 1;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                int time = times[mid];
                if (time < t) {
                    left = mid + 1;
                } else if (time > t) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                    break;
                }
            }
            return mostVote[left - 1];
        }
    }
}
