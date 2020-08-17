package com.yglong.leetcode.array;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar
 * is 1, find the area of largest rectangle in the histogram.
 * <p>
 * Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 * <p>
 * For example, Given height = [2,1,5,6,2,3], return 10.
 */
public class LargestRectangle {

    /**
     * 暴力解法1
     *
     * @param a
     * @return
     */
    public static int findLargestRectangle(int[] a) {
        int res = 0;
        for (int i = 0; i < a.length; i++) {
            for (int height = 1; height <= a[i]; height++) {
                int area = height;
                if (i < a.length - 1) {
                    int j = i + 1;
                    while (j < a.length) {
                        if (a[j] >= height) {
                            area += height;
                            j++;
                        } else {
                            break;
                        }
                    }
                }

                if (i > 0) {
                    int j = i - 1;
                    while (j > 0) {
                        if (a[j] >= height) {
                            area += height;
                            j--;
                        } else {
                            break;
                        }
                    }
                }
                if (area > res) {
                    res = area;
                }
            }
        }

        return res;
    }

    /**
     * 暴力解法2
     *
     * @param a
     * @return
     */
    public static int findLargestRectangle2(int[] a) {
        int res = 0;
        for (int i = 0; i < a.length; i++) {
            int area = a[i];
            if (i < a.length - 1) {
                int j = i + 1;
                while (j < a.length) {
                    if (a[j] >= a[i]) {
                        area += a[i];
                        j++;
                    } else {
                        break;
                    }
                }
            }

            if (i > 0) {
                int j = i - 1;
                while (j > 0) {
                    if (a[j] >= a[i]) {
                        area += a[i];
                        j--;
                    } else {
                        break;
                    }
                }
            }
            if (area > res) {
                res = area;
            }
        }
        return res;
    }

    /**
     * 优化解法，利用stack
     *
     * @return
     */
    public static int findLargestRectangle3(int[] a) {
        a = Arrays.copyOf(a, a.length + 1);
        a[a.length - 1] = 0;

        Stack<Integer> stack = new Stack<>();

        int res = 0;
        int i = 0;
        while (i < a.length) {
            if (stack.empty() || a[i] > a[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int t = stack.pop();
                res = Math.max(res, a[t] * (stack.empty() ? i : i - stack.peek() - 1));
            }
        }

        return res;
    }

    public static int largestRectangleArea(int[] heights) {
        int max = 0;

        int[] idxStack = new int[heights.length];

        int idx = -1;
        int[] valueStack = new int[heights.length];

        for (int i = 0; i < heights.length; i++) {
            if (idx == -1 || valueStack[idx] <= heights[i]) {
                idxStack[++idx] = i;
                valueStack[idx] = heights[i];
                continue;
            }
            int j = idx;
            for (; j >= 0; j--) {
                if (valueStack[j] < heights[i]) {
                    break;
                }
                int tmp = valueStack[j] * (i - idxStack[j]);
                max = Math.max(max, tmp);
            }

            max = Math.max(max, heights[i] * (i - idxStack[j + 1] + 1));
            idx = j + 1;
            valueStack[idx] = heights[i];
        }
        int j = idx;
        for (; j >= 0; j--) {
            int tmp = valueStack[j] * (heights.length - idxStack[j]);
            max = Math.max(max, tmp);
        }
        return max;
    }

    public static int largestRectangleArea2(int[] heights) {
        Stack<Integer> heightStack = new Stack<>();
        heights = Arrays.copyOf(heights, heights.length + 1);
        heights[heights.length - 1] = 0;
        int max = 0;
        for (int h : heights) {
            if (heightStack.isEmpty() || heightStack.peek() <= h) {
                heightStack.push(h);
            } else {
                int index = 1;
                while (!heightStack.empty() && heightStack.peek() > h) {
                    max = Math.max(max, heightStack.pop() * index++);
                }
                for (int i = 0; i < index; i++) {
                    heightStack.push(h);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(findLargestRectangle(new int[]{1, 1, 1, 1, 1, 2, 1, 5, 6, 2, 3, 1, 1, 1, 1, 1, 1, 1}));
        System.out.println(findLargestRectangle2(new int[]{1, 1, 1, 1, 1, 2, 1, 5, 6, 2, 3, 1, 1, 1, 1, 1, 1, 1}));
        System.out.println(findLargestRectangle3(new int[]{1, 1, 1, 1, 1, 2, 1, 5, 6, 2, 3, 1, 1, 1, 1, 1, 1, 1}));
        System.out.println(largestRectangleArea(new int[]{1, 1, 1, 1, 1, 2, 1, 5, 6, 2, 3, 1, 1, 1, 1, 1, 1, 1}));
        System.out.println(largestRectangleArea2(new int[]{1, 1, 1, 1, 1, 2, 1, 5, 6, 2, 3, 1, 1, 1, 1, 1, 1, 1}));
    }
}

