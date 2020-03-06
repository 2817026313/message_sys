package cn.stylefeng.guns.modular.mws.entity;

import cn.stylefeng.guns.modular.mws.vo.AmazonField;
import lombok.Data;

import java.util.List;

/**
 * OrderItem information.
 */
@Data
public class OrderItem {
    /**
     * 	The Amazon Standard Identification Number (ASIN) of the item.
     */
    @AmazonField(name = "ASIN")
    private String asin;
    /**
     * An Amazon-defined order item identifier.
     */
    @AmazonField(name = "OrderItemId")
    private String orderItemId;
    /**
     * The seller SKU of the item.
     */
    @AmazonField(name = "BuyerCustomizedInfo", type = "object")
    private BuyerCustomizedInfo buyerCustomizedInfo;
    @AmazonField(name = "SellerSKU")
    private String sellerSKU;
    @AmazonField(name = "Title")
    private String title;
    @AmazonField(name = "QuantityOrdered")
    private int quantityOrdered;
    @AmazonField(name = "QuantityShipped")
    private int quantityShipped;
    @AmazonField(name = "ItemPrice", type = "object")
    private Money itemPrice;
    @AmazonField(name = "ProductInfo", type = "object")
    private ProductInfo productInfo;
    @AmazonField(name = "ShippingPrice", type = "object")
    private Money shippingPrice;
    @AmazonField(name = "GiftwrapPrice", type = "object")
    private Money giftwrapPrice;
    @AmazonField(name = "ItemTax", type = "object")
    private Money itemTax;
    @AmazonField(name = "ShippingTax", type = "object")
    private Money shippingTax;
    @AmazonField(name = "GiftWrapTax", type = "object")
    private Money giftWrapTax;
    @AmazonField(name = "ShippingDiscount", type = "object")
    private Money shippingDiscount;
    @AmazonField(name = "ShippingDiscountTax", type = "object")
    private Money shippingDiscountTax;
    @AmazonField(name = "PromotionDiscount", type = "object")
    private Money promotionDiscount;
    @AmazonField(name = "PromotionDiscountTax", type = "object")
    private Money promotionDiscountTax;
    @AmazonField(name = "PromotionIds")
    private List<String> promotionIds;
    @AmazonField(name = "CodFee", type = "object")
    private Money codFee;
    @AmazonField(name = "CodFeeDiscount", type = "object")
    private Money codFeeDiscount;
    @AmazonField(name = "IsGift")
    private boolean isGift;
    @AmazonField(name = "GiftMessageText")
    private String giftMessageText;
    @AmazonField(name = "GiftWrapLevel")
    private String giftWrapLevel;
    @AmazonField(name = "TaxCollection", type = "object")
    private TaxCollection taxCollection;
    @AmazonField(name = "ConditionNote")
    private String conditionNote;
    @AmazonField(name = "ConditionId")
    private String conditionId;
    @AmazonField(name = "ConditionSubtypeId")
    private String conditionSubtypeId;
    @AmazonField(name = "ScheduledDeliveryStartDate")
    private String scheduledDeliveryStartDate;
    @AmazonField(name = "ScheduledDeliveryEndDate")
    private String scheduledDeliveryEndDate;
    @AmazonField(name = "PriceDesignation")
    private String priceDesignation;
    @AmazonField(name = "IsTransparency")
    private boolean isTransparency;
    @AmazonField(name = "SerialNumberRequired")
    private boolean serialNumberRequired;

}
