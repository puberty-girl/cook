package com.example.cook2;

public class UserInfo {
    public static String getUserKey() {
        return userKey;
    }

    public static void setUserKey(String userKey) {
        UserInfo.userKey = userKey;
    }

    static String userKey;
}
