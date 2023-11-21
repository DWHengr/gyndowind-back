package com.dwh.gyndowindback.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dwh.gyndowindback.entity.User;
import com.dwh.gyndowindback.nps.NpsService;
import com.dwh.gyndowindback.nps.entity.EditClient;
import com.dwh.gyndowindback.nps.entity.Tunnel;
import com.dwh.gyndowindback.service.TunnelService;
import com.dwh.gyndowindback.service.UserService;
import com.dwh.gyndowindback.utils.ResultUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TunnelServiceImpl implements TunnelService {

    @Resource
    UserService userService;

    @Resource
    NpsService npsService;

    @Override
    public JSONObject getTunnelList(String userid) {
        try {
            User user = userService.getById(userid);
            List<Tunnel> tcp = npsService.getIndexByClientIdAndType(user.getNpsClientId(), "tcp");
            return ResultUtil.Succeed(tcp);
        } catch (Exception e) {
            return ResultUtil.Fail("隧道获取失败~");
        }
    }

    @Override
    public JSONObject editTunnel(String userid, EditClient editClient) {
        try {
            User user = userService.getById(userid);
            if (!user.getNpsClientId().equals(editClient.getClient_id())) {
                return ResultUtil.Fail("只能修改自己所拥有的隧道~");
            }
            boolean flag = npsService.editTunnel(editClient);
            return ResultUtil.ResultByFlag(flag);
        } catch (Exception e) {
            return ResultUtil.Fail("隧道修改失败~");
        }
    }

    @Override
    public JSONObject stopTunnel(String tunnelId) {
        try {
            if (StringUtils.isBlank(tunnelId)) {
                return ResultUtil.Fail("隧道不能为空~");
            }
            boolean flag = npsService.stopTunnel(tunnelId);
            return ResultUtil.ResultByFlag(flag);
        } catch (Exception e) {
            return ResultUtil.Fail("隧道修改失败~");
        }
    }
}
