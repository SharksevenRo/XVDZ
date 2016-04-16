package com.xiaov.web.support;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;


/**
 * Cookie工具，cookie里面存的值是一个map，所以需要设置cookieName和key
 * <li>想要设置cookie，先set再save
 * <li>获取cookie，直接get
 * 
 * @author Sharkseven
 *
 */
public class CookieUtil {

    private HttpServletRequest request;

    private String cookieTempName;

    /**
     * 创建cookie工具
     * @param request
     * @param cookieTempName cookie空间
     */
    public CookieUtil(HttpServletRequest request, String cookieTempName) {
        this.request = request;
        this.cookieTempName = cookieTempName;
    }
    /**
     * 创建cookie工具 使用默认空间
     * @param request
     */
    public CookieUtil(HttpServletRequest request) {
        this(request, "__default_temp_cookie__");
    }

    /**
     * 获取cookie里存储的值
     * @param cookieName 
     * @param key
     * @param isEncrypted 加密
     * @return
     */
    public String getValue(String cookieName, String key, boolean isEncrypted) {
        return getValue(cookieName, key, "", isEncrypted);
    }

    /**
     * 获取cookie里存储的值，没有返回默认值
     * @param cookieName
     * @param key
     * @param defaultValue
     * @param isEncrypted
     * @return
     */
    public String getValue(String cookieName, String key, String defaultValue, boolean isEncrypted) {
        Map<String, String> valueMap = parseCookie(cookieName, isEncrypted);
        String value = valueMap.get(key);

        return StringUtils.isEmpty(value)?defaultValue:value;
    }

    /**
     * 设置普通的值，不考虑加解密，一些可展示的值
     * @param cookieName
     * @param key
     * @param value
     */
    public void setLogValue(String cookieName, String key, String value) {
        Map<String, String> valueMap = parseCookie(cookieName, false);

        if (!StringUtils.isEmpty(value)) {
            valueMap.put(key, value);
        } else {
            valueMap.remove(key);
        }
    }

    /**
     * 设置cookie的值，可选择是否加密
     * @param cookieName
     * @param key
     * @param value
     * @param isEncrypted
     */
    public void setValue(String cookieName, String key, String value, boolean isEncrypted) {
        Map<String, String> valueMap = parseCookie(cookieName, isEncrypted);

        if (!StringUtils.isEmpty(value)) {
            valueMap.put(key, value);
        } else {
            valueMap.remove(key);
        }
    }

    /**
     * 将设置好的cookie 存到response对象中
     * @param response
     * @param cookieName
     * @param isEncrypted
     */
    public void save(HttpServletResponse response, String cookieName, boolean isEncrypted) {
        save(response, cookieName, null, isEncrypted);
    }

    public void save(HttpServletResponse response, String cookieName, String domain, boolean isEncrypted) {

        save(response, cookieName, domain, "/", isEncrypted);
    }

    public void save(HttpServletResponse response, String cookieName, String domain, String path, boolean isEncrypted) {

        save(response, cookieName, domain, path, -1, isEncrypted);
    }

    public void cleanAll(HttpServletResponse response, String cookieName, boolean isEncrypted) {

        save(response, cookieName, null, "/", 0, isEncrypted);
    }

    public void save(HttpServletResponse response, String cookieName, String domain, String path, int age, boolean isEncrypted) {
        Map<String, String> valueMap = parseCookie(cookieName, isEncrypted);
        StringBuffer sb = new StringBuffer();

        for (Entry<String, String> e : valueMap.entrySet()) {
            String key = (String) e.getKey();
            if (!StringUtils.isEmpty(key)) {
                String value = (String) e.getValue();
                if (!StringUtils.isEmpty(value)) {
                    if (sb.length() > 0) sb.append('&');

                    sb.append(key);
                    sb.append('=');

                    try {
                        sb.append(URLEncoder.encode(value, "UTF-8"));
                    } catch (UnsupportedEncodingException ex) {

                        throw new RuntimeException(ex.getMessage(), ex);
                    }
                }
            }
        }

        addCookie(response, cookieName, sb.toString(), domain, path, age, isEncrypted);
    }

