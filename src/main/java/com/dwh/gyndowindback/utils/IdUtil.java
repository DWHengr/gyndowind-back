package com.dwh.gyndowindback.utils;

import java.util.Locale;
import java.util.UUID;

/**
 * @author: dwh
 **/
public class IdUtil {

    public static String generate8NumberByUuid() {
        String id = UUID.randomUUID().toString().substring(0, 8).toUpperCase(Locale.ROOT);
        return id;
    }

    public static String generateUuid() {
        return UUID.randomUUID().toString();
    }
}
