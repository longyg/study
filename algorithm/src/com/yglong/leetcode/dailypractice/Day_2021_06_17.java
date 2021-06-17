package com.yglong.leetcode.dailypractice;

import java.util.HashMap;
import java.util.Map;

/**
 * 65. 有效数字
 * <p>
 * <p>
 * 有效数字（按顺序）可以分成以下几个部分：
 * <p>
 * <p>
 * 一个 小数 或者 整数
 * <p>
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * <p>
 * <p>
 * 小数（按顺序）可以分成以下几个部分：
 * <p>
 * <p>
 * （可选）一个符号字符（'+' 或 '-'）
 * <p>
 * 下述格式之一：
 * <p>
 * 至少一位数字，后面跟着一个点 '.'
 * <p>
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * <p>
 * 一个点 '.' ，后面跟着至少一位数字
 * <p>
 * <p>
 * 整数（按顺序）可以分成以下几个部分：
 * <p>
 * <p>
 * （可选）一个符号字符（'+' 或 '-'）
 * <p>
 * 至少一位数字
 * <p>
 * <p>
 * 部分有效数字列举如下：
 * <p>
 * ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"]
 * <p>
 * <p>
 * 部分无效数字列举如下：
 * <p>
 * ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"]
 * <p>
 * <p>
 * 给你一个字符串 s ，如果 s 是一个 有效数字 ，请返回 true 。
 * <p>
 * <p>
 * 链接：https://leetcode-cn.com/problems/valid-number
 */
public class Day_2021_06_17 {

    public static void main(String[] args) {
        String[] strs = new String[]{"2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"};
        for (String str : strs) {
            System.out.println(isNumber2(str));
        }
        System.out.println("=================");
        strs = new String[]{"abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"};
        for (String str : strs) {
            System.out.println(isNumber2(str));
        }
    }

    /**
     * 正则表达式
     */
    public static boolean isNumber(String s) {
        String optionalPartRegex = "([eE][+\\-]?\\d+)?";
        String xiaoshuRegex = "^[+\\-]?(\\.\\d+|\\d+\\.|\\d+\\.\\d+)" + optionalPartRegex + "$";
        String zhengshuRegex = "^[+\\-]?\\d+" + optionalPartRegex + "$";
        return s.matches(xiaoshuRegex) || s.matches(zhengshuRegex);
    }

    /**
     * 有限状态自动机
     */
    public static boolean isNumber2(String s) {
        Map<State, Map<CharType, State>> transfer = new HashMap<>();
        Map<CharType, State> initMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
            put(CharType.CHAR_SIGN, State.STATE_INT_SIGN);
        }};

        transfer.put(State.STATE_INITIAL, initMap);

        Map<CharType, State> initSignMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
        }};
        transfer.put(State.STATE_INT_SIGN, initSignMap);

        Map<CharType, State> intMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_POINT, State.STATE_POINT);
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
        }};
        transfer.put(State.STATE_INTEGER, intMap);

        Map<CharType, State> pointMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
        }};
        transfer.put(State.STATE_POINT, pointMap);

        Map<CharType, State> pointWithoutIntMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
        }};
        transfer.put(State.STATE_POINT_WITHOUT_INT, pointWithoutIntMap);

        Map<CharType, State> fractionMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            put(CharType.CHAR_EXP, State.STATE_EXP);
        }};
        transfer.put(State.STATE_FRACTION, fractionMap);

        Map<CharType, State> expMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
            put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
        }};
        transfer.put(State.STATE_EXP, expMap);

        Map<CharType, State> expSignMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
        }};
        transfer.put(State.STATE_EXP_SIGN, expSignMap);

        Map<CharType, State> expNumberMap = new HashMap<CharType, State>() {{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
        }};
        transfer.put(State.STATE_EXP_NUMBER, expNumberMap);

        int length = s.length();
        State state = State.STATE_INITIAL;

        for (int i = 0; i < length; i++) {
            CharType charType = toCharType(s.charAt(i));
            if (!transfer.get(state).containsKey(charType)) {
                return false;
            } else {
                state = transfer.get(state).get(charType);
            }
        }

        return state == State.STATE_INTEGER || state == State.STATE_POINT || state == State.STATE_FRACTION
                || state == State.STATE_EXP_NUMBER || state == State.STATE_END;
    }

    private static CharType toCharType(char ch) {
        if (ch >= '0' && ch <= '9') {
            return CharType.CHAR_NUMBER;
        } else if (ch == 'e' || ch == 'E') {
            return CharType.CHAR_EXP;
        } else if (ch == '.') {
            return CharType.CHAR_POINT;
        } else if (ch == '+' || ch == '-') {
            return CharType.CHAR_SIGN;
        } else {
            return CharType.CHAR_ILLEGAL;
        }
    }

    enum State {
        STATE_INITIAL, //初始状态
        STATE_INT_SIGN, // 符号位 +/-
        STATE_INTEGER, // 整数
        STATE_POINT, // 小数点（左边有整数）
        STATE_POINT_WITHOUT_INT, // 小数点(左边无整数)
        STATE_FRACTION, // 小数部分
        STATE_EXP,  // 指数字符 E/e
        STATE_EXP_SIGN, // 指数符号位 +/-
        STATE_EXP_NUMBER, // 指数数字
        STATE_END //结束状态
    }

    enum CharType {
        CHAR_NUMBER, //数字
        CHAR_EXP, // 指数字符E/e
        CHAR_POINT, // 小数点
        CHAR_SIGN, // +/-符号
        CHAR_ILLEGAL // 非法字符
    }
}
