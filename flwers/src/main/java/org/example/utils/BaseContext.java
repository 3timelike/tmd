package org.example.utils;
/**
 * 基于ThreadLocal封装工具类，用于保存和获取当前登录用户的id
 */
public class BaseContext {
    //
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(String id){
        threadLocal.set(id);
    }

    public static String getCurrentId(){
        return threadLocal.get();
    }
}
