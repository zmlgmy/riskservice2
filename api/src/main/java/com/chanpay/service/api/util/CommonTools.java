package com.chanpay.service.api.util;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-16 14:44
 * @Description:
 */

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.chanpay.service.api.pojo.FraudContext;
import org.apache.commons.lang3.StringUtils;

public class CommonTools {
    public CommonTools() {
    }

    public static String valNullToAll(String s) {
        return StringUtils.isBlank(s) ? "All" : s;
    }

    public static Map<String, String> encryptedFields(FraudContext ctx, List<String> encryptedFields) {
        if (null != encryptedFields && 0 != encryptedFields.size()) {
            Map<String, String> result = new HashMap();
            Iterator var3 = encryptedFields.iterator();

            while(var3.hasNext()) {
                String encodeFieldKey = (String)var3.next();
                String fieldValue = (String)ctx.get(encodeFieldKey);
                if (StringUtils.isNotBlank(fieldValue)) {
                    String encryptFieldValue = SecretKeyUtil.encrypt(fieldValue, encodeFieldKey);
                    result.put(encodeFieldKey, encryptFieldValue);
                }
            }

            return result;
        } else {
            return null;
        }
    }
}
