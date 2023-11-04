package com.dwh.gyndowindback.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateUserVo {

    @NotNull(message = "用户名不能为空~")
    private String name;
    @NotNull(message = "账号不能为空~")
    private String account;
    @NotNull(message = "密码不能为空~")
    private String password;
}
