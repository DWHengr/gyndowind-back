package com.dwh.gyndowindback.nps.entity;

import lombok.Data;

import java.util.List;

@Data
public class ClientList {
    private String bridgePort;
    private String bridgeType;
    private String ip;
    private List<Client> rows;
}
