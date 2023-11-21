package com.dwh.gyndowindback.service;

import com.alibaba.fastjson.JSONObject;
import com.dwh.gyndowindback.nps.entity.EditClient;

public interface TunnelService {

    JSONObject getTunnelList(String userid);

    JSONObject editTunnel(String userid, EditClient editClient);

    JSONObject stopTunnel(String tunnelId);

    JSONObject startTunnel(String tunnelId);
}
