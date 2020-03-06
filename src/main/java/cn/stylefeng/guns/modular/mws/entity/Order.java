package cn.stylefeng.guns.modular.mws.entity;

import cn.stylefeng.guns.modular.mws.vo.AmazonField;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.List;

@Data
public class Order {

    @AmazonField(name = "AmazonOrderId")
    private String amazonOrderId;
    @AmazonField(name = "SellerOrderId")
    private String sellerOrderId;
    @AmazonField(name = "PurchaseDate")
    private String purchaseDate;
    @AmazonField(name = "LastUpdateDate")
    private DateTime lastUpdateDate;
    @AmazonField(name = "OrderStatus")
    private String orderStatus;
    @AmazonField(name = "FulfillmentChannel")
    private String fulfillmentChannel;
    @AmazonField(name = "SalesChannel")
    private String salesChannel;
    @AmazonField(name = "OrderChannel")
    private String orderChannel;
    @AmazonField(name = "ShipServiceLevel")
    private String shipServiceLevel;
    @AmazonField(name = "CurrencyCode")
    private int numberOfItemsShipped;
    @AmazonField(name = "NumberOfItemsUnshipped")
    private int numberOfItemsUnshipped;
    @AmazonField(name = "PaymentMethod")
    private String paymentMethod;
    @AmazonField(name = "MarketplaceId")
    private String marketplaceId;
    @AmazonField(name = "BuyerEmail")
    private  String buyerEmail;
    @AmazonField(name = "BuyerName")
    private String buyerName;
    @AmazonField(name = "ShipmentServiceLevelCategory")
    private  String shipmentServiceLevelCategory;
    @AmazonField(name = "ShippedByAmazonTFM")
    private boolean shippedByAmazonTFM;
    @AmazonField(name = "TFMShipmentStatus")
    private String tFMShipmentStatus;
    @AmazonField(name = "CbaDisplayableShippingLabel")
    private String cbaDisplayableShippingLabel;
    @AmazonField(name = "OrderType")
    private String orderType;
    @AmazonField(name = "EarliestShipDate")
    private DateTime earliestShipDate;
    @AmazonField(name = "LatestShipDate")
    private DateTime latestShipDate;
    @AmazonField(name = "EarliestDeliveryDate")
    private DateTime earliestDeliveryDate;
    @AmazonField(name = "LatestDeliveryDate")
    private DateTime latestDeliveryDate;
    @AmazonField(name = "IsBusinessOrder")
    private boolean isBusinessOrder;
    @AmazonField(name = "PurchaseOrderNumber")
    private String purchaseOrderNumber;
    @AmazonField(name = "IsPrime")
    private boolean isPrime;
    @AmazonField(name = "IsPremiumOrder")
    private boolean isPremiumOrder;
    @AmazonField(name = "PromiseResponseDueDate")
    private DateTime promiseResponseDueDate;
    @AmazonField(name = "IsEstimatedShipDateSet")
    private boolean isEstimatedShipDateSet;
    @AmazonField(name = "ShippingAddress", type = "object")
    private Address shippingAddress;
    @AmazonField(name = "OrderTotal", type = "object")
    private Money orderTotal;
    @AmazonField(name = "PaymentExecutionDetail", type = "list")
    private List<PaymentExecutionDetailItem> paymentExecutionDetail;
    @AmazonField(name = "BuyerTaxInfo", type = "object")
    private TaxCollection buyerTaxInfo;
    @AmazonField(name = "PaymentMethodDetails", type = "list")
    private List<PaymentMethodDetail> paymentMethodDetails;
}
