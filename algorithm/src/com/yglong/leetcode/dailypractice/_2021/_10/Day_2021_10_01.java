package com.yglong.leetcode.dailypractice._2021._10;

import java.util.*;

/**
 * 1436. 旅行终点站
 * <p>
 * 难度：简单
 * <p>
 * 链接：https://leetcode-cn.com/problems/destination-city/
 */
public class Day_2021_10_01 {
    public static void main(String[] args) {
        List<List<String>> list = new ArrayList<>();
        list.add(Arrays.asList("London", "New York"));
        list.add(Arrays.asList("New York", "Lima"));
        list.add(Arrays.asList("Lima", "Sao Paulo"));
        System.out.println(destCity(list));
    }

    public static String destCity(List<List<String>> paths) {
        Set<String> set = new HashSet<>();
        for (List<String> path : paths) {
            set.add(path.get(0));
        }
        for (List<String> path : paths) {
            String station = path.get(1);
            if (!set.contains(station)) {
                return station;
            }
        }
        return "";
    }
}
