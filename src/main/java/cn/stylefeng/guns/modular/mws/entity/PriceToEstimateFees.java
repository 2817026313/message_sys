package cn.stylefeng.guns.modular.mws.entity;

import cn.stylefeng.guns.modular.mws.vo.AmazonField;
import lombok.Data;

/**
 * Price information for a product, used to estimate fees.
 */
@Data
public class PriceToEstimateFees {

    @AmazonField(name = "ListingPrice", type = "object")
    private Money listingPrice;

    @AmazonField(name = "ListingPrice", type = "object")
    private Money shipping;

    @AmazonField(name = "ListingPrice", type = "object")
    private Points points;
}
