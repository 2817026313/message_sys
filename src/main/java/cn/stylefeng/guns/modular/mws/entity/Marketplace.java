package cn.stylefeng.guns.modular.mws.entity;

import cn.stylefeng.guns.modular.mws.vo.AmazonField;
import lombok.Data;

/**
 * Detailed information about an Amazon market where a seller can list items for sale and customers can view and purchase items.
 */
@Data
public class Marketplace {
    /**
     *  The encrypted marketplace value.
     */
    @AmazonField(name = "MarketplaceId")
    private String marketplaceId;

    /**
     * Marketplace name
     */
    @AmazonField(name = "Name")
    private String name;

    /**
     * 	The two-digit country code of the marketplace, in ISO 3166-1 alpha-2 format.
     */
    @AmazonField(name = "DefaultCountryCode")
    private String defaultCountryCode;

    /**
     * The ISO 4217 format currency code of the marketplace. Example: USD
     */
    @AmazonField(name = "DefaultCurrencyCode")
    private String defaultCurrencyCode;

    /**
     * The ISO 639-1 format language code of the marketplace. Example: en_US
     */
    @AmazonField(name = "DefaultLanguageCode")
    private String defaultLanguageCode;

    /**
     * 	The domain name associated with the marketplace. Example: www.amazon.com
     */
    @AmazonField(name = "DomainName")
    private String domainName;


}
