package cn.stylefeng.guns.modular.mws.entity;

import cn.stylefeng.guns.modular.mws.vo.AmazonField;
import lombok.Data;

/**
 * A product, marketplace, and proposed price used to request estimated fees.
 */
@Data
public class FeesEstimateRequest {

    @AmazonField(name = "MarketplaceId")
    private String marketplaceId;

    @AmazonField(name = "IdType")
    private String idType;

    @AmazonField(name = "IdValue")
    private String idValue;

    @AmazonField(name = "Identifier")
    private String identifier;

    @AmazonField(name = "IsAmazonFulfilled", type = "boolean")
    private boolean IsAmazonFulfilled;

    @AmazonField(name = "PriceToEstimateFees", type = "object")
    private PriceToEstimateFees PriceToEstimateFees;
}
