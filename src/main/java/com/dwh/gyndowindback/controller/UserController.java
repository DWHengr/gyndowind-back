package com.dwh.gyndowindback.controller;

import com.dwh.gyndowindback.annotation.UrlFree;
import com.dwh.gyndowindback.service.UserService;
import com.dwh.gyndowindback.vo.CreateUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    @Resource
    UserService userService;

    /**
     * 新建用户
     */
    @UrlFree
    @PostMapping("/create")
    public Object createRoom(@Valid @RequestBody CreateUserVo createRoomVo) {
        log.info("新建用户：{}", createRoomVo);
        return userService.createUser(createRoomVo);
    }
}
