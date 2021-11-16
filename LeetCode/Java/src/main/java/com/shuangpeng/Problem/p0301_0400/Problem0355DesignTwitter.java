package com.shuangpeng.Problem.p0301_0400;

import java.util.*;

public class Problem0355DesignTwitter {
}

class Twitter0 {

    Map<Integer, Set<Integer>> followMap;
    Map<Integer, PriorityQueue<int[]>> tweetMap;
    int index;

    class UserInfo {
        int userId;
        PriorityQueue<int[]> queue;

        UserInfo(int userId, PriorityQueue<int[]> queue) {
            this.userId = userId;
            this.queue = queue;
        }
    }

    public Twitter0() {
        followMap = new HashMap<>();
        tweetMap = new HashMap<>();
        index = 0;
    }

    public void postTweet(int userId, int tweetId) {
        tweetMap.putIfAbsent(userId, new PriorityQueue<>((a, b) -> b[0] - a[0]));
        PriorityQueue<int[]> queue = tweetMap.get(userId);
        queue.offer(new int[]{index++, tweetId});
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<UserInfo> queue = new PriorityQueue<>((a, b) -> b.queue.peek()[0] - a.queue.peek()[0]);
        PriorityQueue<int[]> q = tweetMap.get(userId);
        if (q == null) {
            return new ArrayList<>();
        }
        if (!q.isEmpty()) {
            queue.offer(new UserInfo(userId, tweetMap.get(userId)));
        }
        if (followMap.containsKey(userId)) {
            for (int follow : followMap.get(userId)) {
                PriorityQueue<int[]> followQueue = tweetMap.get(follow);
                if (followQueue != null && !followQueue.isEmpty()) {
                    queue.offer(new UserInfo(follow, followQueue));
                }
            }
        }
        int i = 0;
        List<Integer> ans = new ArrayList<>();
        Map<Integer, List<int[]>> deleteMap = new HashMap<>();
        while (!queue.isEmpty() && i < 10) {
            UserInfo userInfo = queue.poll();
            int id = userInfo.userId;
            int[] tweet = userInfo.queue.poll();
            deleteMap.putIfAbsent(id, new ArrayList<>());
            deleteMap.get(id).add(tweet);
            ans.add(tweet[1]);
            if (!userInfo.queue.isEmpty()) {
                queue.offer(userInfo);
            }
            ++i;
        }
        for (Map.Entry<Integer, List<int[]>> entry : deleteMap.entrySet()) {
            for (int[] pair : entry.getValue()) {
                tweetMap.get(entry.getKey()).offer(pair);
            }
        }
        return ans;
    }

    public void follow(int followerId, int followeeId) {
        tweetMap.putIfAbsent(followerId, new PriorityQueue<>((a, b) -> b[0] - a[0]));
        followMap.putIfAbsent(followerId, new HashSet<>());
        followMap.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        Set<Integer> set = followMap.get(followerId);
        if (set != null) {
            set.remove(followeeId);
        }
    }
}

class Twitter {

    class UserInfo {
        Set<Integer> followees;
        LinkedList<Integer> tweets;

        UserInfo() {
            followees = new HashSet<>();
            tweets = new LinkedList<>();
        }
    }

    Map<Integer, UserInfo> users;
    Map<Integer, Integer> timeMap;
    int time;
    int recentMax;

    public Twitter() {
        users = new HashMap<>();
        timeMap = new HashMap<>();
        time = 0;
        recentMax = 10;
    }

    public void init(int userId) {
        users.putIfAbsent(userId, new UserInfo());
    }

    public void postTweet(int userId, int tweetId) {
        init(userId);
        LinkedList<Integer> tweets = users.get(userId).tweets;
        if (tweets.size() == recentMax) {
            tweets.remove(recentMax - 1);
        }
        tweets.addFirst(tweetId);
        timeMap.put(tweetId, ++time);
    }

    public List<Integer> getNewsFeed(int userId) {
        init(userId);
        UserInfo user = users.get(userId);
        LinkedList<Integer> ans = user.tweets;
        for (int followee : user.followees) {
            if (followee == userId) {
                continue;
            }
            LinkedList<Integer> res = new LinkedList<>();
            LinkedList<Integer> tweets = users.get(followee).tweets;
            int i = 0, j = 0, cur = 0;
            while (i < ans.size() && j < tweets.size() && cur < recentMax) {
                if (timeMap.get(ans.get(i)) > timeMap.get(tweets.get(j))) {
                    res.add(ans.get(i));
                    ++i;
                } else {
                    res.add(tweets.get(j));
                    ++j;
                }
                ++cur;
            }
            while (i < ans.size() && cur < recentMax) {
                res.add(ans.get(i));
                ++i;
                ++cur;
            }
            while (j < tweets.size() && cur < recentMax) {
                res.add(tweets.get(j));
                ++j;
                ++cur;
            }
            ans = new LinkedList<>(res);
        }
        return ans;
    }

    public void follow(int followerId, int followeeId) {
        init(followerId);
        init(followeeId);
        users.get(followerId).followees.add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        init(followerId);
        init(followeeId);
        users.get(followerId).followees.remove(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
