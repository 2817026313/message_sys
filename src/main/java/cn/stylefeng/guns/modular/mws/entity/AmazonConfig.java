package cn.stylefeng.guns.modular.mws.entity;

import lombok.Data;
import lombok.NonNull;

@Data
public class AmazonConfig {

    @NonNull
    private String AWSAccessKeyId;
    @NonNull
    private String MWSAuthToken;
    @NonNull
    private String sellerId;
    @NonNull
    private String secretKey;

    private String endpointUrl;

    private String suffixPostUrl;

    private String version;

    private String active;

    public AmazonConfig(){}

    public AmazonConfig(String AWSAccessKeyId, String MWSAuthToken, String sellerId, String secretKey) {
        this.AWSAccessKeyId = AWSAccessKeyId;
        this.MWSAuthToken = MWSAuthToken;
        this.sellerId = sellerId;
        this.secretKey = secretKey;
    }


}
