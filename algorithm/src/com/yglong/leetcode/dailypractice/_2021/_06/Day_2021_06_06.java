package com.yglong.leetcode.dailypractice._2021._06;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * <p>
 * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
 * <p>
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/ones-and-zeroes
 */
public class Day_2021_06_06 {
    public static void main(String[] args) {
        String[] strs = new String[]{"0", "1101", "01", "00111", "1", "10010", "0", "0", "00", "1", "11", "0011"};
        System.out.println(findMaxForm(strs, 6, 3));
    }

    /**
     * 动态规划
     */
    public static int findMaxForm(String[] strs, int m, int n) {
        int length = strs.length;
        int[][][] dp = new int[length + 1][m + 1][n + 1];
        for (int i = 1; i <= length; i++) {
            int[] zerosOnes = getZerosOnes(strs[i - 1]);
            int zeros = zerosOnes[0], ones = zerosOnes[1];
            for (int j = 0; j <= m; j++) {
                for (int k = 0; k <= n; k++) {
                    dp[i][j][k] = dp[i - 1][j][k];
                    if (j >= zeros && k >= ones) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - zeros][k - ones] + 1);
                    }
                }
            }
        }
        return dp[length][m][n];
    }

    public static int[] getZerosOnes(String str) {
        int[] zerosOnes = new int[2];
        int length = str.length();
        for (int i = 0; i < length; i++) {
            zerosOnes[str.charAt(i) - '0']++;
        }
        return zerosOnes;
    }

    /**
     * 暴力法
     */
    public static int findMaxFormExhaustively(String[] strs, int m, int n) {
        if (strs.length < 1) {
            return 0;
        }

        Map<String, Integer> map = new HashMap<>();
        for (String str : strs) {
            map.put(str, getOneCount(str));
        }
        int max = 0;
        for (int i = strs.length; i > 0; i--) {
            if (hasMaxForm(strs, m, n, i, map)) {
                return i;
            }
        }
        return 0;
    }

    private static boolean hasMaxForm(String[] arr, int m, int n, int len, Map<String, Integer> map) {
        return false; // ...
    }

    private static String[] getMaxFromSubArray(String[] arr, int m, int n, Map<String, Integer> map) {
        if (arr.length == 1) {
            if (map.get(arr[0]) <= n) {
                return arr;
            } else {
                return new String[]{};
            }
        }
        int max = 0;
        String[] ret = new String[0];
        for (int i = 0; i < arr.length; i++) {
            String tmp = arr[i];
            String[] subarr = getSubArray(arr, i);
            String[] maxSet = getMaxFromSubArray(subarr, m, n, map);
            String[] maxArr = getMaxSet(maxSet, tmp, m, n, map);
            if (maxArr.length > max) {
                max = maxArr.length;
                ret = maxArr;
            }
        }
        return ret;
    }

    private static String[] getSubArray(String[] arr, int index) {
        String[] newArr = new String[arr.length - 1];
        int n = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != index) {
                newArr[n++] = arr[i];
            }
        }
        return newArr;
    }

    private static String[] getMaxSet(String[] arr, String tmp, int m, int n, Map<String, Integer> map) {
        int oneCount = 0;
        int len = 0;
        for (String s : arr) {
            len += s.length();
            oneCount += map.get(s);
        }
        int tmpOneCount = map.get(tmp);
        if (oneCount + tmpOneCount <= n && ((len - oneCount) + (tmp.length() - tmpOneCount)) <= m) {
            return combine(arr, tmp);
        } else {
            return arr;
        }
    }

    private static String[] combine(String[] arr, String tmp) {
        String[] newArr = new String[arr.length + 1];
        System.arraycopy(arr, 0, newArr, 0, arr.length);
        newArr[arr.length] = tmp;
        return newArr;
    }

    private static int getOneCount(String str) {
        int oneCount = 0;
        for (char aChar : str.toCharArray()) {
            if (aChar == '1') {
                oneCount++;
            }
        }
        return oneCount;
    }
}
