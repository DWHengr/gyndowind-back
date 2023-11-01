package com.dwh.gyndowindback.nps.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Data
@Slf4j
public class ClientCreate {
    private String Remark;
    private String U;
    private String P;
    private String Limit;
    private String VKey;
    private String ConfigConnAllow;
    private String Compress;
    private String Crypt;
    private String RateLimit;
    private String FlowLimit;
    private String MaxConn;
    private String MaxTunnel;

    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        try {
            for (Field field : getClass().getDeclaredFields()) {
                field.setAccessible(true);
                if (field.get(this) == null)
                    continue;
                map.put(field.getName(), field.get(this).toString());
            }
        } catch (Exception e) {
            log.error("toMap err: {} {}", e.getMessage(), e.getStackTrace());
        }
        return map;
    }
}
