package com.dwh.gyndowindback.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dwh.gyndowindback.entity.User;
import com.dwh.gyndowindback.vo.CreateUserVo;
import com.dwh.gyndowindback.vo.LoginVo;


/**
 * 用户表 服务类
 */
public interface UserService extends IService<User> {

    JSONObject createUser(CreateUserVo createUserVo);

    JSONObject validateLogin(LoginVo loginVo);

    JSONObject clientStatus(String userId);
}
