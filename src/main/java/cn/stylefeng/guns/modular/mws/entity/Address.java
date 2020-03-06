package cn.stylefeng.guns.modular.mws.entity;

import cn.stylefeng.guns.modular.mws.vo.AmazonField;
import lombok.Data;

/**
 * The shipping address for the order.
 */
@Data
public class Address {

    /**
     * The name.
     */
    @AmazonField(name = "Name", type = "string")
    private String name;

    /**
     * The street address.
     */
    @AmazonField(name = "AddressLine1", type = "string")
    private String addressLine1;
    /**
     * Additional street address information, if required.
     */
    @AmazonField(name = "AddressLine2", type = "string")
    private String addressLine2;
    /**
     * 	Additional street address information, if required.
     */
    @AmazonField(name = "AddressLine3", type = "string")
    private String addressLine3;
    /**
     * The city.
     */
    @AmazonField(name = "City", type = "string")
    private String city;
    /**
     * 	The municipality.
     */
    @AmazonField(name = "Municipality", type = "string")
    private String municipality;
    /**
     * The county.
     */
    @AmazonField(name = "County", type = "string")
    private String county;
    /**
     * The district.
     */
    @AmazonField(name = "District", type = "string")
    private String district;
    /**
     *	The state or region.
     */
    @AmazonField(name = "StateOrRegion", type = "string")
    private String stateOrRegion;
    /**
     * The postal code.
     */
    @AmazonField(name = "AddressLine2", type = "string")
    private String postalCode;
    /**
     * The country code.
     */
    @AmazonField(name = "CountryCode", type = "string")
    private String countryCode;
    /**
     * The phone number.
     */
    @AmazonField(name = "Phone", type = "string")
    private String phone;
    /**
     * Indicates whether the address is commercial or residential.
     * This element is used only in the US marketplace.
     */
    @AmazonField(name = "AddressType", type = "string")
    private String addressType;
}
