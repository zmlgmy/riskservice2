package com.chanpay.service.api.util;

/**
 * @Author: liweiei
 * @CreateTime: 2019-12-16 14:46
 * @Description:
 */

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Calendar;
import java.util.UUID;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Slf4j
public class SecretKeyUtil {
    private static final String ENCRYPT_STR = "@*7hJeL:3;Q\\/";
    private static final String SEPARATOR = "@#%";

    public SecretKeyUtil() {
    }

    public static String encrypt(String plaintext, String key) {
        String ciphertext = null;
        if (plaintext != null && key != null) {
            try {
                byte[] byteContent = plaintext.getBytes("utf-8");
                byte[] digest = digest(byteContent, key, true);
                if (digest != null) {
                    ciphertext = parseByte2HexStr(digest);
                }
            } catch (UnsupportedEncodingException var5) {
                log.error(var5.getMessage(), var5);
            }
        }

        return ciphertext;
    }

    public static String decrypt(String ciphertext, String key) {
        String plaintext = null;
        if (ciphertext != null && key != null) {
            byte[] byteContent = parseHexStr2Byte(ciphertext);
            byte[] digest = digest(byteContent, key, false);
            if (digest != null) {
                plaintext = new String(digest);
            }
        }

        return plaintext;
    }

    private static String parseByte2HexStr(byte[] bytes) {
        if (bytes != null && bytes.length > 0) {
            StringBuffer sb = new StringBuffer();

            for(int i = 0; i < bytes.length; ++i) {
                String hex = Integer.toHexString(bytes[i]);
                if (hex.length() > 2) {
                    hex = hex.substring(hex.length() - 2);
                }

                if (hex.length() == 1) {
                    sb.append("0");
                }

                sb.append(hex);
            }

            return sb.toString();
        } else {
            return null;
        }
    }

    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr == null) {
            return null;
        } else {
            byte[] result = new byte[hexStr.length() / 2];

            for(int i = 0; i < hexStr.length() / 2; ++i) {
                int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
                result[i] = (byte)(high * 16 + low);
            }

            return result;
        }
    }

    private static byte[] digest(byte[] byteContent, String key, boolean encrypt) {
        byte[] digest = null;

        try {
            byte[] kb = key.getBytes("UTF-8");
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            kb = sha.digest(kb);
            kb = Arrays.copyOf(kb, 16);
            SecretKeySpec secretKeySpec = new SecretKeySpec(kb, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            int mode = encrypt ? 1 : 2;
            cipher.init(mode, secretKeySpec);
            digest = cipher.doFinal(byteContent);
        } catch (BadPaddingException var9) {
            log.warn("error occured when digest:{}", var9.getMessage());
        } catch (Exception var10) {
            log.error("error occured when digest", var10);
        }

        return digest;
    }

    public static String generateSecureCode(String loginId, int validMinutes) {
        if (StringUtils.isBlank(loginId)) {
            throw new IllegalArgumentException("loginId must not null");
        } else if (validMinutes <= 0) {
            throw new IllegalArgumentException("validMinutes must not >0");
        } else {
            Calendar cal = Calendar.getInstance();
            cal.set(12, cal.get(12) + validMinutes);
            StringBuilder result = new StringBuilder();
            result.append(encrypt(loginId, "@*7hJeL:3;Q\\/")).append("@#%");
            result.append(UUID.randomUUID().toString()).append("@#%").append(encrypt(String.valueOf(cal.getTimeInMillis()), loginId));
            return encrypt(result.toString(), "@*7hJeL:3;Q\\/");
        }
    }

    public static SecretKeyUtil.EDecryptSecureCodeResult decryptSecureCode(String secureCode) {
        if (StringUtils.isBlank(secureCode)) {
            return SecretKeyUtil.EDecryptSecureCodeResult.INVALID_SECURE_CODE;
        } else {
            String[] codes = StringUtils.split(decrypt(secureCode, "@*7hJeL:3;Q\\/"), "@#%");
            if (codes.length != 3) {
                return SecretKeyUtil.EDecryptSecureCodeResult.INVALID_SECURE_CODE;
            } else {
                String loginId = decrypt(codes[0], "@*7hJeL:3;Q\\/");
                long timestamp = DataUtil.toLong(decrypt(codes[2], loginId), -1L);
                if (-1L == timestamp) {
                    return SecretKeyUtil.EDecryptSecureCodeResult.INVALID_SECURE_CODE;
                } else {
                    try {
                        UUID.fromString(codes[1]);
                    } catch (Exception var6) {
                        return SecretKeyUtil.EDecryptSecureCodeResult.INVALID_SECURE_CODE;
                    }

                    SecretKeyUtil.EDecryptSecureCodeResult result;
                    if (timestamp < System.currentTimeMillis()) {
                        result = SecretKeyUtil.EDecryptSecureCodeResult.EXPIRED;
                        result.setLoginId(loginId);
                        return result;
                    } else {
                        result = SecretKeyUtil.EDecryptSecureCodeResult.OK;
                        result.setLoginId(loginId);
                        return result;
                    }
                }
            }
        }
    }


    public static enum EDecryptSecureCodeResult {
        OK("成功"),
        INVALID_SECURE_CODE("无效链接"),
        EXPIRED("已过有效期");

        private String displayName;
        private String loginId;

        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }

        public String getLoginId() {
            return this.loginId;
        }

        private EDecryptSecureCodeResult(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() {
            return this.displayName;
        }

        public String toString() {
            return this.displayName + (this.loginId == null ? "" : this.loginId);
        }
    }
}
