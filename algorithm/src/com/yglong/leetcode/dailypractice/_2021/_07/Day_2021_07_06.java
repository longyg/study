package com.yglong.leetcode.dailypractice._2021._07;

import java.util.*;

/**
 * 1418. 点菜展示表
 * <p>
 * <p>
 * 给你一个数组 orders，表示客户在餐厅中完成的订单，确切地说， orders[i]=[customerNamei,tableNumberi,foodItemi] ，
 * 其中 customerNamei 是客户的姓名，tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。
 * <p>
 * 请你返回该餐厅的 点菜展示表 。在这张表中，表中第一行为标题，其第一列为餐桌桌号 “Table” ，后面每一列都是按字母顺序排列的餐品名称。
 * 接下来每一行中的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。
 * <p>
 * 注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：orders = [["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David","3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","Ceviche"]]
 * <p>
 * 输出：[["Table","Beef Burrito","Ceviche","Fried Chicken","Water"],["3","0","2","1","0"],["5","0","1","0","1"],["10","1","0","0","0"]]
 * <p>
 * 解释：
 * <p>
 * 点菜展示表如下所示：
 * <p>
 * Table,Beef Burrito,Ceviche,Fried Chicken,Water
 * <p>
 * 3    ,0           ,2      ,1            ,0
 * <p>
 * 5    ,0           ,1      ,0            ,1
 * <p>
 * 10   ,1           ,0      ,0            ,0
 * <p>
 * 对于餐桌 3：David 点了 "Ceviche" 和 "Fried Chicken"，而 Rous 点了 "Ceviche"
 * <p>
 * 而餐桌 5：Carla 点了 "Water" 和 "Ceviche"
 * <p>
 * 餐桌 10：Corina 点了 "Beef Burrito"
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/display-table-of-food-orders-in-a-restaurant
 */
public class Day_2021_07_06 {
    public static void main(String[] args) {
        List<List<String>> orders = new ArrayList<>();
        orders.add(Arrays.asList("David", "3", "Ceviche"));
        orders.add(Arrays.asList("Corina", "10", "Beef Burrito"));
        orders.add(Arrays.asList("David", "3", "Fried Chicken"));
        orders.add(Arrays.asList("Carla", "5", "Water"));
        orders.add(Arrays.asList("Carla", "5", "Ceviche"));
        orders.add(Arrays.asList("Rous", "3", "Ceviche"));
        displayTable2(orders);
    }

    /**
     * TreeMap解法
     */
    public static List<List<String>> displayTable(List<List<String>> orders) {

        Map<Integer, Map<String, Integer>> m = new TreeMap<>();

        List<String> menus = new ArrayList<>();
        for (List<String> order : orders) {
            String menu = order.get(2);
            if (!menus.contains(menu)) {
                menus.add(menu);
            }
        }

        for (List<String> order : orders) {
            Integer table = Integer.parseInt(order.get(1));
            String name = order.get(2);

            Map<String, Integer> o = m.getOrDefault(table, new TreeMap<>());
            o.put(name, o.getOrDefault(name, 0) + 1);
            m.put(table, o);
        }

        for (String menu : menus) {
            for (Map<String, Integer> o : m.values()) {
                if (!o.containsKey(menu)) {
                    o.put(menu, 0);
                }
            }
        }

        Collections.sort(menus);

        List<List<String>> ret = new ArrayList<>();
        List<String> title = new ArrayList<>();
        title.add("Table");
        title.addAll(menus);
        ret.add(title);

        for (Map.Entry<Integer, Map<String, Integer>> entry : m.entrySet()) {
            List<String> row = new ArrayList<>();
            row.add(entry.getKey().toString());
            for (Integer v : entry.getValue().values()) {
                row.add(v.toString());
            }
            ret.add(row);
        }

        return ret;
    }

    /**
     * HashMap 解法
     */
    public static List<List<String>> displayTable2(List<List<String>> orders) {
        Map<Integer, Map<String, Integer>> m = new TreeMap<>();
        Set<String> menus = new HashSet<>();
        for (List<String> order : orders) {
            int table = Integer.parseInt(order.get(1));
            String menu = order.get(2);
            menus.add(menu);
            Map<String, Integer> o = m.getOrDefault(table, new HashMap<>());
            o.put(menu, o.getOrDefault(menu, 0) + 1);
            m.put(table, o);
        }

        int menuNumber = menus.size();
        List<String> mList = new ArrayList<>();
        for (String menu : menus) {
            mList.add(menu);
        }
        Collections.sort(mList);

        List<Integer> tableIds = new ArrayList<>();
        int tableNumber = m.size();
        for (Integer id : m.keySet()) {
            tableIds.add(id);
        }
        Collections.sort(tableIds);

        List<List<String>> ret = new ArrayList<>();
        List<String> header = new ArrayList<>();
        header.add("Table");
        header.addAll(mList);
        ret.add(header);

        for (int i = 0; i < tableNumber; i++) {
            int tableId = tableIds.get(i);
            List<String> row = new ArrayList<>();
            row.add(Integer.toString(tableId));
            Map<String, Integer> o = m.get(tableId);
            for (int j = 0; j < menuNumber; j++) {
                String menu = mList.get(j);
                Integer num = o.getOrDefault(menu, 0);
                row.add(Integer.toString(num));
            }
            ret.add(row);
        }
        return ret;
    }
}
