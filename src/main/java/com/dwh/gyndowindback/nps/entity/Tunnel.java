package com.dwh.gyndowindback.nps.entity;

import lombok.Data;

import java.util.List;

@Data
public class Tunnel {
    private int Id;
    private int Port;
    private String ServerIp;
    private String Mode;
    private boolean Status;
    private boolean RunStatus;
    private Client Client;
    private String Ports;
    private Flow Flow;
    private String Password;
    private String Remark;
    private String TargetAddr;
    private boolean NoStore;
    private String LocalPath;
    private String StripPre;
    private Target Target;
    private int HealthCheckTimeout;
    private int HealthMaxFail;
    private int HealthCheckInterval;
    private String HealthNextTime;
    private String HttpHealthUrl;
    private String HealthRemoveArr;
    private String HealthCheckType;
    private String HealthCheckTarget;

    @Data
    public static class Client {
        private Cnf Cnf;
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

    @Data
    public static class Target {
        private String TargetStr;
        private List<String> TargetArr;
        private boolean LocalProxy;
    }
}