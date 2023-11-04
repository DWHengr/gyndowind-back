package com.dwh.gyndowindback.nps.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@Data
@Slf4j
public class ClientCreate {
    private String remark;
    private String u;
    private String p;
    private String limit;
    private String vKey;
    private String config_conn_allow;
    private String compress;
    private String crypt;
    private String rate_limit;
    private String flow_limit;
    private String max_conn;
    private String max_tunnel;

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
