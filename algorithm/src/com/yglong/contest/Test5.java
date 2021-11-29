package com.yglong.contest;

/**
 * 计算算术表达式的值
 */
public class Test5 {

    // 3*(2+1)
    public int calculate(String str) {
        char[] cs = str.toCharArray();
        int n = cs.length;
        int i = 0;
        int ans = 0;
        while (i < n) {
            char c = cs[i];
            if (c == '(') {

            } else if (c == ')') {

            } else if (c == '*' || c == '/') {

            } else if (c == '+' || c == '-') {

            } else { // 数字

            }
            i++;
        }
        return ans;
    }
}
