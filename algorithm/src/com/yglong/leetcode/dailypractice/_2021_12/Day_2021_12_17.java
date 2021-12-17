package com.yglong.leetcode.dailypractice._2021_12;

public class Day_2021_12_17 {
    public static void main(String[] args) {
        System.out.println(numWaterBottles(15, 4));
    }

    public static int numWaterBottles(int numBottles, int numExchange) {
        int total = 0;
        int emptyNum = 0;
        while (numBottles > 0) {
            numBottles--;
            total++;
            emptyNum++;
            if (emptyNum == numExchange) {
                numBottles++;
                emptyNum = 0;
            }
        }
        return total;
    }
}
