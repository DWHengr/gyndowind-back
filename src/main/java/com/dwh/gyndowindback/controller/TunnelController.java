package com.dwh.gyndowindback.controller;


import com.alibaba.fastjson.JSONObject;
import com.dwh.gyndowindback.annotation.Userid;
import com.dwh.gyndowindback.nps.entity.EditClient;
import com.dwh.gyndowindback.service.TunnelService;
import com.dwh.gyndowindback.vo.DelTunnelVo;
import com.dwh.gyndowindback.vo.StartTunnelVo;
import com.dwh.gyndowindback.vo.StopTunnelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/tunnel")
@Slf4j
public class TunnelController {

    @Resource
    TunnelService tunnelService;

    @GetMapping("/list")
    public Object getTunnelList(@Userid String userid) {
        JSONObject result = tunnelService.getTunnelList(userid);
        return result;
    }

    @PostMapping("/edit")
    public Object editTunnel(@Userid String userid, @Valid @RequestBody EditClient editClient) {
        JSONObject result = tunnelService.editTunnel(userid, editClient);
        return result;
    }

    @PostMapping("/stop")
    public Object stopTunnel(@RequestBody StopTunnelVo stopTunnelVo) {
        JSONObject result = tunnelService.stopTunnel(stopTunnelVo.tunnelId);
        return result;
    }

    @PostMapping("/start")
    public Object startTunnel(@RequestBody StartTunnelVo startTunnelVo) {
        JSONObject result = tunnelService.startTunnel(startTunnelVo.tunnelId);
        return result;
    }


    @PostMapping("/del")
    public Object delTunnel(@RequestBody DelTunnelVo delTunnelVo) {
        JSONObject result = tunnelService.delTunnel(delTunnelVo.tunnelId);
        return result;
    }
}
