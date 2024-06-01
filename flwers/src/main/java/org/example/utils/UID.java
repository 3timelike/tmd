package org.example.utils;

import java.util.UUID;

public class UID {
    public String produceUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