    private String getCookieValue(String cookieName, boolean isEncrypted) {
        String cookieValue = getCookieValue(cookieName, null);
        if (cookieValue == null) return null;

        String decodedValue = null;
        try {
            decodedValue = URLDecoder.decode(cookieValue, "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {

            throw new RuntimeException(e.getMessage(), e);
        }

        if(isEncrypted) {
            try {
                byte[] decodeValueBytes = decodedValue.getBytes("ISO-8859-1");
                byte[] decryptedResult = new Encrypter().decrypt(decodeValueBytes);
                return new String(decryptedResult, "UTF-8");
            } catch (UnsupportedEncodingException e) {

                throw new RuntimeException(e.getMessage(), e);
            }
        }

        return decodedValue;
    }

    @SuppressWarnings("unchecked")
    private Map<String, String> parseCookie(String cookieName, boolean isEncrypted) {
        Map<String, String> valueMap = (Map<String, String>) getRequest().getAttribute(cookieName);
        if (valueMap != null) return valueMap;

        valueMap = new HashMap<String, String>();
        getRequest().setAttribute(cookieName, valueMap);

        String cookieValue = null;
        if(isEncrypted) {
            cookieValue = getCookieValue(cookieName, true);
        } else {
            cookieValue = getCookieValue(cookieName, false);
        }

        if (StringUtils.isEmpty(cookieValue)) return valueMap;

        String[] kvPairs = cookieValue.split("&");
        for (int i = 0; i < kvPairs.length; i++) {
            if (!StringUtils.isEmpty(kvPairs[i])) {
                int offset = kvPairs[i].indexOf('=');
                if (offset > 0) {
                    String key = kvPairs[i].substring(0, offset);
                    String value = kvPairs[i].substring(offset + 1);
                    if (!StringUtils.isEmpty(key) && !StringUtils.isEmpty(value)) {
                        try {
                            valueMap.put(key, URLDecoder.decode(value, "UTF-8"));    
                        } catch (UnsupportedEncodingException ex) {

                            throw new RuntimeException(ex.getMessage(), ex);
                        }
                    }
                }
            }
        }

        return valueMap;
    }

    private String getCookieValue(String cookieName, String defaultValue) {
        Cookie[] cookies = getRequest().getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookieName.equals(cookie.getName())) return cookie.getValue();
            }
        }

        return defaultValue;
    }

    private void addCookie(HttpServletResponse response, String cookieName, String cookieValue, String domain, String path, int age, boolean isEncrypted) {
        Cookie cookie = null;
        if(isEncrypted) {
            String encryptedValue;
            try {
                encryptedValue = new String(new Encrypter().encrypt(cookieValue .getBytes("UTF-8")), "ISO-8859-1");

                encryptedValue = URLEncoder.encode(encryptedValue, "ISO-8859-1");
            } catch (UnsupportedEncodingException e) {

                throw new RuntimeException(e.getMessage(), e);
            }

            cookie = new Cookie(cookieName, encryptedValue);
        } else {
            try {
                cookieValue = URLEncoder.encode(cookieValue, "ISO-8859-1");
            } catch (UnsupportedEncodingException e) {

                throw new RuntimeException(e.getMessage(), e);
            }

            cookie = new Cookie(cookieName, cookieValue);    
        }

        cookie.setPath(path);
        cookie.setMaxAge(age);
        if (domain != null) cookie.setDomain(domain);

        response.addCookie(cookie);
    }

    private HttpServletRequest getRequest() {
        return request;
    }

    public void setCookieTempName(String cookieTempName) {
        this.cookieTempName = cookieTempName;
    }

    public String getCookieTempName() {
        return this.cookieTempName;
    }
}