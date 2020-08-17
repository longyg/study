package com.yglong.leetcode.array.sum;

import com.yglong.leetcode.array.utils.Utils;

import java.util.*;

/**
 * 根据以上的2Sum, 3Sum, 3Sum Cloest， 还有4Sum，我相信只要认真看完每道题的解法的童鞋，都
 * 会发现一定的规律，相信这时候会有人想，如果变成KSum问题，我们应该如何求解？这是个很好的
 * 想法，下面，我们来看看问题扩展.
 */
public class KSum {

    public static List<List<Integer>> kSum(int[] a, int target, int k) {
        List<List<Integer>> list = new ArrayList<>();
        if (a.length < k) {
            return list;
        }
        Arrays.sort(a);
        return kSum(a, 0, target, k);
    }

    public static List<List<Integer>> kSum(int[] a, int index, int target, int k) {
        List<List<Integer>> res = new ArrayList<>();
        // kSum转化为2Sum问题
        if (k == 2) {
            int i = index;
            int j = a.length - 1;
            while (i < j) {
                int sum = a[i] + a[j];
                if (sum == target) {
                    List<Integer> l = new ArrayList<>();
                    l.add(a[i]);
                    l.add(a[j]);
                    res.add(l);
                    do {
                        i++;
                    } while (i < j && a[i] == a[i - 1]);
                    do {
                        j--;
                    } while (i < j && a[j] == a[j + 1]);
                } else if (sum < target) {
                    i++;
                } else {
                    j--;
                }
            }
        } else {
            for (int i = index; i < a.length - k + 1; i++) {
                if (i > index && a[i] == a[i - 1]) {
                    continue;
                }
                // 递归
                List<List<Integer>> subRes = kSum(a, i + 1, target - a[i], k  - 1);
                if (null != subRes) {
                    for (List<Integer> sub : subRes) {
                        sub.add(0, a[i]);
                    }
                    res.addAll(subRes);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = new int[] {10, -1, -8, -40, -9, 40, 0, 3, -2, 7, 1, 6, 6, -12, 6};
        List<List<Integer>> res1 = kSum(a, 0, 4);
        for (int i = 0; i < res1.size(); i++) {
            System.out.println(Utils.join(res1.get(i), ","));
        }
    }
}
