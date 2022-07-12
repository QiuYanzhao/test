package com.example.demo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "easemob.im.kms")
public class KmsProperties {

    private Property local;
    private List<Property> disasterRecovery;

    @Data
    public static class Property {
        private String awsAccessKeyId;
        private String awsSecretAccessKey;
        private String awsRegion;
        private String defaultCmkAlias;
        private String msyncCmkAlias;
        private String metadataCmkAlias;
        private String adminCmkAlias;
        private String rest2CmkAlias;
        private String moderationCmkAlias;
        BigDecimal bigDecimal;
    }
}
