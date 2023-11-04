package com.dwh.gyndowindback.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dwh.gyndowindback.constant.UserStatus;
import com.dwh.gyndowindback.entity.User;
import com.dwh.gyndowindback.exception.GyndowindException;
import com.dwh.gyndowindback.mapper.UserMapper;
import com.dwh.gyndowindback.nps.NpsService;
import com.dwh.gyndowindback.nps.entity.ClientCreate;
import com.dwh.gyndowindback.service.UserService;
import com.dwh.gyndowindback.utils.IdUtil;
import com.dwh.gyndowindback.utils.ResultUtil;
import com.dwh.gyndowindback.vo.CreateUserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 用户表 服务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    NpsService npsService;

    public User getUserByAccount(String account) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount, account);
        return getOne(queryWrapper);
    }

    @Override
    public JSONObject createUser(CreateUserVo createUserVo) {
        User userByAccount = getUserByAccount(createUserVo.getAccount());
        if (null != userByAccount) {
            throw new GyndowindException("账号已存在~").param("account", createUserVo.getAccount());
        }
        String id = IdUtil.generateUuid();
        //nps创建客户端
        ClientCreate clientCreate = new ClientCreate();
        clientCreate.setRemark(id);
        clientCreate.setCompress("1");
        clientCreate.setCrypt("1");
        clientCreate.setConfig_conn_allow("1");
        String npsClientId = npsService.createClientToId(clientCreate);
        //用户创建
        User user = new User();
        user.setId(IdUtil.generateUuid());
        user.setAccount(createUserVo.getAccount());
        user.setName(createUserVo.getName());
        user.setPassword(createUserVo.getPassword());
        user.setNpsClientId(npsClientId);
        user.setStatus(UserStatus.normal.toString());
        boolean saveResult = save(user);
        JSONObject resultData = new JSONObject();
        resultData.put("id", user.getId());
        resultData.put("account", user.getAccount());
        if (saveResult) {
            return ResultUtil.Succeed(resultData);
        } else {
            throw new GyndowindException("账号创建失败~").param("account", createUserVo.getAccount());
        }
    }
}
