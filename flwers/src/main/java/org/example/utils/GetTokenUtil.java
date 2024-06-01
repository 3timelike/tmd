package org.example.utils;

/**
 * @program: BH_ComputerDesign
 * @ClassName: GetTokenUtil
 * @author: YangSF
 * @create: 2024-04-15 18:32
 */

public class GetTokenUtil {
    public static String getToken(String token){
        if (token != null && token.startsWith("Bearer ")) {
            // 提取token，去除"Bearer "前缀
            return token.substring("Bearer ".length());
        }
        return null; // 没有找到或格式不正确
    }
}
