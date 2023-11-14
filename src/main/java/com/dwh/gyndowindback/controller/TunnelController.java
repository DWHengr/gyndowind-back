package com.dwh.gyndowindback.controller;


import com.alibaba.fastjson.JSONObject;
import com.dwh.gyndowindback.annotation.Userid;
import com.dwh.gyndowindback.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/tunnel")
@Slf4j
public class TunnelController {

    @Resource
    UserService userService;

    @GetMapping("/list")
    public Object getTunnelList(@Userid String userid) {
        JSONObject result = userService.getTunnelList(userid);
        return result;
    }
}
