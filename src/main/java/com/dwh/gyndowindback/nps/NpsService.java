package com.dwh.gyndowindback.nps;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dwh.gyndowindback.nps.entity.Client;
import com.dwh.gyndowindback.nps.entity.ClientCreate;
import com.dwh.gyndowindback.nps.entity.EditClient;
import com.dwh.gyndowindback.nps.entity.Tunnel;
import com.dwh.gyndowindback.utils.MD5Util;
import com.dwh.gyndowindback.utils.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class NpsService {

    @Resource
    NpsConfig npsConfig;

    /**
     * 获取nps服务ip
     *
     * @return
     */
    public String getServiceIp() {
        String regex = "https?://(\\d+\\.\\d+\\.\\d+\\.\\d+):\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(npsConfig.getHost());
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }


    /**
     * 获取nps auth key
     *
     * @return
     */
    public Map<String, Object> getAuthKey() {
        long time = System.currentTimeMillis() / 1000;
        String key = MD5Util.encrypt(npsConfig.getAuthKey() + time);
        Map<String, Object> param = new HashMap<>();
        param.put("auth_key", key);
        param.put("timestamp", time + "");
        return param;
    }

    /**
     * 客户端查询
     */
    public List<Client> getClientBySearch(String search) {
        String url = npsConfig.getHost() + npsConfig.getClientGetUrl();
        Map<String, Object> param = getAuthKey();
        param.put("order", "asc");
        param.put("offset", "0");
        param.put("limit", "999");
        param.put("search", search);
        try {
            String response = OkHttpUtils.postResponseForm(url, param);
            JSONObject jsonObject = JSONObject.parseObject(response);
            if (null != jsonObject) {
                JSONArray rows = jsonObject.getJSONArray("rows");
                if (null != rows && rows.size() > 0) {
                    List<Client> clients = rows.toJavaList(Client.class);
                    return clients;
                }
            }
        } catch (Exception e) {
            log.error("客户端获取失败:{} {}", e.getMessage(), e.getStackTrace());
        }
        return null;
    }


    /**
     * 客户端创建
     *
     * @param clientCreate
     */
    public JSONObject createClient(ClientCreate clientCreate) {
        String url = npsConfig.getHost() + npsConfig.getClientAddUrl();
        Map<String, Object> param = getAuthKey();
        param.putAll(clientCreate.toMap());
        try {
            String response = OkHttpUtils.postResponseForm(url, param);
            JSONObject jsonObject = JSONObject.parseObject(response);
            return jsonObject;
        } catch (Exception e) {
            log.error("客户端创建失败:{} {}", e.getMessage(), e.getStackTrace());
        }
        return null;
    }

    /**
     * 客户端创建,并返回Id
     *
     * @param clientCreate
     */
    public String createClientToId(ClientCreate clientCreate) {
        JSONObject clientCreateJson = createClient(clientCreate);
        Integer status = clientCreateJson.getInteger("status");
        if (status == 1) {
            List<Client> clients = getClientBySearch(clientCreate.getRemark());
            if (null != clients && clients.size() > 0) {
                return clients.get(0).getId() + "";
            }
        }
        return null;
    }

    /**
     * 获取隧道,根据客户ID和协议
     *
     * @param clientId
     */
    public List<Tunnel> getIndexByClientIdAndType(String clientId, String type) {
        String url = npsConfig.getHost() + npsConfig.getTunnelGetUrl();
        Map<String, Object> param = getAuthKey();
        param.put("type", type);
        param.put("offset", "0");
        param.put("limit", "999");
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

    /**
     * 修改隧道
     */
    public boolean editTunnel(EditClient editClient) {
        String url = npsConfig.getHost() + npsConfig.getTunnelEditUrl();
        Map<String, Object> param = getAuthKey();
        JSONObject json = (JSONObject) JSONObject.toJSON(editClient);
        param.putAll(json.getInnerMap());
        try {
            String response = OkHttpUtils.postResponseForm(url, param);
            JSONObject jsonObject = JSONObject.parseObject(response);
            if (null != jsonObject) {
                if (jsonObject.getInteger("status") == 1) {
                    return true;
                }
            }
        } catch (Exception e) {
            log.error("隧道修改失败:{} {}", e.getMessage(), e.getStackTrace());
        }
        return false;
    }
}
