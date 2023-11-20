package com.dwh.gyndowindback.controller;


import com.alibaba.fastjson.JSONObject;
import com.dwh.gyndowindback.annotation.Userid;
import com.dwh.gyndowindback.nps.entity.EditClient;
import com.dwh.gyndowindback.service.TunnelService;
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
}
