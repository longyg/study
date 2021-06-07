package com.yglong.algorithm.dynamicprogramming;

/**
 * 背包问题
 * <p>
 * 找出将物品装入背包的最优方案，使得物品总价值最大
 * <p>
 * 背包容量固定，每种物品占用一定容量，且价值不一样
 */
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] capacities = new int[4];
        for (int i = 0; i < 4; i++) {
            capacities[i] = i + 1;
        }
        Item[] items = new Item[]{
                new Item("gita", 1, 1500),
                new Item("yinxiang", 4, 3000),
                new Item("computer", 3, 2000),
                new Item("iphone", 1, 2000),
                new Item("mp3", 1, 1000)
        };

        Item[] best = findBest(items, capacities);
        int total = 0;
        for (Item item : best) {
            System.out.println(item.name + ":" + item.price + ":" + item.capacity);
            total += item.price;
        }
        System.out.println("total: " + total);
    }

    public static Item[] findBest(Item[] items, int[] capacities) {
        ItemValue[][] dp = new ItemValue[items.length][capacities.length];
        for (int i = 0; i < items.length; i++) { // 行
            for (int j = 0; j < capacities.length; j++) { //列
                Item item = items[i];
                int c = capacities[j];

                if (item.capacity > c) { //背包容量比物品体积小
                    if (i - 1 < 0) {
                        dp[i][j] = new ItemValue(new Item[0], 0);
                    } else {
                        dp[i][j] = dp[i - 1][j];
                    }

                } else { //背包容量比物品体积大或相等
                    if (i - 1 < 0) {
                        dp[i][j] = new ItemValue(new Item[]{item}, item.price);
                    } else {
                        ItemValue prevMax = dp[i - 1][j];
                        int newAmount = item.price;
                        Item[] prevItems = new Item[0];
                        int leftCap = c - item.capacity;
                        if (leftCap > 0) {
                            ItemValue prevLeft = dp[i - 1][leftCap - 1];
                            newAmount += prevLeft.amount;
                            prevItems = prevLeft.items;
                        }
                        if (prevMax.amount < newAmount) {
                            dp[i][j] = new ItemValue(combine(prevItems, item), newAmount);
                        } else {
                            dp[i][j] = prevMax;
                        }
                    }
                }
            }
        }
        return dp[items.length - 1][capacities.length - 1].items;
    }

    private static Item[] combine(Item[] a, Item b) {
        Item[] ret = new Item[a.length + 1];
        int n = 0;
        for (Item item : a) {
            ret[n++] = item;
        }
        ret[n] = b;
        return ret;
    }

    private static class Item {
        String name;
        Integer capacity;
        Integer price;

        public Item(String name, Integer capacity, Integer price) {
            this.name = name;
            this.capacity = capacity;
            this.price = price;
        }
    }

    private static class ItemValue {
        Item[] items;
        Integer amount;

        public ItemValue(Item[] items, Integer amount) {
            this.items = items;
            this.amount = amount;
        }
    }
}
