package com.chanpay.service.api.util;

/**
* @Author: liweiei
* @CreateTime: 2019-12-12 19:50
* @Description:
*/


public class StringUtils {
    public StringUtils() {
    }

    public static String upperCaseFirstChar(String x) {
    if (x == null) {
    return null;
    } else if (x.isEmpty()) {
    return "";
    } else {
    String firstChar = x.substring(0, 1);
    String exceptFirst = x.substring(1);
    return firstChar.toUpperCase() + exceptFirst;
    }
    }

    public static String lowerCaseFirstChar(String x) {
    if (x == null) {
    return null;
    } else if (x.isEmpty()) {
    return "";
    } else {
    String firstChar = x.substring(0, 1);
    String exceptFirst = x.substring(1);
    return firstChar.toLowerCase() + exceptFirst;
    }
    }

    public static String generateRandomStr(int length, boolean onlyNumber) {
    StringBuffer sb = new StringBuffer();
    String[] condicateChars = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    int i = 0;

    while(true) {
    String str;
    do {
    if (i >= length) {
    return sb.toString();
    }

    int len = onlyNumber ? 10 : condicateChars.length;
    int index = (int)(Math.random() * (double)len);
    str = condicateChars[index];
    } while(!onlyNumber && ("1".equals(str) || "i".equals(str) || "l".equals(str) || "0".equals(str) || "O".equals(str)));

    sb.append(str);
    ++i;
    }
    }

    public static String camel2underline(String str) {
    StringBuffer sb = new StringBuffer();
    if (str != null) {
    for(int i = 0; i < str.length(); ++i) {
    char c = str.charAt(i);
    String s = str.substring(i, i + 1);
    if (c >= 'A' && c <= 'Z') {
    sb.append("_").append(s.toLowerCase());
    } else {
    sb.append(s);
    }
    }
    }

    return sb.toString();
    }

    public static String underline2camel(String str) {
    StringBuffer sb = new StringBuffer();
    if (str != null && str.length() > 0) {
    String[] arr = str.split("_");

    for(int i = 0; i < arr.length; ++i) {
    String s = arr[i];
    if (s.length() > 0) {
    if (i > 0) {
    sb.append(s.substring(0, 1).toUpperCase());
    } else {
    sb.append(s.substring(0, 1));
    }

    sb.append(s.substring(1));
    }
    }
    }

    return sb.toString();
    }
}

