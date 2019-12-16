package com.chanpay.service.api.util;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-16 14:47
 * @Description:
 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
@Slf4j
public class DataUtil {
    private static DataUtil dataUtil = new DataUtil();
    private static FastDateFormat dateFormat = FastDateFormat.getInstance("yyyy-MM-dd");
    private static FastDateFormat timestampFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");

    private DataUtil() {
    }

    public static DataUtil getInstance() {
        return dataUtil;
    }

    public static Integer toInteger(Object obj) {
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception var2) {
            return 0;
        }
    }

    public static Integer toInteger(Object obj, Integer defVal) {
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception var3) {
            return defVal;
        }
    }

    public static Long toLong(Object obj) {
        try {
            return Long.parseLong(obj.toString());
        } catch (Exception var2) {
            return 0L;
        }
    }

    public static Long toLong(Object obj, Long defVal) {
        try {
            return Long.parseLong(obj.toString());
        } catch (Exception var3) {
            return defVal;
        }
    }

    public static boolean toBoolean(Object obj) {
        try {
            return Boolean.parseBoolean(obj.toString());
        } catch (Exception var2) {
            return false;
        }
    }

    public static Double toDouble(Object obj) {
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception var2) {
            return 0.0D;
        }
    }

    public static Double toDouble(Object obj, Double defVal) {
        try {
            return Double.parseDouble(obj.toString());
        } catch (Exception var3) {
            return defVal;
        }
    }

    public static String[] toStringArray(List<?> list) {
        List<String> strList = new ArrayList();
        Iterator<?> originIt = list.iterator();
        Object o = null;

        while(originIt.hasNext()) {
            o = originIt.next();
            if (null != o) {
                strList.add(o.toString());
            } else {
                strList.add("");
            }
        }

        return (String[])Arrays.copyOf(strList.toArray(), strList.size(), String[].class);
    }

    public static List<Integer> toIntegerList(List<String> list) {
        if (list != null && !list.isEmpty()) {
            ArrayList intList = new ArrayList();

            try {
                Iterator var2 = list.iterator();

                while(var2.hasNext()) {
                    String element = (String)var2.next();
                    intList.add(Integer.parseInt(element));
                }

                return intList;
            } catch (Exception var4) {
                return Collections.emptyList();
            }
        } else {
            return Collections.emptyList();
        }
    }

    public static Date toDate(String dateStr) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            return sf.parse(dateStr);
        } catch (Exception var3) {
            return null;
        }
    }

    public static String toDateString(Date date) throws ParseException {
        return dateFormat.format(date);
    }

    public static String toDateTimeString(Date date) throws ParseException {
        return timestampFormat.format(date);
    }

    public static String formatDateTime(Long timestamp) {
        try {
            String result = null;
            Date date = new Date(timestamp);
            result = timestampFormat.format(date);
            return result;
        } catch (Exception var3) {
            log.warn("error format date", var3.getMessage());
            return "";
        }
    }

    public static Date toDateTime(String dateStr) throws ParseException {
        return timestampFormat.parse(dateStr);
    }

    public String Object2String(Object obj, String defaultVal) {
        if (null == obj) {
            return "";
        } else {
            try {
                return String.valueOf(obj);
            } catch (Exception var4) {
                return defaultVal;
            }
        }
    }

    public String Object2String(Object obj) {
        if (null == obj) {
            return "";
        } else {
            try {
                return String.valueOf(obj);
            } catch (Exception var3) {
                return "";
            }
        }
    }

    public static boolean isBlank(Object o) {
        return null == o ? true : StringUtils.isBlank(o.toString());
    }

    public static String replaceJavaVarNameNotSupportChar(String fieldName) {
        return ("m_" + fieldName).replaceAll("[^a-zA-Z0-9_]", "");
    }

    public static String decodeUnicode(String theString) {
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        int x = 0;

        while(true) {
            while(true) {
                while(x < len) {
                    char aChar = theString.charAt(x++);
                    if (aChar == '\\') {
                        aChar = theString.charAt(x++);
                        if (aChar == 'u') {
                            int value = 0;

                            for(int i = 0; i < 4; ++i) {
                                aChar = theString.charAt(x++);
                                switch(aChar) {
                                    case '0':
                                    case '1':
                                    case '2':
                                    case '3':
                                    case '4':
                                    case '5':
                                    case '6':
                                    case '7':
                                    case '8':
                                    case '9':
                                        value = (value << 4) + aChar - 48;
                                        break;
                                    case ':':
                                    case ';':
                                    case '<':
                                    case '=':
                                    case '>':
                                    case '?':
                                    case '@':
                                    case 'G':
                                    case 'H':
                                    case 'I':
                                    case 'J':
                                    case 'K':
                                    case 'L':
                                    case 'M':
                                    case 'N':
                                    case 'O':
                                    case 'P':
                                    case 'Q':
                                    case 'R':
                                    case 'S':
                                    case 'T':
                                    case 'U':
                                    case 'V':
                                    case 'W':
                                    case 'X':
                                    case 'Y':
                                    case 'Z':
                                    case '[':
                                    case '\\':
                                    case ']':
                                    case '^':
                                    case '_':
                                    case '`':
                                    default:
                                        throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
                                    case 'A':
                                    case 'B':
                                    case 'C':
                                    case 'D':
                                    case 'E':
                                    case 'F':
                                        value = (value << 4) + 10 + aChar - 65;
                                        break;
                                    case 'a':
                                    case 'b':
                                    case 'c':
                                    case 'd':
                                    case 'e':
                                    case 'f':
                                        value = (value << 4) + 10 + aChar - 97;
                                }
                            }

                            outBuffer.append((char)value);
                        } else if (aChar != 'x' && aChar != 'X') {
                            if (aChar == 't') {
                                aChar = '\t';
                            } else if (aChar == 'r') {
                                aChar = '\r';
                            } else if (aChar == 'n') {
                                aChar = '\n';
                            } else if (aChar == 'f') {
                                aChar = '\f';
                            }

                            outBuffer.append(aChar);
                        } else {
                            x += 2;
                        }
                    } else {
                        outBuffer.append(aChar);
                    }
                }

                return outBuffer.toString();
            }
        }
    }

    public static String StringFilter(String str) throws PatternSyntaxException {
        String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    public static List<Double> sortListDoubles(List<Double> list, boolean isDesc) {
        if (isDesc) {
            Collections.sort(list, new Comparator<Double>() {
                public int compare(Double o1, Double o2) {
                    if (o1 < o2) {
                        return 1;
                    } else {
                        return o1 > o2 ? -1 : 0;
                    }
                }
            });
        } else {
            Collections.sort(list, new Comparator<Double>() {
                public int compare(Double o1, Double o2) {
                    if (o1 > o2) {
                        return 1;
                    } else {
                        return o1 < o2 ? -1 : 0;
                    }
                }
            });
        }

        return list;
    }

    public static String decodeUnicode2(String dataStr) {
        StringBuffer buffer = new StringBuffer();
        String tempStr = "";
        String operStr = dataStr;
        if (dataStr != null && dataStr.indexOf("\\u") == -1) {
            return buffer.append(dataStr).toString();
        } else {
            if (dataStr != null && !dataStr.equals("") && !dataStr.startsWith("\\u")) {
                tempStr = dataStr.substring(0, dataStr.indexOf("\\u"));
                operStr = dataStr.substring(dataStr.indexOf("\\u"), dataStr.length());
            }

            buffer.append(tempStr);

            while(operStr != null && !operStr.equals("") && operStr.startsWith("\\u")) {
                tempStr = operStr.substring(0, 6);
                operStr = operStr.substring(6, operStr.length());
                String charStr = "";
                charStr = tempStr.substring(2, tempStr.length());
                char letter = (char)Integer.parseInt(charStr, 16);
                buffer.append((new Character(letter)).toString());
                if (operStr.indexOf("\\u") == -1) {
                    buffer.append(operStr);
                } else {
                    tempStr = operStr.substring(0, operStr.indexOf("\\u"));
                    operStr = operStr.substring(operStr.indexOf("\\u"), operStr.length());
                    buffer.append(tempStr);
                }
            }

            return buffer.toString();
        }
    }

    public static String escapeSingleQuotes(String text) {
        return StringUtils.isBlank(text) ? "" : text.replaceAll("'", "''");
    }

    public static Timestamp getNowTime() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return now;
    }

    public static String getMethodName(String s) {
        StringBuilder str = new StringBuilder();
        str.append("set");
        str.append(s.substring(0, 1).toUpperCase()).append(s.substring(1));
        return str.toString();
    }

    public static String md5(String src) {
        if (StringUtils.isNotBlank(src)) {
            try {
                MessageDigest m = MessageDigest.getInstance("MD5");
                m.reset();
                m.update(src.getBytes());
                byte[] digest = m.digest();
                BigInteger bigInt = new BigInteger(1, digest);

                String hashtext;
                for(hashtext = bigInt.toString(16); hashtext.length() < 32; hashtext = "0" + hashtext) {
                }

                return hashtext;
            } catch (NoSuchAlgorithmException var5) {
                log.error(var5.getMessage(), var5);
            }
        }

        return null;
    }

    public static String quote(String x) {
        return "\"" + StringEscapeUtils.escapeJava(x) + "\"";
    }

    public static String getAdminSystemKey(String name, String env) {
        return StringUtils.upperCase(env) + "_" + StringUtils.upperCase(name);
    }

    public static String toDateTimeString(long timeMillis) throws ParseException {
        return toDateTimeString(new Date(timeMillis));
    }

    public static String escapeHtml4(String message) {
        return StringEscapeUtils.escapeHtml4(message);
    }

    public static List<String> jsonStrToList(String jsonStr) {
        List<String> list = new ArrayList();
        if (StringUtils.isNotBlank(jsonStr)) {
            if (jsonStr.contains("[")) {
                try {
                    list = JSON.parseArray(jsonStr, String.class);
                } catch (Exception var3) {
                    log.error(var3.getMessage(), var3);
                }
            } else {
                ((List)list).add(jsonStr);
            }
        }

        return (List)list;
    }

    public static JSONArray toJSONArray(List<HashMap<String, String>> list) {
        JSONArray jsonArray = new JSONArray();
        new JSONObject();
        Iterator var3 = list.iterator();

        while(var3.hasNext()) {
            Map<String, String> map = (Map)var3.next();
            JSONObject jsonObject = mapToJson(map);
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }

    public static JSONObject mapToJson(Map<String, String> map) {
        JSONObject json = new JSONObject();
        Set<String> keySet = map.keySet();
        Iterator var3 = keySet.iterator();

        while(var3.hasNext()) {
            String key = (String)var3.next();
            json.put(key, map.get(key));
        }

        return json;
    }

    public static String defaultBlank(String value, String defaultValue) {
        return StringUtils.isBlank(value) ? defaultValue : value;
    }

    public static <T> T findEqualsItemByFieldName(Collection<T> items, Class<T> clazz, String property, Object equalValue) {
        Iterator var4 = items.iterator();

        Object t;
        do {
            if (!var4.hasNext()) {
                return null;
            }

            t = var4.next();
        } while(!isEqualsByFieldName(t, property, equalValue));

        return (T) t;
    }

    public static <T> Object getValue(T t, Class<T> clazz, String fieldName) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(clazz, fieldName);
        return descriptor == null ? null : descriptor.getReadMethod().invoke(t);
    }

    public static boolean isEqualsByFieldName(Object target, String fieldName, Object value) {
        try {
            PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(target.getClass(), fieldName);
            if (descriptor == null) {
                return false;
            } else {
                Object obj = descriptor.getReadMethod().invoke(target);
                if (obj == value) {
                    return true;
                } else {
                    return null != obj && obj.equals(value);
                }
            }
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | BeansException var5) {
            throw new RuntimeException("error occured when find equals object values", var5);
        }
    }

    public static boolean validateEmails(String[] emails) {
        if (emails != null && emails.length != 0) {
            String[] var1 = emails;
            int var2 = emails.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                String email = var1[var3];
                if (!validateEmail(email)) {
                    return false;
                }
            }

            return true;
        } else {
            throw new IllegalArgumentException("the method of validateEmails occur illegal argument, detail : emails is null or empty");
        }
    }

    public static boolean validateEmail(String email) {
        try {
            if (StringUtils.isEmpty(email)) {
                throw new IllegalArgumentException("the method of validateEmail occur illegal argument, detail : email is null or empty");
            } else {
                String EMAIL_REGEX = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
                email = email.trim();
                return email.matches(EMAIL_REGEX);
            }
        } catch (PatternSyntaxException var2) {
            log.error("the method of validateEmail catch PatternSyntaxException.detail:" + var2.getMessage());
            return false;
        }
    }

    public static boolean validateHasRepeatArray(String[] arrays) {
        if (arrays == null) {
            log.error("the mothod of validateHasRepeatArray occur illegal argument,detail:argument is null or empty");
            return false;
        } else if (arrays.length == 0) {
            return true;
        } else {
            Set<String> set = new HashSet();
            String[] var2 = arrays;
            int var3 = arrays.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                String entry = var2[var4];
                if (set.contains(entry)) {
                    return true;
                }

                set.add(entry);
            }

            return false;
        }
    }

    public static Map<String, Object> convertBean(Object bean) throws Exception {
        Class type = bean.getClass();
        Map<String, Object> returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        for(int i = 0; i < propertyDescriptors.length; ++i) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!propertyName.equals("class")) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }

        return returnMap;
    }

}
