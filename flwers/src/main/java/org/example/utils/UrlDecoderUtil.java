package org.example.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

//这个 UrlDecoderUtil 类提供了两个静态方法来处理 URL 编码的字符串：

//        decodeUrlString(String encodedString) 方法用于解码 URL 编码的字符串为原始字符串。它使用 URLDecoder.decode(encodedString, "UTF-8") 方法来进行解码，并且声明了可能抛出 UnsupportedEncodingException 异常。
//
//        decodeUrlStringSafely(String encodedString) 方法在解码 URL 编码的字符串时，处理了可能抛出的异常。它调用了 decodeUrlString 方法来进行解码，如果解码过程中出现 UnsupportedEncodingException 异常，会捕获该异常并返回原始编码字符串。
//
//        总的来说，这个工具类的功能就是提供了安全的方式来解码 URL 编码的字符串，同时处理了可能的异常情况。这样的设计可以确保在大多数情况下都能正确地解码 URL 字符串，而在极少数情况下出现异常时，也能够 graceful 地处理并返回原始编码字符串。
public class UrlDecoderUtil {  
  
    /**  
     * 解码URL编码的字符串为原始字符串。  
     *  
     * @param encodedString URL编码的字符串  
     * @return 解码后的原始字符串  
     * @throws UnsupportedEncodingException 如果不支持指定的字符编码  
     */  
    public static String decodeUrlString(String encodedString) throws UnsupportedEncodingException {  
        return URLDecoder.decode(encodedString, "UTF-8");  
    }  
  
    /**  
     * 解码URL编码的字符串为原始字符串，并处理可能抛出的异常。  
     *  
     * @param encodedString URL编码的字符串  
     * @return 解码后的原始字符串，如果解码失败则返回原始编码字符串  
     */  
    public static String decodeUrlStringSafely(String encodedString) {  
        try {  
            return decodeUrlString(encodedString);  
        } catch (UnsupportedEncodingException e) {  
            // 如果不支持UTF-8编码（这在现代Java环境中几乎不可能发生），则返回原始编码字符串  
            return encodedString;  
        }  
    }  
}