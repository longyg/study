package com.yglong.leetcode.dailypractice._2021_07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 981. 基于时间的键值存储
 * <p>
 * <p>
 * 创建一个基于时间的键值存储类 TimeMap，它支持下面两个操作：
 * <p>
 * <p>
 * 1. set(string key, string value, int timestamp)
 * <p>
 * 存储键key、值value，以及给定的时间戳timestamp。
 * <p>
 * <p>
 * 2. get(string key, int timestamp)
 * <p>
 * 返回先前调用set(key, value, timestamp_prev)所存储的值，其中timestamp_prev <= timestamp。
 * <p>
 * 如果有多个这样的值，则返回对应最大的timestamp_prev的那个值。
 * <p>
 * 如果没有值，则返回空字符串（""）。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/time-based-key-value-store
 */
public class Day_2021_07_10 {
    public static void main(String[] args) {
        TimeMap timeMap = new TimeMap();
        timeMap.set("love", "high", 10);
        timeMap.set("love", "low", 20);
        System.out.println(timeMap.get("love", 5));
        System.out.println(timeMap.get("love", 10));
        System.out.println(timeMap.get("love", 15));
        System.out.println(timeMap.get("love", 20));
        System.out.println(timeMap.get("love", 25));
    }

    static class TimeMap {
        Map<String, List<Pair>> map = new HashMap<>();

        public TimeMap() {
        }

        static class Pair {
            private final int timestamp;
            private final String value;

            Pair(int timestamp, String value) {
                this.timestamp = timestamp;
                this.value = value;
            }
        }

        public void set(String key, String value, int timestamp) {
            List<Pair> pairs = map.getOrDefault(key, new ArrayList<>());
            pairs.add(new Pair(timestamp, value));
            map.put(key, pairs);
        }

        public String get(String key, int timestamp) {
            if (!map.containsKey(key)) {
                return "";
            }

            List<Pair> pairs = map.get(key);
            // 二分查找
            int i = binarySearch(pairs, timestamp);
            if (i > -1) {
                return pairs.get(i).value;
            }
            return "";
        }

        private int binarySearch(List<Pair> pairs, int t) {
            int n = pairs.size();
            if (pairs.get(n - 1).timestamp <= t) {
                return n - 1;
            }
            if (pairs.get(0).timestamp > t) {
                return -1;
            }
            if (pairs.get(0).timestamp == t) {
                return 0;
            }
            int start = 0;
            int end = n - 1;
            while (start < end) {
                int mid = start + (end - start) / 2;
                if (pairs.get(mid).timestamp < t) {
                    if (mid < end && pairs.get(mid + 1).timestamp > t) {
                        return mid;
                    }
                    start = mid + 1;
                } else {
                    if (mid > 0 && pairs.get(mid - 1).timestamp <= t) {
                        return mid - 1;
                    }
                    end = mid - 1;
                }
            }
            return -1;
        }
    }
}
