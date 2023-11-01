package com.dwh.gyndowindback.nps;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "nps")
public class NpsConfig {
    private String authKey;
    private String host;
    private String clientAddUrl;
    private String clientDelUrl;
    private String tunnelGetUrl;
    private String tunnelAddUrl;
    private String tunnelEditUrl;
    private String tunnelDelUrl;
}
