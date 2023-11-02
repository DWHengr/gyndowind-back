package com.dwh.gyndowindback.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dwh.gyndowindback.entity.User;
import com.dwh.gyndowindback.mapper.UserMapper;
import com.dwh.gyndowindback.service.UserService;
import org.springframework.stereotype.Service;


/**
 * 用户表 服务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
