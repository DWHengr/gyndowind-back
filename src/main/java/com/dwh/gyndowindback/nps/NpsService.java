package com.dwh.gyndowindback.nps;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dwh.gyndowindback.nps.entity.Tunnel;
import com.dwh.gyndowindback.utils.MD5Util;
import com.dwh.gyndowindback.utils.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class NpsService {

    @Resource
    NpsConfig npsConfig;

    /**
     * 获取nps auth key
     *
     * @return
     */
    public Map<String, String> getAuthKey() {
        long time = System.currentTimeMillis() / 1000;
        String key = MD5Util.encrypt(npsConfig.getAuthKey() + time);
        Map<String, String> param = new HashMap<>();
        param.put("auth_key", key);
        param.put("timestamp", time + "");
        return param;
    }

    /**
     * 获取隧道,根据客户ID和协议
     *
     * @param clientId
     */
    public List<Tunnel> getIndex(String clientId, String type) {
        String url = npsConfig.getHost() + npsConfig.getTunnelGetUrl();
        Map<String, String> param = getAuthKey();
        param.put("type", type);
        param.put("offset", "0");
        param.put("limit", "10");
        if (null != clientId)
            param.put("client_id", clientId);
        try {
            String response = OkHttpUtils.postResponseForm(url, param);
            JSONObject jsonObject = JSONObject.parseObject(response);
            if (null != jsonObject) {
                JSONArray rows = jsonObject.getJSONArray("rows");
                if (null != rows && rows.size() > 0) {
                    List<Tunnel> tunnels = rows.toJavaList(Tunnel.class);
                    return tunnels;
                }
            }
        } catch (Exception e) {
            log.error("隧道获取失败:{} {}", e.getMessage(), e.getStackTrace());
        }
        return null;
    }
}
