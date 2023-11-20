package com.dwh.gyndowindback.nps.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EditClient {
    @NotNull(message = "id不能为空~")
    private String id;
    @NotNull(message = "type不能为空~")
    private String type;
    @NotNull(message = "client_id不能为空~")
    private String client_id;
    @NotNull(message = "remark不能为空~")
    private String remark;
    @NotNull(message = "port不能为空~")
    private String port;
    @NotNull(message = "target不能为空~")
    private String target;
}
