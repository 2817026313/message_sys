package cn.stylefeng.guns.modular.mws.service;


import cn.stylefeng.guns.modular.mws.entity.AmazonConfig;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public interface OrderService {

    /**
     * Returns orders created or updated during a time frame that you specify.
     * @param config
     * @param params :
     *               CreatedAfter : Required, A date used for selecting orders created after (or at) a specified time.
     *               CreatedBefore :  	A date used for selecting orders created before (or at) a specified time.
     *               LastUpdatedAfter：Required,A date used for selecting orders that were last updated after (or at) a specified time. An update is defined as any change in order status, including the creation of a new order. Includes updates made by Amazon and by the seller.
     *               LastUpdatedBefore:  	A date used for selecting orders that were last updated before (or at) a specified time. An update is defined as any change in order status, including the creation of a new order. Includes updates made by Amazon and by the seller.
     *               OrderStatus:  A list of OrderStatus values. Used to select orders with a current status that matches one of the status values that you specify.
     *               MarketplaceId: Required, A list of MarketplaceId values. Used to select orders that were placed in the Marketplaces that you specify.
     *               FulfillmentChannel: A list that indicates how an order was fulfilled.
     *               PaymentMethod:  	A list of PaymentMethod values. Used to select orders paid for with the payment methods that you specify.
     *               BuyerEmail:   The e-mail address of a buyer. Used to select only the orders that contain the specified e-mail address.
     *               SellerOrderId: An order identifier that is specified by the seller. Not an Amazon order identifier. Used to select only the orders that match a seller-specified order identifier.
     *               MaxResultsPerPage: 	A number that indicates the maximum number of orders that can be returned per page.
     *               EasyShipShipmentStatus: A list of EasyShipShipmentStatus values. Used to select Easy Ship orders with current statuses that match the status values that you specify
     * @return
     */
    JSONObject listOrders (AmazonConfig config, Map<String, Object> params) throws Exception;

    /**
     * Returns the next page of orders using the NextToken parameter.
     * @param config
     * @param nextToken A string token returned in the response of your previous request to either ListOrders or ListOrdersByNextToken.
     * @return
     * @throws Exception
     */
    JSONObject listOrdersByNextToken (AmazonConfig config, String nextToken) throws Exception;

    /**
     * Returns orders based on the AmazonOrderId values that you specify.
     * @param config
     * @param amazonOrderId  A list of AmazonOrderId values. An AmazonOrderId is an Amazon-defined order identifier, in 3-7-7 format.
     * @return
     * @throws Exception
     */
    JSONObject getOrder(AmazonConfig config, String amazonOrderId) throws Exception;

    /**
     * Returns order items based on the AmazonOrderId that you specify.
     * @param config
     * @param amazonOrderId  An Amazon-defined order identifier, in 3-7-7 format.
     * @return
     * @throws Exception
     */
    JSONObject listOrderItems(AmazonConfig config, String amazonOrderId) throws Exception;

    /**
     * Returns the next page of order items using the NextToken parameter.
     * @param config
     * @param nextToken A string token returned in the response of your previous request to either
     * @return
     * @throws Exception
     */
    JSONObject listOrderItemsByNextToken(AmazonConfig config, String nextToken) throws Exception;

    /**
     * Returns the operational status of the Orders API section.
     * @param config
     * @return
     * @throws Exception
     */
    JSONObject getServiceStatus (AmazonConfig config) throws Exception;
}
