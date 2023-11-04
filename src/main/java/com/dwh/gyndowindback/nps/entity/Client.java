package com.dwh.gyndowindback.nps.entity;

import lombok.Data;

@Data
public class Client {
    private Cnf Cnf;
    private int Id;
    private String VerifyKey;
    private String Addr;
    private String Remark;
    private boolean Status;
    private boolean IsConnect;
    private int RateLimit;
    private Flow Flow;
    private Rate Rate;
    private boolean NoStore;
    private boolean NoDisplay;
    private int MaxConn;
    private int NowConn;
    private String WebUserName;
    private String WebPassword;
    private boolean ConfigConnAllow;
    private int MaxTunnelNum;
    private String Version;

    @Data
    public static class Cnf {
        private String U;
        private String P;
        private boolean Compress;
        private boolean Crypt;
    }

    @Data
    public static class Flow {
        private int ExportFlow;
        private int InletFlow;
        private int FlowLimit;
    }

    @Data
    public static class Rate {
        private int NowRate;
    }
}
